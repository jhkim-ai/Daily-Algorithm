package Category.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2621_카드게임 {

    private static int base;
    private static int[] cnt;
    private static Card[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        cards = new Card[5];
        cnt = new int[10];

        for(int n = 0; n < 5; ++n){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            char color = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());
            cards[n] = new Card(color, num);
            cnt[num]++;
        }

        Arrays.sort(cards);

        int ans = 0;
        if(isStraightFlush()) ans = base + 900;
        else if(isFourCard()) ans = base + 800;
        else if(isFullHouse()) ans = base + 700;
        else if(isFlush()) ans = base + 600;
        else if(isStraight()) ans = base + 500;
        else if(isTriple()) ans = base + 400;
        else if(isTwoPair()) ans = base  + 300;
        else if(isOnePair()) ans = base + 200;
        else ans = cards[4].num + 100;

        System.out.println(ans);
    }

    public static boolean isStraightFlush(){
        char color = cards[0].color;

        for(int i = 1; i < 5; ++i){
            if(color != cards[i].color) return false;
            if(cards[i-1].num + 1 != cards[i].num) return false;
        }

        base = cards[4].num;
        return true;
    }

    public static boolean isFourCard(){
        for(int num = 1; num < 10; ++num){
            if(cnt[num] == 4) {
                base = num;
                return true;
            }
        }

        return false;
    }

    public static boolean isFullHouse(){
        int two = 0;
        int three = 0;
        for(int num = 1; num < 10; ++num){
            if(cnt[num] == 2) two = num;
            if(cnt[num] == 3) three = num;
        }

        if(two != 0 && three != 0) {
            base = three * 10 + two;
            return true;
        }
        return false;
    }

    public static boolean isFlush(){
        int maxNum = cards[0].num;
        char color = cards[0].color;

        for(int i = 1; i < 5; ++i){
            if(color != cards[i].color) return false;
            maxNum = Math.max(maxNum, cards[i].num);
        }

        base = maxNum;
        return true;
    }

    public static boolean isStraight(){
        for(int i = 1; i < 5; ++i){
            if(cards[i-1].num + 1 != cards[i].num) return false;
        }

        base = cards[4].num;
        return true;
    }

    public static boolean isTriple(){
        for(int num = 1; num < 10; ++num){
            if(cnt[num] == 3){
                base = num;
                return true;
            }
        }
        return false;
    }

    public static boolean isTwoPair(){
        Queue<Integer> q = new LinkedList<>();
        for(int num = 1; num < 10; ++num){
            if(cnt[num] == 2) q.offer(num);
        }

        if(q.size() == 2){
            base = q.poll();
            base += q.poll() * 10;
            return true;
        }
        return false;
    }

    public static boolean isOnePair(){
        for(int num = 1; num < 10; ++num){
            if(cnt[num] == 2){
                base = num;
                return true;
            }
        }
        return false;
    }

    public static class Card implements Comparable<Card>{
        char color;
        int num;

        public Card(char color, int num){
            this.color = color;
            this.num = num;
        }

        @Override
        public int compareTo(Card c){
            return Integer.compare(this.num, c.num);
        }
    }
}
