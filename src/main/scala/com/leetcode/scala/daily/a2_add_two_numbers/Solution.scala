package com.leetcode.scala.daily.a2_add_two_numbers

/**
 * title：两数相加
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 *
 * Example 1:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 *
 * Example 2:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Example 3:
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 *
 * Constraints:
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 *
 *
 * 解题思路：为了尽量少地创建新节点，可以使用已有的节点，将节点的值更新为相应位置节点的和。
 * 由于两个链表都是非空的，所以可任选其中一个作为返回结果的头节点，如选择l1
 */
//Definition for singly-linked list.
class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
}

object Solution {
    /**
     * 模拟
     */
    def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
        var (a, b, cur) = (l1, l2, l1)
        var carry = 0
        while (a != null || b != null) {
            val sum: Int = (if (a != null) a.x else 0) + (if (b != null) b.x else 0) + carry
            carry = sum / 10
            cur.x = sum % 10
            if (a != null) a = a.next
            if (b != null) b = b.next
            cur.next = if (a != null) a else b
            // 避免a、b同时为null时，将cur置为null，从而导致处理进位carry时的cur.next报错
            if (cur.next != null) cur = cur.next
        }
        if (carry > 0) cur.next = new ListNode(carry)
        l1
    }
}

object Solution2 {
    /**
     * 递归。使用函数式编程的思想
     */
    def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
        (l1, l2) match {
            // (null, null) 会进入第一个case (l1, null)
            case (l1, null) => l1
            case (null, l2) => l2
            case (_, _) =>
                val carry: Int = (l1.x + l2.x) / 10
                val cur = new ListNode((l1.x + l2.x) % 10)
                cur.next = if (carry > 0) {
                    addTwoNumbers(new ListNode(carry), addTwoNumbers(l1.next, l2.next))
                } else {
                    addTwoNumbers(l1.next, l2.next)
                }
                cur
        }
    }
}
