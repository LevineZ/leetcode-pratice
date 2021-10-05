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
        }else if(nullOrEmpty(s)){
            return p.length() % 2 == 0 && p.replaceAll("\\*", "").length() == p.length() / 2;
        }

        boolean[][] dp = new boolean[s.length()][p.length()];
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        dp[0][0] = '.' == pChars[0] || sChars[0] == pChars[0];
        if (p.length() > 1) {
            dp[0][1] = '*' == pChars[1] && dp[0][0];
        }
        for (int i = 1; i < sChars.length; i++) {
            for (int j = 1; j < p.length(); j++) {
                if (pChars[j] != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && (pChars[j] == '.' || sChars[i] == pChars[j]);
                } else {
                    dp[i][j] = dp[i][j - 2] || (dp[i - 1][j] && (sChars[i] == pChars[j - 1] || pChars[j - 1] == '.'));
                }
            }
        }
        return dp[s.length() - 1][p.length() - 1];
    }

    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b";
        System.out.println("result: " + isMatch(s, p));
    }
}
