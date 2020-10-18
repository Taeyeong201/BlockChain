package core

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class HashUtil {
    companion object {
        fun applySHA256(input: String): String {
            val hexString = StringBuffer()
            val hash: ByteArray
            return try {
                val digest = MessageDigest.getInstance("SHA-256")
                hash = digest.digest(input.toByteArray(charset("UTF-8")))
                for (i in hash.indices) {
                    val hex = Integer.toString((0xff and hash[i].toInt()) + 0x100, 16).substring(1)
                    hexString.append(hex)
                }
                hexString.toString()
            } catch (e: NoSuchAlgorithmException) {
                throw RuntimeException(e)
            } catch (e: UnsupportedEncodingException) {
                throw RuntimeException(e)
            }
        }
    }
}