// SPDX-License-Identifier: Apache-2.0

package androidx.compose

internal class ViewApplyAdapter() : ApplyAdapter<Any> {
    override fun Any.start(instance: Any) {}
    override fun Any.insertAt(index: Int, instance: Any) {}
    override fun Any.removeAt(index: Int, count: Int) {}
    override fun Any.move(from: Int, to: Int, count: Int) {}
    override fun Any.end(instance: Any, parent: Any) {}
}

class ViewComposer(
    val root: Any,
    val context: Context,
    recomposer: Recomposer
) : Composer<Any>(
    SlotTable(),
    Applier(root, ViewApplyAdapter()),
    recomposer
) {
}

internal actual var currentComposer: Composer<*>? = null
    private set

actual fun <T> Composer<*>.runWithCurrent(block: () -> T): T {
    val prev = currentComposer
    try {
        currentComposer = this
        return block()
    } finally {
        currentComposer = prev
    }
}

@PublishedApi
internal actual val currentComposerNonNull
    get() = currentComposer ?: emptyComposition()

private fun emptyComposition(): Nothing =
    error("Composition requires an active composition context")

internal actual fun createComposer(
    root: Any,
    context: Context,
    recomposer: Recomposer
): Composer<*> {
    return ViewComposer(root, context, recomposer)
}
