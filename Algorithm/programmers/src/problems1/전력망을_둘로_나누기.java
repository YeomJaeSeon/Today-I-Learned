package problems1;

import java.util.*;

public class 전력망을_둘로_나누기 {
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        전력망을_둘로_나누기 instance = new 전력망을_둘로_나누기();
        System.out.println(instance.solution(9, new int[][]{{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}}));
    }

    public int solution(int n, int[][] wires) {
        for(int i = 0; i < wires.length; i++){
            min = Math.min(min, disconnect(i, n, wires));
        }

        return min;
    }
    public int disconnect(int removeIdx, int n, int[][] wires){
        int[][] targetWires = Arrays.stream(wires).filter(_d -> !(wires[removeIdx][0] == _d[0] && wires[removeIdx][1] == _d[1])).toArray(int[][]::new);

        List<List<Integer>> list = new ArrayList<>(); // 그래프 연결정보
        boolean[] visited = new boolean[n + 1]; // 방문 여부 - default: false

        for(int i = 0; i <= n; i++){
            list.add(new ArrayList<>());
        }

        for (int[] targetWire : targetWires) {
            int a = targetWire[0];
            int b = targetWire[1];

            // 양 side 모두 추가
            list.get(a).add(b);
            list.get(b).add(a);
        }

        List<Integer> visitCntList = new ArrayList<>();

        for(int i = 1; i <= n; i++){
            int visitCount = bfs(list, i, visited);
            if(visitCount != 0){
                visitCntList.add(visitCount);
            }
        }

        return Math.abs(visitCntList.get(0) - visitCntList.get(1));
    }
    public int bfs(List<List<Integer>> list, int startIdx, boolean[] visited){
        if(visited[startIdx]){
            return 0;
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(startIdx);
        visited[startIdx] = true;

        int visitCount = 1; // 방문 횟수

        while(!q.isEmpty()){
            Integer current = q.poll();
            List<Integer> neighbors = list.get(current);

            for (Integer neighbor : neighbors) {
                if(visited[neighbor]) continue;

                q.offer(neighbor);
                visited[neighbor] = true;
                visitCount++;
            }
        }

        return visitCount;
    }
}

/**
 * 전선 하나씩 끊어 만든 두 트리의 각 노드 개수의 최소값을 구하면 됨.
 *
 * 1. 노드를 탐색해가며 방문 횟수를 계산해야함
 * 2. 그래프 탐색을 하면되는데 나는 bfs를 이용함
 *
 * - 어려워 보이는데 쉬운문제임
 */