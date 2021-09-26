package levinePratice.array;

/**
 * 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * @author levine
 * @version 1.0
 * @date 2021/9/26 5:25 下午
 */
public class TwoNumbersPlus {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null){
            l1 = new ListNode(0);
        }
        if(l2 == null){
            l2 = new ListNode(0);
        }
        if(l1.next==null && l2.next==null){
            //最小情况
            int val = l1.val+l2.val;
            if(val>9){
                //设置第二位
                ListNode node = new ListNode(val % 10);
                //设置第一位
                node.next = new ListNode(val/10);
                return node;
            }else {
                return new ListNode(val);
            }
        }else {
            int val = l1.val+l2.val;
            if(val>9){
                val = val-10;
                if(l1.next!=null) {
                    l1.next.val++;
                } else {
                    l2.next.val++;
                }
            }
            ListNode node = new ListNode(val);
            node.next = addTwoNumbers(l1.next,l2.next);
            return node;
        }
    }

}
