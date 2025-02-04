import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 홍도완 {
	/// 아이디어
	/// 각 주사위의 아래와 위를 제외한 나머지 수를 선택이 가능하며, 첫번째 아래가 정해지면, 그 이후의 아래는 자동으로 고르 수 있다.
	/// 첫 주사위의 아래를 1~6까지 하면 된다.
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine().trim());
		Dice[] dices = new Dice[n];
		for (int i = 0; i < n; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			dices[i] = new Dice(
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())
					);
		}
		br.close();
		int answer = 0;
		for(int i = 0; i < 6; ++i) {
			int temp = 0;
			dices[0].setDown(i);
			temp += dices[0].getOtherMax();
			
			for(int j = 1; j < n; ++j) {
//				bw.write(String.format("before: up: %d\tdown: %d\tmax: %d\n", dices[j].getUpIndex(), dices[j].getDownIndex(), dices[j].getOtherMax()));
				dices[j - 1].matchDice(dices[j]);
//				bw.write(String.format("after: up: %d\tdown: %d\tmax: %d\n", dices[j].getUpIndex(), dices[j].getDownIndex(), dices[j].getOtherMax()));
				temp += dices[j].getOtherMax();
			}
//			bw.write("\n");
			answer = Math.max(answer, temp);
		}
		
		bw.write(String.format("%d", answer));

		bw.flush();
		bw.close();
	}
}

class Dice{
	private int[] numbers;
	private int downindex = 0;
	private int upindex = 5;
	public Dice(int ...is){
		numbers = is;
	}
	public void setDown(int index) {	
		downindex = index;
		switch(index) {
		case 0:
			upindex = 5;
			break;
		case 1:
			upindex = 3;
			break;
		case 2:
			upindex = 4;
			break;			
		case 3:
			upindex = 1;
			break;
		case 4:
			upindex = 2;
			break;
		case 5:
			upindex = 0;
			break;
		}
	}
	
	public int getDownIndex() {
		return downindex;
	}
	public int getUpIndex() {
		return upindex;
	}
	public int getOtherMax() throws IOException{
		int max = 0;
		for(int i = 0; i < 6; ++i) {
			if(i == upindex || i == downindex) continue;
			max = Math.max(max, numbers[i]);
		}
		return max;
	}
	public void matchDice(Dice other) {
		for(int i = 0; i < 6; ++i) {
			if(other.numbers[i] == this.numbers[this.upindex]) {
				other.setDown(i);
				return;
			}
		}
	}
}
