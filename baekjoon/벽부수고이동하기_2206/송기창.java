import java.util.*;
import java.io.*;

// 아이디어
// BFS 문제입니다.
// 벽을 부수지 않고 이동하는게 더 빠를수도 있기 때문에
// 벽을 부수고 이동하는 경우와 벽을 부수지 않고 이동하는 경우를 따로 생각해야합니다.
public class Main {
	// 이동 방향
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		// 맵의 크기
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 맵의 상태 입력
		int[][] d = new int[n][m];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {
				d[i][j] = (int) (s.charAt(j) - '0');
			}
		}

		// 벽을 부순 경우와 부수지 않은 경우로 나누기 위해 3차원 방문배열 선언
		boolean[][][] visit = new boolean[n][m][2];
		Queue<int[]> q = new ArrayDeque<>();

		// queue에 y, x, 이동거리, 벽 파괴여부를 저장
		q.add(new int[] { 0, 0, 1, 0 });
		visit[0][0][0] = true;
		// BFS 탐색 시작
		while (!q.isEmpty()) {
			int[] now = q.peek();
			q.remove();
			int y = now[0];
			int x = now[1];
			int distance = now[2];
			int distroy = now[3];

			// 도착시 정답 출력 후 종료
			if (y == n - 1 && x == m - 1) {
				System.out.print(distance);
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				// 범위 초과 검사
				if (ny < 0 || nx < 0 || ny >= n || nx >= m)
					continue;
				// 벽이 아니면 바로 이동
				if (d[ny][nx] == 0 && !visit[ny][nx][distroy]) {
					visit[ny][nx][distroy] = true;
					q.add(new int[] { ny, nx, distance + 1, distroy });
				}
				// 벽인데 아직 부수지 않았을때 부수고 이동
				if (d[ny][nx] == 1 && distroy == 0 && !visit[ny][nx][1]) {
					visit[ny][nx][distroy] = true;
					q.add(new int[] { ny, nx, distance + 1, 1 });
				}
			}
		}
		System.out.println(-1);
	}
}