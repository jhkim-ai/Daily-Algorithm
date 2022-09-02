package Category.Segment_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10868_최솟값 {

    private static int N, M;
    private static int height, cntLeafNode;
    private static int[] arr;
    private static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];

        for(int idx = 1; idx <= N; ++idx) {
            arr[idx] = Integer.parseInt(br.readLine());
        }

        setInfo();

        makeSegmentTree(1, 1, cntLeafNode);

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(query(1, 1, cntLeafNode, start, end)).append("\n");
        }

        System.out.println(sb.toString());

//        System.out.println(Arrays.toString(arr));
//        update(1, 1, cntLeafNode, 8, 77);
//        postOrder(1);
    }

    public static int makeSegmentTree(int node, int left, int right) {
        if(left == right) {
            if(left <= N) {
                return tree[node] = arr[left];
            } else {
                return tree[node] = Integer.MAX_VALUE;
            }
        }

        int mid = (left + right) / 2;
        int a = makeSegmentTree(node * 2, left, mid);
        int b = makeSegmentTree(node * 2 + 1, mid + 1, right);
        return tree[node] = Math.min(a, b);
    }

    public static int query(int node, int left, int right, int tLeft, int tRight) {
        if(left > tRight || right < tLeft) return Integer.MAX_VALUE;
        if(tLeft <= left && right <= tRight) return tree[node];

        int mid = (left + right) / 2;
        int a = query(node * 2, left, mid, tLeft, tRight);
        int b = query(node * 2 + 1, mid + 1, right, tLeft, tRight);

        return Math.min(a, b);
    }

    public static int update(int node, int left, int right, int idx, int newNum) {
                System.out.println("hi");
        if(left == right) {
            if(left == idx){
                tree[node] = newNum;
            }
            return tree[node];
        }

        if(left <= idx && idx <= right){
            int mid = (left + right) / 2;
            int a = update(node * 2, left, mid, idx, newNum);
            int b = update(node * 2 + 1, mid + 1, right, idx, newNum);

            return tree[node] = Math.min(a, b);
        }

        return tree[node];
    }

    public static void setInfo() {
        height = 1;
        cntLeafNode = 1;
        while(cntLeafNode <= N) {
            cntLeafNode = cntLeafNode << 1;
            height++;
        }
        tree = new int[1 << height];
    }

    public static void postOrder(int node) {
        if(node >= 1 << height) return;
        postOrder(node*2);
        postOrder(node*2+1);
        System.out.println("node: " + node + ", val:" + tree[node]);
    }
}

