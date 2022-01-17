package StepByStep.day210423;

import java.util.*;
import java.io.*;

public class BOJ1774_우주신과의교감 {

    static int N, M;
    static List<Point> locList;
    static List<Edge> edgeList;
    static int[] root;
    static int[] rank;
    static double ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        locList = new ArrayList<>();
        edgeList = new ArrayList<>();

        for (int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            locList.add(new Point(y, x, i));
        }

        // 0. 집합 초기화
        makeSet();

        // 0-1. 제시된 간선 M개를 Union
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // Idea. MST 를 만들자 (Kruskal)

        // 1. 모든 점에 대하여 거리를 구한다.
        for (int i = 0; i < locList.size(); ++i) {
            for (int j = i + 1; j < locList.size(); ++j) {
                Point p = locList.get(i);
                Point q = locList.get(j);
                double weight = setDistance(p, q);
                Edge edge = new Edge(p.num, q.num, weight);
                edgeList.add(edge);
            }
        }

        // 2. 거리에 대한 오름차순 정렬
        Collections.sort(edgeList);

        // 3. 가장 작은 edge 부터 MST를 완성해 나간다.
        for (Edge e : edgeList) {
            if (N - 1 == M)
                break;
            if (!union(e.start, e.end)) continue;
            ans += e.weight;
            ++M;
        }
        System.out.printf("%.2f", ans);
    }

    static void makeSet() {
        root = new int[N + 1];
        rank = new int[N + 1];
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }
    }

    static int findSet(int a) {
        if (root[a] == a)
            return a;
        return root[a] = findSet(root[a]);
    }

    static boolean union(int a, int b) {
        a = findSet(a);
        b = findSet(b);

        if (a == b) return false;

        if (rank[a] < rank[b])
            root[a] = b;
        else {
            root[b] = a;
            if (rank[a] == rank[b])
                rank[a]++;
        }
        return true;
    }

    static double setDistance(Point p, Point q) {

        return Math.sqrt(Math.pow(p.x - q.x, 2) + Math.pow(p.y - q.y, 2));
    }

    static class Point {
        int y;
        int x;
        int num;

        public Point(int y, int x, int num) {
            this.y = y;
            this.x = x;
            this.num = num;
        }
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        double weight;

        public Edge(int start, int end, double weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Double.compare(this.weight, e.weight);
        }

        @Override
        public String toString() {
            return weight + "";
        }
    }
}
