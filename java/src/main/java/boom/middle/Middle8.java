package boom.middle;

public class Middle8 {
    public static void main(String[] args) {
        String s = "-091283472332";
        System.out.println("myAtoi(s) = " + myAtoi(s));
    }

    public static int myAtoi(String s) {
        if (s.isEmpty())
            return 0;
        int start = 0;
        char[] chars = s.toCharArray();
        //去空格
        while (start < chars.length && chars[start] == ' '){
            start++;
        }
        if(start >= chars.length){
            return 0;
        }
        boolean hasMinus = chars[start] == '-';
        //如果这个字符是开始的符号 那么跳过
        if(chars[start] == '+' || chars[start] == '-'){
            start++;
            if(start >= chars.length)
                return 0;
        }
        char min = '0', max = '9';
        //消除前置0
        while (start < chars.length && chars[start] == min){
            start++;
        }
        if(start >= chars.length)
            return 0;

        int max_len = 10;
        StringBuilder strBuffer = new StringBuilder();
        for (int i = start; i < chars.length; i++) {
            char cur_char = chars[i];
            //有效的数字字符
            if (cur_char >= min && cur_char <= max) {
                strBuffer.append(cur_char);
            } else
                break;
            //长度超过，直接不用继续走下去了
            if (strBuffer.length() > max_len)
                break;
        }

        if (strBuffer.length() == 0)
            return 0;
        long temp_num = Long.parseLong(strBuffer.toString());
        int ans;
        if (temp_num > Integer.MAX_VALUE)
            ans = hasMinus ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        else
            ans = hasMinus ? (int) temp_num * -1 : (int) temp_num;
        return ans;
    }
}
