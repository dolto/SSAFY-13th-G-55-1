package algo_study;

import java.util.*;
import java.io.*;

// 아이디어
// 그리디 문제입니다. 모든 구간을 a만큼 확인하면서 가장 수분의 합이 적은 구간을 기억해두고
// 그 구간에 물을 주는 방식을 반복하면 됩니다.
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		List<Integer> li = new ArrayList<>();
		// n개의 화분에 k개의 수분인 상태로 시작
		for (int i = 0; i < n; i++) {
			li.add(k);
		}

		int day=0;
		while(true){
			day++;
			int minsum = 9999999;
			int minidx = 0;
			// 수분의 합이 가장 작은 구간 구하기
			for (int i = 0; i <= n - a; i++) {
				int sum = 0;
				for (int j = i; j < i + a; j++) {
					sum += li.get(j);
				}
				if (sum < minsum) {
					minsum = sum;
					minidx = i;
				}
			}

			for (int i = minidx; i < minidx + a; i++) {
				li.set(i, li.get(i) + b);
			}

			for (int i = 0; i < n; i++) {
				li.set(i, li.get(i) - 1);
			}
			
			for(int i=0; i<n; i++) {
				if(li.get(i)<=0) {
					System.out.print(day);
					return;
				}
			}
		}
	}
}
