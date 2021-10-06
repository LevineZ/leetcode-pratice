package boom.middle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 有效括号组合需满足：左括号必须以正确的顺序闭合。
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 输入：n = 1
 * 输出：["()"]
 */
public class Middle22 {
    /**
     * 第一种解法 排列组合 暴力枚举出所有情况，把不符合条件的过滤掉得出结果
     */
    public static List<String> generateParenthesis(int n) {
        List<String> resList = new ArrayList<>();
        resList.add("(");
        for (int i = 0; i < n * 2 - 1; i++) {
            List<String> tempStrings = new ArrayList();
            for (String str : resList) {
                tempStrings.add(str + "(");
                String tempStr = str + ")";
                if (tempStr.replaceAll("\\)", "").length() >= tempStr.replaceAll("\\(", "").length()) {
                    tempStrings.add(tempStr);
                }
            }
            resList = tempStrings;
        }
        return resList.stream().filter(str -> str.replaceAll("\\(", "").length() == n).collect(Collectors.toList());
    }

    /**
     * 基于二叉树 用时比枚举少，但是还是比较慢 占用空间也比较大
     */
    public static List<String> generateParenthesisByTree(int n) {
        Node<Character> root = new Node<>('(');
        addCharForAllLeafNode(root, n * 2 - 1);
        List<String> resList = new ArrayList<>();
        dfs(resList, root, new StringBuilder(), 1, 0, n);
        return resList;
    }

    public static void dfs(List<String> list, Node<Character> node, StringBuilder stringBuilder, int leftCount, int rightCount, int n) {
        if (rightCount > leftCount || rightCount > n || leftCount > n)
            return;
        StringBuilder stringBuilderForThis = new StringBuilder(stringBuilder.toString());
        stringBuilderForThis.append(node.getVal());
        //如果是叶子节点，说明以及到底了
        if (node.getLeft() == null) {
            list.add(stringBuilderForThis.toString());
        } else {
            dfs(list, node.getLeft(), stringBuilderForThis, leftCount + 1, rightCount, n);
            dfs(list, node.getRight(), stringBuilderForThis, leftCount, rightCount + 1, n);
        }
    }

    public static void addCharForAllLeafNode(Node<Character> root, int times) {
        List<Node<Character>> leafNodes = new ArrayList<>();
        leafNodes.add(root);
        Function<Character, Node<Character>> createNode = Node::new;
        for (int i = 0; i < times; i++) {
            List<Node<Character>> tempNodes = new ArrayList<>();
            for (Node<Character> node : leafNodes) {
                node.setLeft(createNode.apply('('));
                node.setRight(createNode.apply(')'));
                tempNodes.add(node.getLeft());
                tempNodes.add(node.getRight());
            }
            leafNodes = tempNodes;
        }
    }

    public static class Node<T> {
        private T val;
        private Node left;
        private Node right;

        public Node(T val) {
            this.val = val;
        }

        public T getVal() {
            return val;
        }

        public void setVal(T val) {
            this.val = val;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }
    }

    public static void main(String[] args) {
        List<String> res = generateParenthesisByTree(3);
        System.out.println("res = " + res);
    }
}
