package tf1.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main1260 {
    static List<List<Integer>> list = new ArrayList<>();
    static int N, M, V;
    static boolean[] dfsVisited;
    static boolean[] bfsVisited;
    static StringBuilder dfsSb = new StringBuilder();
    static StringBuilder bfsSb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        dfsVisited = new boolean[N + 1];
        bfsVisited = new boolean[N + 1];

        for(int i = 0; i <= N; i++){
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        // 오름차순 sort
        for (List<Integer> integers : list) {
            Collections.sort(integers);
        }

        dfs(V);
        bfs(V);

        System.out.println(dfsSb.toString());
        System.out.println(bfsSb.toString());
    }
    static void dfs(int start){
        dfsVisited[start] = true;
        dfsSb.append(start + " ");
        for (Integer next : list.get(start)) {
            if(!dfsVisited[next]){
                dfs(next);
            }
        }
    }
    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        bfsVisited[start] = true;

        while (!q.isEmpty()){
            Integer curr = q.poll();
            bfsSb.append(curr + " ");

            for (Integer next : list.get(curr)) {
                if(!bfsVisited[next]){
                    bfsVisited[next] = true;
                    q.offer(next);
                }
            }
        }
    }
}
