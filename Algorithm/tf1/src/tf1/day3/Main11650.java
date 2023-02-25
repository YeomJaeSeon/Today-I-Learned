package tf1.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main11650 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Point> list = new ArrayList<>();

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Point(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            ));
        }

        Collections.sort(list, (o1, o2) -> {
            if (o1.x != o2.x) {
                return o1.x - o2.x;
            } else {
                return o1.y - o2.y;
            }
        });

        for (Point point : list) {
            System.out.println(point.x + " " + point.y);
        }
    }
}
class Point{
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
