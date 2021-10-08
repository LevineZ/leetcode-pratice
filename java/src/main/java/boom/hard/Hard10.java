package boom.hard;

/**
 * 未做完
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
        for (int i = 1; i < pChars.length; i++) {
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
