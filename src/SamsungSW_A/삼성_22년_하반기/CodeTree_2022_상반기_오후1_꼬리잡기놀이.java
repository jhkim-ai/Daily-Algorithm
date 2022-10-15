package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class CodeTree_2022_상반기_오후1_꼬리잡기놀이 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, K;
    private static int round, ans;
    private static int[][] originMap;
    private static int[][] tmpMap;
    private static int[][] teamMap;
    private static List<Team> teamList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        originMap = new int[N][N];
        round = -1;

        for(int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x) {
                originMap[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        init();

        while(K-- > 0) {
            ++round;
            move();
            throwBall();

            if(round == N * 4 - 1) round = -1;
        }
        System.out.println(ans);
    }

    public static void move() {
        teamMap = new int[N][N];
        tmpMap = copyMap();

        copyMapSetting();

        for(int y = 0; y < N; ++y) {
            Arrays.fill(teamMap[y], -1);
        }

        int idx = -1;
        for(Team team : teamList) {
            ++idx;
            int[] head = team.members.get(team.head);
            int prevY = head[0];
            int prevX = head[1];
            boolean flag = false;

            for(int d = 0; d < 4; ++d) {
                int ny = head[0] + dy[d];
                int nx = head[1] + dx[d];

                if(!isIn(ny, nx)) continue;
                if(tmpMap[ny][nx] == 4) {
                    head[0] = ny;
                    head[1] = nx;

                    tmpMap[ny][nx] = 1;
                    teamMap[ny][nx] = idx;
                    flag = true;
                    break;
                }
            }

            if(!flag) {
                for(int d = 0; d < 4; ++d) {
                    int ny = head[0] + dy[d];
                    int nx = head[1] + dx[d];

                    if(!isIn(ny, nx)) continue;
                    if(tmpMap[ny][nx] == 3) {
                        head[0] = ny;
                        head[1] = nx;

                        tmpMap[ny][nx] = 1;
                        teamMap[ny][nx] = idx;
                        flag = true;
                        break;
                    }
                }
            }

            if(team.head == 0) {
                for(int i = team.head + 1; i <= team.tail; ++i) {
                    int[] member = team.members.get(i);
                    int tmpY = member[0];
                    int tmpX = member[1];

                    member[0] = prevY;
                    member[1] = prevX;

                    prevY = tmpY;
                    prevX = tmpX;

                    tmpMap[member[0]][member[1]] = 2;
                    if(i == team.tail) tmpMap[member[0]][member[1]] = 3;

                    teamMap[member[0]][member[1]] = idx;
                }
            } else {
                for(int i = team.head - 1; i >= team.tail; --i) {
                    int[] member = team.members.get(i);
                    int tmpY = member[0];
                    int tmpX = member[1];

                    member[0] = prevY;
                    member[1] = prevX;

                    prevY = tmpY;
                    prevX = tmpX;

                    tmpMap[member[0]][member[1]] = 2;
                    if(i == team.tail) tmpMap[member[0]][member[1]] = 3;

                    teamMap[member[0]][member[1]] = idx;
                }
            }

            if(tmpMap[prevY][prevX] != 1) tmpMap[prevY][prevX] = 4;
        }
    }

    public static void throwBall() {
        int dir = round / N;
        int line = round % N;

        switch(dir) {
            case 0:
                throwRight(line);
                break;
            case 1:
                throwUp(line);
                break;
            case 2:
                line = N - line - 1;
                throwLeft(line);
                break;
            case 3:
                line = N- line - 1;
                throwDown(line);
                break;
        }
    }

    public static void throwRight(int line) {
        for(int x = 0; x < N; ++x) {
            if(teamMap[line][x] == -1) continue;
            getScore(teamMap[line][x], line, x);
            break;
        }
    }

    public static void throwUp(int line) {
        for(int y = N-1; y >= 0; --y) {
            if(teamMap[y][line] == -1) continue;
            getScore(teamMap[y][line], y, line);
            break;
        }
    }

    public static void throwLeft(int line) {
        for(int x = N-1; x >= 0; --x) {
            if(teamMap[line][x] == -1) continue;
            getScore(teamMap[line][x], line, x);
            break;
        }
    }

    public static void throwDown(int line) {
        for(int y = 0; y < N; ++y) {
            if(teamMap[y][line] == -1) continue;
            getScore(teamMap[y][line], y, line);
            break;
        }
    }

    public static void getScore(int teamIdx, int y, int x) {
        Team team = teamList.get(teamIdx);
        int memberIdx = 1;

        if(team.head == 0) {
            for(int idx = team.head; idx <= team.tail; ++idx) {
                int[] member = team.members.get(idx);
                if(member[0] == y && member[1] == x) {
                    break;
                }
                ++memberIdx;
            }
        } else {
            for(int idx = team.head; idx >= team.tail; --idx) {
                int[] member = team.members.get(idx);
                if(member[0] == y && member[1] == x) {
                    break;
                }
                ++memberIdx;
            }
        }
        ans += memberIdx * memberIdx;
        team.headSwitch();
    }

    public static void init() {
        teamList = new ArrayList<>();

        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                if(originMap[y][x] == 1) {
                    int[] head = {y, x};
                    Team team = new Team();
                    team.members.add(head);

                    findRestMember(team);
                    team.tail = team.members.size() - 1;
                    teamList.add(team);
                }
            }
        }
    }

    public static void findRestMember(Team team) {
        int[] head = team.members.get(team.head);
        int y = head[0];
        int x = head[1];
        boolean[][] visited = new boolean[N][N];

        originMap[y][x] = 4;
        visited[y][x] = true;

        outer:
        while(true) {
            for(int d = 0; d < 4; ++d) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                if (!isIn(ny, nx) || visited[ny][nx] || originMap[ny][nx] == 0 || originMap[ny][nx] == 4) {
                    continue;
                }

                if(originMap[ny][nx] == 2) {
                    int[] member = {ny, nx};
                    visited[ny][nx] = true;
                    team.members.add(member);

                    originMap[ny][nx] = 4;
                    y = ny;
                    x = nx;
                    d = -1;
                }
            }
            break;
        }

        int[] lastMember = team.members.get(team.members.size() - 1);
        for(int d = 0; d < 4; ++d) {
            int ny = lastMember[0] + dy[d];
            int nx = lastMember[1] + dx[d];
            if(!isIn(ny, nx)) continue;

            if(originMap[ny][nx] == 3) {
                team.members.add(new int[]{ny, nx});
                break;
            }
        }
    }

    public static int[][] copyMap() {
        int[][] tmpMap = new int[N][];
        for(int y = 0; y < N; ++y) {
            tmpMap[y] = originMap[y].clone();
        }

        return tmpMap;
    }

    public static void copyMapSetting() {
        for(Team team : teamList) {

            int[] head = team.members.get(team.head);
            tmpMap[head[0]][head[1]] = 1;
            int[] tail = team.members.get(team.tail);
            tmpMap[tail[0]][tail[1]] = 3;

            if(team.head == 0) {
                for(int idx = team.head+1; idx < team.tail; ++idx) {
                    int[] member = team.members.get(idx);
                    tmpMap[member[0]][member[1]] = 2;
                }
            } else {
                for(int idx = team.head-1; idx > team.tail; --idx) {
                    int[] member = team.members.get(idx);
                    tmpMap[member[0]][member[1]] = 2;
                }
            }
        }
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }
    public static class Team {
        int head = 0;
        int tail = 0;
        List<int[]> members = new ArrayList<>();

        public void headSwitch() {
            int tmp = head;
            head = tail;
            tail = tmp;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if(head == 0) {
                for(int idx = head; idx <= tail; ++idx) {
                    int[] info = this.members.get(idx);
                    sb.append("(y: " + info[0] + ", x: " + info[1]).append(") ");
                }
            } else {
                for(int idx = head; idx >= tail; --idx) {
                    int[] info = this.members.get(idx);
                    sb.append("(y: " + info[0] + ", x: " + info[1]).append(" )");
                }
            }
            return sb.toString();
        }
    }
}
