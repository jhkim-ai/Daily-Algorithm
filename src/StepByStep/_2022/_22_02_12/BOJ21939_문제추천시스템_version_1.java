package StepByStep._2022._22_02_12;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ21939_문제추천시스템_version_1 {

    private static int N, M;
    private static List<Question> list;
    private static PriorityQueue<Question> heap;
    private static Map<Integer, Integer> map;
    private static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        init();
        run();
        System.out.println(sb);
    }

    // 1. 초기화
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        heap = new PriorityQueue<>();
        map = new HashMap<>();
        for(int i = 0; i < N; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int number = Integer.parseInt(st.nextToken());
            int rank = Integer.parseInt(st.nextToken());
            map.put(number, rank);
            list.add(new Question(number, rank));
        }
        heapSort();
        M = Integer.parseInt(br.readLine());
    }

    public static void run() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < M; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();
            if(command.equals("add")){
                int number = Integer.parseInt(st.nextToken());
                int rank = Integer.parseInt(st.nextToken());
                add(number, rank);
            } else if(command.equals("recommend")){
                int idx = Integer.parseInt(st.nextToken());
                recommend(idx);
            } else if(command.equals("solved")){
                int number = Integer.parseInt(st.nextToken());
                solved(number);
            }
        }
    }

    public static void add(int number, int rank){
        map.put(number, rank);
        list.add(new Question(number, rank));
        Collections.sort(list);
    }

    public static void recommend(int idx){
        Question question = list.get(0);
        if(idx == 1){
            question = list.get(list.size()-1);
            list.remove(list.size()-1);
        } else {
            list.remove(0);
        }

        map.remove(question.number);
        sb.append(question.number).append("\n");
    }

    public static void solved(int number){

    }

    public static void heapSort(){

        list.add(heap.poll());
    }

    public static class Question implements Comparable<Question>{
        int number;
        int rank;

        public Question(int number, int rank){
            this.number = number;
            this.rank = rank;
        }

        @Override
        public int compareTo(Question q){
            if(this.rank == q.rank){
                return Integer.compare(this.number, q.number);
            }
            return Integer.compare(this.rank, q.rank);
        }
    }
}
