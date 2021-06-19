package Test.DeliveryHero_2021;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

public class Solution1 {

    public static List<File> files;

    public static void main(String[] args) throws Exception{
        String input = "715K 2009-09-23 system.zip~\n" +
                " 179K 2013-08-14 to-do-list.xml~\n" +
                " 645K 2013-06-19 blockbuster.mpeg~\n" +
                "  536 2010-12-12 notes.html\n" +
                " 688M 1990-02-11 delete-this.zip~\n" +
                "  23K 1987-05-24 setup.png~\n" +
                " 616M 1965-06-06 important.html\n" +
                "  14M 1992-05-31 crucial-module.java~\n" +
                " 192K 1990-01-31 very-long-filename.dll~";

        // ============ 여기서부터  ============= //
        SimpleDateFormat dateFormat = new
                SimpleDateFormat ("yyyy-MM-dd");
        String[] splitStr = input.split(",");
        int ans = Integer.MAX_VALUE;
        files = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(splitStr[0]);
        while(st.hasMoreTokens()){
            String size = st.nextToken();
            String originDate = st.nextToken();
            String name = st.nextToken();
            files.add(new File(size, originDate, name));
        }

        Collections.sort(files,(o1,o2)->{

            Date date1 = null;
            Date date2 = null;
            try {
                date1 = dateFormat.parse(o1.date);
                date2 = dateFormat.parse(o2.date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return date1.compareTo(date2);
        });

        for(File f : files){
            if(f.name.charAt(f.name.length()-1) != '~') continue;
            if(!isBig(f.size)) continue;
            Date day1 = dateFormat.parse("1990-01-31");
            Date day2= dateFormat.parse(f.date);
            if(day1.compareTo(day2) >= 0) continue;

            int idx = f.name.indexOf('.');
            String s = f.name.substring(0, idx);

            ans = Math.min(ans, s.length());
        }

    }

    public static boolean isBig(String size){
        int checkSize = 14 * (1<<20);
        char unit = size.charAt(size.length() - 1);
        int num = Integer.parseInt(size.substring(0, size.length()-1));

        switch (unit){
            case 'K':
                num *= (1<<10);
                break;
            case 'M':
                num *= (1<<20);
                break;
            case 'G':
                num *= (1<<30);
                break;
        }

        if(num >= checkSize) return false;
        return true;
    }

    static class File{
        String size;
        String date;
        String name;

        public File(String size, String date, String name){
            this.size = size;
            this.date = date;
            this.name = name;
        }

        @Override
        public String toString() {
            return "size='" + size + '\'' +
                    ", day=" + date +
                    ", name='" + name + '\'';
        }
    }


    private static String input = "715K 2009-09-23 system.zip~\n" +
            " 179K 2013-08-14 to-do-list.xml~\n" +
            " 645K 2013-06-19 blockbuster.mpeg~\n" +
            "  536 2010-12-12 notes.html\n" +
            " 688M 1990-02-11 delete-this.zip~\n" +
            "  23K 1987-05-24 setup.png~\n" +
            " 616M 1965-06-06 important.html\n" +
            "  14M 1992-05-31 crucial-module.java~\n" +
            " 192K 1990-01-31 very-long-filename.dll~";
}
