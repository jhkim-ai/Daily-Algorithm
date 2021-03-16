import java.io.*;
import java.util.*;

public class sample {

    static int N;
    static List<String> arr;
    static Set<String> s = new HashSet<>();

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        N = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            s.add(br.readLine());
        }
        for (String str : s){
            arr.add(str);
        }

        Collections.sort(arr, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                if(s1.length() == s2.length()){
                    return s1.compareTo(s2);
                }
                return s1.length()-s2.length();
            }
        });
        StringBuilder sb = new StringBuilder();
        for(String str : arr)
            sb.append(str).append("\n");
        System.out.println(sb);
    }

    static String input = "13\n" +
            "but\n" +
            "i\n" +
            "wont\n" +
            "hesitate\n" +
            "no\n" +
            "more\n" +
            "no\n" +
            "more\n" +
            "it\n" +
            "cannot\n" +
            "wait\n" +
            "im\n" +
            "yours";
}
