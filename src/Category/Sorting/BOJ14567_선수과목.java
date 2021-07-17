package Category.Sorting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14567_선수과목 {

    private static int N, M;
    private static int[] arr;
    private static Subject[] subjects;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        subjects = new Subject[M];
        Arrays.fill(arr, 1);
        arr[0] = 0;

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            subjects[m] = new Subject(a, b);
        }

        Arrays.sort(subjects);

        for(int m = 0; m < M; ++m){
            Subject subject = subjects[m];
            int a = subject.before;
            int b = subject.after;
            int cnt = arr[a] + 1;
            arr[b] = Math.max(arr[b], cnt);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
    }

    static class Subject implements Comparable<Subject>{
        int before;
        int after;

        public Subject(int before, int after) {
            this.before = before;
            this.after = after;
        }

        @Override
        public int compareTo(Subject s){
            if(this.before == s.before){
                return Integer.compare(this.after, s.after);
            }
            return Integer.compare(this.before, s.before);
        }

        @Override
        public String toString(){
            return "["+before + ", " + after + "]";
        }
    }
}
