package boom.middle;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 *
 *  
 *
 */
public class Middle29 {
    /**
     * 暴力相减的解法 不太行
     */
    public static int divide(int dividend, int divisor) {
        long dividendLong = (long) dividend, divisorLong = (long) divisor;
        int ans = 0;
        if (dividendLong == 0 || Math.abs(dividendLong) < Math.abs(divisorLong)) {
            return ans;
        }
        boolean samePrefix = (dividendLong >= 0 && divisorLong >= 0) || (dividendLong <= 0 && divisorLong <= 0);
        dividendLong = Math.abs(dividendLong);
        divisorLong = Math.abs(divisorLong);
        if (divisorLong == 1) {
            if (samePrefix) {
                return dividendLong >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) dividendLong;
            } else {
                return dividendLong * -1 <= Integer.MIN_VALUE ? Integer.MIN_VALUE : (int) (dividendLong * -1);
            }
        }
        while (dividendLong >= divisorLong) {
            dividendLong -= divisorLong;
            if (samePrefix) {
                ans++;
                if (ans >= Integer.MAX_VALUE) {
                    break;
                }
            } else {
                ans--;
                if (ans <= Integer.MIN_VALUE) {
                    break;
                }
            }
        }
        return ans;
    }

//    /**
//     * 加快收敛速度
//     */
//    public static int divideV2(int dividend, int divisor) {
//        long dividendLong = (long) dividend, divisorLong = (long) divisor;
//        int ans = 0;
//        if (dividendLong == 0 || Math.abs(dividendLong) < Math.abs(divisorLong)) {
//            return ans;
//        }
//        boolean samePrefix = (dividendLong >= 0 && divisorLong >= 0) || (dividendLong <= 0 && divisorLong <= 0);
//        dividendLong = Math.abs(dividendLong);
//        divisorLong = Math.abs(divisorLong);
//        if (divisorLong == 1) {
//            if (samePrefix) {
//                return dividendLong >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) dividendLong;
//            } else {
//                return dividendLong * -1 <= Integer.MIN_VALUE ? Integer.MIN_VALUE : (int) (dividendLong * -1);
//            }
//        }
//
//    }

    public static void main(String[] args) {
        int dividend = 10, divisor = 3;
        int res = divide(dividend, divisor);
        System.out.println("res = " + res);
    }
}
