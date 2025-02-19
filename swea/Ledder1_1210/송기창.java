import java.util.*;
import java.io.*;

// 아이디어
// 도착점에서 역으로 탐색하여 목적지를 찾으면 됩니다.
// 문제에서도 인덱스 번호가 0, 0 에서 시작하기 때문에 이에 맞게 풀면 됩니다.
public class SWEA_1210_Ladder1_송기창 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 해당 문제의 경우 항상 tc가 10개 주어진다고 확정되어있음
		for (int tc = 1; tc <= 10; tc++) {
			// 이 경우 n도 항상 tc와 같음
			int n = Integer.parseInt(br.readLine());
			int dx = 0, dy = 0;

			// 100*100 크기 공간 정보 입력
			int[][] d = new int[100][100];
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					d[i][j] = Integer.parseInt(st.nextToken());
					// 도착하는곳 좌표 미리 구해두기
					if (d[i][j] == 2) {
						dx = j;
						dy = i;
					}
				}
			}

			// 시작점 찾아가는 부분
			// 방향을 나타낼 변수 mode, 0: 위, 1: 왼쪽, 2: 오른쪽
			int mode = 0;
			while (dy > 0) {
				// 왼쪽 만나면 방향 전환후 왼쪽으로 한칸 이동
				if (dx > 0 && mode != 2 && d[dy][dx - 1] == 1) {
					mode = 1;
					dx--;
					continue;
				}
				// 오른쪽 만나면 방향 전환후 오른쪽으로 한칸 이동
				else if (dx < 99 && mode != 1 && d[dy][dx + 1] == 1) {
					mode = 2;
					dx++;
					continue;
				}
				// 둘 다 아니면 위로 이동
				dy--;
				mode = 0;
			}
			System.out.printf("#%d %d\n", n, dx);
		}
	}
}
