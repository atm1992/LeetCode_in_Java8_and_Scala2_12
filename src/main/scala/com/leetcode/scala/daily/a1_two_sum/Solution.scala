package com.leetcode.scala.daily.a1_two_sum

/**
 * title：两数之和
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 *
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *  
 *
 * Constraints:
 * 2 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * Only one valid answer exists.
 **/
object Solution {

    import scala.collection.mutable

    def twoSum(nums: Array[Int], target: Int): Array[Int] = {
        val num2idx: mutable.HashMap[Int, Int] = mutable.HashMap[Int, Int]()
        for (i <- nums.indices) {
            val tmp: Int = target - nums(i)
            if (num2idx.contains(tmp))
                return Array(num2idx(tmp), i)
            num2idx.put(nums(i), i)
        }
        Array[Int]()
    }
}

object Test extends App {
    Solution.twoSum(Array(2, 7, 11, 15), 9).mkString(", ").foreach(print)
}
