package StepByStep._2021.day211002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ1283_단축키지정 {

    private static int N;
    private static String[] arr;

    private static Set<Character> s;
    private static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new String[N];
        s = new HashSet<>();
        map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }
        // 알고리즘 시작
        outer:
        for (int i = 0; i < N; i++) {
            String now = arr[i];
            StringTokenizer st = new StringTokenizer(now, " ");

            // 1단계
            int len = 0;
            while (st.hasMoreTokens()) {
                String word = st.nextToken();
                char first = Character.toUpperCase(word.charAt(0)); // all 대문자

                if (!s.contains(first)) {
                    s.add(first);
                    map.put(arr[i], len); // new : 0, Save As : 5
                    len += word.length();
                    continue outer;
                }

                len += word.length() + 1; // 공백처리
            }

            // 2단계
            for (int j = 0; j < now.length(); j++) {
                char word = Character.toUpperCase(now.charAt(j));
                if (word == ' ') {
                    continue;
                }

                if (!s.contains(word)) {
                    s.add(word);
                    map.put(arr[i], j);
                    continue outer;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            String now = arr[i];
            int idx = -1;

            if (map.containsKey(now)) {
                idx = map.get(arr[i]);
            }

            for (int j = 0; j < arr[i].length(); j++) {
                char c = arr[i].charAt(j);
                if (idx == j) {
                    sb.append("[").append(c).append("]");
                } else {
                    sb.append(c);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
