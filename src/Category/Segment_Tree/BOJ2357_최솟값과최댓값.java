package Category.Segment_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2357_최솟값과최댓값 {

    private static final int MIN_VALUE = Integer.MIN_VALUE;
    private static final int MAX_VALUE = Integer.MAX_VALUE;

    private static int N, M;
    private static int height, cntLeafNode;
    private static int[] arr, minTree, maxTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];

        for(int idx = 1; idx <= N; ++idx){
            arr[idx] = Integer.parseInt(br.readLine());
        }

        setTreeInfo();

        makeMinTree(1, 1, cntLeafNode);
        makeMaxTree(1, 1, cntLeafNode);

        while(M-- > 0){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int minVal = getPartialMinValue(1, 1, cntLeafNode, a, b);
            int maxVal = getPartialMaxValue(1, 1, cntLeafNode, a, b);

            sb.append(String.format("%d %d\n", minVal, maxVal));
        }

        System.out.println(sb.toString());
    }

    public static int makeMaxTree(int node, int left, int right) {
        if(left == right) {
            if(left <= N) return maxTree[node] = arr[left];
            else return MIN_VALUE;
        }

        int mid = (left + right) / 2;
        int a = makeMaxTree(node * 2, left, mid);
        int b = makeMaxTree(node * 2 + 1, mid + 1, right);

        return maxTree[node] = Math.max(a, b);
    }

    public static int getPartialMaxValue(int node, int left, int right, int tLeft, int tRight){
        if(left > tRight || right < tLeft) return MIN_VALUE;
        if(tLeft <= left && right <= tRight) return maxTree[node];

        int mid = (left + right) / 2;
        int a = getPartialMaxValue(node * 2, left, mid, tLeft, tRight);
        int b = getPartialMaxValue(node * 2 + 1, mid + 1, right, tLeft, tRight);

        return Math.max(a, b);
    }

    public static int makeMinTree(int node, int left, int right) {
        if(left == right) {
            if(left <= N) return minTree[node] = arr[left];
            else return MAX_VALUE;
        }

        int mid = (left + right) / 2;
        int a = makeMinTree(node * 2, left, mid);
        int b = makeMinTree(node * 2 + 1, mid + 1, right);

        return minTree[node] = Math.min(a, b);
    }

    public static int getPartialMinValue(int node, int left, int right, int tLeft, int tRight){
        if(left > tRight || right < tLeft) return MAX_VALUE;
        if(tLeft <= left && right <= tRight) return minTree[node];

        int mid = (left + right) / 2;
        int a = getPartialMinValue(node * 2, left, mid, tLeft, tRight);
        int b = getPartialMinValue(node * 2 + 1, mid + 1, right, tLeft, tRight);

        return Math.min(a, b);
    }

    public static void setTreeInfo() {
        height = 1;
        cntLeafNode = 1;

        while(cntLeafNode < N) {
            ++height;
            cntLeafNode = cntLeafNode << 1;
        }

        minTree = new int[1 << height];
        maxTree = new int[1 << height];
    }
}
