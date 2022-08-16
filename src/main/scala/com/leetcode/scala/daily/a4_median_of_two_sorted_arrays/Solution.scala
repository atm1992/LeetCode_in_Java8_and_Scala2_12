package com.leetcode.scala.daily.a4_median_of_two_sorted_arrays

/**
 * title: 寻找两个正序数组的中位数
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 *
 *
 * Example 1:
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 *
 * Example 2:
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *
 * Example 3:
 * Input: nums1 = [0,0], nums2 = [0,0]
 * Output: 0.00000
 *
 * Example 4:
 * Input: nums1 = [], nums2 = [1]
 * Output: 1.00000
 *
 * Example 5:
 * Input: nums1 = [2], nums2 = []
 * Output: 2.00000
 *  
 *
 * Constraints:
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 */
object Solution {
    /**
     * 二分查找
     * 题目要求时间复杂度为O(log (m+n))，看到有log，并且是有序数组，第一想法就应该是二分查找。
     * 找到nums1与nums2之间的较短数组，对其进行二分查找，确定了一个数组的下标，自然也就确定了另一个数组的下标，因为中位数的下标是确定的。
     * 用i与j分别去划分数组nums1与nums2，划分为左半边（[0, i) + [0, j)）和右半边（[i, m) + [j, n)）。
     * 若m+n为偶数，则 i + j == m-i + n-j，即 i + j == (m+n)/2；此时的中位数为左半边的最大值与右半边的最小值相加后除以2
     * 若m+n为奇数，则 i + j == m-i + n-j + 1 (多出的那个元素放到左半边)，即 i + j == (m+n+1)/2；此时的中位数就是左半边的最大值(也就是左半边多出的那个元素)
     * 对于m+n为偶数来说，(m+n)/2 等价于 (m+n+1)/2；对于m+n为奇数来说，(m+n+1)/2 也等价于 (m+n+1)/2；所以，无论m+n是偶数还是奇数，都有 i + j == (m+n+1)/2。
     * i 的取值范围：[0, m]。i取0时，[0, i)为空数组，即 将整个nums1划分到右半边；i取m时，[i, m)为空数组，即 将整个nums1划分到左半边。初始时，i 可取0与m之间的中间位置。
     * 时间复杂度为 O(log min(m, n))。
     */
    def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1)
        val (m, n) = (nums1.length, nums2.length)
        val medianIdx: Int = (m + n + 1) / 2
        var (left, right) = (0, m)
        var (maxLeft, minRight) = (0, 0)
        while (left <= right) {
            val i: Int = (left + right) / 2
            val j: Int = medianIdx - i
            val nums1Left: Int = if (i == 0) Int.MinValue else nums1(i - 1)
            val nums1Right: Int = if (i == m) Int.MaxValue else nums1(i)
            val nums2Left: Int = if (j == 0) Int.MinValue else nums2(j - 1)
            val nums2Right: Int = if (j == n) Int.MaxValue else nums2(j)
            if (nums1Left <= nums2Right) {
                maxLeft = math.max(nums1Left, nums2Left)
                minRight = math.min(nums1Right, nums2Right)
                // i 向右移，逐渐增大nums1Left、nums1Right
                left = i + 1
            } else {
                // i 向左移，逐渐减小nums1Left、nums1Right
                right = i - 1
            }
        }
        if ((m + n) % 2 == 1) maxLeft else (maxLeft + minRight) / 2.0
    }
}
