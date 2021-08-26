package Category.PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// https://orubt.tistory.com/180
public class BOJ17612_쇼핑몰 {

    private static int N, K, outNo;
    private static long ans;
    private static Queue<Customer> customers;
    private static PriorityQueue<Customer> out;
    private static Stack<Integer> remainPosId;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        customers = new LinkedList<>();
        out = new PriorityQueue<>();
        remainPosId = new Stack<>();

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            customers.offer(new Customer(id, w));
        }

        run();
        System.out.println(ans);
    }

    public static void run() {
        int time = 0;
        if (N < K) {
            for(int i = 0; i < N; ++i){
                Customer customer = customers.poll();
                customer.posId = i;
                customer.time = customer.w;
                out.offer(customer);
            }
        } else {
            for(int i = 0; i < K; ++i){
                Customer customer = customers.poll();
                customer.posId = i;
                customer.time = customer.w;
                out.offer(customer);
            }
        }
        while(!out.isEmpty()) {
            time = out.peek().time;
            removeCustomer(time); // out
            addCustomer(time); // in
        }
    }

    public static void addCustomer(int time){
        while(!remainPosId.isEmpty()){
            if(customers.isEmpty()) return;
            Customer customer = customers.poll();
            customer.posId = remainPosId.pop();
            customer.time = time + customer.w;
            out.offer(customer);
        }
    }

    public static void removeCustomer(int time){
        while(!out.isEmpty()){
            if(time == out.peek().time){
                Customer customer = out.poll();
                remainPosId.add(customer.posId); // stack 에 남은 pos 추가
                calculate(customer.id);
            } else {
                break;
            }
        }
    }

    public static void calculate(int customerId){
        ans += 1l * customerId * ++outNo;
    }

    static class Customer implements Comparable<Customer> {

        int id;
        int w;
        int posId;
        int time;

        public Customer(int id, int w) {
            this.id = id;
            this.w = w;
        }

        @Override
        public int compareTo(Customer c) {
            if (this.time == c.time) {
                return Integer.compare(c.posId, this.posId);
            }
            return Integer.compare(this.time, c.time);
        }
    }
}
