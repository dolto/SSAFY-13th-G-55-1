package algo_study;

import java.util.*;

// 아이디어
// 6목 이상을 체크하는 것이 중요한 문제입니다. 6목 이상을 체크하는 로직을 섬세하게 구현하지 않으면 통과할 수 없습니다.
// 다양한 방법을 시도하다가 다음과 같이 구현했습니다.
// 저는 6목 체크를 위해 먼저 나온 돌에서부터 오목 체크를 진행하고
// 이후 이미 체크한 돌을 지나게 되면 이전 돌에서 확인했기 때문에 건너 뛰도록 구현했습니다.
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 방향벡터는 4가지입니다.
		int[] dx = { -1, 1, 1, 0 };
		int[] dy = { 1, 1, 0, 1 };

		int[][] d = new int[19][19];
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				d[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (d[i][j] != 0) {
					int type = d[i][j];
					for (int k = 0; k < 4; k++) {
						// 이전 돌이 같은 타입이면 이미 이전 돌에서 확인했기 때문에 건너 뜁니다.
						int px = i - dx[k];
						int py = j - dy[k];
						if (px >= 0 && px < 19 && py >= 0 && py < 19 && d[px][py] == type)
							continue;

						int count = 1;
						int nx = i;
						int ny = j;
						while (true) {
							nx += dx[k];
							ny += dy[k];
							if (nx < 0 || nx >= 19 || ny < 0 || ny >= 19 || d[nx][ny] != type)
								break;
							count++;
						}
						if (count == 5) {
							System.out.println(type);
							// 시작 좌표는 배열 index값 0부터 시작이라 둘 다 +1
							System.out.printf("%d %d\n", i + 1, j + 1);
							// 승리 확인시 종료
							return;
						}
					}
				}
			}
		}
		// 여기까지 오면 무승부
		System.out.println(0);
		sc.close();
	}

}
