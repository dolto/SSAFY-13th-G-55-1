import java.io.*;
import java.util.*;

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int answer1 = 0, answer2 = 0;

	/// 아이디어
	// 문제를 대충 읽으면 각각의 카드 9장의 내는 순서까지 고려해야하지만
	// 이미 한쪽의 내는 순서는 고정되어 있으므로, 다른 쪽의 모든 경우의 수를 구하면 된다.
	// 승리하는 수만 세는 것이 아니라, 패배하는 수도 세야하기 때문에, 가지치기는 아마 안 될 듯 하다.
	public static void main(String[] args) throws IOException {
		final int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			boolean[] visited = new boolean[19];
			int[] p1 = new int[9];
			for (int i = 0; i < 9; ++i) {
				int card = Integer.parseInt(st.nextToken());
				visited[card] = true;
				p1[i] = card;
			}
			
			solve(0,p1,0,0,visited);
			bw.write(String.format("#%d %d %d\n", t, answer2, answer1));
			answer1 = 0; answer2 = 0;
		}
		bw.flush();
		bw.close();
		br.close();
	}

	static void solve(int cnt, int[] p1, int p1point, int p2point, boolean[] visited) {
		if (cnt == 9) {
			if (p1point > p2point) {
				answer2 += 1;
			} else {
				answer1 += 1;
			}
			return;
		}

		for (int i = 1; i <= 18; ++i) {
			if (!visited[i]) {
				visited[i] = true;
				if (i > p1[cnt]) {
					p2point += i + p1[cnt];
					solve(cnt + 1, p1, p1point, p2point, visited);
					p2point -= i + p1[cnt];
				} else {
					p1point += i + p1[cnt];
					solve(cnt + 1, p1, p1point, p2point, visited);
					p1point -= i + p1[cnt];
				}
				visited[i] = false;
			}
		}
	}
}
