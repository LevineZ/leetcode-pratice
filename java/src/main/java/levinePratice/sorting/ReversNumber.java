package levinePratice.sorting;

/**
 * 数字翻转，如345-》543，250-》52，不得超过Integer.MaxValue
 * @author levine
 * @version 1.0
 * @date 2021/10/19 7:18 下午
 */
public class ReversNumber {
    public static int reverse(int x) {
        int res = 0;
        int last;
        while(x!=0) {
            int tmp = x % 10;
            last = res;
            res = res * 10 + tmp;
            //溢出判断
            if (last != res / 10) {
                return 0;
            }
            x /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(reverse(250));
        System.out.println(reverse(45678));
    }
}
