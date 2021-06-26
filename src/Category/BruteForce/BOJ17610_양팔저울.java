package Category.BruteForce;

import java.util.*;
import java.io.*;

public class BOJ17610_양팔저울 {

    private static final char[] OPERATION = {'-', '+'};

    private static int N;
    private static int[] weight;
    private static boolean[] possibleWeight;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weight = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; ++i){
            weight[i] = Integer.parseInt(st.nextToken());
        }

        // 1. 정렬 (정렬이 굳이 필요한가? 라는 생각이 듭니다)
        Arrays.sort(weight);

        // 2. 모든 원소의 합 = 만들 수 있는 가장 큰 수 & boolean 배열에 만들었던 수를 memo 한다.
        int sum = 0;
        for(int num : weight)
            sum += num;
        possibleWeight = new boolean[sum+1];

        // 3. 부분집합을 구하여 만들 수 있는 조합을 possibleWeight 에 저장한다.
        subset();

        // 4. 만들 수 없는 수를 세어 정답 출력
        int ans = 0;
        for(int i = 1; i < possibleWeight.length; ++i){
            if(!possibleWeight[i]) ans++;
        }

        System.out.println(ans);
    }

    // 3. Bit Masking 을 이용한 부분집합 구하기
    public static void subset(){
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i < (1<<N); ++i){
            list.clear();
            for(int j = 0; j < N; ++j){
                if((i & (1 << j)) == 0) continue;
                list.add(weight[j]);
            }

            // 부분집합을 구했다면, '+'와 '-'를 중복순열로 배열하여 여러가지 경우의 수를 구해본다.
            int size = list.size()-1;
            permutationWithRepetition(list, size, new char[size]);
        }
    }

    // 3-1. '+'와 '-'의 중복순열을 구하는 함수
    public static void permutationWithRepetition(List<Integer> list, int cnt, char[] selected){
        if(cnt == 0){
            int size = list.size();
            if(size == 1) { // 원소가 한 개밖에 없다면 check 후 다음으로 진행
                possibleWeight[list.get(0)] = true;
                return;
            }

            // 하나의 경우의 수인 '수의 배열'과 '기호의 조합'을 연산한다.
            int sum = list.get(0);
            for(int i = 0; i < size-1; ++i){
                int next = list.get(i+1);
                if(selected[i] == '+') sum += next;
                else sum -= next;
            }

            sum = Math.abs(sum); // 절대값으로 나타내어야 한다.( 1 - 3 = -2 이지만, 실제로는 1 + ㅁ = 3 으로 나타내지기 때문)
            if(possibleWeight[sum]) return; // 이미 등록되어 있다면 pass

            possibleWeight[sum] = true; // 새로운 숫자라면 등록
            return;
        }

        for(int i = 0; i < OPERATION.length; ++i){
            selected[selected.length - cnt] = OPERATION[i];
            permutationWithRepetition(list, cnt-1, selected);
        }
    }
}