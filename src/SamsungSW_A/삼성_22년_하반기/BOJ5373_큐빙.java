package SamsungSW_A.삼성_22년_하반기;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BOJ5373_큐빙 {

    private static final int SIZE = 3;
    private static final char[] color = {'w', 'y', 'r', 'o', 'g', 'b'};

    private static int T, N;
    private static char[][][] cube;

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Character, Integer> map = new HashMap<>();
        map.put('U', 0);
        map.put('D', 1);
        map.put('F', 2);
        map.put('B', 3);
        map.put('L', 4);
        map.put('R', 5);

        initCube();
        rotateTop('-');
        print(0);
//        T = Integer.parseInt(br.readLine());
//        while(T-- > 0) {
//            N = Integer.parseInt(br.readLine());
//            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

//            while(st.hasMoreTokens()) {
//                String command = st.nextToken();
//                char face = command.charAt(0);
//                char dir = command.charAt(1);
//
//                switch (face) {
//                    case 'U':
//                        rotateTop(dir);
//                        break;
//                }
//
//                print(map.get(dir));
//            }
//        }
    }

    public static void rotateTop(char dir) {
        if(dir == '+') { // 시계 방향
            for(int i = 0; i < 3; ++i) {
                char tmp = cube[2][0][i];
                cube[2][0][i] = cube[5][0][i];
                cube[5][0][i] = cube[3][0][i];
                cube[3][0][i] = cube[4][0][i];
                cube[4][0][i] = tmp;
            }
            clockwise(0);
        } else { // 반시계 방향
            for(int i = 0; i < 3; ++i) {
                char tmp = cube[2][0][i];
                cube[2][0][i] = cube[4][0][i];
                cube[4][0][i] = cube[3][0][i];
                cube[3][0][i] = cube[5][0][i];
                cube[5][0][i] = tmp;
            }
            counterClockwise(0);
        }
    }

    public static void rotateBottom(char dir) {
        if(dir == '+') { // 시계 방향
            for(int i = 0; i < 3; ++i) {
                char tmp = cube[2][2][i];
                cube[2][2][i] = cube[5][2][i];
                cube[5][2][i] = cube[3][2][i];
                cube[3][2][i] = cube[4][2][i];
                cube[4][2][i] = tmp;
            }
            clockwise(1);
        } else { // 반시계 방향
            for(int i = 0; i < 3; ++i) {
                char tmp = cube[2][2][i];
                cube[2][2][i] = cube[4][2][i];
                cube[4][2][i] = cube[3][2][i];
                cube[3][2][i] = cube[5][2][i];
                cube[5][2][i] = tmp;
            }
            counterClockwise(1);
        }
    }

    public static void rotateFront(char dir) {
        char tmp;
        if(dir == '+') { // 시계 방향
            tmp = cube[1][2][0];
            cube[1][2][0] = cube[4][2][2];
            cube[4][2][2] = cube[1][0][2];
            cube[1][0][2] = cube[5][0][0];
            cube[5][0][0] = tmp;

            tmp = cube[1][2][1];
            cube[1][2][1] = cube[4][1][2];
            cube[4][1][2] = cube[1][0][1];
            cube[1][0][1] = cube[5][1][0];
            cube[5][1][0] = tmp;

            tmp = cube[1][2][2];
            cube[1][2][2] = cube[4][0][2];
            cube[4][0][2] = cube[1][0][0];
            cube[1][0][0] = cube[5][2][0];
            cube[5][2][0] = tmp;

            clockwise(0);
        } else { // 반시계 방향
            tmp = cube[1][2][0];
            cube[1][2][0] = cube[4][2][2];
            cube[4][2][2] = cube[1][0][2];
            cube[1][0][2] = cube[5][0][0];
            cube[5][0][0] = tmp;

            tmp = cube[1][2][1];
            cube[1][2][1] = cube[4][1][2];
            cube[4][1][2] = cube[1][0][1];
            cube[1][0][1] = cube[5][1][0];
            cube[5][1][0] = tmp;

            tmp = cube[1][2][2];
            cube[1][2][2] = cube[4][0][2];
            cube[4][0][2] = cube[1][0][0];
            cube[1][0][0] = cube[5][2][0];
            cube[5][2][0] = tmp;

            counterClockwise(0);
        }
    }

    public static void clockwise(int n) {
        char tmp = cube[n][0][0];

        for(int i = 0; i < SIZE - 1; ++i) cube[n][i][0] = cube[n][i+1][0];
        for(int i = 0; i < SIZE - 1; ++i) cube[n][SIZE-1][i] = cube[n][SIZE-1][i+1];
        for(int i = SIZE-1; i > 0; --i) cube[n][i][SIZE-1] = cube[n][i-1][SIZE-1];
        for(int i = SIZE-1; i > 0; --i) cube[n][0][i] = cube[n][0][i-1];

        cube[n][0][1] = tmp;
    }

    public static void counterClockwise(int n) {
        char tmp = cube[n][0][0];

        for(int i = 0; i < SIZE - 1; ++i) cube[n][0][i] = cube[n][0][i+1];
        for(int i = 0; i < SIZE - 1; ++i) cube[n][i][SIZE-1] = cube[n][i+1][SIZE-1];
        for(int i = SIZE-1; i > 0; --i) cube[n][SIZE-1][i] = cube[n][SIZE-1][i-1];
        for(int i = SIZE-1; i > 0; --i) cube[n][i][0] = cube[n][i-1][0];

        cube[n][1][0] = tmp;
    }

    public static void initCube() {
        cube = new char[6][3][3];
        for(int i = 0; i < 6; ++i){
            for(int j = 0; j < 3; ++j){
                Arrays.fill(cube[i][j], color[i]);
            }
        }
    }

    public static void print(int n) {
        System.out.println("================");
        for(int h = 0; h < 6; ++h) {
            for (int y = 0; y < 3; ++y) {
                System.out.println(Arrays.toString(cube[h][y]));
            }
            System.out.println("#############");
        }
        System.out.println("================");
    }
}
