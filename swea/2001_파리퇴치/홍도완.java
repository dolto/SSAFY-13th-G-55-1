import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	/// 시간제한: 30초
	/// 메모리제한: 256mb
	/// 시간: 82ms
	/// 메모리: 24mb
	//
	/// 아이디어
	// 2차원이지만, 1차원 여러개를 투포인터로 누적합을 구하면 된다.
	// 가로로 마지막까지 이동하고나서, 아래로 한칸, 왼쪽으로 쭉 이동하고나서 아래로 한칸...
	// 하려고 했지만 임채진형의 갓갓 조언이 있으셨다.
	// 하다가 로직 실수한건 송기창형이 제공해준 자료로 교정하였다... 압도적 감사 ㅠㅠㅠㅠ
	public static void main(String[] args) throws IOException {
		final int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			final int N = Integer.parseInt(st.nextToken());
			final int M = Integer.parseInt(st.nextToken());
			PrefixSum ps = new PrefixSum(N, M);
			int[][] map = new int[N][N];
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = 0;

			ps.initMap(map);
			for(int i = M - 1; i < N; ++i) {
				for(int j = M - 1; j < N; ++j) {
					answer = Math.max(answer, ps.getSum(i, j));
				}
			}
			
			bw.write(String.format("#%d %d\n", t, answer));
		}
		bw.flush();
		br.close();
		bw.close();
	}

	static class PrefixSum {
		int[][] sumMap;
		int N, M;

		public PrefixSum(int N, int M) {
			this.N = N;
			this.M = M;
			sumMap = new int[N][N];
		}

		void initMap(int[][] map) {
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					int ti = i - 1;
					int tj = j - 1;
					int temp = 0;

					if (ti >= 0) {
						temp += sumMap[ti][j];
					}
					if (tj >= 0) {
						temp += sumMap[i][tj];
					}
					if (ti >= 0 && tj >= 0) {
						temp -= sumMap[ti][tj];
					}
					temp += map[i][j];

					sumMap[i][j] = temp;
				}
			}
		}

		int getSum(int r, int c) {
			int tr = r - M;
			int tc = c - M;
			int result = sumMap[r][c];
			if (tc >= 0)
				result -= sumMap[r][tc];
			if (tr >= 0)
				result -= sumMap[tr][c];
			if (tr >= 0 && tc >= 0)
				result += sumMap[tr][tc];
			return result;
		}
	}
}
