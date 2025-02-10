import java.util.*;
import java.io.*;
public class Main_13300 {
	public static void main(String[] args) throws IOException{
		//아이디어 : 여학생과 남학생을 맵으로 각각 받아 계산을 하였습니다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		HashMap<Integer, Integer> map0 = new HashMap<>();
		HashMap<Integer, Integer> map1 = new HashMap<>();
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(a == 0) {
				map0.put(b, map0.getOrDefault(b, 0) + 1);
			}else {
				map1.put(b, map1.getOrDefault(b, 0) + 1);
			}
		}
		int sum = 0;
		if(!map0.isEmpty()) {
			for(int i : map0.keySet()) {
				int value = map0.get(i);
				if(value <= K) {
					sum += 1;
				}else if(value % K == 0 ) {
					sum += value / K;
				}else {
					sum += value / K + 1;
				}
				
			}
		}
	
		if(!map1.isEmpty()) {
			for(int i : map1.keySet()) {
				int value = map1.get(i);
				if(value <= K) {
					sum += 1;
				}else if(value % K == 0) {
					
				}else {
					sum += value / K + 1;
				}
			
				
			}
		}
	

		System.out.println(sum);
	}
	
}
