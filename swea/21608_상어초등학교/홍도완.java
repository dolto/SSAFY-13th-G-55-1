import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	/// 시간제한: 1초
	/// 메모리제한: 1024mb
	//
	/// 아이디어
	// 사방이 인접한 학생이다 학생1의 위치가, 1,2 이고, 학생2의 위치가 2,1인경우 (2 - 1) + (2 - 1) = 2 이므로 대각선은
	/// 안됨
	// 3차원 배열을 이용해서, 본인이 앉은 자리의 사방에 자신이 앉았다는 자신의 인덱스에 표시해주자.
	// [y][x][0] 에는 본인의 자리에 true를 넣고 (학생은 1번부터 시작), [y][x][i] 에는 학생이 앉은 사방에 true를
	/// 넣으면 될 거 같다.
	// 다른 학생이 앉을 때, 본인이 좋아하는 학생의 범위를 찾고, 그중 가장 많은 자리를 찾으면 된다.
	// 이렇게 해도 배열의 메모리는 최대 640kb정도이므로, 얼추 될 것 같다.
	public static void main(String[] args) throws IOException {
		final int N = Integer.parseInt(br.readLine().trim());
		final int S = N * N + 1;
		Point[] answerPoint = new Point[S];
		int answer = 0;
		boolean[][][] map = new boolean[N][N][S];
		int[][] friends = new int[S][4];

		int[][] offset = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }, };

		for (int i = 1; i < S; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int[][] likes = new int[N][N];

			for (int j = 0; j < 4; ++j) {
				int temp = Integer.parseInt(st.nextToken());
				friends[s][j] = temp;
			}

			int maxLike = 0;
			TreeSet<Point> maxPoints = new TreeSet<>();

			for (int r = 0; r < N; ++r) {
				for (int c = 0; c < N; ++c) {
					// 이미 누군가 앉았다면 논외로 처리함
					if (map[r][c][0]) {
						likes[r][c] = -1;
						continue;
					}

					for (int j = 0; j < 4; ++j) {
						if (map[r][c][friends[s][j]]) {
							likes[r][c] += 1;
						}
					}
					// 좋아하는 친구의 최대치가 많으면, 갱신하고, 기존에 넣었던 자리는 다 버림
					if (maxLike < likes[r][c]) {
						maxLike = likes[r][c];
						maxPoints.clear();
//						bw.write("clear\n");
					}
					// 근처의 빈자리를 계산 한 후, 좋아하는 친구가 가장 많은 자리에 추가함
					if (maxLike == likes[r][c]) {
						int temp = 0;
						for (int[] rc : offset) {
							int tr = rc[0] + r;
							int tc = rc[1] + c;
							if (tr >= 0 && tr < N && tc >= 0 && tc < N) {
								if (!map[tr][tc][0])
									temp += 1;
							}
						}
						maxPoints.add(new Point(r, c, temp));
					}
				}
			}

			// 언제나 근처에 친구가 가장 많고, 근처에 가장 빈자리가 많으며, 그중 가장 왼쪽위에 있는 자리임을 보장함
			Point target = maxPoints.first();
//			bw.write(String.format("%d %d %d %d\n", target.r, target.c, target.empty, maxLike));

			map[target.r][target.c][0] = true;
			for (int[] rc : offset) {
				int tr = rc[0] + target.r;
				int tc = rc[1] + target.c;
				if (tr >= 0 && tr < N && tc >= 0 && tc < N) {
					map[tr][tc][s] = true;
				}
			}
			answerPoint[s] = target;
		}


		for (int i = 1; i < S; ++i) {
			int temp = 0;
			for(int j = 0; j < 4; ++j) {
				if(map[answerPoint[i].r][answerPoint[i].c][friends[i][j]]) temp += 1;
			}
			
			switch(temp) {
			case 1:
				answer += 1;
				break;
			case 2:
				answer += 10;
				break;
			case 3:
				answer += 100;
				break;
			case 4:
				answer += 1000;
				break;
			}
		}
		bw.write(Integer.toString(answer));
		bw.flush();
		br.close();
		bw.close();
	}

	static class Point implements Comparable<Point> {
		int r, c, empty;

		Point(int r, int c, int empty) {
			this.r = r;
			this.c = c;
			this.empty = empty;
		}

		@Override
		public boolean equals(Object obj) {
			Point o = (Point) obj;
			return (o.c == this.c) && (o.r == this.r);
		}

		@Override
		public int compareTo(Point o) {
			// 만약 같다면 r이 작은 순으로
			if (o.empty == this.empty) {
				// 그것도 같다면 c가 작은 순으로
				if (this.r == o.r)
					return Integer.compare(this.c, o.c);
				return Integer.compare(this.r, o.r);
			}
			// 아니라면 empty가 많은 순으로
			return Integer.compare(o.empty, this.empty);
		}
	}
}
