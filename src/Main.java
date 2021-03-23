import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        User[] arr = new User[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            arr[i] = new User(Integer.parseInt(st.nextToken()), st.nextToken());
        }

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for(User u : arr){
            sb.append(u.age).append(" ").append(u.name).append("\n");
        }
        System.out.println(sb);
    }

    static class User implements Comparable<User>{
        int age;
        String name;

        @Override
        public int compareTo(User u){
            return Integer.compare(this.age, u.age);
        }

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }
}
