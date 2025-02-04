package algo_study;

import java.io.*;
import java.util.*;

public class baekjoon_2116 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] d = new int[n][6];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				d[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;
		for (int i = 0; i < 6; i++) {
			int sum = 0;
			int bottom = d[0][i];
			int top;
			if (i == 0)
				top = d[0][5];
			else if (i == 1)
				top = d[0][3];
			else if (i == 2)
				top = d[0][4];
			else if (i == 3)
				top = d[0][1];
			else if (i == 4)
				top = d[0][2];
			else
				top = d[0][0];

			int maxSide = 0;
			for (int j = 0; j < 6; j++) {
				if (d[0][j] != bottom && d[0][j] != top) {
					maxSide = Math.max(maxSide, d[0][j]);
				}
			}
			sum += maxSide;

			for (int j = 1; j < n; j++) {
				int bottom2 = top;
				int top2 = 0;

				for (int k = 0; k < 6; k++) {
					if (d[j][k] == bottom2) {
						if (k == 0)
							top2 = d[j][5];
						else if (k == 1)
							top2 = d[j][3];
						else if (k == 2)
							top2 = d[j][4];
						else if (k == 3)
							top2 = d[j][1];
						else if (k == 4)
							top2 = d[j][2];
						else
							top2 = d[j][0];
						break;
					}
				}

				int maxSide2 = 0;
				for (int k = 0; k < 6; k++) {
					if (d[j][k] != bottom2 && d[j][k] != top2) {
						maxSide2 = Math.max(maxSide2, d[j][k]);
					}
				}
				sum += maxSide2;
				top = top2;
			}
			ans = Math.max(ans, sum);
		}

		System.out.println(ans);
	}
}
