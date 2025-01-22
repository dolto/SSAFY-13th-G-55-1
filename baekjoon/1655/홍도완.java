import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	///아이디어
	///최대이진힙과 최소이진힙을 생성
	///최대이진힙에 값을 넣고 최소이진힙에 값을 넣는식으로 함
	///최소이진힙이 최대이진힙보다 크기가 크도록 유지하면 최대이진힙의 값은 언제나 순열의 중간값
	static public void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine().trim());
		
		// 최소, 최대힙 생성(최대크기 미리 할당)
		PriorityQueue<Integer> minQ = new PriorityQueue<>(N);
		PriorityQueue<Integer> maxQ = new PriorityQueue<>(N, Collections.reverseOrder());
		
		// N이 0이면 br.readLine은 에러가 발생하므로, 미리 확인
		if(N > 0) {
			// 미리 최대힙에 값을 넣어둠
			int n = Integer.parseInt(br.readLine().trim());
			maxQ.add(n);
			bw.write(maxQ.peek().toString()+"\n");
		}
		// 위에 하나를 이미 꺼냈으니, i는 1부터 시작
		for(int i = 1; i < N; ++i) {
			int n = Integer.parseInt(br.readLine().trim());
			if(maxQ.size() == minQ.size()) {
				maxQ.add(n);
			}else {
				minQ.add(n);
			}
			
			// 최대힙은 언제나 최소힙보다 작아야함
			if(maxQ.peek() > minQ.peek()) {
				int max = maxQ.poll();
				int min = minQ.poll();
				maxQ.add(min);
				minQ.add(max);
			}

			// 최대힙은 언제나 중간값
			bw.write(maxQ.peek().toString()+"\n");
		}
		br.close();
		bw.flush();
		bw.close();
	}
}
