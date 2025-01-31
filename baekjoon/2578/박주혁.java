import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[][] matrix = new int[5][5];
		Map<Integer, int[]> m = new HashMap<>();
		boolean[][] visited = new boolean[5][5];
        // 빙고판 숫자 저장
		for (int i=0; i < 5; i++) {
			String[] s = br.readLine().split(" ");
			for (int j=0; j < 5; j++) {
				m.putIfAbsent(Integer.parseInt(s[j]), new int[] {i, j});
				matrix[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		boolean bingo = false;
		int count = 0;
		for (int i=0; i < 5; i++) {
			String[] s = br.readLine().split(" ");
			for (int j=0; j < 5; j++) {
                // 숫자에 알맞은 x, y 좌표 반환
				int[] xy = find_xy(Integer.parseInt(s[j]), matrix);
				// 방문한 곳 기록
                visited[xy[0]][xy[1]] = true;
				count++;
                // 최소 12번 이상 숫자 호출 시 빙고 검사
				if (count >= 12) {
					if (backtrack(visited, 0) == true) {
						bingo = true;
						break;
					}
				}
			}
			if (bingo == true) {
				break;
			}
		}
		
		bw.write(""+count);
		bw.flush();
		
		
	}
	
    // 숫자에 알맞은 x, y 좌표 검사
	public static int[] find_xy(int n, int[][] matrix) {
		for (int i=0; i < 5; i++) {
			for (int j=0; j < 5; j++) {
				if (matrix[i][j] == n) return new int[] {i, j};
			}
		}
		return new int[] {};
	}
	
    // 빙고 갯수 검사
	public static boolean backtrack(boolean[][] visited, int bcount) {
		// 가로, 세로 빙고 검사
        for (int i=0; i < 5; i++) {
			int count_r = 0;
			int count_c = 0;
			for (int j=0; j < 5; j++) {
				if (visited[i][j] == true) count_c++;
				if (visited[j][i] == true) count_r++;
			}
			if (count_c == 5) bcount++;
			if (count_r == 5) bcount++;
		}
		
        // 대각선 빙고 검사
		int count_xy = 0;
		int count_yx = 0;
		for (int i=0; i < 5; i++) {
			if (visited[i][i] == true) count_xy++;
			if (visited[i][4-i] == true) count_yx++;
		}
		
		if (count_xy == 5) bcount++;
		if (count_yx == 5) bcount++;
		
		if (bcount >= 3) return true;
		return false;
	}

}
