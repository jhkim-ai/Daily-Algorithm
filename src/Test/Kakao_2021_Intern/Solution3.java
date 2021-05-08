package Test.Kakao_2021_Intern;

import java.util.*;

public class Solution3 {

    static int N, K;
    static char[] arr;
    static Stack<Integer> stack;

    public static void main(String[] args) {
        N = 8;
        K = 2;
        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};

        // 1. init (Idea. 선형으로 다루기)
        stack = new Stack<>();
        arr = new char[N];
        for (int i = 0; i < N; ++i) {
            arr[i] = 'O';
        }

        // 2. start command
        startCommand(cmd);

        // 3. answer
        StringBuilder sb = new StringBuilder();
        for(char c : arr)
            sb.append(c);
        System.out.println(sb);
    }

    // 2. start command
    public static void startCommand(String[] cmd){
        for (int n = 0; n < cmd.length; ++n) {
            StringTokenizer st = new StringTokenizer(cmd[n], " ");

            // 조작 키
            char key = st.nextToken().charAt(0);
            // "D", "U"일 경우
            int num = 0;
            if (st.hasMoreTokens())
                num = Integer.parseInt(st.nextToken());

            switch (key) {
                case 'U':
                    up(num);
                    break;
                case 'D':
                    down(num);
                    break;
                case 'C':
                    cancel();
                    break;
                case 'Z':
                    restore();
                    break;
            }
        }
    }

    // "U" : 위로 이동
    public static void up(int num) {
        while (num-- > 0) {
            K--;
            if (arr[K] == 'X')
                num++;
        }
    }

    // "D" : 아래로 이동
    public static void down(int num) {
        while (num-- > 0) {
            K++;
            if (arr[K] == 'X')
                num++;
        }
    }

    // "C" : 값 지우기
    public static void cancel() {
        // stack 추가 및 X 표시
        stack.push(K);
        arr[K] = 'X';

        // next : 다음 idx 탐색 + K값 보존
        int next = K;
        next++;

        while (true) {
            // 다음 행이 없을 경우, 바로 윗행 선택
            if (next >= N) {
                next = K - 1;
                while (true) {
                    // 윗행이 없을 경우 (모두 'X'인 경우) 종료
                    if(next < 0){
                        return;
                    }
                    // 알맞는 윗행 선택 시, 종료
                    if (arr[next] == 'O') {
                        K = next;
                        return;
                    }
                    // 바로 윗행이 'X'일 경우 그 윗행으로 이동
                    next--;
                }
            }
            // 알맞는 아래 행 선택 시, 종료
            if (arr[next] == 'O') {
                K = next;
                return;
            }
            // 아래 행이 'X' 경우 그 아래 행 탐색
            next++;
        }
    }

    // "Z" : 최근에 삭제된 행 복구
    public static void restore() {
        arr[stack.pop()] = 'O';
    }
}
