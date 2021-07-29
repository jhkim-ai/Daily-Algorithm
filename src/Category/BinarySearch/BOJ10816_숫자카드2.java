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

//        solution1(); // HashMap 사용 : 245816KB 1288ms
//        solution2(); // counting 정렬 사용 : 255372KB 880ms
        solution3(); // BinarySearch 사용 : 185324KB 1268ms
        System.out.println(sb);
    }

    // HashMap 사용용 : 1288ms
    public static void solution1() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        hashMap = new HashMap<>();
        for (int i = 0; i < N; ++i) {
            int num = Integer.parseInt(st.nextToken());
            if (!hashMap.containsKey(num)) {
                hashMap.put(num, 1);
                continue;
            }
            int count = hashMap.get(num);
            hashMap.replace(num, count + 1);
        }

        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; ++i) {
            int findNum = Integer.parseInt(st.nextToken());
            if (!hashMap.containsKey(findNum)) sb.append(0).append(" ");
            else sb.append(hashMap.get(findNum)).append(" ");
        }
    }

    // Counting 정렬 사용 : 880ms
    public static void solution2() throws Exception {
        count = new int[20000001];
        StringTokenizer st = new StringTokenizer(br.readLine());

        while (N-- > 0) count[Integer.parseInt(st.nextToken()) + 10000000]++;
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (M-- > 0) {
            int findNum = Integer.parseInt(st.nextToken()) + 10000000;
            sb.append(count[findNum]).append(" ");
        }
    }

    // 185324KB 1268ms
    // BinarySearch 사용 : upper_bound - lower_bound
    public static void solution3() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정수가 가진 카드 Deck
        int[] deck = new int[N];
        for(int i = 0; i < N; ++i)
            deck[i] = Integer.parseInt(st.nextToken());

        // 확인할 카드 Deck
        M = Integer.parseInt(br.readLine());
        int[] findNum = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; ++i)
            findNum[i] = Integer.parseInt(st.nextToken());

        // 정수가 가진 카드 Deck 을 정렬
        Arrays.sort(deck);
        for(int num : findNum) {
            // upperBound(K값 초과가 처음 나오는 곳) - lowerBound(K값이 처음 나오는 곳) = K의 개수
            int start = lowerBound(deck, num);
            int end = upperBound(deck, num);
            sb.append(end - start).append(" ");
        }
    }

    // target 값이 처음 나오는 위치
    public static int lowerBound(int[] deck, int target){
        int left = 0;
        int right = deck.length;
        while(left < right){
            int mid = (left + right) / 2;

            if(deck[mid] >= target) right = mid;
            else left = mid + 1;
        }
        return right;
    }

    // target 초과값이 처음 나오는 위치
    public static int upperBound(int[] deck, int target){
        int left = 0;
        int right = deck.length;

        while(left < right){
            int mid = (left + right) / 2;

            if(deck[mid] <= target) left = mid + 1;
            else right = mid;
        }

        return right;
    }
}
