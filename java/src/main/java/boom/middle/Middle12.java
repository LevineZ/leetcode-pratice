package boom.middle;

public class Middle12 {
    public static void main(String[] args) {
        int num = 1994;
        System.out.println("intToRoman(num) = " + intToRoman(num));
    }

    public static String intToRoman(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        int singleDigit = num % 10;
        num /= 10;
        if (singleDigit > 0) {
            stringBuilder.append(parseSingleDigit(singleDigit));
        }
        if (num > 0) {
            int tens = num % 10;
            num /= 10;
            if (tens > 0) {
                stringBuilder.insert(0, parseTens(tens));
            }
        }
        if (num > 0) {
            int hundreds = num % 10;
            num /= 10;
            if (hundreds > 0) {
                stringBuilder.insert(0, parseHundreds(hundreds));
            }
        }
        if (num > 0) {
            int thousands = num % 10;
            if (thousands > 0) {
                for (int i = 0; i < thousands; i++) {
                    stringBuilder.insert(0, "M");
                }
            }
        }
        return stringBuilder.toString();
    }

    public static String parseSingleDigit(int number) {
        if (number == 4) {
            return "IV";
        } else if (number == 9) {
            return "IX";
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (number >= 5) {
            stringBuilder.append("V");
            number -= 5;
        }
        for (int i = 0; i < number; i++) {
            stringBuilder.append("I");
        }
        return stringBuilder.toString();
    }

    public static String parseTens(int tens) {
        if (tens == 4) {
            return "XL";
        } else if (tens == 9) {
            return "XC";
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (tens >= 5) {
            stringBuilder.append("L");
            tens -= 5;
        }
        for (int i = 0; i < tens; i++) {
            stringBuilder.append("X");
        }
        return stringBuilder.toString();
    }

    public static String parseHundreds(int hundreds) {
        if (hundreds == 4) {
            return "CD";
        } else if (hundreds == 9) {
            return "CM";
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (hundreds >= 5) {
            stringBuilder.append("D");
            hundreds -= 5;
        }
        for (int i = 0; i < hundreds; i++) {
            stringBuilder.append("C");
        }
        return stringBuilder.toString();
    }
}
