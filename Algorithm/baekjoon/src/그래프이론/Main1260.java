package 그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main1260 {
    static int N, M, V;
    static List<List<Integer>> list = new ArrayList<>();
    static boolean[] dfsVisited;
    static boolean[] bfsVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        dfsVisited = new boolean[N + 1];
        bfsVisited = new boolean[N + 1];

        for(int i = 0; i <= N; i++){
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // u v로 표현했지만 간선은 양방향임

            list.get(u).add(v);
            list.get(v).add(u);
        }

        // sort
        for (List<Integer> subList: list) {
            Collections.sort(subList);
        }

        dfs(V);
        System.out.println();
        bfs(V);
    }
    private static void dfs(int start){
        System.out.print(start + " ");
        dfsVisited[start] = true;

        for (Integer next : list.get(start)) {
            if(!dfsVisited[next]){
                dfs(next);
            }
        }
    }
    private static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        bfsVisited[start] = true;

        while(!q.isEmpty()){
            Integer curr = q.poll();
            System.out.print(curr + " ");
            for (Integer next : list.get(curr)) {
                if(!bfsVisited[next]){
                    bfsVisited[next] = true;
                    q.offer(next);
                }
            }
        }
    }
}
