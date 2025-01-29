package algo_study;

import java.util.Scanner;

public class baekjoon_2563 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 문제에서 도화지의 크기를 100으로 고정했기 때문에 크기를 고정해놓고 풀면 편합니다.
		int[][] matrix = new int[101][101];

		int paper = sc.nextInt();
		for (int i = 0; i < paper; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			// 색종이의 크기는 항상 10으로 고정되어 있기 때문에 해당 구간만큼 값을 증가시킵니다.
			for (int j = a; j <= a + 9; j++) {
				for (int k = b; k <= b + 9; k++) {
					matrix[j][k]++;
				}
			}
		}

		// 도화지를 순회하면서 영역의 넓이를 구하고 출력합니다.
		int count = 0;
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				if (matrix[i][j] > 0)
					count++;
			}
		}
		System.out.println(count);
	}

}