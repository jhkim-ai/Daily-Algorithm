package StepByStep.day210406;

import java.util.*;
import java.io.*;

public class BOJ1057_토너먼트 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());   // n <= 100,000
        int kim = Integer.parseInt(st.nextToken()); // 김지민
        int lim = Integer.parseInt(st.nextToken()); // 임한수

        // kim < lim 이 되도록 변경
        if(kim > lim){
            int tmp = lim;
            lim = kim;
            kim = tmp;
        }

        int ans = -1;   // 정답
        int cnt = 0;    // 경기 횟수

        // ----------- 알고리즘 시작 ----------- //
        while(n != 1) {
            cnt++;

            // 같은 대진표인지 check
            if(isMatch(kim, lim)) {
                ans = cnt;
                break;
            }

            // 다음 대진표 번호 부여
            kim = nextRound(kim);
            lim = nextRound(lim);
        }

        // 정답
        System.out.println(ans);
    }

    // 같은 대진표인지 확인
    static boolean isMatch(int kim, int lim){
        if(kim % 2 == 1 && lim % 2 == 0 && (lim/2 - kim/2) == 1)
            return true;
        return false;
    }

    // 다음 라운드 대진표 번호
    static int nextRound(int name){
        if(name % 2 == 0)
            return name/2;
        return (name/2) + 1;
    }
}
