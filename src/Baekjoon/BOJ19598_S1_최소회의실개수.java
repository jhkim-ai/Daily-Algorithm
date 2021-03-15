package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ19598_S1_최소회의실개수 {

    static int N, ans;
    static List<Room> list;
    static Room[] arr;

    public static void main(String[] args) throws Exception{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new Room[N];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i] = new Room(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(arr, new Comparator<Room>(){
            @Override
            public int compare(Room o1, Room o2) {
                return o1.start - o2.start;
            }
        });
        list.add(arr[0]);

        for (int i = 1; i < N; i++) {
            Room r = arr[i];
            boolean flag = false;
            int size = list.size();
            for (int j = 0; j < size; j++) {
                if(r.start >= list.get(j).end){
                    list.get(j).start = r.start;
                    list.get(j).end = r.end;
                    flag = true;
                    break;
                }
            }

            if(!flag){
                list.add(arr[i]);
            }
        }
        System.out.println(list.size());
    }

    static class Room{
        int start;
        int end;

        public Room(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Room{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    static String input = "6\n" +
            "0 40\n" +
            "15 30\n" +
            "5 10\n" +
            "16 50\n" +
            "40 70\n" +
            "45 70\n";
}
