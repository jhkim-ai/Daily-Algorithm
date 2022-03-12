import java.io.*;
import java.util.*;

public class Main {

    private static int N, back;
    private static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        q = new LinkedList<>();
        back = -1;
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();

            if(command.equals("push")){
                int num = Integer.parseInt(st.nextToken());
                q.offer(num);
                back = num;
            } else if(command.equals("front")){
                if(q.size() > 0){
                    sb.append(q.peek()).append("\n");
                } else {
                    sb.append(-1).append("\n");
                }
            } else if(command.equals("back")){
                sb.append(back).append("\n");
            } else if(command.equals("empty")){
                if(q.isEmpty()){
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else if(command.equals("size")){
                sb.append(q.size()).append("\n");
            } else if(command.equals("pop")){
                if(q.size() > 0) {
                    sb.append(q.poll()).append("\n");
                    if(q.size() == 0){
                        back = -1;
                    }
                } else {
                    sb.append(-1).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}
