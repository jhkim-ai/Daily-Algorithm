package StepByStep._2022._22_02_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1946_신입사원 {
    private static int N;
    private static Grade[] grades;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; ++t){
            N = Integer.parseInt(br.readLine());
            grades = new Grade[N];
            for(int i = 0; i < N; ++i) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int documentScore = Integer.parseInt(st.nextToken());
                int interviewScore = Integer.parseInt(st.nextToken());
                grades[i] = new Grade(documentScore, interviewScore);
            }
            Arrays.sort(grades);
            sb.append(getCount()).append("\n");
        }
        System.out.println(sb);
    }

    public static int getCount(){
        int count = 1;
        int interviewScore = grades[0].interview;
        for(Grade grade : grades){
            if(interviewScore > grade.interview){
                interviewScore = grade.interview;
                count++;
            }
        }
        return count;
    }

    public static class Grade implements Comparable<Grade>{
        int document;
        int interview;

        public Grade(int document, int interview){
            this.document = document;
            this.interview = interview;
        }

        @Override
        public int compareTo(Grade grade){
            return Integer.compare(this.document, grade.document);
        }
    }
}
