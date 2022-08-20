package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class _11_BOJ14889_스타트와링크 {

    private static int N, ans;
    private static int[][] ability;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        ability = new int[N][N];
        ans = Integer.MAX_VALUE;

        for(int y = 0; y < N; ++y){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x){
                ability[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        combination(N/2, new HashSet<Integer>(), 0);
        System.out.println(ans);
    }

    public static void combination(int cnt, Set<Integer> selected, int startIdx){
        if(cnt == 0){
            List<Integer> link = new ArrayList<>();
            List<Integer> start = new ArrayList<>();
            for(int i = 0; i < N; ++i){
                if(selected.contains(i)) link.add(i);
                else start.add(i);
            }
            ans = Math.min(ans, Math.abs(getScore(link) - getScore(start)));
            return;
        }

        for(int i = startIdx; i < N; ++i){
            selected.add(i);
            combination(cnt - 1, selected, i + 1);
            selected.remove(i);
        }
    }

    public static int getScore(List<Integer> list){
        int sum = 0;
        int size = list.size();
        for(int i = 0; i < size - 1; ++i){
            for(int j = i + 1; j < size; ++j){
                sum += ability[list.get(i)][list.get(j)];
                sum += ability[list.get(j)][list.get(i)];
            }
        }

        return sum;
    }
}
