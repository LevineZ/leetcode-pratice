package levinePratice.recursion;

/**
 *
 * N阶乘递归实现
 * @author michaellevine
 * @Auther levine
 * @Date 2021/5/21
 */
public class TestRecursionFactorial {


    public static int recursionN(int n) throws Exception {
        if (n < 1) {
            throw new Exception("Exception");
        } else if (n == 1) {
            return 1;
        } else {
            return n * recursionN(n - 1);
        }
        
    }

    public static void main(String[] args) throws Exception {
        System.out.println(recursionN(10));
    }
}
