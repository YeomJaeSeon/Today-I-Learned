package re_study_list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2606 {
    static int N, M;
    static boolean[] visited;
    static List<List<Integer>> list = new ArrayList<>(); // graph 관계도
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        M = Integer.parseInt(br.readLine());

        for(int i = 0; i <= N; i++){
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }
        System.out.println(bfs());
    }
    static int bfs(){
        int infectedCnt = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = true;

        while (!q.isEmpty()){
            Integer curr = q.poll();
            infectedCnt++;
            for (Integer next : list.get(curr)) {
                if(!visited[next]){
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
        return infectedCnt - 1;
    }
}
