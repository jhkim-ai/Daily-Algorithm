package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_4013_특이한자석 {

    static int K;
    static Deque[] magnet;
    static ArrayList<int[]> rotationInfo;
    static List<int[]> isPossible;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; ++t) {
            K = Integer.parseInt(br.readLine());
            magnet = new Deque[4];
            rotationInfo = new ArrayList<>();

            // 자석 정보
            for (int i = 0; i < 4; ++i) {
                magnet[i] = new LinkedList<Integer>();
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 8; ++j) {
                    magnet[i].add(Integer.parseInt(st.nextToken()));
                }
            }

            // 회전 정보
            for (int i = 0; i < K; ++i) {
                st = new StringTokenizer(br.readLine(), " ");
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                rotationInfo.add(new int[]{num - 1, dir});
            }

            isPossible = new ArrayList<>();
            for (int[] rotation : rotationInfo) {
                isPossible.clear();

                // 회전 가능 자석 찾기
                // param 1: 자석 번호, param 2: 방향
                validMagnet(rotation[0], rotation[1]);


            }
        }
    }

    static void validMagnet(int num, int dir) {
        isPossible.add(new int[]{num, dir});

        if (num != 0 && isDifferentPoll(num - 1, num)) {
            if (!isPossible.contains(num - 1)) {
                validMagnet(num - 1, dir * -1);
            }
        }

        if (num != 3 && isDifferentPoll(num, num + 1))
            if (!isPossible.contains(num + 1))
                validMagnet(num + 1, dir * -1);
    }

    static boolean isMagnet(int num) {
        return num >= 0 && num < 4;
    }

    // a : 왼쪽, b : 왼쪽
    static boolean isDifferentPoll(int a, int b) {

        int aPole = -1, bPole = -1;

        int index = 0;
        for (Object pole : magnet[a]) {
            if (index == 2) {
                aPole = (int) pole;
                break;
            }
            ++index;
        }

        index = 0;
        for (Object pole : magnet[b]) {
            if (index == 6) {
                bPole = (int) pole;
                break;
            }
            ++index;
        }

        return aPole == bPole ? false : true;
    }

    static String input = "1\n" +
            "2\n" +
            "0 0 1 0 0 1 0 0\n" +
            "1 0 0 1 1 1 0 1\n" +
            "0 0 1 0 1 1 0 0\n" +
            "0 0 1 0 1 1 0 1\n" +
            "1 1\n" +
            "3 -1";
}
