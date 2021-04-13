package Category.Implementation;

import sun.font.FontRunIterator;

import java.io.*;
import java.util.*;

public class BOJ2564_경비원 {

    static int N, M, numOfPeople;
    static int donggeun;

    static int[] people;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());           // 가로
        M = Integer.parseInt(st.nextToken());           // 세로
        numOfPeople = Integer.parseInt(br.readLine()); // 사람 수
        people = new int[numOfPeople];              // 4각형의 둘레를 직선으로

        int dist = -1;
        int dir = -1;
        int location = -1;

        // 직선에 1 ~ numOfPeople 명까지 위치를 저장
        for (int i = 0; i < numOfPeople; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            dir = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());
            location = setLocation(dist, dir);
            people[i] = location;
        }

        // 직선으로 동근이 위치 저장
        st = new StringTokenizer(br.readLine(), " ");
        dir = Integer.parseInt(st.nextToken());
        dist = Integer.parseInt(st.nextToken());
        donggeun = setLocation(dist, dir);

        // ----------- 알고리즘 시작 ----------- //
        int ans = 0;
        for(int person : people){       // 동근이 위치로부터 각각의 거리의 합 구하기
            ans += getDistance(person);
        }

        // ----------- 출력 ----------- //
        System.out.println(ans);
    }

    // 동, 남, 서, 북 순으로 위치를 직선화하여 저장하는 함수
    static int setLocation(int dist, int dir) {
        int location = 0;

        if (dir == 4) {         // 동
            location = dist;
        }
        else if (dir == 2){     // 남
            location = M + N - dist;
        }
        else if (dir == 3){     // 서
            location = M + N + (M - dist);
        }
        else{                   // 북
            location = N + (2*M) + dist;
        }
        return location;
    }

    // 한 사람과 동근이의 거리 구하기
    static int getDistance(int person) {
        int distance = Math.abs(donggeun - person);

        // 시계 방향 혹은 반시계 방향 중 가까운 거리를 선택
        return Math.min(distance, 2*(N+M) - distance);
    }
}
