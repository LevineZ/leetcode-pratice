package boom.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 */
public class Middle24 {
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

    /**
     * 用一个列表来存储各个节点，方便随机访问，然后重新组成一个链表，用空间换时间
     * time: 0ms
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        List<ListNode> nodeList = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            nodeList.add(p);
            p = p.next;
        }
        ListNode ans = null;
        p = null;
        for (int i = 0; i < nodeList.size(); i += 2) {
            if (ans == null) {
                ans = nodeList.get(i + 1);
                p = ans;
            } else if (i + 1 < nodeList.size()) {
                p.next = nodeList.get(i + 1);
                p = p.next;
            }
            p.next = nodeList.get(i);
            p = p.next;
        }
        p.next = null;
        return ans;
    }

    /**
     * 递归解法
     */
    public static ListNode swapPairsV2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ans = head.next, node = head.next.next;
        ans.next = head;
        head.next = swapPairsV2(node);
        return ans;
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
        int[] nums = new int[]{1, 2, 3, 4};
        ListNode head = generateListNode(nums, 0);
        ListNode ans = swapPairsV2(head);
        System.out.println("ans = " + Arrays.toString(transferListNodeToArray(ans)));
    }
}
