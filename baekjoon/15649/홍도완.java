import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	///아이디어
	///정석적인 백트래킹 문제
	///n을 골랐다고 가정 했을 때, 그 외의 선택하지 않은 숫자를 선택 (지금까지 선택했던 선택지를 기억해야함)
	///문자열을 저장하는게 출력할 때 용이하다.
	///Stringbuilder로 선택지가 쌓이는게 좋다... 하지만 매개변수를 복제하지 않으면 어렵다
	///visited[i]를 백트래킹과정에서 해제해주는걸, 응용해서 StringBuilder에서도 선택지를 해제해주면 되지 않을까? 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N,M;

	static public void main(String[] args) throws IOException {
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[N];	
		
		dfs(0,visited, new StringBuilder(M * 2));

		br.close();
		bw.flush();
		bw.close();
	}
	
	static void dfs(int n, boolean[] visited, StringBuilder ans) throws IOException {
		if(n == M) {
			bw.write(ans+"\n");
			return;
		}
		
		for(int i = 0; i < N; ++i) {
			if(!visited[i]) {
				visited[i] = true;
				ans.append((i + 1) + " ");
				dfs(n+1,visited,ans);
				ans.setLength(ans.length() - 2);;
				visited[i] = false;
			}

		}
	}
}
