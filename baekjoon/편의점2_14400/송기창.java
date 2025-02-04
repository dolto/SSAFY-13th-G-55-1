package algo_study;

import java.util.*;
import java.io.*;

// 아이디어
// 각 x와 y좌표를 정렬해두고 중앙에 위치하는 값을 구해서 거리를 계산하면 그게 최소 거리의 합이 됩니다.
// 주의점: 최종 거리 계산할때 overflow 방지를 위해 long 타입 선언이 필요합니다.
// 주의점2: scanner를 사용하면 안되는 것 같습니다.
public class baekjoon_14400 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] xList = new int[n];
		int[] yList = new int[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			xList[i] = Integer.parseInt(st.nextToken());
			yList[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(xList);
		Arrays.sort(yList);

		int ansX = xList[n / 2];
		int ansY = yList[n / 2];

		long distance = 0;
		for (int i = 0; i < n; i++) {
			distance += Math.abs(xList[i] - ansX) + Math.abs(yList[i] - ansY);
		}
		System.out.print(distance);
	}
}
