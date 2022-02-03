package Category.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ13335_트럭 {

    private static int N, W, L, time;
    private static Queue<Integer> qTrucks;
    private static Queue<Integer> qBridge;

    private static int sumWeight;    	  // 다리에 있는 트럭들의 무게
    private static int cntEndTruck;  	  // 도착한 트럭 수

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        qTrucks = new LinkedList<>();
        qBridge = new LinkedList<>();

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; ++i){
            qTrucks.offer(Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < W; ++i){
            qBridge.offer(0);
        }

        sumWeight = 0;    // 다리에 있는 트럭들의 무게 합
        cntEndTruck = 0;  // 도착한 트럭 수

        run();

        System.out.println(time);
    }

    public static void run(){

        while(true){
            ++time;
            int nextSumWeight = sumWeight - qBridge.peek(); // 다음 시간에 다리에 있는 트럭들의 무게 합
            if(!qTrucks.isEmpty())
                nextSumWeight += qTrucks.peek();

            if(isOverWeight(nextSumWeight)) { // 다리에 무게가 over 된다면 단순히 움직이기만
                int out = qBridge.poll();
                if(out != 0) {
                    cntEndTruck++;
                    sumWeight -= out;
                }
                qBridge.offer(0);
                continue;
            } else {
                int in = 0;
                if(!qTrucks.isEmpty()) in = qTrucks.poll();
                sumWeight += in;
                qBridge.offer(in);
                int out = qBridge.poll();
                if(out != 0){
                    sumWeight -= out;
                    cntEndTruck++;
                }
            }

            if(cntEndTruck == N) return;      // 모두 다리를 넘어왔다면 종료
        }
    }

    // 다리가 버틸 수 있는 무게가 L보다 큰가?
    public static boolean isOverWeight(int sumWeight){
        return sumWeight > L;
    }
}
