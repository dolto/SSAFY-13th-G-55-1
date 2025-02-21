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
	/// 메모리제한: 256mb
	// 시간: 2.7초
	// 메모리: 249mb
	//
	/// 아이디어
	// 각구간을 계속해서 구해야한다...
	// 2차원 prefixSum을 구한다음, 각 구간의 범위를 구한다.
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());
		final int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		PrefixSum prefixSum = new PrefixSum(N);
		prefixSum.initMap(map);
		for(int i = 0; i < M; ++i) {
			st= new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken()) - 1;
			int c1 = Integer.parseInt(st.nextToken()) - 1;
			int r2 = Integer.parseInt(st.nextToken()) - 1;
			int c2 = Integer.parseInt(st.nextToken()) - 1;
			
			int sum = prefixSum.getSum(r2, c2, r1, c1);
			bw.write(String.format("%d\n", sum));
		}

		bw.flush();
		br.close();
		bw.close();
	}

	static class PrefixSum {
		int[][] sumMap;
		int N;

		public PrefixSum(int N) {
			this.N = N;
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

		int getSum(int r2, int c2, int r1, int c1) {
			int tr = r1 - 1;
			int tc = c1 - 1;
			int result = sumMap[r2][c2];
			if (tc >= 0)
				result -= sumMap[r2][tc];
			if (tr >= 0)
				result -= sumMap[tr][c2];
			if (tr >= 0 && tc >= 0)
				result += sumMap[tr][tc];
			return result;
		}
	}
}
