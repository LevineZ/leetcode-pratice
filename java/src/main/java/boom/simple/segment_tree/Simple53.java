package boom.simple.segment_tree;

/**
 * @author boom
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 解法： 线段树
 * 说明：https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/
 */
public class Simple53 {
    static class SegmentInfo {
        public int lSum;
        public int rSum;
        public int mSum;
        public int iSum;
    }

    public static SegmentInfo getSegmentInfo(int[] nums, int l, int r) {
        SegmentInfo ret = new SegmentInfo();
        if (l != r) {
            int m = (l + r) / 2;
            SegmentInfo leftSeg = getSegmentInfo(nums, l, m);
            SegmentInfo rightSeg = getSegmentInfo(nums, m + 1, r);
            ret.iSum = leftSeg.iSum + rightSeg.iSum;
            ret.lSum = Math.max(leftSeg.lSum, leftSeg.iSum + rightSeg.lSum);
            ret.rSum = Math.max(rightSeg.rSum, rightSeg.iSum + leftSeg.rSum);
            ret.mSum = Math.max(leftSeg.mSum, rightSeg.mSum);
            ret.mSum = Math.max(ret.mSum, leftSeg.rSum + rightSeg.lSum);
        } else {
            ret.iSum = ret.lSum = ret.rSum = ret.mSum = nums[l];
        }
        return ret;
    }

    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        SegmentInfo segmentInfo = getSegmentInfo(nums, 0, nums.length - 1);
        return segmentInfo.mSum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1};
        int result = maxSubArray(nums);
        System.out.println("result = " + result);
    }
}
