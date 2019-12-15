// SPDX-License-Identifier: Apache-2.0

package androidx.compose

import kotlin.native.ThreadLocal
import kotlin.native.identityHashCode

actual typealias BitSet = kotlin.native.BitSet

actual open class ThreadLocal<T> actual constructor(initialValue: () -> T) {
    @ThreadLocal var field = initialValue.invoke()
    actual fun get(): T { return field }
    actual fun set(value: T) { field = value }
}

actual fun identityHashCode(instance: Any?): Int = instance.identityHashCode()

actual inline fun <R> synchronized(lock: Any, block: () -> R): R {
    //TODO kotlin.synchronized(lock) {
        return block()
    //}
}

actual annotation class MainThread //TODO typealias androidx.annotation.MainThread

actual annotation class TestOnly //TODO typealias org.jetbrains.annotations.TestOnly

actual annotation class CheckResult(actual val suggest: String) //TODO typealias androidx.annotation.CheckResult
