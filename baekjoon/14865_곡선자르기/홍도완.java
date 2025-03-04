import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 홍도완 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	// 시간제한 : 2초
	// 메모리제한: 512mb
	// 사용시간 : 1252ms
	// 메모리: 347mb

	/// 아이디어(의식의 흐름)
	// 한 봉우리가 다른 봉우리를 포함하기 위해선, x축이 다른 봉우리보다 넓고, y축이 다른 봉우리보다 높아야 한다.
	// 그림을 그리면서 서로 교차되거나 겹치는 분은 없다는 가정이 있으므로, y축이 다른 봉우리보다 높다면, 반드시 포함할 수 밖에 없다.
	// 생각해보니, y좌표가 높은지 비교할 필요가 없다... 한 봉우리의 x 넓이에 포함된 봉우리는 더 낮을 수 밖에 없다!
	// 하지만 캔버스의 크기는 절대 배열로 만들 수 없다... 그렇다면 x축과 교차되는 부분을 구하는건 가능하지만... y축은?
	// 그림을 그리는 순서를 따라가면서, 봉우리를 만들 수 있을 것 같다.
	// y가 위로 올라가며 x축과 교차하면 x범위 시작, y가 내려가며 x축과 교차하면 봉우리 생성.
	// 다시 y가 위로 올라가며 x축과 교차하면 x범위 시작...
	// 다만 이렇게 하면, 봉우리를 생성하면서 포함 미포함을 구분할 수 없을 것 같다...
	// 각 봉우리의 x좌표의 시작점으로 정렬한다.
	// 이후 순회하면서, 다른 봉우리가 x좌표의 끝지점보다 작다면, 포함된 봉우리
	//
	// 일단 해보자
	
	/// 아이디어(실제 풀이)
	// 문제를 분할해서 풀자.
	// 문제1: 봉우리 구하기
	// int[][] pen에 봉우리의 꼭짓점들을 전부 저장한다.
	// 이후, 변이 아래에서 위로 올라갈 때, 봉우리를 생성하고
	// 변이 위에서 아래로 내려갈 때 봉우리를 마저 정의한다.
	// 문제2: 봉우리 포함과 미포함 구하기
	// 만든 봉우리들을, x의 범위가 l~r 이라고 했을 때, l을 중심으로 오름차순 정렬을 한다.
	// 첫번째 봉우리를 stack에 넣는다.
	// 두번째 봉우리부터(이를 b라 하면), stack.peek()의 x의 끝지점보다, b.x의 끝지점이 더 큰동안 pop을 해준다.
	// 이유는 stack.peek()의 x범위에 포함되지 않았다는 것을 알 수 있기 때문
	// pop이 끝나고, 만약 stack이 남아있다면 stack.peek()은 봉우리를 포함하는 거고, b는 위의 봉우리에게 포함되었다.
	// (총 몇개의 봉우리를 포함하는지 정확하게 기록되지 않을 것 같지만, 일단 봉우리가 다른 봉우리를 포함한다는 것만 알면 됨)
	// 이렇게 포함하고, 포함된 봉우리를 분류할 수 있다.
	public static void main(String[] args) throws IOException {
		final int N = Integer.parseInt(br.readLine());
		int[][] pen = new int[N][2];
		ArrayList<Bong> bongs = new ArrayList<>(N / 2);

		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pen[i][0] = Integer.parseInt(st.nextToken());
			pen[i][1] = Integer.parseInt(st.nextToken());
		}
//		pen[N][0] = pen[0][0];
//		pen[N][1] = pen[0][1];
		br.close();

		int index = -1;
		for (int i = 0; i < N; ++i) {
			int next = (i + 1) % N;
//			System.out.println(pen[previus][1]);
//			System.out.println(pen[i][1]);
//			System.out.println();

			if (pen[next][1] > 0 && pen[i][1] < 0) {
				bongs.add(new Bong());
				index += 1;
				bongs.get(index).x.add(pen[i][0]);
			} else if (index >= 0 && pen[next][1] < 0 && pen[i][1] > 0) {
				bongs.get(index).x.add(pen[i][0]);
				Collections.sort(bongs.get(index).x);
			}

		}

		int ti = 0;
		while (bongs.get(index).x.size() == 1) {
			int next = (ti + 1) % N;
			if (index >= 0 && pen[next][1] < 0 && pen[ti][1] > 0) {
				bongs.get(index).x.add(pen[ti][0]);
				Collections.sort(bongs.get(index).x);
			}
			ti = (ti + 1) % N;
		}
//		bw.write(bongs.toString() + "\n");

		Collections.sort(bongs);

		Stack<Bong> target = new Stack<>();
		target.push(bongs.get(0));
		for (int i = 1; i < bongs.size(); ++i) {
			while (!target.isEmpty() && target.peek().x.get(1) < bongs.get(i).x.get(1)) {
				target.pop();
			}
			if (!target.isEmpty()) {
				target.peek().poham += 1;
				bongs.get(i).parent = target.size();
			}
			target.push(bongs.get(i));
		}

//		bw.write(bongs.toString() + "\n");
		int answer1 = 0, answer2 = 0;
		for (Bong b : bongs) {
			if (b.poham == 0) {
				answer1 += 1;
			}
			if (b.parent == 0) {
				answer2 += 1;
			}
		}

		bw.write(String.format("%d %d", answer2, answer1));

		bw.flush();
		bw.close();
	}

	static class Bong implements Comparable<Bong> {
		ArrayList<Integer> x = new ArrayList<>(2);
		int poham = 0;
		int parent = 0;

		@Override
		public int compareTo(Bong o) {
			return Integer.compare(this.x.get(0), o.x.get(0));
		}

		@Override
		public String toString() {
			return x.toString() + " " + String.format("%d %d", poham, parent);
		}
	}

}
