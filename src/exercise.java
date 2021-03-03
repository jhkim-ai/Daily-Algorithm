// 1874, 스택 수열

import java.io.*;
import java.util.*;

public class exercise {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();
        Stack<Integer> stk = new Stack<>();

        for (int i = 0; i < N; i++) {
            queue.add(Integer.parseInt(br.readLine()));
        }

        int n = 0;
        int cnt = 0;
        while (cnt != N) {
            stk.push(++n);
            sb.append("+").append("\n");
            while (!stk.isEmpty() && stk.peek() == queue.peek()) {

                stk.pop();
                queue.poll();
                sb.append("-").append("\n");
                cnt++;
            }


            if ((!stk.isEmpty()) && stk.peek() > queue.peek()) {
                break;
            }

        }

        if (!stk.isEmpty()) {
            System.out.println("NO");
            return;
        }
        System.out.println(sb);

    }

}