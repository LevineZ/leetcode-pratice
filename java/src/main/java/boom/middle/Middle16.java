package boom.middle;

import java.util.Arrays;

/**
 *  给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *  和第15题同样的思路
 */
public class Middle16 {
    public static int threeSumClosest(int[] nums, int target) {
        int ans = nums[0] + nums[1] + nums[2];
        //长度小于3的话直接返回空列表
        if (nums.length > 3) {
            //先排序
            Arrays.sort(nums);
            int left = 0, right = nums.length - 1;
            while (left < right - 1) {
                //固定右边的指针，移动左边指针
                while (left < right - 1) {
                    int sum = nums[left] + nums[right];
                    for (int i = left + 1; i < right; i++) {
                        int val = sum + nums[i];
                        ans = Math.abs(target - val) > Math.abs(target - ans) ? ans : val;
                    }

                    int nowLeft = nums[left];
                    while (left < right - 1) {
                        if (nums[++left] != nowLeft) {
                            break;
                        }
                    }
                }
                //将left指向0， 相当于固定左边，移动右边指针
                int nowRight = nums[right];
                while (right > 0 && nums[right] == nowRight) {
                    right--;
                }
                left = 0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 0};
        int target = -100;
        System.out.printf("result: %s", threeSumClosest(nums, target));
    }
}
