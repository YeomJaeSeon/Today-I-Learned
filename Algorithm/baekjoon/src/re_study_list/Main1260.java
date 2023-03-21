package re_study_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main1260 {
    static int N, M, V;
    static List<List<Integer>> list = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];

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

        // sort
        for (List<Integer> integers : list) {
            Collections.sort(integers);
        }

        dfs(V);
        System.out.println();

        visited = new boolean[N + 1];
        bfs(V);
    }

    private static void dfs(int start){
        visited[start] = true;
        System.out.print(start + " ");

        for (Integer next : list.get(start)) {
            if(visited[next]){
                continue;
            }

            dfs(next);
        }
    }

    private static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()){
            Integer curr = q.poll();
            System.out.print(curr + " ");
            for (Integer next : list.get(curr)) {
                if(visited[next]){
                    continue;
                }
                visited[next] = true;
                q.offer(next);
            }
        }
    }
}
