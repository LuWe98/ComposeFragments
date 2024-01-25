package com.welu.composefragments.result

import android.os.Bundle
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


//------------------------------------------------------------------------------------
// Byte - ResultTypes
//------------------------------------------------------------------------------------
@Parcelize
class ByteResult(override val value: Byte): WrappedFragmentResult<Byte>

@Parcelize
class ByteListResult(override val value: List<Byte>): WrappedFragmentResult<List<Byte>>

@Parcelize
class ByteArrayResult(override val value: Array<Byte>): WrappedFragmentResult<Array<Byte>>

@Parcelize
class ByteBooleanArrayResult(override val value: ByteArray): WrappedFragmentResult<ByteArray>


//------------------------------------------------------------------------------------
// Boolean - ResultTypes
//------------------------------------------------------------------------------------
@Parcelize
class BooleanResult(override val value: Boolean): WrappedFragmentResult<Boolean>

@Parcelize
class BooleanListResult(override val value: List<Boolean>): WrappedFragmentResult<List<Boolean>>

@Parcelize
class BooleanArrayResult(override val value: Array<Boolean>): WrappedFragmentResult<Array<Boolean>>

@Parcelize
class PrimitiveBooleanArrayResult(override val value: BooleanArray): WrappedFragmentResult<BooleanArray>


//------------------------------------------------------------------------------------
// Short - ResultTypes
//------------------------------------------------------------------------------------
@Parcelize
class ShortResult(override val value: Short): WrappedFragmentResult<Short>

@Parcelize
class ShortListResult(override val value: List<Short>): WrappedFragmentResult<List<Short>>

@Parcelize
class ShortArrayResult(override val value: Array<Short>): WrappedFragmentResult<Array<Short>>

@Parcelize
class PrimitiveShortArrayResult(override val value: IntArray): WrappedFragmentResult<IntArray>


//------------------------------------------------------------------------------------
// Int - ResultTypes
//------------------------------------------------------------------------------------
@Parcelize
class IntResult(override val value: Int): WrappedFragmentResult<Int>

@Parcelize
class IntListResult(override val value: List<Int>): WrappedFragmentResult<List<Int>>

@Parcelize
class IntArrayResult(override val value: Array<Int>): WrappedFragmentResult<Array<Int>>

@Parcelize
class PrimitiveIntArrayResult(override val value: IntArray): WrappedFragmentResult<IntArray>


//------------------------------------------------------------------------------------
// Long - ResultTypes
//------------------------------------------------------------------------------------
@Parcelize
class LongResult(override val value: Long): WrappedFragmentResult<Long>

@Parcelize
class LongListResult(override val value: List<Long>): WrappedFragmentResult<List<Long>>

@Parcelize
class LongArrayResult(override val value: Array<Long>): WrappedFragmentResult<Array<Long>>

@Parcelize
class PrimitiveLongArrayResult(override val value: LongArray): WrappedFragmentResult<LongArray>


//------------------------------------------------------------------------------------
// Float - ResultTypes
//------------------------------------------------------------------------------------
@Parcelize
class FloatResult(override val value: Float): WrappedFragmentResult<Float>

@Parcelize
class FloatListResult(override val value: List<Float>): WrappedFragmentResult<List<Float>>

@Parcelize
class FloatArrayResult(override val value: Array<Float>): WrappedFragmentResult<Array<Float>>

@Parcelize
class PrimitiveFloatArrayResult(override val value: FloatArray): WrappedFragmentResult<FloatArray>


//------------------------------------------------------------------------------------
// Float - ResultTypes
//------------------------------------------------------------------------------------
@Parcelize
class DoubleResult(override val value: Double): WrappedFragmentResult<Double>

@Parcelize
class DoubleListResult(override val value: List<Double>): WrappedFragmentResult<List<Double>>

@Parcelize
class DoubleArrayResult(override val value: Array<Double>): WrappedFragmentResult<Array<Double>>

@Parcelize
class PrimitiveDoubleArrayResult(override val value: DoubleArray): WrappedFragmentResult<DoubleArray>


//------------------------------------------------------------------------------------
// Char - ResultTypes
//------------------------------------------------------------------------------------
@Parcelize
class CharResult(override val value: Char): WrappedFragmentResult<Char>

@Parcelize
class CharListResult(override val value: List<Char>): WrappedFragmentResult<List<Char>>

@Parcelize
class CharArrayResult(override val value: Array<Char>): WrappedFragmentResult<Array<Char>>

@Parcelize
class PrimitiveCharArrayResult(override val value: CharArray): WrappedFragmentResult<CharArray>


//------------------------------------------------------------------------------------
// String - ResultTypes
//------------------------------------------------------------------------------------
@Parcelize
class StringResult(override val value: String): WrappedFragmentResult<String>

@Parcelize
class StringListResult(override val value: List<String>): WrappedFragmentResult<List<String>>

@Parcelize
class StringArrayResult(override val value: Array<String>): WrappedFragmentResult<Array<String>>


//------------------------------------------------------------------------------------
// Serializable - ResultTypes
//------------------------------------------------------------------------------------
@Parcelize
class SerializableResult<T: Serializable>(override val value: T): WrappedFragmentResult<T>

@Parcelize
class SerializableArrayResult<T: Serializable>(override val value: Array<T>): WrappedFragmentResult<Array<T>>

@Parcelize
class SerializableListResult<T: Serializable>(override val value: List<T>): WrappedFragmentResult<List<T>>


//------------------------------------------------------------------------------------
// Parcelable - ResultTypes
//------------------------------------------------------------------------------------
@Parcelize
class ParcelableResult<T: Parcelable>(override val value: T): WrappedFragmentResult<T>

@Parcelize
class ParcelableArrayResult<T: Parcelable>(override val value: Array<T>): WrappedFragmentResult<Array<T>>

@Parcelize
class ParcelableListResult<T: Parcelable>(override val value: List<T>): WrappedFragmentResult<List<T>>


//------------------------------------------------------------------------------------
// Bundle - ResultTypes
//------------------------------------------------------------------------------------
@Parcelize
class BundleResult(override val value: Bundle): WrappedFragmentResult<Bundle>