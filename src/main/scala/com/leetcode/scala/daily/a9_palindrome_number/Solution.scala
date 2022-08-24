package com.leetcode.scala.daily.a9_palindrome_number

/**
 * title: 回文数
 * Given an integer x, return true if x is palindrome integer.
 * An integer is a palindrome when it reads the same backward as forward. For example, 121 is palindrome while 123 is not.
 *
 *
 * Example 1:
 * Input: x = 121
 * Output: true
 *
 * Example 2:
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 *
 * Example 3:
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 *
 * Example 4:
 * Input: x = -101
 * Output: false
 *
 *
 * Constraints:
 * -2^31 <= x <= 2^31 - 1
 *
 * Follow up: Could you solve it without converting the integer to a string?
 */
object Solution {
    /**
     * 逐位反转后一半，每次都判断原始的前半部分与反转后的后半部分之间的大小关系
     */
    def isPalindrome(x: Int): Boolean = {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false
        var (num, reversePart) = (x, 0)
        while (num > reversePart) {
            reversePart = reversePart * 10 + num % 10
            num /= 10
        }
        //注意：需要考虑长度为奇数的回文
        num == reversePart || num == reversePart / 10
    }
}