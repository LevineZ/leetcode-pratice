package boom.hard;

import java.util.*;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 */
public class Hard23 {
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

    public static Integer getKey(Map<Integer, ListNode> nodeMap) {
        Integer key = null;
        Integer curVal = null;
        for (Map.Entry<Integer, ListNode> entry : nodeMap.entrySet()) {
            if (key == null) {
                key = entry.getKey();
                curVal = entry.getValue().val;
                continue;
            }
            if (entry.getValue().val < curVal) {
                key = entry.getKey();
                curVal = entry.getValue().val;
            }
        }
        return key;
    }

    /**
     * 此解法是基于Map
     * 用时： 926ms
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        Map<Integer, ListNode> nodeMap = new HashMap<>();
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                nodeMap.put(i, lists[i]);
            }
        }
        if (nodeMap.isEmpty()) {
            return null;
        }
        ListNode ans = null;
        ListNode p = null;
        while (!nodeMap.isEmpty()) {
            Integer flagkey = getKey(nodeMap);
            if (flagkey != null) {
                ListNode node = nodeMap.get(flagkey);
                ListNode nextNode = node.next;
                if (ans == null) {
                    ans = node;
                    p = ans;
                } else {
                    p.next = node;
                    p = p.next;
                }
                p.next = null;
                if (nextNode == null) {
                    nodeMap.remove(flagkey);
                } else {
                    nodeMap.put(flagkey, nextNode);
                }
            }
        }
        return ans;
    }

    /**
     * 此解法是基于优先队列
     * 用时： 6ms
     */
    public static ListNode mergeKListsV2(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                queue.offer(lists[i]);
            }
        }
        if (queue.isEmpty()) {
            return null;
        }
        ListNode ans = null;
        ListNode p = null;
        while (!queue.isEmpty()){
            ListNode node = queue.poll();
            ListNode nextNode = node.next;
            if (ans == null) {
                ans = node;
                p = ans;
            } else {
                p.next = node;
                p = p.next;
            }
            p.next = null;
            if (nextNode != null) {
                queue.offer(nextNode);
            }
        }
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
        int[][] nums = new int[][]{new int[]{1, 4, 5}, new int[]{1, 3, 4}, new int[]{2, 6}};
        ListNode[] listNodes = new ListNode[nums.length];
        for (int i = 0; i < nums.length; i++) {
            listNodes[i] = generateListNode(nums[i], 0);
        }

        ListNode ans = mergeKListsV2(listNodes);
        System.out.println("ans = " + Arrays.toString(transferListNodeToArray(ans)));
    }
}
