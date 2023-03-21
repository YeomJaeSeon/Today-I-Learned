package tf1.day1;

import java.util.*;

public class 대충_만든_자판 {
    public static void main(String[] args) {
        대충_만든_자판 instance = new 대충_만든_자판();
        System.out.println(Arrays.toString(instance.solution(new String[]{"AA"}, new String[]{"B"})));

    }
    public int[] solution(String[] keymap, String[] targets) {
        List<Integer> list = new ArrayList<>();

        Map<Character, Integer> map = initMap(keymap);
        System.out.println(map);

        for (String target : targets) {
            int total = 0;
            for(int i = 0; i < target.length(); i++){
                char ch = target.charAt(i);
                if(!map.containsKey(ch)){
                    total = -1;
                    break;
                }

                Integer level = map.get(ch);
                total += level;
            }
            list.add(total);
        }

        return list.stream().mapToInt(i -> i).toArray();
    }
    private Map<Character, Integer> initMap(String[] keymap){
        Map<Character, Integer> map = new HashMap<>();

        for (String key : keymap) {
            for(int i = 0; i < key.length(); i++){
                int level = i + 1;
                char ch = key.charAt(i);
                if(!map.containsKey(ch)){
                    map.put(ch, level);
                }else{
                    //갖고 있으면
                    Integer alreadyLevel = map.get(ch);
                    if(alreadyLevel > level){
                        map.put(ch, level);
                    }

                }
            }
        }

        return map;
    }
}

/**
 * map을 이용해 keymap들의 최적 레벨을 찾고
 * targets를 돌면서 sum을 구하면 끝.
 *
 * -> -1일 경우 주의
 */