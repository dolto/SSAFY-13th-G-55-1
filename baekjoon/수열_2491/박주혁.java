import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int[] nums = new int[n];
		
		String[] s = br.readLine().split(" ");
		for (int i=0; i < n; i++) {
			nums[i] = Integer.parseInt(s[i]);
		}
		
		int cnt = 0;
		int max = 0;
		
		// 연속해서 증가하는 수열 중 가장 긴 길이 검사 로직
		for (int i=0; i < n-1; i++) {
			cnt = 0;
			for (int j=i; j < n-1; j++) {
				if (nums[j] <= nums[j+1]) cnt++;
				else {
					break;
				}
			}
			if (cnt > max) max = cnt;
			
		}
		
		// 연속해서 감소하는 수열 중 가장 긴 길이 검사 로직
		for (int i=0; i < n-1; i++) {
			cnt = 0;
			for (int j=i; j < n-1; j++) {
				if (nums[j] >= nums[j+1]) cnt++;
				else {
					break;
				}
			}
			if (cnt > max) max = cnt;
			
		}
		
		// 기본 1개에 추가로 길이 더하여 출력
		bw.write(""+(max+1));
		bw.flush();
	}

}
