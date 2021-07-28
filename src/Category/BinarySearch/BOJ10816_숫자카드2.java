package Category.BinarySearch;

import java.util.*;
import java.io.*;

public class BOJ10816_숫자카드2 {

    private static int N, M;
    private static HashMap<Integer, Integer> hashMap;
    private static int[] count;
    private static StringBuilder sb;
    private static BufferedReader br;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

//        solution1(); // HashMap 사용
        solution2(); // counting 정렬 사용
        System.out.println(sb);
    }

    // HashMap 사용용
   public static void solution1() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        hashMap = new HashMap<>();
        for(int i = 0; i < N; ++i){
            int num = Integer.parseInt(st.nextToken());
            if(!hashMap.containsKey(num)) {
                hashMap.put(num, 1);
                continue;
            }
            int count = hashMap.get(num);
            hashMap.replace(num, count+1);
        }

        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; ++i){
            int findNum = Integer.parseInt(st.nextToken());
            if(!hashMap.containsKey(findNum)) sb.append(0).append(" ");
            else sb.append(hashMap.get(findNum)).append(" ");
        }
    }

    public static void solution2() throws Exception{
        count = new int[20000001];
        StringTokenizer st = new StringTokenizer(br.readLine());

        while(N-- > 0) count[Integer.parseInt(st.nextToken())+10000000]++;
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while(M-- > 0){
            int findNum = Integer.parseInt(st.nextToken()) + 10000000;
            sb.append(count[findNum]).append(" ");
        }
    }
}
