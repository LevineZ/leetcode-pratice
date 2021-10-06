package boom.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 * 思路：
 * 用一个列表存储每一个节点，就可以得出节点个数以及可以快速访问某个节点，这样就只需要遍历一次链表，删除某个节点
 */
public class Middle19 {
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

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> listNodes = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            listNodes.add(p);
            p = p.next;
        }
        if (n == listNodes.size()) {
            //如果删除的是第一个节点，直接返回第二个节点
            return head.next;
        } else if (n == 1) {
            //如果删除的是最后一个节点，且节点数为1，直接返回null， 否则将倒数第二个节点的next指向null
            listNodes.get(listNodes.size() - 2).next = null;
        } else {
            listNodes.get(listNodes.size() - (n + 1)).next = listNodes.get(listNodes.size() - (n - 1));
        }
        return head;
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
        int[] nums = new int[]{1, 2};
        int n = 1;
        ListNode head = generateListNode(nums, 0);
        ListNode ans = removeNthFromEnd(head, n);
        int[] arrayAns = transferListNodeToArray(ans);
        System.out.println("arrayAns = " + Arrays.toString(arrayAns));
    }
}
