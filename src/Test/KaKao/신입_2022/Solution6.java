package Test.KaKao.신입_2022;

public class Solution6 {

    private static int[][] board;
    private static int[][] skill;

    public static void main(String[] args) {
        board = new int[][] {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
        skill = new int[][] {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};

        for(int[] skillInfo : skill){
            int type = skillInfo[0]; // 1: 공격, 2: 회복
            int startY = skillInfo[1];
            int startX = skillInfo[2];
            int endY = skillInfo[3];
            int endX = skillInfo[4];
            int degree = skillInfo[5];

            run(type, startY, startX, endY, endX, degree);
        }

        int ans = getBuilding();
        System.out.println(ans);
    }

    public static void run(int type, int startY, int startX, int endY, int endX, int degree){

        if(type == 1){
            type = -1;
        } else {
            type = 1;
        }

        for(int y = startY; y <= endY; ++y){
            for(int x = startX; x <= endX; ++x){
                board[y][x] += degree * type;
            }
        }

        // print();
    }

    public static int getBuilding(){
        int count = 0;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if(board[y][x] > 0){
                    count++;
                }
            }
        }
        return count;
    }

    public static void print(){
        System.out.println("=======================");
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                System.out.print(board[y][x] + " ");
            }
            System.out.println();
        }
    }
}
