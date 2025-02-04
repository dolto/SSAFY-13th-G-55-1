import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] box = new int[n][2];
        int[][] area = new int[100][100];
        int answer = 0;
        for (int i=0; i < n; i++) {
            String[] s = br.readLine().split(" ");

            box[i][1] = Integer.parseInt(s[0])-1;
            box[i][0] = 100 - Integer.parseInt(s[1])-1;
        }

        // 전체 흰색 도화지의 영역에서 색종이가 차지하고 있는 부분의 좌표를 1로 저장
        for (int i=0; i < n; i++) {
            for (int x=0; x < 10; x++) {
                for (int y=0; y < 10; y++) {
                    area[box[i][0]-x][box[i][1]+y] = 1;
                }
            }
        }
        
        // 도화지에서 1인 영역의 개수 카운트
        for (int i=0; i < 100; i++) {
            for (int j=0; j < 100; j++) {
                if (area[i][j] == 1) {
                    answer++;
                }
            }
        }


        bw.write(""+answer);
        bw.flush();


        // 기존에 n개의 색종이의 넓이를 더한 뒤, 겹치는 부분의 넓이를 따로 구하여 빼는 식으로 로직을 구현
        // 그러나 한개 이상 중첩된 넓이에 대해서 처리를 하는 부분이 복잡하여 실패
//        int dis_x = 0;
//        int dis_y = 0;
//        int dup = 0;
//        // 겹치는 부분 구하는 로직
//        for (int i=0; i < n; i++) {
//            for (int j=i+1; j < n; j++) {
//                dis_x = Math.max(box[i][0], box[j][0]) - Math.min(box[i][0], box[j][0]);
//                dis_y = Math.max(box[i][1], box[j][1]) - Math.min(box[i][1], box[j][1]);
//                if (dis_x < 10 && dis_y < 10) {
//                    dup += (10 - dis_x) * (10 - dis_y);
//                }
//            }
//        }

    }
}