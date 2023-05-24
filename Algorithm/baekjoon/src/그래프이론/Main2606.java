package 그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main2606 {
    static int N, M;
    static List<List<Integer>> list = new ArrayList<>();
    static boolean[] dfsVisited;
    static boolean[] bfsVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // init list
        for(int i = 0; i <= N; i++){
            list.add(new ArrayList<>());
        }
        // init visited boolean array
        dfsVisited = new boolean[N + 1];
        bfsVisited = new boolean[N + 1];

        for(int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list.get(u).add(v);
            list.get(v).add(u);
        }

        dfs(1);
        long dfsCount = IntStream.range(0, dfsVisited.length).mapToObj(i -> dfsVisited[i]).filter(i -> i).count();
        System.out.println(dfsCount - 1); // except idx 0

        bfs(1);
        long bfsCount = IntStream.range(0, bfsVisited.length).mapToObj(i -> bfsVisited[i]).filter(i -> i).count();
        System.out.println(bfsCount - 1); // except idx 0
    }
    static private void dfs(int start){
        dfsVisited[start] = true;

        for (Integer next : list.get(start)) {
            if(!dfsVisited[next]){
                dfs(next);
            }
        }
    }
    static private void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        bfsVisited[start] = true;

        while (!q.isEmpty()){
            Integer curr = q.poll();
            for (Integer next : list.get(curr)) {
                if(!bfsVisited[next]){
                    bfsVisited[next] = true;
                    q.offer(next);
                }
            }
        }
    }
}

