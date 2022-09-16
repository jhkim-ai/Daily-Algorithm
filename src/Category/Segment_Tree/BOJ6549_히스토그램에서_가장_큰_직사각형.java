package Category.Segment_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6549_히스토그램에서_가장_큰_직사각형 {

    private static int N;
    private static long ans;
    private static long[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());

            if(N == 0) break;

            arr = new long[N + 1];
            tree = new long[4 * N];
            ans = Integer.MIN_VALUE;

            for(int idx = 1; idx <= N; ++idx){
                arr[idx] = Long.parseLong(st.nextToken());
            }

            makeTree(1, 1, N);
            getMaxSize(1, 1, N);

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static long makeTree(int node, int left, int right) {
        if(left == right) return tree[node] = arr[left];

        int mid = (left + right) / 2;
        long a = makeTree(node * 2, left, mid);
        long b = makeTree(node * 2 + 1, mid + 1, right);

        return tree[node] = Math.min(a, b);
    }

    public static void getMaxSize(int node, int left, int right) {

        if(left != right) {
            int mid = (left + right) / 2;
            getMaxSize(node * 2, left, mid);
            getMaxSize(node * 2 + 1, mid + 1, right);
        }

        if(tree[node] == 0) return;
        ans = Math.max(ans, (right - left + 1) * tree[node]);
//        System.out.println("node: " + node + ", val: " + tree[node] + ", ans: " + ans);
    }
}
