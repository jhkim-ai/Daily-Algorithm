package StepByStep._2022._22_02_19;

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

public class BOJ7662_이중우선순위큐 {

    private static int K;
    private static PriorityQueue<Integer> maxPq;
    private static PriorityQueue<Integer> minPq;
    private static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; ++t){
            K = Integer.parseInt(br.readLine());
            maxPq = new PriorityQueue<>(Collections.reverseOrder());
            minPq = new PriorityQueue<>();
            map = new HashMap<>();

            while(K-- > 0){
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                char command = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());

                if(command == 'I'){
                    add(num);
                } else {
                    delete(num);
                }
            }

        }
        System.out.println(sb);
    }

    public static void add(int num){
        maxPq.add(num);
        minPq.add(num);
        if(!map.containsKey(num)){
            map.put(num, 1);
        } else {
            map.put(map.get(num), 1);
        }
    }

    public static void delete(int num){

    }

}
