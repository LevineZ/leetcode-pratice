package boom.simple;

/**
 * @author boom
 * 简单题第35题：搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * 此解法为使用二叉搜索树
 */
public class Simple35 {
    public static class Node {
        public int index;
        public int value;
        public Node left;
        public Node right;
    }

    public static Node transferToBinaryTree(int[] nums, int start, int end) {
        int mid = (start + end) / 2;
        Node node = new Node();
        node.index = mid;
        node.value = nums[mid];
        if (mid > start) {
            node.left = transferToBinaryTree(nums, start, mid - 1);
        }
        if (mid < end) {
            node.right = transferToBinaryTree(nums, mid + 1, end);
        }
        return node;
    }

    public static int searchOrInsertNum(int target, Node root) {
        Node node = root;
        while (true) {
            if (node.value == target) {
                return node.index;
            }
            if (node.value < target) {
                if (node.right != null) {
                    node = node.right;
                } else {
                    return node.index + 1;
                }
            } else {
                if (node.left != null) {
                    node = node.left;
                } else {
                    return Math.max(node.index, 0);
                }
            }
        }
    }

    public static int searchInsert(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] >= target ? 0 : 1;
        }
        Node node = transferToBinaryTree(nums, 0, nums.length - 1);
        return searchOrInsertNum(target, node);
    }

    public static void main(String[] args) {
        System.out.println("result: " + searchInsert(new int[]{1, 3, 5, 6}, 7));
    }
}
