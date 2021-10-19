package levinePratice.string;

import java.util.LinkedHashMap;

/**
 * 计算字符串中字母出现次数（区分大小写）
 * @author levine
 * @version 1.0
 * @date 2021/8/9 5:27 下午
 */
public class CaculateStrings1 {
    public static void main(String[] args) {
        LinkedHashMap<Character, Integer> linkedHashMap = new LinkedHashMap<>();
        String params = "Aaaasmoollhnnnjojoja";
        char[] c = params.toCharArray();
        for (Character ch : c) {
            if (linkedHashMap.containsKey(ch)) {
                linkedHashMap.put(ch, linkedHashMap.get(ch) + 1);
            } else {
                linkedHashMap.put(ch, 1);
            }
        }
        for (Character ch1 : linkedHashMap.keySet()) {
            System.out.printf("%s%s",ch1,linkedHashMap.get(ch1));
        }
    }
}
