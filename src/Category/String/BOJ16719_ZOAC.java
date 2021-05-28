package Category.String;

import java.io.*;
import java.util.*;

public class BOJ16719_ZOAC {

    public static String str;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        Word[] list = new Word[str.length()];
        List<Word> ans = new ArrayList<>();

        for (int idx = 0; idx < str.length(); ++idx) {
            list[idx] = new Word(str.charAt(idx), idx);
        }
        Arrays.sort(list);

        ans.add(list[0]);
        for (int idx = 1; idx < list.length; ++idx) {
            for (int i = 0; i < ans.size(); ++i) {
                if (list[idx].index > ans.get(i).index) continue;
            }
        }


    }

    static class Word implements Comparable<Word> {
        char ascii;
        int index;

        public Word(char ascii, int index) {
            this.ascii = ascii;
            this.index = index;
        }

        @Override
        public int compareTo(Word w) {
            return Integer.compare(this.ascii, w.ascii);
        }

    }
}
