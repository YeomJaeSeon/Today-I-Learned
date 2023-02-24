package tf1.day2;

import java.util.*;

public class 신고_결과_받기 {
    static Map<String, Integer> warningMap = new HashMap<>();
    static Set<String> warningResultSet = new HashSet<>();
    static Map<String, Set<String>> infoMap = new HashMap<>();
    public static void main(String[] args) {
        신고_결과_받기 instance = new 신고_결과_받기();
        System.out.println(Arrays.toString(
                instance.solution(new String[]{"con", "ryan"},
                        new String[]{"ryan con", "ryan con", "ryan con", "ryan con"},
                        3
                        )
        ));

    }
    public int[] solution(String[] id_list, String[] report, int k) {
        //infoMap init
        for(String id: id_list){
            infoMap.put(id, new HashSet<>());
        }

        for(String reportInfo: report){
            String[] s = reportInfo.split(" ");
            String reporter = s[0];
            String badUser = s[1];

            // warningMap setting
            if(!warningMap.containsKey(badUser)){
                warningMap.put(badUser, 1);
            }else{
                if(!infoMap.get(reporter).contains(badUser)){
                    // 해당 신고자가 전에 신고하지 않았으면 count++
                    warningMap.put(badUser, warningMap.get(badUser) + 1);
                }
            }

            // infoMap setting
            infoMap.get(reporter).add(badUser);
        }

        // setting WarningResultSet
        Set<Map.Entry<String, Integer>> entries = warningMap.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            if(entry.getValue() >= k){
                warningResultSet.add(entry.getKey());
            }
        }

        int[] mailArr = new int[id_list.length];
        for(int i = 0; i < id_list.length; i++){
            String targetId = id_list[i];
            Set<String> badUsers = infoMap.get(targetId);
            badUsers.retainAll(warningResultSet);
            mailArr[i] = badUsers.size();
        }

        return mailArr;
    }
}

/**
 * Map, Set 자료구조를 이용하여 품
 *
 * Set의 retainAll을 이용해서, 교집합을 구함.
 * waringResultSet -> k이상 신고받은 유저들
 *
 * info.get(id) -> 보고자가 신고한 유저들
 *
 * 몇번 메일을 보고자가 받은지 알려면 두 집합의 교집합 개수를 구하면됨
 *
 * (주의)
 * 이미 신고자가 신고한기록이있으면 count++하지 않는다.
 */