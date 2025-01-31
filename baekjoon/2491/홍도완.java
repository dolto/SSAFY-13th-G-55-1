import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/// 아이디어
///증가하는 수열과 감소하는 수열중 가장 긴 값을 구하면된다
///증가하는 수열과 감소하는 수열의 최댓값을 저장하는 UpMAX, DownMAX 변수를 선언하고 1로 초기화한다
///(N은 항상 1 이상이며, 수가 하나인 수열은 무조건 조건을 만족하기 때문에 1로 초기화하는 것)
///이전 수를 받는 current변수를 선언하고 최초 수열의 수를 받고, 나머지를 반복문으로 돌리며 temp로 받습니다
///반복문이 끝날 때 마다 current변수를 temp로 갱신하며, 이전수와 현재수를 비교할 수 있도록 합니다.
public class 홍도완 {
	static int UpMAX = 1, DownMAX = 1, N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine().trim());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int current = Integer.parseInt(st.nextToken());
		int up = 1, down = 1;
		for(int i = 1; i < N; ++i) {
			int temp = Integer.parseInt(st.nextToken()); 
			if(current >= temp) {
				down += 1;
				DownMAX = Math.max(DownMAX, down);
			}else {
				down = 1;
			}
			
			if(current <= temp) {
				up += 1;
				UpMAX = Math.max(UpMAX, up);
			}else {
				up = 1;
			}
			current = temp;
		}
		
		bw.write(String.format("%d", Math.max(UpMAX, DownMAX)));
		
		br.close();
		bw.flush();
		bw.close();
	}
}
