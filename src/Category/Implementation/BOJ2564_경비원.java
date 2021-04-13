package Category.Implementation;

import java.io.*;
import java.util.*;

public class BOJ2564_경비원 {

    static int N, M, numOfPeople, perimeter;
    static Point[] people;
    static Point dongsu;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());           // 가로
        M = Integer.parseInt(st.nextToken());           // 세로
        numOfPeople = Integer.parseInt(st.nextToken()); // 사람 수
        perimeter = 2*(N+M);
        people = new Point[numOfPeople];

        for (int i = 0; i < numOfPeople; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int dist = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            people[i] = new Point(dist, dir);
        }
        st = new StringTokenizer(br.readLine(), " ");
        dongsu = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        // ----------- 알고리즘 시작 ----------- //

        int ansDistance = 0;
        for (int i = 0; i < numOfPeople; i++) {
            ansDistance += getDistance(people[i]);
        }
        System.out.println(ansDistance);
    }

    static int getDistance(Point man){
        int dir = man.dir;
        int dist = man.dist;
        int sum = 0;

        // 동수와 위치가 같다면
        if(dir == dongsu.dir){
            sum = Math.abs(dist - dongsu.dist);
        }
        // 동수와 위치가 다르다면
        else {
            if (dir == 1) {       // 북
                sum += M + dist + dongsu.dist;
            } else if (dir == 2) { // 남
                sum = Math.abs(dist - dongsu.dist);
            } else if (dir == 3) { // 서

            } else if (dir == 4) { // 동

            }
        }
        return Math.min(sum, perimeter-sum);
    }

    static class Point{
        int dist;
        int dir;

        public Point(int dist, int dir) {
            this.dist = dist;
            this.dir = dir;
        }
    }
}
