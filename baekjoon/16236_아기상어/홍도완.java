import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	/// 시간제한: 2초
	/// 메모리제한: 512mb
	/// 시간: 112ms
	// 메모리: 14mb
	//
	/// 아이디어
	// 조건에 맞춰서 아기상어를 이동시키면 된다... 정렬에 주의

	public static void main(String[] args) throws IOException {
		final int N = Integer.parseInt(br.readLine());
		Fish[][] map = new Fish[N][N];
		Shark shark = null;

		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 9) {
					shark = new Shark(j, i);
					map[i][j] = new Fish(j, i, 0);
				} else {
					map[i][j] = new Fish(j, i, temp);
				}
			}
		}

//		for(int i = 0; i < N; ++i) {
//			for(int j = 0; j < N; ++j) {
//				bw.write(String.format("%d ", map[i][j].size));
//			}
//			bw.write("\n");
//		}

		int answer = 0;
		int temp = shark.Move(map);
		while (temp != 0) {
			answer += temp;
			temp = shark.Move(map);
		}
		bw.write(Integer.toString(answer));

		bw.flush();
		br.close();
		bw.close();
	}

	static class Fish implements Comparable<Fish> {
		int x, y, size, layer = 0;

		Fish(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}

		@Override
		public int compareTo(Fish o) {
			if (this.layer == o.layer) {
				if (this.y == o.y) {
					return Integer.compare(this.x, o.x);
				}
				return Integer.compare(this.y, o.y);
			}
			return Integer.compare(this.layer, o.layer);
		}
	}

	static class Shark extends Fish {
		int point = 0;

		Shark(int x, int y) {
			super(x, y, 2);
		}

		int Move(Fish[][] map) {
			final int N = map.length;
			Queue<Fish> queue = new ArrayDeque<>();
			ArrayList<Fish> targets = new ArrayList<>();
			boolean[][] visited = new boolean[N][N];
			visited[this.y][this.x] = true;
			queue.add(this);

			int[][] offset = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

			this.layer = 0;
//			for(int i = 0; i < N; ++i) {
//				for(int j = 0; j < N; ++j) {
//					map[i][j].layer = 0;
//				}
//			}
			while (!queue.isEmpty()) {
				Fish fish = queue.poll();
//				for (int j = 0; j < N; ++j) {
//					for (int k = 0; k < N; ++k) {
//						System.out.print(map[j][k].layer + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
				for (int i = 0; i < 4; ++i) {
					int ty = offset[i][0] + fish.y;
					int tx = offset[i][1] + fish.x;
					if (ty >= 0 && ty < N && tx >= 0 && tx < N) {
						Fish target = map[ty][tx];
						if (visited[ty][tx]) {
							continue;
						}
						target.layer = fish.layer + 1;
						if ((target.size == 0 || target.size == this.size) && targets.isEmpty()) {
							visited[ty][tx] = true;
							queue.add(target);
						} else if (target.size != 0 && target.size < this.size) {
							visited[ty][tx] = true;
							targets.add(target);
						}
					}
				}
			}
			if (targets.isEmpty()) {
				return 0;
			}

			Collections.sort(targets);
//			System.out.println(targets.get(0).y + " " + targets.get(0).x + " " + targets.get(0).layer);
			targets.get(0).size = 0;
			this.point += 1;
			if (this.point == this.size) {
				this.size += 1;
				this.point = 0;
			}
			this.x = targets.get(0).x;
			this.y = targets.get(0).y;
			return targets.get(0).layer;
		}
	}
}
