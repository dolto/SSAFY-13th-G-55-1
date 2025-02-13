import java.util.*;
import java.io.*;

// 아이디어
// 슬라이딩 윈도우 방식으로 해결했습니다.
// 첫 인덱스부터 k개의 이름 길이에 해당하는 수를 모두 카운팅 해두고
// 다음 인덱스부터 해당 이름 길이에 해당하는 이름들 수를 모두 카운팅하고
// 새로운 인덱스의 이름 길이는 추가, 카운팅한 이름은 삭제하는 방식으로 진행하면 됩니다.
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		String[] name = new String[n];
		for (int i = 0; i < n; i++) {
			name[i] = br.readLine();
		}

		// (3 ≤ N ≤ 300,000, 1 ≤ K ≤ N) 범위상 long을 사용하지 않으면 틀림
		long ans = 0;

		int[] nameLen = new int[25];
		// 최초 m만큼 이름 길이 해당하는 카운트 수 증가
		for (int i = 0; i <= m; i++) {
			nameLen[name[i].length()]++;
		}
		// 첫 index 쌍 추가
		ans += (nameLen[name[0].length()] - 1);
		// 첫 index 제거
		nameLen[name[0].length()]--;
		
		int end=m;
		// 슬라이딩 윈도우
		for (int i = 1; i < n; i++) {
			// 뒤에 값 추가가 가능한지
			if(end+1<n) {
				end++;
				nameLen[name[end].length()]++;
			}
			
			ans += (nameLen[name[i].length()] - 1);
			
			// 반복이 종료될 때 항상 제일 앞의 값 버리기
			nameLen[name[i].length()]--;
		}
		System.out.println(ans);
	}
}
