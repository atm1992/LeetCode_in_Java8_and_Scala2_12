package com.leetcode.java.daily.a1_two_sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * title：两数之和
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
 * <p>
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * <p>
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *  
 * <p>
 * Constraints:
 * 2 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * Only one valid answer exists.
 */
class Solution {
    /**
     * 哈希表
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> num2idx = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (num2idx.containsKey(target - nums[i])) {
                return new int[]{num2idx.get(target - nums[i]), i};
            }
            num2idx.put(nums[i], i);
        }
        return new int[0];
    }
}

class Test {
    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(Arrays.toString(obj.twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}
