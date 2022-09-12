package Category.Segment_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2357_최솟값과최댓값 {

    private static int N, M;
    private static long[] arr, minTree, maxTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new long[N + 1];

        for(int idx = 1; idx <= N; ++idx){
            arr[idx] = Long.parseLong(br.readLine());
        }

        minTree = new long[N * 4];
        maxTree = new long[N * 4];

        makeMinTree(1, 1, N);
        makeMaxTree(1, 1, N);

        while(M-- > 0){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            long minVal = getPartialMinValue(1, 1, N, a, b);
            long maxVal = getPartialMaxValue(1, 1, N, a, b);

            // sb.append(String.format("%d %d\n", minVal, maxVal));    // 속도: 3140 ms
            sb.append(minVal).append(" ").append(maxVal).append("\n"); // 속도: 764 ms
        }

        System.out.println(sb.toString());
    }

    public static long makeMaxTree(int node, int left, int right) {
        if(left == right) return maxTree[node] = arr[left];

        int mid = (left + right) / 2;
        long a = makeMaxTree(node * 2, left, mid);
        long b = makeMaxTree(node * 2 + 1, mid + 1, right);

        return maxTree[node] = Math.max(a, b);
    }

    public static long getPartialMaxValue(int node, int left, int right, int tLeft, int tRight){
        if(left > tRight || right < tLeft) return 0;
        if(tLeft <= left && right <= tRight) return maxTree[node];

        int mid = (left + right) / 2;
        long a = getPartialMaxValue(node * 2, left, mid, tLeft, tRight);
        long b = getPartialMaxValue(node * 2 + 1, mid + 1, right, tLeft, tRight);

        return Math.max(a, b);
    }

    public static long makeMinTree(int node, int left, int right) {
        if(left == right) return minTree[node] = arr[left];

        int mid = (left + right) / 2;
        long a = makeMinTree(node * 2, left, mid);
        long b = makeMinTree(node * 2 + 1, mid + 1, right);

        return minTree[node] = Math.min(a, b);
    }

    public static long getPartialMinValue(int node, int left, int right, int tLeft, int tRight){
        if(tRight < left || right < tLeft) return 1000000001;
        if(tLeft <= left && right <= tRight) return minTree[node];

        int mid = (left + right) / 2;
        long a = getPartialMinValue(node * 2, left, mid, tLeft, tRight);
        long b = getPartialMinValue(node * 2 + 1, mid + 1, right, tLeft, tRight);

        return Math.min(a, b);
    }
}
