package boom.middle;

public class Middle6 {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 4;
        System.out.println("convert(s, numRows) = " + convert(s, numRows));
    }

    /**
     * 这题算是找了个规律
     * rows = 1，是比较特殊情况，直接返回整个串
     * 当rows > 1的时候，
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder ansBuffer = new StringBuilder();
        int half_z_len = 2 * (numRows - 1);
        int space_len = half_z_len;
        int times = s.length() / half_z_len + 1;
        for (int i = 0; i < numRows; i++) {
            for (int loop = 0; loop < times; loop++) {
                int cur_pos = i + loop * half_z_len;
                if (cur_pos >= s.length())
                    continue;
                ansBuffer.append(s.charAt(cur_pos));
                if (space_len != half_z_len && (cur_pos + space_len) < s.length()) {
                    ansBuffer.append(s.charAt(cur_pos + space_len));
                }
            }
            space_len = space_len - 2 == 0 ? half_z_len : space_len - 2;
        }
        return ansBuffer.toString();
    }
}
