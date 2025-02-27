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

public class 홍도완 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	/// 시간제한: 1초
	/// 메모리제한: 256mb
	/// 시간: 544ms
	// 메모리: 285mb
	//
	/// 아이디어
	// 첫째 열에서 마지막열까지 연결 가능한 파이프를 구한다.
	// 맨 위의 첫째열에서 최대한 위쪽 반대편으로 연결에 성공하면 그게 최선의 방법이라고 가정하자
	// 최선의 방법을 찾았으니, 그 지점을 전부 건물로 처리하고, 다음을 보는 그리드 알고리즘을 해보자... 실패...
    // 근데 그렇다고 복원하지 않는 dfs가 맞는다? 뭔가 이상한 것 같지만, 위쪽을 우선적으로 봤기 때문에 
    // 그 아래 행에서 출발하는 파이프는 애초에 거길 갈 일이 없어야 한다라는 가정이 증명되는 가보다...
	// 아마 메모리도 평소처럼, 시간제한도 평소처럼 주었으니 dp는 아니지 않을까?

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int R = Integer.parseInt(st.nextToken());
		final int C = Integer.parseInt(st.nextToken());

		char[][] map = new char[R][C];
		for (int i = 0; i < R; ++i) {
			map[i] = br.readLine().trim().toCharArray();
		}

		int answer = 0;
		for (int i = 0; i < R; ++i) {
			int r = i, c = 0;
			if (solve(r, c, map, R, C)) {
//				for(char[] m:map) {
//					for(char cc : m) {
//						bw.write(String.format("%c ", cc));
//					}
//					bw.write("\n");
//				}
				answer += 1;
			}
		}

		bw.write(Integer.toString(answer));

		bw.flush();
		br.close();
		bw.close();
	}

	static boolean solve(int r, int c, char[][] map, final int R, final int C) {
		if (c == C - 1) {
			return true;
		}
		final int[][] offset = { { -1, 1 }, { 0, 1 }, { 1, 1 } };
		boolean ret = false;
		for (int[] o : offset) {
			int tr = o[0] + r;
			int tc = o[1] + c;
			if (tr >= 0 && tr < R && tc >= 0 && tc < C && map[tr][tc] == '.') {
				map[tr][tc] = 'x';
				ret = solve(tr, tc, map, R, C);
				if(ret) {
					break;
				}
//				map[tr][tc] = '.';
			}
		}

		return ret;
	}

}
