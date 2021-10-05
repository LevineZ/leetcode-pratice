package boom.middle;

import java.util.*;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 目前的解法是直接暴力解题，时间和空间花费较多，有待优化
 */
public class Middle17 {
    public static Map<Character, char[]> numberToCharsMap = new HashMap<Character, char[]>() {{
        put('2', new char[]{'a', 'b', 'c'});
        put('3', new char[]{'d', 'e', 'f'});
        put('4', new char[]{'g', 'h', 'i'});
        put('5', new char[]{'j', 'k', 'l'});
        put('6', new char[]{'m', 'n', 'o'});
        put('7', new char[]{'p', 'q', 'r', 's'});
        put('8', new char[]{'t', 'u', 'v'});
        put('9', new char[]{'w', 'x', 'y', 'z'});
    }};

    public static List<String> letterCombinations(String digits) {
        List<String> resList = new ArrayList<>();
        if (digits != null && !digits.isEmpty()) {
            char[] digitChars = digits.toCharArray();
            for (char charIns : digitChars) {
                char[] numberChars = numberToCharsMap.get(charIns);
                List<String> strings = new ArrayList<>();
                if (resList.isEmpty()) {
                    for (char numberChar : numberChars) {
                        resList.add(String.valueOf(numberChar));
                    }
                } else {
                    for (String str : resList) {
                        for (char numberChar : numberChars) {
                            strings.add(str + numberChar);
                        }
                    }
                    resList = strings;
                }
            }
        }
        return resList;
    }

    public static void main(String[] args) {
        String digits = "22";
        System.out.println("result: "+ letterCombinations(digits));
    }
}
