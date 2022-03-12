package StepByStep._2022._22_01_29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ3151_합이0 {

    private static int N, zero;
    private static List<Integer> positive;
    private static List<Integer> negative;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long ans = 0;
        positive = new ArrayList<>();
        negative = new ArrayList<>();
        // 입력
        for(int i = 0; i < N; ++i){
            int n = Integer.parseInt(st.nextToken());
            if(n > 0){
                positive.add(n);
            } else if(n < 0){
                negative.add(n*-1);
            } else {
                zero++;
            }
        }

        int pSize = positive.size();
        int nSize = negative.size();

        // (양수 + 양수) 먼저구하기
        long[] plusPlus = new long[20001];
        for(int a = 0; a < pSize-1; ++a){
            for(int b = a + 1; b < pSize; ++b){
                plusPlus[positive.get(a) + positive.get(b)]++;
            }
        }
        // (양수 + 양수 + 음수) 구하기
        for(int idx = 0; idx < nSize; ++idx){
            if(plusPlus[negative.get(idx)] > 0){
                ans += plusPlus[negative.get(idx)];
            }
        }

        // (음수 + 음수) 먼저구하기
        long[] minusMinus = new long[20001];
        for(int a = 0; a < nSize-1; ++a){
            for(int b = a + 1; b < nSize; ++b){
                minusMinus[negative.get(a) + negative.get(b)]++;
            }
        }
        // (음수 + 음수 + 양수) 구하기
        for(int idx = 0; idx < pSize; ++idx){
            if(minusMinus[positive.get(idx)] > 0){
                ans += minusMinus[positive.get(idx)];
            }
        }

        // 0 + 양수 + 음수
        if(zero > 0){
            for(int a = 0; a < pSize; ++a){
                for(int b = 0; b < nSize; ++b){
                    if(positive.get(a) == negative.get(b)){
                        ans += zero;
                    }
                }
            }
        }

        // 0 + 0 + 0 : long 사이즈
        if(zero >= 3){
            ans += (zero * (zero-1) * (zero-2)) / 6 * 1l;
        }

        System.out.println(ans);
    }
}
