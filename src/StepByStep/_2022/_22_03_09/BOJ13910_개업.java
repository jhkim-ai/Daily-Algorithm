package StepByStep._2022._22_03_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ13910_개업 {

    private static int N, M, ans;
    private static int[] arr;
    private static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        list = new ArrayList<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
            list.add(arr[i]);
        }

        Combination(2, new int[2], 0);
        Collections.sort(list, Collections.reverseOrder());

        if(M == 1){
            ans += N / arr[0];
            if(N % arr[0] != 0) ans++;
        } else {
            int size = list.size();
            for(int start = 0; start < size; ++start){
                int restOfDish = N;

            }
        }

        System.out.println(ans);
    }

    public static void Combination(int cnt, int[] selected, int startIdx){
        if(cnt == 0){
            System.out.println(Arrays.toString(selected));
            int sum = 0;
            for(int num : selected){
                sum += num;
            }
            list.add(sum);
            return;
        }

        for(int i = startIdx; i < M; ++i){
            selected[selected.length - cnt] = arr[i];
            Combination(cnt - 1, selected, i+1);
        }
    }
}
