package com.gonzaga.yu.workit.utils

object PearlTextUtils {

    fun nullIfBlank(value: String?): String? {
        return if (isBlank(value)) {
            null
        } else value
    }

    /**
     * A checker for whether or not the input value is entirely whitespace. This is slightly more
     * aggressive than the android TextUtils#isEmpty method, which only returns true for
     * `null` or `""`.
     *
     * @param value a possibly blank input string value
     * @return `true` if and only if the value is all whitespace, `null`, or empty
     */
    @JvmStatic
    fun isBlank(value: String?): Boolean {
        return value == null || value.trim { it <= ' ' }.length == 0
    }

    fun substringAfterLast(str: String, separator: String): String? {
        if (isBlank(str)) {
            return null
        }
        if (isBlank(separator)) {
            return ""
        }
        val pos = str.lastIndexOf(separator)
        return if (pos == -1 || pos == str.length - separator.length) {
            ""
        } else str.substring(pos + separator.length)
    }
}