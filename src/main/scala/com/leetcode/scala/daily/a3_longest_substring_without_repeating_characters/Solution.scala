package com.leetcode.scala.daily.a3_longest_substring_without_repeating_characters


/**
 * title: 无重复字符的最长子串
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Example 4:
 * Input: s = ""
 * Output: 0
 *  
 *
 * Constraints:
 * 0 <= s.length <= 5 * 10^4
 * s consists of English letters, digits, symbols and spaces.
 **/
object Solution {

    import scala.collection.mutable

    def lengthOfLongestSubstring(s: String): Int = {
        var res = 0
        var left = 0
        val ch2lastIdx = new mutable.HashMap[Char, Int]()
        for (i <- 0.until(s.length)) {
            val ch: Char = s.charAt(i)
            // 若该字符之前的下标小于left，则认为该字符在之前已被逻辑删除了。说明该字符在[left, i-1]范围内没有出现过
            if (ch2lastIdx.getOrElse(ch, -1) >= left) {
                res = math.max(res, i - left)
                left = ch2lastIdx(ch) + 1
            }
            ch2lastIdx.put(ch, i)
        }
        math.max(res, s.length - left)
    }
}

object Test extends App {
    println(Solution.lengthOfLongestSubstring("abcabcbb"))
}
