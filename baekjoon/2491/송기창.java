package algorithm;

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(bf.readLine());

		int[] d = new int[n];
		for (int i = 0; i < n; i++) {
			d[i] = Integer.parseInt(st.nextToken());
		}

		int maxLen = 1;

		// 증가수열
		int index = 0;
		while (true) {
			if (index >= (n - 1))
				break;

			int sum = 1;
			for (int i = index + 1; i < n; i++) {
				index++;
				if (d[i - 1] <= d[i]) {
					sum++;
				} else
					break;
			}
			if (sum > maxLen) {
				maxLen = sum;
			}
		}

		// 감소수열
		index = 0;
		while (true) {
			if (index >= (n - 1))
				break;

			int sum = 1;
			for (int i = index + 1; i < n; i++) {
				index++;
				if (d[i - 1] >= d[i])
					sum++;
				else
					break;
			}
			if (sum > maxLen) {
				maxLen = sum;
			}
		}

		// 정답 출력
		System.out.println(maxLen);
	}
}
