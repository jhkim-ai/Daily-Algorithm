package Category.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17609_회문 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            sb.append(isPalindrome(br.readLine())).append("\n");
        }
        System.out.println(sb);
    }

    public static int isPalindrome(String str) {
        int res = 0;
        char[] arr = str.toCharArray();
        int idx = 0;
        int left = 0;
        int right = str.length() - 1;
        int size = arr.length / 2;

        for (; idx <= size; ++idx) {
            if (arr[left + idx] != arr[right - idx]) {
                res = 2;
                break;
            }
        }

        if (res != 0) {
            res = isValid(arr, idx, right - idx - 1);
            if(res == 1) return res;
            res = isValid(arr, idx+1, right-idx);
        }
        return res;
    }

    public static int isValid(char[] arr, int left, int right) {
        int size = arr.length / 2;
        for (int i = 0; left + i <= size; ++i) {
            if (arr[left + i] != arr[right - i]){
                return 2;
            }
        }

        return 1;
    }
}
