package StepByStep._2022._22_03_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ1235_학생번호 {

    private static int N, size, ans;
    private static String[] input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        input = new String[N];
        for(int i = 0; i < N; ++i){
            input[i] = br.readLine();
        }
        size = input[0].length();

        for(ans = 1; ans <= size; ++ans){
            if(getMinLength(ans)) break;
        }

        System.out.println(ans);
    }

    public static boolean getMinLength(int k){
        Set<String> set = new HashSet<>();
        int startIdx = size - k;

        for(int n = 0; n < N; ++n){
            String extractedStr = input[n].substring(startIdx);

            if(set.contains(extractedStr)) return false;

            set.add(extractedStr);
        }

        return true;
    }
}
