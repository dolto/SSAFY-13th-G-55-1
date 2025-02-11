import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 홍도완 {
	/// 아이디어
	/// 정수 k가 주어지기 때문에 이동횟수가 바닥나고, 사각으로 이동하면서 찾는다.
	/// 최대한 짧게 이동해야하니 BFS로 푸는 것이 유리하다.
	/// (DFS의 경우 무한루프에 빠질 수 있다.)
	/// BFS이므로 도착하면 그 시점이 최소 거리이다.
	/// 정수 k가 주어지며 이를 적절하게 이용해야 통과할 수 있다
	/// 즉, map에 위치한 원숭이가 말의 이동을 많이 할 수 있다면 좋다.
	/// map에 위치한 원숭이가 말의 이동을 몇 번 할 수 있는지 알 수 있게 해야하며, 해당 위치로 이동했어도, 만약 말의 이동을 더 많이 할 수 있는 상태라면, 고려해야 하는 사항이다.
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		final int k = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine());

		final int W = Integer.parseInt(st.nextToken());
		final int H = Integer.parseInt(st.nextToken());
		int[][][] map = new int[H][W][2];
		for (int h = 0; h < H; ++h) {
			st = new StringTokenizer(br.readLine());
			for (int w = 0; w < W; ++w) {
				int temp = Integer.parseInt(st.nextToken());
				map[h][w][0] = temp;
			}
		}

		Queue<Point> move = new ArrayDeque<>(200);
		{
			Point temp = new Point(0, 0, k, 0);
			move.add(temp);
		}

		int[][] offsetKight = { { 2, 1 }, { 2, -1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }, { -2, -1 }, { 1, -2 },
				{ -1, -2 }, };
		int[][] offsetMonky = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

		int answer = -1;

		while (!move.isEmpty()) {
			Point point = move.poll();
			if (point.x == W - 1 && point.y == H - 1) {
				answer = point.answer;
				break;
			}
			for (int i = 0; i < 4; ++i) {
				int tx = offsetMonky[i][0] + point.x;
				int ty = offsetMonky[i][1] + point.y;
				if (tx >= 0 && tx < W && ty >= 0 && ty < H) {
					if (map[ty][tx][0] == 0 || (map[ty][tx][1] < point.k && map[ty][tx][0] != 1)) {
						// 0과 1이 들어가면 안되므로 +3으로 맞춰준다. (그것 외에 딱히 의미는 없다)_
						map[ty][tx][0] = point.answer + 3;
						map[ty][tx][1] = point.k;
						move.add(new Point(tx, ty, point.k, point.answer + 1));
					}
				}
			}

			if (point.k > 0) {
				for (int i = 0; i < 8; ++i) {
					int tx = offsetKight[i][0] + point.x;
					int ty = offsetKight[i][1] + point.y;
					if (tx >= 0 && tx < W && ty >= 0 && ty < H) {
						if (map[ty][tx][0] == 0 || (map[ty][tx][1] < point.k - 1 && map[ty][tx][0] != 1)) {
							// 0과 1이 들어가면 안되므로 +3으로 맞춰준다. (그것 외에 딱히 의미는 없다)
							map[ty][tx][0] = point.answer + 3;
							map[ty][tx][1] = point.k - 1;
							move.add(new Point(tx, ty, point.k - 1, point.answer + 1));
						}
					}
				}
			}

//			bw.write(String.format("%d %d\n", point.y, point.x));
//			for (int i = 0; i < H; ++i) {
//				for (int j = 0; j < W; ++j) {
//					bw.write(String.format("%d,%d\t", map[i][j][0], map[i][j][1]));
//				}
//				bw.write("\n");
//			}
//			bw.write(String.format("\n", point.y, point.x));
		}

		bw.write(String.format("%d", answer));

		br.close();
		bw.flush();
		bw.close();
	}
}

class Point {
	int x, y, k, answer;

	Point(int x, int y, int k, int answer) {
		this.x = x;
		this.y = y;
		this.k = k;
		this.answer = answer;
	}
}
