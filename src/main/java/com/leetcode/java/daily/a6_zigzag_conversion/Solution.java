package com.leetcode.java.daily.a6_zigzag_conversion;

import sun.applet.Main;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * title: Z 字形变换
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string s, int numRows);
 * <p>
 * <p>
 * Example 1:
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * <p>
 * Example 2:
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * <p>
 * Example 3:
 * Input: s = "A", numRows = 1
 * Output: "A"
 * <p>
 * <p>
 * Constraints:
 * 1 <= s.length <= 1000
 * s consists of English letters (lower-case and upper-case), ',' and '.'.
 * 1 <= numRows <= 1000
 */
class Solution {
    /**
     * 使用二维数组
     * 找规律，Z 字形变换可看作是以下形状的循环
     * P
     * A   L
     * Y A
     * P
     * 每个循环体内的元素个数mod = 2 * (numRows - 1)
     * 所以可通过s中的元素下标i对mod取模，来得到元素在循环体内的下标idx
     * 若idx < numRows，则元素位于第idx行；若idx >= numRows，则元素位于第mod - idx行。行数从0开始
     */
    public String convert(String s, int numRows) {
        //若只有一行或只有一列，则直接返回s
        if (numRows == 1 || numRows >= s.length()) return s;
        StringBuilder[] mat = new StringBuilder[numRows];
        for (int i = 0; i < mat.length; i++) {
            mat[i] = new StringBuilder();
        }
        int mod = 2 * (numRows - 1);
        for (int i = 0; i < s.length(); i++) {
            int idx = i % mod;
            if (idx < numRows) {
                mat[idx].append(s.charAt(i));
            } else {
                mat[mod - idx].append(s.charAt(i));
            }
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : mat) {
            res.append(row.toString());
        }
        return res.toString();
    }
}

class Test {
    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.convert("PAYPALISHIRING", 3));
    }
}
