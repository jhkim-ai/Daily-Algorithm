package Category.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ13866_팀나누기 {

    private static int ans;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        ans = Integer.MAX_VALUE;
        arr = new int[4];
        for(int i = 0; i < 4; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        combination(2, new HashSet<Integer>(), 0);
        System.out.println(ans);
    }

    public static void combination(int cnt, Set<Integer> selected, int startIdx){
        if(cnt == 0){
            int sumA = 0;
            int sumB = 0;
            for(int i = 0; i < 4; ++i){
                if(selected.contains(i)) sumA += arr[i];
                else sumB += arr[i];
            }

            ans = Math.min(ans, Math.abs(sumA - sumB));
            return;
        }

        for(int i = startIdx; i < 4; ++i){
            selected.add(i);
            combination(cnt-1, selected, i+1);
            selected.remove(i);
        }
    }
}
