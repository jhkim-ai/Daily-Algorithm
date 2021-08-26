package Category.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BOJ3005_크로스워퍼쳐다보기 {

    public static int R, C;
    public static char[][] map;
    public static List<Character> wordList;
    public static List<String> ansList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        wordList = new ArrayList<>();
        ansList = new ArrayList<>();

        for (int y = 0; y < R; ++y) {
            map[y] = br.readLine().toCharArray();
        }

        // 알고리즘
        findStringCol();
        findStringRow();
        Collections.sort(ansList);
        System.out.println(ansList.get(0));
    }
    public static void findStringCol(){
        wordList.clear();
        for (int y = 0; y < R; ++y) {
            for (int x = 0; x < C; ++x) {
                char c = map[y][x];
                if (c != '#') { // 장애물이 아니라면 word 추가
                    wordList.add(c);
                } else { // 장애물이라면, checking
                    if (!isNotOverLength(wordList.size())) { // size 가 2 이상이면, String 만들기
                        String str = list2String(wordList);
                        ansList.add(str);
                    }
                    wordList.clear(); // size 가 2 미만이면, 패스한다.
                }
            }
            int size = wordList.size();
            if (size != 0) {
                if (!isNotOverLength(size)) {
                    String str = list2String(wordList);
                    ansList.add(str);
                }
                wordList.clear();
            }
        }
    }

    public static void findStringRow(){
        wordList.clear();
        for (int x = 0; x < C; ++x) {
            for (int y = 0; y < R; ++y) {
                char c = map[y][x];
                if (c != '#') { // 장애물이 아니라면 word 추가
                    wordList.add(c);
                } else { // 장애물이라면, checking
                    if (!isNotOverLength(wordList.size())) { // size 가 2 이상이면, String 만들기
                        String str = list2String(wordList);
                        ansList.add(str);
                    }
                    wordList.clear(); // size 가 2 미만이면, 패스한다.
                }
            }
            int size = wordList.size();
            if (size != 0) {
                if (!isNotOverLength(size)) {
                    String str = list2String(wordList);
                    ansList.add(str);
                }
                wordList.clear();
            }
        }
    }

    public static String list2String(List<Character> wordList) {
        String str = wordList.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining());
        return str;
    }

    public static boolean isNotOverLength(int size) {
        return size < 2;
    }
}
