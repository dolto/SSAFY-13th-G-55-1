import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	/// 아이디어
	// 존재하는지만 확인하면 되기 때문에, 다음과같은 과정을 따르면 된다.
	// 일단 l=0, r=N-1, 으로 설정하여 왼쪽, 오른쪽 (start, end라 생각해도 좋다)을 정한다.
	// m(middle)을 (l+r)/2로 설정한다.
	// m위치가 찾고자 하는 값보다 작으면, l과 r의 위치를 m 그 다음으로 갱신해준다.
	// l과r이 유효한 동안 반복해주고, 만약 값을 찾는다면 break;해준다.
	public static void main(String args[]) throws IOException{
		final int N = Integer.parseInt(br.readLine().trim());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		
		final int M = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine());
		for(int f = 0; f < M; ++f) {
			int find = Integer.parseInt(st.nextToken());
			int l = 0, r = N - 1;
			int m = r / 2;
			
			while(l < r) {
				if(nums[m] > find) {
					r = m - 1;
				}else if(nums[m] < find) {
					l = m + 1;
				}else {
					break;
				}
				m = (l + r)/2;
			}
			if(find == nums[m]) {
				bw.write("1\n");
			}else {
				bw.write("0\n");
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
