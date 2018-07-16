[libui-ktx](../../index.md) / [libui](../index.md) / [uiAreaKeyEvent](./index.md)

# uiAreaKeyEvent

`class uiAreaKeyEvent : `[`CStructVar`](../../kotlinx.cinterop/-c-struct-var/index.md)

### Types

| Name | Summary |
|---|---|
| [Companion](-companion.md) | `companion object Companion : `[`Type`](../../kotlinx.cinterop/-c-struct-var/-type/index.md) |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `uiAreaKeyEvent(rawPtr: `[`NativePtr`](../../kotlinx.cinterop/-native-ptr.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [ExtKey](-ext-key.md) | `var ExtKey: `[`uiExtKey`](../ui-ext-key.md) |
| [Key](-key.md) | `var Key: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html) |
| [Modifier](-modifier.md) | `var Modifier: `[`uiModifiers`](../ui-modifiers.md) |
| [Modifiers](-modifiers.md) | `var Modifiers: `[`uiModifiers`](../ui-modifiers.md) |
| [Up](-up.md) | `var Up: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Extension Properties

| Name | Summary |
|---|---|
| [rawPtr](../../kotlinx.cinterop/raw-ptr.md) | `val `[`NativePointed`](../../kotlinx.cinterop/-native-pointed/index.md)`?.rawPtr: `[`NativePtr`](../../kotlinx.cinterop/-native-ptr.md) |

### Extension Functions

| Name | Summary |
|---|---|
| [arrayMemberAt](../../kotlinx.cinterop/array-member-at.md) | `fun <T : `[`CVariable`](../../kotlinx.cinterop/-c-variable/index.md)`> `[`CStructVar`](../../kotlinx.cinterop/-c-struct-var/index.md)`.arrayMemberAt(offset: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`): `[`CArrayPointer`](../../kotlinx.cinterop/-c-array-pointer.md)`<`[`T`](../../kotlinx.cinterop/array-member-at.md#T)`>` |
| [memberAt](../../kotlinx.cinterop/member-at.md) | `fun <T : `[`CPointed`](../../kotlinx.cinterop/-c-pointed/index.md)`> `[`CStructVar`](../../kotlinx.cinterop/-c-struct-var/index.md)`.memberAt(offset: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`): `[`T`](../../kotlinx.cinterop/member-at.md#T)<br>Returns the member of this [CStructVar](../../kotlinx.cinterop/-c-struct-var/index.md) which is located by given offset in bytes. |
| [readValue](../../kotlinx.cinterop/read-value.md) | `fun <T : `[`CVariable`](../../kotlinx.cinterop/-c-variable/index.md)`> `[`CPointed`](../../kotlinx.cinterop/-c-pointed/index.md)`.readValue(size: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, align: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`CValue`](../../kotlinx.cinterop/-c-value/index.md)`<`[`T`](../../kotlinx.cinterop/read-value.md#T)`>` |
| [readValues](../../kotlinx.cinterop/read-values.md) | `fun <T : `[`CVariable`](../../kotlinx.cinterop/-c-variable/index.md)`> `[`CPointed`](../../kotlinx.cinterop/-c-pointed/index.md)`.readValues(size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, align: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`CValues`](../../kotlinx.cinterop/-c-values/index.md)`<`[`T`](../../kotlinx.cinterop/read-values.md#T)`>` |
| [reinterpret](../../kotlinx.cinterop/reinterpret.md) | `fun <T : `[`NativePointed`](../../kotlinx.cinterop/-native-pointed/index.md)`> `[`NativePointed`](../../kotlinx.cinterop/-native-pointed/index.md)`.reinterpret(): `[`T`](../../kotlinx.cinterop/reinterpret.md#T)<br>Changes the interpretation of the pointed data or code. |