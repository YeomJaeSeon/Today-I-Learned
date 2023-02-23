package tf1.day1;

import java.util.*;

public class 가장_가까운_같은_글자 {
    static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        가장_가까운_같은_글자 instance = new 가장_가까운_같은_글자();
        System.out.println(
                Arrays.toString(instance.solution("banana"))
        );
    }
    public int[] solution(String s) {
        List<Integer> answer = new ArrayList<>();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(!map.containsKey(c)){
                map.put(c, i);
                answer.add(-1);
            }else{
                //1. 가장 최근의 map의 value 조회하고
                Integer index = map.get(c);
                //2. answer에 차이 넣고
                answer.add(i - index);
                //3. 최신화
                map.put(c, i);
            }
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
}
