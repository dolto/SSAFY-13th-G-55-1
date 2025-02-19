import java.io.*;
import java.util.*;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static long maxAns = 0;
	static long minAns = Long.MAX_VALUE;

	/// 아이디어
	// 0~k+1까지의 수를 선택하고, 중간에 부등호가 틀리다면 가지치기
	// int를 넘는 수이기 때문에 (10자리 수는) 이점을 주의
	public static void main(String[] args) throws IOException {
		final int N = Integer.parseInt(br.readLine().trim()) + 1;
		final char[] InequalitySign = br.readLine().toCharArray();
		int[] answer = new int[N];

		solve(0, N, -2,InequalitySign, answer, new boolean[10]);
		String tempMax = Long.toString(maxAns);
		String tempMin = Long.toString(minAns);
		
		if(tempMax.length() != N) {
			tempMax = "0"+tempMax;
		}
		if(tempMin.length() != N) {
			tempMin = "0"+tempMin;
		}
		bw.write(String.format("%s\n%s", tempMax, tempMin));
		bw.flush();
		bw.close();
		br.close();
	}

	static long getAnswerToInteger(int[] answer) throws IOException {
		StringBuilder sb = new StringBuilder(answer.length * 2);
		for (int a : answer) {
			sb.append(a);
		}
		return Long.parseLong(sb.toString());
	}

	static void solve(int cnt, int n, int inequlityIndex, char[] InequlitySign, int[] answer, boolean[] visited) throws IOException {
		if (cnt == n) {
			long ans = getAnswerToInteger(answer);
			maxAns = Math.max(maxAns, ans);
			minAns = Math.min(minAns, ans);
			return;
		}

		SEARCH: for (int i = 0; i < 10; ++i) {
			if (!visited[i]) {
				if(cnt > 0) {
					switch(InequlitySign[inequlityIndex]) {
					case '<':
						if(answer[cnt - 1] > i) continue SEARCH;
						break;
					case '>':
						if(answer[cnt - 1] < i) continue SEARCH;
						break;
					}
				}
				visited[i] = true;
				answer[cnt] = i;
				solve(cnt + 1, n, Math.min((n - 2) * 2, inequlityIndex + 2), InequlitySign, answer, visited);
				answer[cnt] = 0;
				visited[i] = false;
			}
		}
	}
}
