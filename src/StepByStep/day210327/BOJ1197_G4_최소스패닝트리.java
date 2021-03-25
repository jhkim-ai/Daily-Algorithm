package StepByStep.day210327;

import java.io.*;
import java.util.*;

public class BOJ1197_G4_최소스패닝트리 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        Node[] graph = new Node[v];

        for (int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine(), " ");
//            graph[i] = new Node(i+1, )
        }
    }

    static class Node{
        int num;
        int cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}
