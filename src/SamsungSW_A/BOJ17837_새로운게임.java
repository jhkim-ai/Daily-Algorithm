package SamsungSW_A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17837_새로운게임 {

    private static final int[] dy = {0, 0, 0, -1, 1};
    private static final int[] dx = {0, 1, -1, 0, 0};

    private static int N, K;

    private static Piece[] pieces;
    private static int[][] map;
    private static Piece[][] chess;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // map 의 크기
        K = Integer.parseInt(st.nextToken()); // 말의 개수
        pieces = new Piece[K];   // 말의 순서를 배열로 저장
        map = new int[N][N];     // 입력으로 주어진 체스 판
        chess = new Piece[N][N]; // 체스 판: 제일 위에 있는 말을 나타낸다.

        // map 입력
        for (int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(K);
        // 말 정보 입력
        for (int k = 0; k < K; ++k) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            Piece piece = new Piece(k + 1, y, x, d);
            pieces[k] = piece;
            chess[piece.y][piece.x] = piece; // 체스 판은 가장 위에 있는 말을 저장한다.
            System.out.println(pieces[k]);
        }

        int count = run();
        System.out.println(count);
    }

    public static int run() {
        int count = 1;
        outer:
        while (true) {

//            if(count > 18){
//                break;
//            }

            if (count > 1000) {
                count = -1;
                break;
            }

            System.out.println("=============");
            System.out.println(count);

            for (Piece piece : pieces) {
                int getPieces = 0;
                int ny = piece.y + dy[piece.d];
                int nx = piece.x + dx[piece.d];

                if (!isIn(ny, nx) || map[ny][nx] == 2) { // 범위 밖이거나 "파란" 칸 일 때
                    getPieces = blueBlock(piece);
                } else if (map[ny][nx] == 1) { // "빨간" 칸 일 때
                    getPieces = redBlock(piece, ny, nx);
                } else { // "하얀" 칸 일 때
                    getPieces = move(piece, piece.prev, ny, nx);
                }

                if (getPieces >= 4) {
                    break outer;
                }
            }

            count++;
        }

        return count;
    }

    // 빨간색
    public static int redBlock(Piece piece, int ny, int nx) {
        int nowY = piece.y;
        int nowX = piece.x;

        Stack<Piece> s = new Stack<>();
        Piece prev = piece.prev;

        // 체스 판 바꾸기
        chess[nowY][nowX] = piece.prev;

        // stack 을 이용한 순서 뒤집기
        do {
            s.push(piece);
            if (piece.prev != null) {
                piece.prev.next = null;
                piece.prev = null;
            }
            Piece nextPiece = piece.next;
            piece.next = null;
            piece = nextPiece;
            System.out.println("red1 do While");
        } while (piece != null);

        Piece origin = s.pop();
        Piece start = origin;
        Piece next = null;
        while (!s.isEmpty()) {
            next = s.pop();
            System.out.println("start:" + start.idx + ", next:" + next.idx);
            next.prev = start;
            start.next = next;
            start = next;
            System.out.println("red2 Stack");
        }
        System.out.println("stack: " + s);
        origin.prev = prev;
        if (prev != null) {
            prev.next = origin;
        }

        // 다음 칸 검색
        return move(origin, prev, ny, nx);
    }

    // 파란색
    public static int blueBlock(Piece piece) {
        // 방향 전환
        int d = piece.d;
        if (d % 2 == 1) {
            d++;
        } else {
            d--;
        }
        piece.d = d;

        int ny = piece.y + dy[d];
        int nx = piece.x + dx[d];

        if (!isIn(ny, nx) || map[ny][nx] == 2) { // 새로 이동한 칸이 파란색이면 방향만 전환
            return getPieces(chess[piece.y][piece.x]);
        }

        // 한 칸 이동
        return move(piece, piece.prev, ny, nx);
    }

    public static int move(Piece piece, Piece prev, int ny, int nx) {
        System.out.println("--------move-------");
        // 현재 말 아래층에 말이 존재하면
        if (prev != null) {
            // 아래층 말의 다음을 null 로 비운다.
            prev.next = null;
        }

        // 그리고 아래층 말은 그 위치에서 가장 위에 있기 때문에 chess 판에 나타낸다.
        // 아래층이 null 이라면 null 을 등록
        chess[piece.y][piece.x] = prev;

        // 현재 말의 아래층을 등록
        piece.prev = chess[ny][nx];

        // 새로 이동한 곳에 말이 놓여져 있다면, 그 말의 위층에 현재 말로 등록
        if (chess[ny][nx] != null) {
            chess[ny][nx].next = piece;
        }
        // 체스판을 제일 위에 있는 말로 교체
        // chess[ny][nx] = top;

        // 현재 말부터 맨 위의 말까지 한 칸 이동
        while (piece != null) {
            piece.y = ny; // 한 칸 이동
            piece.x = nx;
            System.out.println(piece.idx + ": " + piece.y + ", " + piece.x);
            if (piece.next != null) {
                System.out.println("next: " + piece.next.idx);
            }
            if (piece.prev != null) {
                System.out.println("prev: " + piece.prev.idx);
            }
            if (chess[ny][nx] != null) {
                System.out.println("원래 맨 위: " + chess[ny][nx].idx);
            }
            // System.out.println("다음: "+piece.next.idx);
            chess[ny][nx] = piece;
            piece = piece.next; // 다음 말
//            System.out.println("move while");
        }
        System.out.println("최종 맨위: " + chess[ny][nx].idx);

        return getPieces(chess[ny][nx]);
    }

    public static int getPieces(Piece piece) {
        int count = 0;

        while (piece != null) {
            count++;
            piece = piece.prev;
        }

        return count;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    static class Piece {

        int idx = -1;
        int y = -1;
        int x = -1;
        int d = -1;
        Piece prev = null;
        Piece next = null;

        public Piece(int idx, int y, int x, int d) {
            this.idx = idx;
            this.y = y;
            this.x = x;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Piece{" +
                "idx=" + idx +
                ", y=" + y +
                ", x=" + x +
                ", d=" + d +
                ", prev=" + prev +
                ", next=" + next +
                '}';
        }
    }
}
