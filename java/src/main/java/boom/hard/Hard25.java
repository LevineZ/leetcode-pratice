package boom.hard;

import java.util.Arrays;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * <p>
 * 思路：使用递归
 */
public class Hard25 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverse(ListNode[] nodes) {
        ListNode head = null;
        ListNode p = null;
        for (int i = nodes.length - 1; i >= 0; i--) {
            if (head == null) {
                head = nodes[i];
                p = head;
            } else {
                p.next = nodes[i];
                p = p.next;
            }
        }
        return head;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1 || head == null) {
            return head;
        }
        ListNode[] nodes = new ListNode[k];
        ListNode p = head;
        for (int i = 0; i < k; i++) {
            if (p == null) {
                return head;
            }
            nodes[i] = p;
            p = p.next;
        }
        ListNode resNode = reverse(nodes);
        nodes[0].next = reverseKGroup(p, k);
        return resNode;
    }

    public static ListNode generateListNode(int[] nums, int pos) {
        ListNode head = new ListNode(nums[pos]);
        if (pos < nums.length - 1) {
            head.next = generateListNode(nums, pos + 1);
        }
        return head;
    }

    public static int[] transferListNodeToArray(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        int len = 1;
        ListNode p = head;
        while ((p = p.next) != null) {
            len++;
        }
        int[] nums = new int[len];
        p = head;
        int index = 0;
        while (p != null) {
            nums[index++] = p.val;
            p = p.next;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int k = 2;
        ListNode head = generateListNode(nums, 0);
        ListNode ans = reverseKGroup(head, k);
        System.out.println("ans = " + Arrays.toString(transferListNodeToArray(ans)));
    }
}
