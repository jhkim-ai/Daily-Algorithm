package Category.Sorting;

import java.util.*;
import java.io.*;

public class BOJ1015_수열정렬 {

    private static int N;
    private static int[] A, P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        P = new int[N];
        Element[] elements = new Element[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
            elements[i] = new Element(i, A[i]);
        }

        Arrays.sort(elements);
        for (int i = 0; i < N; ++i) {
            P[elements[i].index] = i;
        }

        for (int p : P) {
            sb.append(p).append(" ");
        }
        System.out.println(sb);
    }

    static class Element implements Comparable<Element> {

        int index;
        int value;

        public Element(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Element e) {
            return Integer.compare(this.value, e.value);
        }
    }
}
