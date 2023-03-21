package tf1.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2606 {
    static int N, M;
    static List<List<Integer>> list = new ArrayList<>();
    static boolean[] dfsVisited;
    static boolean[] bfsVisited;
    static int dfsCount;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for(int i = 0; i <= N; i++){
            list.add(new ArrayList<>());
        }
        dfsVisited = new boolean[N + 1];
        bfsVisited = new boolean[N + 1];

        for(int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        System.out.println(bfs(1));
        dfs(1);
        System.out.println(dfsCount - 1);
    }
    static int bfs(int start){
        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        bfsVisited[start] = true;

        while (!q.isEmpty()){
            Integer curr = q.poll();
            count++;

            for (Integer next : list.get(curr)) {
                if(!bfsVisited[next]){
                    bfsVisited[next] = true;
                    q.offer(next);
                }
            }
        }

        return --count;
    }
    static void dfs(int start){
        dfsVisited[start] = true;
        dfsCount++;

        for (Integer next : list.get(start)) {
            if(!dfsVisited[next]){
                dfs(next);
            }
        }
    }
}
