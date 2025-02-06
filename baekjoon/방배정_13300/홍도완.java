import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 홍도완 {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	/// 아이디어
	// 성별은 남/여이고, 학년은 1~6학년 까지 있다. 이를 통해 학생은 int[2][6]의 배열에 넣을 수 있다.
	// 분류된 학생을 순회하며 k로 나누고, 나머지가 있다면 방을 하나 더 배정하는 식으로 방을 배정한다.
	// 전체 방의 개수를 출력하면 된다. (이게 최소임을 보장한다)
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] students = new int[2][6];
		for(int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken()); 
			students[s][c - 1] += 1;
		}
		
		int answer = 0;
		for(int i = 0; i < 2; ++i) {
			for(int j = 0; j < 6; ++j) {
				int cnt = students[i][j] / k;
				answer += cnt;
				if(students[i][j] % k > 0) answer += 1;
			}
		}
		
		bw.write(Integer.toString(answer));
		bw.flush();
		bw.close();
		br.close();
	}

}
