// SPDX-License-Identifier: Apache-2.0

package androidx.compose

import kotlin.native.ref.WeakReference

/**
 * A map from a key to a set of values used for keeping the relation between some
 * entities and a models changes of which this entities are observing.
 *
 * Two main differences from a regular Map<K, Set<V>>:
 * 1) Object.hashCode is not used, so the values can be mutable and change their hashCode value
 * 2) Objects are stored with WeakReference to prevent leaking them.
*/
actual class ObserverMap<K : Any, V : Any> {
    private val keyToValue =
        mutableMapOf<IdentityWeakReference<K>, MutableSet<IdentityWeakReference<V>>>()
    private val valueToKey =
        mutableMapOf<IdentityWeakReference<V>, MutableSet<IdentityWeakReference<K>>>()
    //TODO private val keyQueue = ReferenceQueue<K>()
    //TODO private val valueQueue = ReferenceQueue<V>()

    /**
     * Adds a [value] into a set associated with this [key].
     */
    actual fun add(key: K, value: V) {
        clearReferences()
        val weakKey = IdentityWeakReference(key /*TODO, keyQueue*/)
        val weakValue = IdentityWeakReference(value/*TODO, valueQueue*/)
        addToSet(keyToValue, weakKey, weakValue)
        addToSet(valueToKey, weakValue, weakKey)
    }

    /**
     * Removes all the values associated with this [key].
     *
     * @return the list of values removed from the set as a result of this operation.
     */
    actual fun remove(key: K) {
        clearReferences()
        val weakKey = IdentityWeakReference(key)
        removeFromSet(keyToValue, valueToKey, weakKey)
    }

    /**
     * Removes exact [value] from the set associated with this [key].
     */
    actual fun remove(key: K, value: V) {
        clearReferences()
        val weakKey = IdentityWeakReference(key)
        val weakValue = IdentityWeakReference(value)
        keyToValue[weakKey]?.remove(weakValue)
        valueToKey[weakValue]?.remove(weakKey)
    }

    /**
     * Returns `true` when the map contains the given key and value
     */
    actual fun contains(key: K, value: V): Boolean {
        clearReferences()
        val set = keyToValue[IdentityWeakReference(key)]
        return set?.contains(IdentityWeakReference(value)) ?: false
    }

    /**
     * Clears all the keys and values from the map.
     */
    actual fun clear() {
        keyToValue.clear()
        valueToKey.clear()
        clearReferences()
    }

    /**
     * @return a list of values associated with the provided [keys].
     */
    actual operator fun get(keys: Iterable<K>): List<V> {
        clearReferences()
        val set = mutableSetOf<IdentityWeakReference<V>>()
        keys.forEach { key ->
            val weakKey = IdentityWeakReference(key)
            keyToValue[weakKey]?.let(set::addAll)
        }
        @Suppress("UNCHECKED_CAST")
        return set.mapNotNull { it.get() }
    }

    /**
     * Clears all the values that match the given [predicate] from all the sets.
     */
    @Suppress("UNCHECKED_CAST")
    actual fun clearValues(predicate: (V) -> Boolean) {
        clearReferences()
        val matching = mutableListOf<V>()
        valueToKey.keys.forEach { value ->
            val v = value.get()
            if (v != null && predicate(v)) {
                matching.add(v)
            }
        }
        matching.forEach { removeValue(it) }
    }

    /**
     * Removes all values matching [value].
     */
    actual fun removeValue(value: V) {
        clearReferences()
        val weakValue = IdentityWeakReference(value)
        valueToKey.remove(weakValue)?.forEach { key ->
            val valueSet = keyToValue[key]!!
            valueSet.remove(weakValue)
            if (valueSet.isEmpty()) {
                keyToValue.remove(key)
            }
        }
    }

    private fun clearReferences() {
        //TODO pollQueue(keyQueue, keyToValue, valueToKey)
        //TODO pollQueue(valueQueue, valueToKey, keyToValue)
    }

    /*TODO private fun <T, U> pollQueue(
        queue: ReferenceQueue<T>,
        keyMap: MutableMap<IdentityWeakReference<T>, MutableSet<IdentityWeakReference<U>>>,
        valueMap: MutableMap<IdentityWeakReference<U>, MutableSet<IdentityWeakReference<T>>>
    ) {
        do {
            val ref = queue.poll()
            if (ref != null) {
                @Suppress("UNCHECKED_CAST")
                val weakKey = ref as IdentityWeakReference<T>
                removeFromSet(keyMap, valueMap, weakKey)
            }
        } while (ref != null)
    }*/

    private fun <T : Any, U : Any> addToSet(
        map: MutableMap<IdentityWeakReference<T>, MutableSet<IdentityWeakReference<U>>>,
        key: IdentityWeakReference<T>,
        value: IdentityWeakReference<U>
    ) {
        var set = map[key]
        if (set == null) {
            set = mutableSetOf()
            map.put(key, set)
        }
        set.add(value)
    }

    private fun <T : Any, U : Any> removeFromSet(
        mapFromKey: MutableMap<IdentityWeakReference<T>, MutableSet<IdentityWeakReference<U>>>,
        mapToKey: MutableMap<IdentityWeakReference<U>, MutableSet<IdentityWeakReference<T>>>,
        key: IdentityWeakReference<T>
    ) {
        mapFromKey.remove(key)?.forEach { value ->
            mapToKey[value]?.remove(key)
        }
    }
}

private class IdentityWeakReference<T : Any>(value: T /*TODO, queue: ReferenceQueue<T>? = null*/) {
    val ref = WeakReference<T>(value /*TODO, queue*/)
    val hash = identityHashCode(value)

    fun get() = ref.get()

    override fun equals(other: Any?): Boolean {
        if (other !is IdentityWeakReference<*>) {
            return false
        }
        return hash == other.hash && get() === other.get()
    }

    override fun hashCode(): Int = hash
}