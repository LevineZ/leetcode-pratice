package levinePratice.array;

/**
 * 删除有序数组中的重复项（不能使用额外空间）
 *
 * @author levine
 * @version 1.0
 * @date 2021/10/11 3:15 下午
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] arrays = {0, 1, 1, 2, 3, 4, 4, 4, 5, 6, 7, 7, 8, 9};
        System.out.println(removeDuplicatesNumbers(arrays));

    }
    public static int removeDuplicatesNumbers(int[] originArrays) {
        int l = originArrays.length;
        if (l == 0) {
            return 0;
        }
        int p = 0;
        int q = 1;
        while (q < l) {
            if (originArrays[p] != originArrays[q]) {
                originArrays[p + 1] = originArrays[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }

}
