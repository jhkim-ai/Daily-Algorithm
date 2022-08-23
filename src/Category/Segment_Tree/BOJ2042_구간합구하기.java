package Category.Segment_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2042_구간합구하기 {

    private static int N, M, K;
    private static int height, cntLeafNode;
    private static long[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        for(int idx = 1; idx <= N; ++idx){
            arr[idx] = Long.parseLong(br.readLine());
        }

        setTreeInfo();
        makeSegmentTree(1, 1, cntLeafNode);

        int cnt = M + K;
        while(cnt-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1) {
                long diff = c - arr[b];
                arr[b] = c;
                updateTree(1, 1, cntLeafNode, b, diff);
            } else {
                sb.append(getPartialSum(1, 1, cntLeafNode, b, (int)c)).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static void setTreeInfo() {
        height = 1;
        cntLeafNode = 1;

        while(true) {
            if(N <= cntLeafNode) {
                tree = new long[1 << height];
                return;
            }

            height++;
            cntLeafNode *= 2;
        }
    }

    public static long makeSegmentTree(int node, int left, int right) {
        if(left == right){
            if(left <= N) {
                return tree[node] = arr[left];
            }
            return 0;
        }

        int mid = (left + right) / 2;
        tree[node] = makeSegmentTree(node * 2, left, mid);
        tree[node] += makeSegmentTree(node * 2 + 1, mid + 1, right);

        return tree[node];
    }

    public static long getPartialSum(int node, int left, int right, int tLeft, int tRight) {
        if(left > tRight || right < tLeft) return 0;
        if(tLeft <= left && right <= tRight) return tree[node];

        int mid = (left + right) / 2;
        long sum = getPartialSum(node * 2, left, mid, tLeft, tRight);
        sum += getPartialSum(node * 2 + 1, mid + 1, right, tLeft, tRight);

        return sum;
    }

    public static void updateTree(int node, int left, int right, int idx, long diff) {
        if(left <= idx && idx <= right) {
            tree[node] += diff;
            if(left == right) {
                return;
            }
        } else {
            return;
        }

        int mid = (left + right) / 2;
        updateTree(node * 2, left, mid, idx, diff);
        updateTree(node * 2 + 1, mid + 1, right, idx, diff);
    }
}
