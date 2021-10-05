package boom.middle;

import java.util.*;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * 解法： 排序加双指针，关键点是去重
 * 此版本花费时间较长，有待优化
 */
public class Middle15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        final List<List<Integer>> resList = new ArrayList<List<Integer>>();
        //长度小于3的话直接返回空列表
        if (nums.length >= 3) {
            //先排序
            Arrays.sort(nums);
            int left = 0, right = nums.length - 1;
            while (left < right - 1) {
                //固定右边的指针，移动左边指针
                while (left < right - 1) {
                    int sum = nums[left] + nums[right];
                    int index = Arrays.binarySearch(nums, left + 1, right, sum * -1);
                    if (index >= 0) {
                        resList.add(Arrays.asList(nums[left], nums[index], nums[right]));
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
        return resList;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4};
        List<List<Integer>> list = threeSum(nums);
        System.out.println(list);
    }
}
