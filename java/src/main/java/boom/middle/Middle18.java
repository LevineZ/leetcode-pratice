package boom.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] ：
 * <p>
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * 思路参考第15题：三数之和
 * 排序加三指针
 * 步骤：1、固定三个指针的位置
 * 2、在第二个指针和第三个指针中间滑动
 * 3、滑动结束，右移第二个指针，然后再执行2步骤
 * 4、重复执行3步骤直到第二个指针无法向前移动的时候，右移第一个指针，然后重置第二个指针的位置到第一个指针后面，重复执行2，3步骤
 * 5、重复执行4步骤直到第一个指针无法向前移动的时候，左移第三个指针，重置第一个指针的位置为0，继续执行2，3，4
 * 6、直到第三个指针无法左移的时候，执行结束，返回结果
 */
public class Middle18 {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> resList = new ArrayList<>();
        if (nums.length >= 4) {
            //排序
            Arrays.sort(nums);
            System.out.println("nums = " + Arrays.toString(nums));
            //做一个简单判断 如果排序之后的前四个数之和都大于target 或最后四个数之和都小于target，说明不可能存在和为target的四元组，直接返回空列表就完事
            if (nums[0] + nums[1] + nums[2] + nums[3] > target || nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] + nums[nums.length - 4] < target) {
                return resList;
            }
            int first = 0, second, third = nums.length - 1;
            while (first < third - 2) {
                //固定右边的指针，移动左边指针
                while (first < third - 2) {
                    second = first + 1;
                    while (second < third - 1) {
                        int sum = nums[first] + nums[third] + nums[second];
                        int index = Arrays.binarySearch(nums, second + 1, third, target - sum);
                        if (index >= 0) {
                            resList.add(Arrays.asList(nums[first], nums[second], nums[index], nums[third]));
                        }
                        int nowSecond = nums[second];
                        while (second < third - 1) {
                            if (nums[++second] != nowSecond) {
                                break;
                            }
                        }
                    }

                    int nowFirst = nums[first];
                    while (first < third - 2) {
                        if (nums[++first] != nowFirst) {
                            break;
                        }
                    }
                }
                int nowThird = nums[third];
                while (third > 0 && nums[third] == nowThird) {
                    third--;
                }
                first = 0;
            }
        }
        return resList;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,-2,-5,-4,-3,3,3,5};
        int target = -11;
        List<List<Integer>> ans = fourSum(nums, target);
        System.out.println("ans = " + ans);
    }
}
