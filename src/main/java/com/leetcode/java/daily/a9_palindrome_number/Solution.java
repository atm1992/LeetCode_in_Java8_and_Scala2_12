package com.leetcode.java.daily.a9_palindrome_number;

/**
 * title: 回文数
 * Given an integer x, return true if x is palindrome integer.
 * An integer is a palindrome when it reads the same backward as forward. For example, 121 is palindrome while 123 is not.
 * <p>
 * <p>
 * Example 1:
 * Input: x = 121
 * Output: true
 * <p>
 * Example 2:
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * <p>
 * Example 3:
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 * <p>
 * Example 4:
 * Input: x = -101
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * -2^31 <= x <= 2^31 - 1
 * <p>
 * Follow up: Could you solve it without converting the integer to a string?
 */
class Solution {
    /**
     * 逐位反转后一半，每次都判断原始的前半部分与反转后的后半部分之间的大小关系
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int reversePart = 0;
        while (x > reversePart) {
            reversePart = reversePart * 10 + x % 10;
            x /= 10;
        }
        //注意：需要考虑长度为奇数的回文
        return x == reversePart || x == reversePart / 10;
    }
}