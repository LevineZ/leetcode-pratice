package boom.hard;

import java.util.Arrays;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * 示例 4：
 * <p>
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5：
 * <p>
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 * <p>
 * 解法： 动态规划
 * dp[i][j] 表示s的前i个字符是否和p的前j个字符匹配，最后的结果是dp[s.length()][p.length()]
 */
public class Hard10 {
    public static boolean nullOrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isMatch(String s, String p) {
        //特殊情况处理
        if (nullOrEmpty(s) && nullOrEmpty(p)) {
            return true;
        }
        if (".*".equals(p)) {
            return true;
        }
        if (nullOrEmpty(p)) {
            return false;
        } else if (nullOrEmpty(s)) {
            return p.length() % 2 == 0 && p.replaceAll("\\*", "").length() == p.length() / 2;
        }

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        //初始化
        dp[0][0] = true;
        for (int i = 1; i <= pChars.length; i++) {
            int pos = i - 1;
            if (pChars[pos] == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }
        for (int i = 1; i <= sChars.length; i++) {
            int iPos = i - 1;
            for (int j = 1; j <= pChars.length; j++) {
                int jPos = j - 1;
                if (pChars[jPos] != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && (pChars[jPos] == '.' || sChars[iPos] == pChars[jPos]);
                } else {
                    dp[i][j] = dp[i][j - 2] || (dp[i - 1][j] && (sChars[iPos] == pChars[jPos - 1] || pChars[jPos - 1] == '.'));
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        String s = "aa";
        String p = "a*";
        System.out.println("result: " + isMatch(s, p));
    }
}
