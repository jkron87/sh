package com.spothero.kron.util

import com.spothero.kron.util.UuidExtensions.CHARS
import java.nio.ByteBuffer
import java.util.UUID


object UuidExtensions {
    const val CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_"
}
/**
 * Defines a url-friendly, 24-character, base-64 representation for UUID
 */
fun UUID.toBase64String(): String {
    val buf = ByteBuffer.wrap(ByteArray(16))
    buf.putLong(this.mostSignificantBits)
    buf.putLong(this.leastSignificantBits)
    val bb = buf.array()
    val sb = StringBuilder()
    // takes 3 chars to represent every 4 in a traditional random UUID
    for (i in 0 until 8) {
        val next = bb.get(i * 2)
        val next2 = bb.get(i * 2 + 1)
        val sum = ((next.toInt() + Byte.MAX_VALUE + 1) * 256) + next2.toInt() + Byte.MAX_VALUE + 1
        sb.append(CHARS[sum / 4096])
        sb.append(CHARS[(sum / 64) % 64])
        sb.append(CHARS[sum % 64])
    }
    return sb.toString()
}