import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] orders = new int[n];
        String[] s = br.readLine().split(" ");
        for (int i=0; i < n; i++) {
            // 현재 순서 번호 저장
            orders[i] = i+1;
            // 바뿨야하는 범위 저장
            int change = Integer.parseInt(s[i]);
            // 현재 위치에서 바꿔야 하는 범위까지 앞뒤로 교환하며 갱신
            for (int j=i; j > i-change; j--) {
                int temp = orders[j];
                orders[j] = orders[j-1];
                orders[j-1] = temp;
            }
        }

        for (int i=0; i < n; i++) {
            bw.write(""+orders[i]+" ");
        }

        bw.flush();

    }
}