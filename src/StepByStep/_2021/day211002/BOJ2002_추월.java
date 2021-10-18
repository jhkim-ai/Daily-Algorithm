package StepByStep._2021.day211002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ2002_추월 {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int ans = 0;
        N = Integer.parseInt(br.readLine());
        List<String> entrance = new ArrayList<>();
        List<String> exit = new ArrayList<>();

        // 입구
        for (int i = 0; i < N; i++) {
            entrance.add(br.readLine());
        }

        // 출구
        for (int i = 0; i < N; i++) {
            exit.add(br.readLine());
        }

        // 1. 앞에서부터 탐색
        // 2. 입구List 와 출구List 의 순서가 다르다는 것은 순위변동이 존재
        // 3. 순위변동된 값을 증가시키고, 해당 차 번호를 입구List 와 출구List 에서 삭제
        int idx = 0;
        while(N-- > 0){
            String in = entrance.get(idx);
            String out = exit.get(idx);

            if(in.equals(out)){ // 같다면
                ++idx;
            } else { // 다르다면
                ans++;
                entrance.remove(out);
                exit.remove(out);
            }
        }

        System.out.println(ans);
    }
}
