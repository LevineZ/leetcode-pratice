package boom.simple.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * <p>
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * <p>
 * Emmmm 这个解法感觉不是很像dp，有一点像但不完全像。
 */
public class Simple118 {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> firstRow = new ArrayList<>(1);
        firstRow.add(1);
        result.add(firstRow);
        for (int i = 1; i < numRows; i++) {
            List<Integer> lastRow = result.get(i - 1);
            List<Integer> row = new ArrayList<>(i + 1);
            for (int j = 0; j < i + 1; j++) {
                int num = j - 1 >= 0 && lastRow.size() > j - 1 ? lastRow.get(j - 1) : 0;
                if (lastRow.size() > j) {
                    num += lastRow.get(j);
                }
                row.add(Math.max(num, 1));
            }
            result.add(row);
        }
        return result;
    }

    public static void main(String[] args) {
        int numRows = 1;
        List<List<Integer>> result = generate(numRows);
        System.out.println("result = " + result);
    }
}
