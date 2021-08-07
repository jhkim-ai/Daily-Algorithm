package Category.String;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BOJ1032_명령프로픔트 {

    private static int N;
    private static String[] results;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        results = new String[N];
        for (int i = 0; i < N; ++i) {
            results[i] = br.readLine();
        }

        char[] command = results[0].toCharArray();

        for (int idx = 1; idx < N; ++idx) {
            char[] result = results[idx].toCharArray();
            for (int j = 0; j < result.length; ++j) {
                char c = result[j];
                if (command[j] == c) continue;
                command[j] = '?';
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char c : command) {
            sb.append(c);
        }
        System.out.println(sb);
    }
}
