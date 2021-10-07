package boom.middle;

public class Middle11 {
    public static void main(String[] args) {
        int[] height = new int[]{2, 3, 4, 5, 18, 17, 6};
        long start = System.currentTimeMillis();
        System.out.println("maxArea(height) = " + maxAreaForDoublePoint(height));
        System.out.println("use time = " + (System.currentTimeMillis() - start));
    }

    /**
     * 超时
     */
    public static int maxArea(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = Math.min(height[i], height[j]) * (j - i);
                if (maxArea < area) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    /**
     * 双指针
     */
    public static int maxAreaForDoublePoint(int[] height) {
        int left = 0, right = height.length - 1, maxArea = Math.min(height[left], height[right]) * (right - left);
        while (true) {
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
            if (left == right)
                break;
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
        }
        return maxArea;
    }
}
