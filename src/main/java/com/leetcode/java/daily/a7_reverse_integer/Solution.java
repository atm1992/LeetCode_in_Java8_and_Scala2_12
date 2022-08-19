package com.leetcode.java.daily.a7_reverse_integer;

/**
 * title: 整数反转
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 * <p>
 * <p>
 * Example 1:
 * Input: x = 123
 * Output: 321
 * <p>
 * Example 2:
 * Input: x = -123
 * Output: -321
 * <p>
 * Example 3:
 * Input: x = 120
 * Output: 21
 * <p>
 * Example 4:
 * Input: x = 0
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * -2^31 <= x <= 2^31 - 1
 */
class Solution {
    /**
     * 题目要求不得使用64位整数，也就是说只能使用32位有符号整数，即 计算过程中的数字不得超出范围[-2^31, 2^31 - 1]。
     * 假设反转后的整数为 res * 10 + digit，由于输入的x满足 -2^31 <= x <= 2^31 - 1，所以反转后的整数最后一位一定不大于2
     * 即 -2147483648 = -2^31 = INT_MIN <= res * 10 + digit <= INT_MAX = 2^31 - 1 = 2147483647，
     * 当输入的x为正数时，res * 10 + digit <= INT_MAX = 2147483647 = floor(INT_MAX / 10) * 10 + 7，由上可知，最后一位digit <= 2，
     * 所以，等价于 res <= floor(INT_MAX / 10)。
     * 同理，当输入的x为负数时，INT_MIN <= res * 10 + digit，即 -res * 10 - digit <= -INT_MIN = 2147483648 = ceil(-INT_MIN / 10) * 10 - 2 = -ceil(INT_MIN / 10) * 10 - 2，
     * 由上可知，最后一位digit <= 2，所以 -res <= -ceil(INT_MIN / 10)，即 等价于 res >= ceil(INT_MIN / 10)。
     * 综上，要求 -2^31 <= res * 10 + digit <= 2^31 - 1，即要求 ceil(INT_MIN / 10) <= res <= floor(INT_MAX / 10)
     */
    public int reverse(int x) {
        int INT_MIN = Integer.MIN_VALUE, INT_MAX = Integer.MAX_VALUE;
        int res = 0;
        while (x != 0) {
            if (res < INT_MIN / 10 || res > INT_MAX / 10) return 0;
            int digit = x % 10;
            x /= 10;
            res = res * 10 + digit;
        }
        return res;
    }
}

class Test {
    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println("obj.reverse(120) = " + obj.reverse(120));
        System.out.println("obj.reverse(-123) = " + obj.reverse(-123));
    }
}