package Category.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://bit.ly/3smBP1K
public class BOJ11000_강의실배정 {

    private static int N;
    private static int[][] schedules;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        schedules = new int[N][2];

        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            schedules[i][0] = Integer.parseInt(st.nextToken()); // start Time
            schedules[i][1] = Integer.parseInt(st.nextToken()); // end Time
        }

        Arrays.sort(schedules, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            } else {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(schedules[0][1]); // 맨 처음 강의 추가
        for (int i = 1; i < N; ++i) {
            int start = schedules[i][0];
            int end = schedules[i][1];

            if (start >= pq.peek()) {  // 강의 끝난 뒤에 진행한다면, 같은 강의실 가능
                pq.poll();             // 강의실 개수가 늘어나지 않게 poll()을 한다.
            }
            pq.offer(end);             // 강의실 개수 증가
        }

        System.out.println(pq.size()); // pq 남은 개수가 강의실의 개수다.
    }
}
