import java.io.*;
import java.util.*;

public class 홍도완 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/// 아이디어
	// N이 정렬되어있다면, 오름차순으로 할 수 있을 것이다... 수열을 출력하고, 중복수열을 피해야 한다.
    // N과 M시리즈중 안푼 문제가 6번부터이길레 6번 제출합니다.
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());
		final int M = Integer.parseInt(st.nextToken());
		int[] nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		
		solve(0,0,nums,M,new StringBuilder(), new boolean[N]);
		bw.flush();
		bw.close();
		br.close();
	}

	static void solve(int index, int cnt, int[] nums, int m, StringBuilder sb, boolean[] visited)throws IOException {
		if(cnt == m) {
			bw.write(String.format("%s\n", sb.toString()));
			return;
		}
		
		for(int i = index; i < nums.length; ++i) {
			if(!visited[i]) {
				String temp = String.format("%d ", nums[i]);
				sb.append(temp);
				visited[i] = true;
				solve(i + 1, cnt+1, nums, m, sb, visited);
				visited[i] = false;
				sb.setLength(sb.length() - temp.length());
			}
		}
	}
}
