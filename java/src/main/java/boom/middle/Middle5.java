package boom.middle;

public class Middle5 {

    /**
     * 思路：遍历字符串，将遍历的字符串作为回文串的中心字符，往左右扩散，遇到不是回文串就结束，不过写的非常粗糙，可以优化
     */

    public static void main(String[] args) {
        String s = "accccb";
        String longestPalindrome = longestPalindrome(s);
        System.out.println("longestPalindrome = " + longestPalindrome);
    }

    /**
     * 这个是有点类似左右扩散的思路， 不过写的很粗糙
     */
    public static String longestPalindromeV1(String s) {
        if (isPalindrome(s)) {
            return s;
        }
        String ans = String.valueOf(s.charAt(0));
        //字符数为奇数的回文串
        for (int mid = 0; mid < s.length(); mid++) {
            int right_len = s.length() - mid - 1;
            int min_half = Math.min(mid, right_len);
            for (int i = min_half; i > 0; i--) {
                if (ans.length() >= (i * 2 + 1))
                    break;
                String temp_str = s.substring(mid - i, mid + i + 1);
                if (isPalindrome(temp_str)) {
                    ans = temp_str;
                }
            }
        }
        //字符数为偶数的回文串
        for (int mid = 0; mid < s.length() - 1; mid++) {
            if (s.charAt(mid) == s.charAt(mid + 1)) {
                int right_len = s.length() - mid - 2;
                int min_half = Math.min(mid, right_len);
                for (int i = min_half; i >= 0; i--) {
                    if (ans.length() >= (i * 2 + 2))
                        break;
                    String temp_str = s.substring(mid - i, mid + i + 2);
                    if (isPalindrome(temp_str)) {
                        ans = temp_str;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 从中心往左右扩散
     */
    public static String longestPalindromeV2(String s) {
        if (isPalindrome(s)) {
            return s;
        }
        String ans = String.valueOf(s.charAt(0));
        for (int i = 0; i < s.length(); i++) {
            int left, right;
            left = right = i;
            while (left > 0 && s.charAt(i) == s.charAt(left - 1)) {
                left--;
            }
            while (right < s.length() - 1 && s.charAt(i) == s.charAt(right + 1)) {
                right++;
            }
            for (; left >= 0 && right < s.length(); left--, right++) {
                if (ans.length() >= (right - left + 1))
                    continue;
                String temp_str = s.substring(left, right + 1);
                if (isPalindrome(temp_str)) {
                    ans = temp_str;
                } else {
                    break;
                }
            }
        }
        return ans;
    }


    /**
     * dp
     */
    public static String longestPalindrome(String s) {
        if (isPalindrome(s)) {
            return s;
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = s.length() - 1; j >= 0; j--) {
                if (i >= j)
                    continue;
                dp[i][j] = (j - i == 1 || dp[i + 1][j - 1]) && s.charAt(i) == s.charAt(j);
            }
        }
        int left = 0, right = 0, len = 1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (dp[i][j] && len < (Math.abs(i - j) + 1)) {
                    left = i;
                    right = j;
                    len = (Math.abs(i - j) + 1);
                }
            }
        }
        return s.substring(left, right + 1);
    }

    public static boolean isPalindrome(String s) {
        if (s.length() == 1) {
            return true;
        }
        int mid = s.length() / 2;
        int left, right;
        if (s.length() % 2 == 0) {
            if (s.charAt(mid) != s.charAt(mid - 1)) {
                return false;
            }
            if (s.length() == 2) {
                return true;
            }
            left = mid - 2;
            right = mid + 1;
        } else {
            left = mid - 1;
            right = mid + 1;
        }
        for (; left >= 0; left--, right++) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
        }
        return true;
    }
}
