package Category.MST;

import java.util.*;
import java.io.*;

public class BOJ16398_행성연결 {

    private static int N;
    private static Node[] nodes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N+1];

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            nodes[a] = new Node(a, b, w);
        }

    }

    static class Node implements Comparable<Node>{
        int num;
        int next;
        int weight;

        public Node(int num, int next, int weight) {
            this.num = num;
            this.next = next;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n){
            return Integer.compare(this.weight, n.weight);
        }
    }
}
