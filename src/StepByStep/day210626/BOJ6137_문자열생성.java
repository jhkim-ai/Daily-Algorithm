package StepByStep.day210626;

import java.util.*;
import java.io.*;

public class BOJ6137_문자열생성 {

    private static int N;
    private static char[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new char[N];

        for (int i = 0; i < N; ++i) {
            arr[i] = br.readLine().charAt(0);
        }
        int index = 0;
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            ++index;
            sb1.append(arr[start]);
            sb2.append(arr[end]);

            String s1 = sb1.toString();
            String s2 = sb2.toString();

            if (s1.compareTo(s2) == 0) {
                boolean flag = false;
                int tmpStart = start;
                int tmpEnd = end;
                while (tmpStart <= tmpEnd) {
                    ++tmpStart;
                    --tmpEnd;
                    if (tmpStart >= arr.length || tmpEnd < 0) break;
                    if (arr[tmpStart] == arr[tmpEnd]) continue;
                    if (arr[tmpStart] < arr[tmpEnd]) {
                        sb2.deleteCharAt(sb2.length() - 1);
                        sb2.append(arr[start]);
                        start++;
                    } else {
                        sb1.deleteCharAt(sb1.length() - 1);
                        sb1.append(arr[end]);
                        end--;
                    }
                    flag = true;
                    break;
                }
                if (!flag) {
                    sb2.deleteCharAt(sb2.length() - 1);
                    sb2.append(arr[start]);
                    start++;
                }
            } else if (s1.compareTo(s2) < 0) {
                sb2.deleteCharAt(sb2.length() - 1);
                sb2.append(arr[start]);
                start++;
            } else {
                sb1.deleteCharAt(sb1.length() - 1);
                sb1.append(arr[end]);
                end--;
            }
            if (index >= 80) {
                sb1.append("\n");
                sb2.append("\n");
                index = 0;
            }
        }
        System.out.println(sb1);
    }
}
