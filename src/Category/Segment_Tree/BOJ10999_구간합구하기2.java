package Category.Segment_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10999_구간합구하기2 {

    private static int N, M, K;
    private static int height, cntLeafNode;
    private static long[] arr, tree, lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        for(int idx = 1; idx <= N; ++idx) {
            arr[idx] = Long.parseLong(br.readLine());
        }

        setTreeInfo();
        makeSegmentTree(1, 1, cntLeafNode);

        int cnt = M + K;
        while(cnt-- > 0){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 1){
                long d = Long.parseLong(st.nextToken());
                update(1, 1, cntLeafNode, b, c, d);
            } else {
                sb.append(query(1, 1, cntLeafNode, b, c)).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static void propagate(int node, int left, int right) {
        if(lazy[node] == 0) return;

        tree[node] += (right - left + 1) * lazy[node];
        if(left != right) {
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }
        lazy[node] =0;
    }

    public static void update(int node, int left, int right, int tLeft, int tRight, long diff) {

        propagate(node, left, right);

        if(left > tRight || right < tLeft) return;
        if(tLeft <= left && right <= tRight) {
            tree[node] += (right - left + 1) * diff;
            if(left != right){
                lazy[node * 2] += diff;
                lazy[node * 2 + 1] += diff;
            }
            return;
        }

        int mid = (left + right) / 2;
        update(node * 2, left, mid, tLeft, tRight, diff);
        update(node * 2 + 1, mid + 1, right, tLeft, tRight, diff);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    public static long query(int node, int left, int right, int tLeft, int tRight) {

        propagate(node, left, right);

        if(left > tRight || right < tLeft) return 0;
        if(tLeft <= left && right <= tRight) return tree[node];

        int mid = (left + right) / 2;
        long sum = query(node * 2, left, mid, tLeft, tRight);
        sum += query(node * 2 + 1, mid + 1, right, tLeft, tRight);

        return sum;
    }

    public static long makeSegmentTree(int node, int left, int right) {
        if(left == right) {
            if(left <= N) {
                return tree[node] = arr[left];
            }
            return tree[node] = 0;
        }

        int mid = (left + right) / 2;
        tree[node] = makeSegmentTree(node * 2, left, mid);
        return tree[node] += makeSegmentTree(node * 2 + 1, mid + 1, right);
    }

    public static void setTreeInfo() {
        height = 1;
        cntLeafNode = 1;

        while(cntLeafNode <= N) {
            cntLeafNode = cntLeafNode << 1;
            ++height;
        }

        tree = new long[1 << height];
        lazy = new long[1 << height];
    }
}
