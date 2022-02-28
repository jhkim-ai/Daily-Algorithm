package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _2_BOJ12100_2048easy {

    private static int N, ans;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int y = 0; y < N; ++y){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(5, new int[5]);
        System.out.println(ans);
    }

    public static void dfs(int cnt, int[] selected){
        if(cnt == 0){
            int[][] copyMap = copyMap();
            for(int i = 0; i < selected.length; ++i){
                move(selected[i], copyMap);
            }
            ans = Math.max(ans, getMaxVal(copyMap));
            return;
        }

        for(int d = 0; d < 4; ++d){
            selected[selected.length - cnt] = d;
            dfs(cnt - 1, selected);
        }
    }

    public static int[][] copyMap(){
        int[][] copyMap = new int[N][];
        for(int y = 0; y < N; ++y){
            copyMap[y] = map[y].clone();
        }
        return copyMap;
    }

    public static void move(int d, int[][] copyMap){ // 0: 상, 1: 하, 2: 좌, 3: 우
        List<Integer> before = new ArrayList<>();
        List<Integer> after = new ArrayList<>();
        int aSize;
        int bSize;
        if(d == 0){
            for(int x = 0; x < N; ++x){
                after.clear();
                before.clear();

                for(int y = 0; y < N; ++y){
                    int val = copyMap[y][x];
                    if(val == 0) continue;
                    before.add(val);
                }

                bSize = before.size();

                for(int y = 0; y < bSize; ++y){
                    int val = before.get(y);
                    if(y == bSize-1){
                        after.add(val);
                        continue;
                    }

                    if(val == before.get(y+1)){
                        after.add(val * 2);
                        ++y;
                    } else {
                        after.add(val);
                    }
                }

                aSize = N - after.size();
                for(int i = 0; i < aSize; ++i){
                    after.add(0);
                }

                for(int y = 0; y < N; ++y){
                    copyMap[y][x] = after.get(y);
                }
            }
        } else if(d == 1){ // 하
            for(int x = 0; x < N; ++x){
                after.clear();
                before.clear();

                for(int y = N-1; y >= 0; --y){
                    int val = copyMap[y][x];
                    if(val == 0) continue;
                    before.add(val);
                }

                bSize = before.size();

                for(int y = 0; y < bSize; ++y){
                    int val = before.get(y);
                    if(y == bSize-1){
                        after.add(val);
                        continue;
                    }

                    if(val == before.get(y+1)){
                        after.add(val * 2);
                        ++y;
                    } else {
                        after.add(val);
                    }
                }

                aSize = N - after.size();
                for(int i = 0; i < aSize; ++i){
                    after.add(0);
                }

                for(int y = N-1; y >= 0; --y){
                    copyMap[y][x] = after.get(N-1-y);
                }
            }
        } else if(d == 2){ // 좌
            for(int y = 0; y < N; ++y){
                after.clear();
                before.clear();

                for(int x = 0; x < N; ++x){
                    int val = copyMap[y][x];
                    if(val == 0) continue;
                    before.add(val);
                }

                bSize = before.size();

                for(int x = 0; x < bSize; ++x){
                    int val = before.get(x);
                    if(x == bSize-1){
                        after.add(val);
                        continue;
                    }

                    if(val == before.get(x+1)){
                        after.add(val * 2);
                        ++x;
                    } else {
                        after.add(val);
                    }
                }

                aSize = N - after.size();
                for(int i = 0; i < aSize; ++i){
                    after.add(0);
                }

                for(int x = 0; x < N; ++x){
                    copyMap[y][x] = after.get(x);
                }
            }
        } else if(d == 3){ // 우
            for(int y = 0; y < N; ++y){
                after.clear();
                before.clear();

                for(int x = N-1; x >= 0; --x){
                    int val = copyMap[y][x];
                    if(val == 0) continue;
                    before.add(val);
                }

                bSize = before.size();

                for(int x = 0; x < bSize; ++x){
                    int val = before.get(x);
                    if(x == bSize-1){
                        after.add(val);
                        continue;
                    }

                    if(val == before.get(x+1)){
                        after.add(val * 2);
                        ++x;
                    } else {
                        after.add(val);
                    }
                }

                aSize = N - after.size();
                for(int i = 0; i < aSize; ++i){
                    after.add(0);
                }

                for(int x = N-1; x >= 0; --x){
                    copyMap[y][x] = after.get(N-1-x);
                }
            }
        }
    }

    public static int getMaxVal(int[][] copyMap){
        int num = 0;

        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                num = Math.max(copyMap[y][x], num);
            }
        }

        return num;
    }

    public static void print(){
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }
}
