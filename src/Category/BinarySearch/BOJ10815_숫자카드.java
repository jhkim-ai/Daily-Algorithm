package Category.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10815_숫자카드 {

    private static int N, M;
    private static int[] card1, card2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        card1 = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; ++i){
            card1[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        card2 = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < M; ++i){
            card2[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(card1);
        outer:
        for(int i = 0; i < M; ++i){
            int left = 0;
            int right = N-1;
            while(left <= right){
                int mid = (left + right) / 2;

                if(card1[mid] == card2[i]){
                    sb.append("1 ");
                    continue outer;
                }

                if(card1[mid] > card2[i]){
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            sb.append("0 ");
        }
        System.out.println(sb);
    }
}
