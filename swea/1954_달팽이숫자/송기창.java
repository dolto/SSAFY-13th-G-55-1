import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tcn = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= tcn; tc++) {
			int n = Integer.parseInt(br.readLine());
			System.out.printf("#%d\n", tc);

			// 달팽이 배열 채우기
			int[][] d = new int[n][n];
			int num = 1;
			for (int i = 0; i < n; i++) {
				for (int j = i; j < n - i; j++) {
					d[i][j] = num;
					num++;
				}
				for (int j = i+1; j < n - i; j++) {
					d[j][n - i - 1] = num;
					num++;
				}
				for (int j = n - i - 2; j >= 0 + i; j--) {
					d[n - i - 1][j] = num;
					num++;
				}
				for (int j = n - (i + 1) - 1; j >= 0 + (i + 1); j--) {
					d[j][0 + i] = num;
					num++;
				}
			}

			// 배열 출력부
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(d[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
