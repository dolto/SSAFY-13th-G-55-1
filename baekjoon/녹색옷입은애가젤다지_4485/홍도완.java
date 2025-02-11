import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 홍도완 {
	/// 아이디어
	/// 다익스트라를 구현한다.
	/// TreeSet으로 하지 말것... 이거 Set이다...
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		int T = 0;
		int[][] offsetMove = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		while (N != 0) {
			T += 1;
			int[][] map = new int[N][N];
			int[][] timemap = new int[N][N];
			Queue<Point> moves = new PriorityQueue<>();
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
					timemap[i][j] = Integer.MAX_VALUE;
//					timemap[i][j] = 100;
				}
			}
			moves.add(new Point(0, 0, map[0][0]));
			timemap[0][0] = map[0][0];
			while (!moves.isEmpty()) {
				Point point = moves.poll();
				for (int i = 0; i < 4; ++i) {
					int tx = offsetMove[i][0] + point.x;
					int ty = offsetMove[i][1] + point.y;
					if (tx >= 0 && tx < N && ty >= 0 && ty < N) {
						int temp = map[ty][tx] + point.k;
						if (timemap[ty][tx] > temp) {
							timemap[ty][tx] = temp;
							moves.add(new Point(tx, ty, temp));
						}
					}
				}

//				bw.write(String.format("%d %d\n", point.y, point.x));
//				for (int i = 0; i < N; ++i) {
//					for (int j = 0; j < N; ++j) {
//						bw.write(String.format("%2d\t", timemap[i][j]));
//					}
//					bw.write("\n");
//				}
//				bw.write("\n");
			}

			bw.write(String.format("Problem %d: %d\n", T, timemap[N - 1][N - 1]));
			N = Integer.parseInt(br.readLine());
		}
		br.close();
		bw.flush();
		bw.close();
	}
}

class Point implements Comparable<Point> {
	int x, y, k;

	Point(int x, int y, int k) {
		this.x = x;
		this.y = y;
		this.k = k;
	}

	@Override
	public int compareTo(Point o) {
		return Integer.compare(this.k, o.k);
	}
}
