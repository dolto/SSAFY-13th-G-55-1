import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 홍도완 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	// 시간: 1초
	// 메모리제한: 192mb
	// 사용시간: 112ms
	// 메모리: 14mb
	/// 아이디어
	// bfs로 가장 먼저 찾은 layer가 정답이다... 말이되고픈 원숭이의 하위 호환
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());
		final int M = Integer.parseInt(st.nextToken());
		final int[][] OFFSET = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

		char[][] map = new char[N][];
		for (int i = 0; i < N; ++i) {
			map[i] = br.readLine().toCharArray();
		}
		map[0][0] = '0';

		Queue<Cal> queue = new ArrayDeque<>();
		queue.add(new Cal(0, 0, 1));
		while (!queue.isEmpty()) {
			Cal cal = queue.poll();
			if (cal.r == N - 1 && cal.c == M - 1) {
				bw.write(Integer.toString(cal.layer));
				break;
			}
			for (int[] of : OFFSET) {
				int tr = of[0] + cal.r;
				int tc = of[1] + cal.c;
				if (tr >= 0 && tr < N && tc >= 0 && tc < M && map[tr][tc] == '1') {
					queue.add(new Cal(tr, tc, cal.layer + 1));
					map[tr][tc] = '0';
				}
			}
		}

		bw.flush();
		br.close();
		bw.close();
	}

	static class Cal {
		int r, c, layer;

		Cal(int r, int c, int layer) {
			this.r = r;
			this.c = c;
			this.layer = layer;
		}
	}
}
