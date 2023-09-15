package daily;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main10845 {
    private static int N;
    public static void main(String[] args) throws IOException {
        Queue<Integer> q = new LinkedList<>();
        int last = -1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();
            Integer number = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : null;

            switch (command){
                case "push":
                    q.offer(number);
                    last = number;
                    break;
                case "pop":
                    System.out.println(q.isEmpty() ? -1 : q.poll());
                    break;
                case "size":
                    System.out.println(q.size());
                    break;
                case "empty":
                    System.out.println(q.isEmpty() ? 1 : 0);
                    break;
                case "front":
                    System.out.println(q.isEmpty() ? -1 : q.peek());
                    break;
                case "back":
                    System.out.println(q.isEmpty() ? -1 : last);
                    break;
            }
        }
    }
}
