package Test.Toss_2021;

public class Solution3 {

    public static void main(String[] args) {
        String amountText = "3+00";

        boolean answer = checkFormat(amountText) || onlyNum(amountText);
        System.out.println(answer);
    }

    public static boolean checkFormat(String str) {

        int count = 0;

        // 맨 앞 자리가 0일 경우 pass
        if (str.charAt(0) == '0' || str.charAt(0) == ',') return false;

        for (int idx = str.length() - 1; idx >= 0; --idx) {
            char c = str.charAt(idx);

            // 3자리마다 ','가 아닐 경우
            if ((count % 4 == 3) && c != ',') {
                return false;
            }

            // 각 자리마다 숫자가 아닐 경우
            if ((count % 4 != 3) && !isNum(c)) {
                return false;
            }
            count++;
        }
        return true;
    }

    public static boolean onlyNum(String str) {
        if (str.charAt(0) == '0' || str.charAt(0) == ',') return false;
        if (str.matches("^[0-9]*$")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }
}
