package com.leetcode.scala.daily.a8_string_to_integer_atoi

import scala.util.control.Breaks

/**
 * title: 字符串转换整数 (atoi)
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
 * The algorithm for myAtoi(string s) is as follows:
 * Read in and ignore any leading whitespace.
 * Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
 * Read in the next characters until the next non-digit charcter or the end of the input is reached. The rest of the string is ignored.
 * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
 * If the integer is out of the 32-bit signed integer range [-2^31, 2^31 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -2^31 should be clamped to -2^31, and integers greater than 2^31 - 1 should be clamped to 2^31 - 1.
 * Return the integer as the final result.
 *
 * Note:
 * Only the space character ' ' is considered a whitespace character.
 * Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
 *
 *
 * Example 1:
 * Input: s = "42"
 * Output: 42
 * Explanation: The underlined characters are what is read in, the caret is the current reader position.
 * Step 1: "42" (no characters read because there is no leading whitespace)
 * ^
 * Step 2: "42" (no characters read because there is neither a '-' nor '+')
 * ^
 * Step 3: "42" ("42" is read in)
 * ^
 * The parsed integer is 42.
 * Since 42 is in the range [-2^31, 2^31 - 1], the final result is 42.
 *
 * Example 2:
 * Input: s = "   -42"
 * Output: -42
 * Explanation:
 * Step 1: "   -42" (leading whitespace is read and ignored)
 * ^
 * Step 2: "   -42" ('-' is read, so the result should be negative)
 * ^
 * Step 3: "   -42" ("42" is read in)
 * ^
 * The parsed integer is -42.
 * Since -42 is in the range [-2^31, 2^31 - 1], the final result is -42.
 *
 * Example 3:
 * Input: s = "4193 with words"
 * Output: 4193
 * Explanation:
 * Step 1: "4193 with words" (no characters read because there is no leading whitespace)
 * ^
 * Step 2: "4193 with words" (no characters read because there is neither a '-' nor '+')
 * ^
 * Step 3: "4193 with words" ("4193" is read in; reading stops because the next character is a non-digit)
 * ^
 * The parsed integer is 4193.
 * Since 4193 is in the range [-2^31, 2^31 - 1], the final result is 4193.
 *
 * Example 4:
 * Input: s = "words and 987"
 * Output: 0
 * Explanation:
 * Step 1: "words and 987" (no characters read because there is no leading whitespace)
 * ^
 * Step 2: "words and 987" (no characters read because there is neither a '-' nor '+')
 * ^
 * Step 3: "words and 987" (reading stops immediately because there is a non-digit 'w')
 * ^
 * The parsed integer is 0 because no digits were read.
 * Since 0 is in the range [-2^31, 2^31 - 1], the final result is 0.
 *
 * Example 5:
 * Input: s = "-91283472332"
 * Output: -2147483648
 * Explanation:
 * Step 1: "-91283472332" (no characters read because there is no leading whitespace)
 * ^
 * Step 2: "-91283472332" ('-' is read, so the result should be negative)
 * ^
 * Step 3: "-91283472332" ("91283472332" is read in)
 * ^
 * The parsed integer is -91283472332.
 * Since -91283472332 is less than the lower bound of the range [-2^31, 2^31 - 1], the final result is clamped to -2^31 = -2147483648.
 *
 *
 * Constraints:
 * 0 <= s.length <= 200
 * s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'
 */
object Solution {
    /**
     * 因为要求取值范围在 [−2^31,  2^31 − 1]，所以在最后一步计算前先判断当前值与 2^31 / 10 的大小关系。
     * 设数字拼接边界 boundary = 2^31 / 10，存在以下两种情况的越界：
     * 1、res > boundary；
     * 2、res == boundary，last_ch > '7'。2^31 的尾数为8，2^31 − 1 的尾数为7。因为 2 ^ 10 = 1024, 4 * 4 * 4 * 2 的尾数为8
     * 若当前res满足上述两种情况，则直接根据符号位，返回相应的INT_MAX, INT_MIN
     **/
    def myAtoi(s: String): Int = {
        import scala.util.control.Breaks
        // 以大写字母开头的变量不能使用多变量赋值，因为多变量赋值的本质是模式匹配。
        // 在模式匹配中，以大写字母开头的变量表示稳定标识符，这是给常量预留的。
        // 因此若使用多变量赋值的形式，则不是对变量赋值，而是对常量进行匹配
        val INT_MIN: Int = Int.MinValue
        val INT_MAX: Int = Int.MaxValue
        val boundary: Int = INT_MAX / 10
        var (idx, sign, n, res) = (0, 1, s.length, 0)
        while (idx < n && s.charAt(idx) == ' ') {
            idx += 1
        }
        if (idx == n) return res
        if (s.charAt(idx) == '+' || s.charAt(idx) == '-') {
            if (s.charAt(idx) == '-') sign = -1
            idx += 1
        }
        Breaks.breakable {
            for (i <- idx.until(n)) {
                val ch: Char = s.charAt(i)
                if (ch < '0' || ch > '9') Breaks.break
                if (res > boundary || (res == boundary && ch > '7')) {
                    return if (sign == 1) INT_MAX else INT_MIN
                }
                res = res * 10 + ch - '0'
            }
        }
        sign * res
    }
}

object Test {
    def main(args: Array[String]): Unit = {
        println(Solution.myAtoi("  -41 93 with words"))
    }
}