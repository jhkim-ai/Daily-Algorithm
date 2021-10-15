package SamsungSW_A.삼성_21년_하반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ14891_톱니바퀴 {

    private static List<Integer>[] gears;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        gears = new List[4];
        for (int i = 0; i < 4; i++) {
            gears[i] = new ArrayList<>();
            String tmp = br.readLine();
            for (int j = 0; j < 8; j++) {
                int num = tmp.charAt(j) - '0';
                gears[i].add(num);
            }
        }

        // 실행
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            int[] dirs = getDir(num, dir);
            rotate(dirs);
            System.out.println("dir: " + Arrays.toString(dirs));
            for (int idx = 0; idx < 4; idx++) {
                System.out.println(idx + "th , arr:" + gears[idx]);
            }
            System.out.println("====================");
        }

        System.out.println(getScore());
    }

    public static void rotate(int[] dirs){
        int idx = -1;
        for(int d : dirs){
            idx++;
            if(d == 0){  // 움직이지 않는 상태
                continue;
            } else if (d == 1){ // 시게방향
                clockwise(idx);
            } else if (d == -1){ // 반시계방향
                counterclockwise(idx);
            }
        }
    }

    public static void clockwise(int idx){
        List<Integer> gear = gears[idx];

        int lastValue = gear.remove(7);
        gear.add(0, lastValue);
    }

    public static void counterclockwise(int idx){
        List<Integer> gear = gears[idx];

        int firstValue = gear.remove(0);
        gear.add(firstValue);
    }

    public static int[] getDir(int num, int dir) {
        List<Integer> mainGear = gears[num];
        int left = mainGear.get(6);
        int right = mainGear.get(2);
        int[] dirs = new int[4];
        boolean flag = false;
        dirs[num] = dir;

        // 오른쪽
        for (int i = num + 1; i < 4; i++) {
            int leftOfNextGear = gears[i].get(6);
            if (right != leftOfNextGear) {
                dirs[i] = dirs[i - 1] * -1;
                right = gears[i].get(2);
                flag =true;
            } else {
                break;
            }
        }

        // 왼쪽
        for (int i = num - 1; i >= 0; i--) {
            int rightOfPrevGear = gears[i].get(2);
            if (left != rightOfPrevGear) {
                dirs[i] = dirs[i + 1] * -1;
                left = gears[i].get(6);
                flag = true;
            } else {
                break;
            }
        }

        if(!flag){
            dirs[num] = 0;
        }

        return dirs;
    }

    public static int getScore(){
        int sum = 0;
        int val = 1;
        for (int i = 0; i < 4; i++) {
            if(gears[i].get(0) == 1){
                sum += val;
            }
            val *= 2;
        }
        return sum;
    }
}

//11001001
//10100110
//11100011
//01010101
//1
//2 1
