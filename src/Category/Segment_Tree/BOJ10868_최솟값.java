package Category.Segment_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10868_최솟값 {

    private static int N, M;
    private static int height, cntLeafNode;
    private static int[] input, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N+1];

        for(int idx = 1; idx <= N; ++idx) {
            input[idx] = Integer.parseInt(br.readLine());
        }

        setTreeInfo();
        makeSegmentTree(1, 1, cntLeafNode);

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(getMinValue(1, 1, cntLeafNode, a, b)).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void setTreeInfo() {
        height = 1;
        cntLeafNode = 1;

        while(true) {
            if(N <= cntLeafNode) {
                tree = new int[(int)Math.pow(2, height)];
                return;
            }
            height++;
            cntLeafNode *= 2;
        }
    }

    public static int makeSegmentTree(int node, int left, int right) {
        if(left == right) {
            if(left <= N) {
                return tree[node] = input[left];
            }

            return Integer.MAX_VALUE;
        }

        int mid = (left + right) / 2;
        tree[node] = makeSegmentTree(node * 2, left, mid);
        tree[node] = Math.min(tree[node], makeSegmentTree(node * 2 + 1, mid + 1, right));

        return tree[node];
    }

    public static int getMinValue(int node, int left, int right, int tLeft, int tRight) {
        if(left > tRight || right < tLeft) {
            return Integer.MAX_VALUE;
        }

        if(tLeft <= left && right <= tRight) {
            return tree[node];
        }

        int mid = (left + right) / 2;
        int val = Integer.MAX_VALUE;
        val = Math.min(val, getMinValue(node * 2, left, mid, tLeft, tRight));
        val = Math.min(val, getMinValue(node * 2 + 1, mid + 1, right, tLeft, tRight));

        return val;
    }

}
