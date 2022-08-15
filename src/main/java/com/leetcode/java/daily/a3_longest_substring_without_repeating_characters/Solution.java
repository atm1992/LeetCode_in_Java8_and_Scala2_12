package com.leetcode.java.daily.a3_longest_substring_without_repeating_characters;

import java.util.HashMap;

/**
 * title: 无重复字符的最长子串
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * <p>
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * <p>
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * Example 4:
 * Input: s = ""
 * Output: 0
 *  
 * <p>
 * Constraints:
 * 0 <= s.length <= 5 * 10^4
 * s consists of English letters, digits, symbols and spaces.
 */
class Solution {
    /**
     * 哈希表
     */
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int left = 0;
        HashMap<Character, Integer> ch2lastIdx = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 若该字符之前的下标小于left，则认为该字符在之前已被逻辑删除了。说明该字符在[left, i-1]范围内没有出现过
            if (ch2lastIdx.getOrDefault(ch, -1) >= left) {
                res = Math.max(res, i - left);
                left = ch2lastIdx.get(ch) + 1;
            }
            ch2lastIdx.put(ch, i);
        }
        return Math.max(res, s.length() - left);
    }
}

class Test {
    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.lengthOfLongestSubstring("abcabcbb"));
    }
}
