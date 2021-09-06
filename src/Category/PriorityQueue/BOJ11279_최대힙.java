package Category.PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ11279_최대힙 {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o2, o1);
        });
        N = Integer.parseInt(br.readLine());

        while(N-- > 0){
            int num = Integer.parseInt(br.readLine());

            if(num == 0){
                if(pq.isEmpty()){
                    sb.append(0);
                } else {
                    sb.append(pq.poll());
                }
                sb.append("\n");
            } else {
                pq.offer(num);
            }
        }
        System.out.println(sb);
    }
}
