package problems1;

import java.util.*;

public class 귤_고르기 {
    // key: 크기, value: 개수
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        귤_고르기 instance = new 귤_고르기();
        System.out.println(instance.solution(6, new int[]{1, 3, 2, 5, 4, 5, 2, 3}));

    }
    public int solution(int k, int[] tangerine) {
        for (int t : tangerine) {
            if(!map.containsKey(t)){
                map.put(t, 1);
            }else{
                Integer value = map.get(t);
                map.put(t, value + 1);
            }
        }

        List<Integer> mapValueList = new ArrayList<>(map.values());

        // 정렬
        Collections.sort(mapValueList, (o1, o2) -> o2 - o1);

        int typeCnt = 0;
        for (Integer cnt : mapValueList) {
            k -= cnt;
            typeCnt++;

            if(k <= 0){
                break;
            }

        }

        return typeCnt;
    }
}

/**
 * 자료구조 잘 선택하면 너무 쉬운문제
 *
 * 난 Map이용
 * size에 맞는 개수를 map을 이용해서 구한뒤 map.values()를 다시 내림차순으로 정렬하였다.
 */