package Category.Segment_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10999_구간합구하기2 {

    private static int N, M, K;
    private static int height, cntLeafNode;
    private static long[] input, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        input = new long[N + 1];
        for(int idx = 1; idx <= N; ++idx) {
            input[idx] = Long.parseLong(br.readLine());
        }

        setTreeInfo();
        makeSegmentTree(1, 1, cntLeafNode);

        int cnt = M + K;
        while(cnt-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 1) {
                long d = Long.parseLong(st.nextToken());
                update(1, 1, cntLeafNode, b, c, d);
            } else {
                sb.append(getPartialSum(1, 1, cntLeafNode, b, c));
                sb.append("\n");
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
        if(left == right) {
            if(left <= N) {
                return tree[node] = input[left];
            }
            return 0;
        }

        int mid = (left + right) / 2;
        tree[node] = makeSegmentTree(node * 2, left, mid);
        return tree[node] += makeSegmentTree(node * 2 + 1, mid + 1, right);
    }

    public static long update(int node, int left, int right, int tLeft, int tRight, long diff) {

        if(left > tRight || right < tLeft) return tree[node];
        if(left == right) return tree[node] += diff;

        int mid = (left + right) / 2;
        tree[node] = update(node * 2, left, mid, tLeft, tRight, diff);
        return tree[node] += update(node * 2 + 1, mid + 1, right, tLeft, tRight, diff);
    }

    public static long getPartialSum(int node, int left, int right, int tLeft, int tRight) {
        if(left > tRight || right < tLeft) {
            return 0;
        }
        if(tLeft <= left && right <= tRight) {
            return tree[node];
        }

        int mid = (left + right) / 2;
        long sum = getPartialSum(node * 2, left, mid, tLeft, tRight);
        sum += getPartialSum(node * 2 + 1, mid + 1, right, tLeft, tRight);

        return sum;
    }
}
