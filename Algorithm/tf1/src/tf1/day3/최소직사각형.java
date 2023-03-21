package tf1.day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 최소직사각형 {
    public static void main(String[] args) {
        최소직사각형 instance = new 최소직사각형();
        System.out.println(instance.solution(new int[][]{{60, 50}, {30, 70}, {60, 30}, {80, 40}}));
    }
    public int solution(int[][] sizes) {
        int answer = 0;
        List<Integer> widthList = new ArrayList<>();
        List<Integer> heightList = new ArrayList<>();

        for (int[] size : sizes) {
            // 각 명함 오름차순 정렬
            Arrays.sort(size);

            int width = size[0];
            int height = size[1];
            widthList.add(width);
            heightList.add(height);
        }

        int maxWidth = widthList.stream().mapToInt(i -> i).max().getAsInt();
        int maxHeight = heightList.stream().mapToInt(i -> i).max().getAsInt();


        return maxWidth * maxHeight;
    }
}
