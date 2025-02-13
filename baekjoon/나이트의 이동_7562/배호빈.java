

import java.util.*;
import java.io.*;

public class Main_7562 {
    //아이디어 나이트가 움직이는 방향으로 bfs를 돌리고 도착지점을 출력하면 최소 이동 횟수를 알 수 있습니다.
    static class Node{
        int r;
        int c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }
    static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[][] arr;
    static int[][] dist;
    static boolean[][] check;
    static ArrayDeque<Node> que;
    static int destRow;
    static int destCol;
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startRow = Integer.parseInt(st.nextToken());
            int startCol = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            destRow = Integer.parseInt(st.nextToken());
            destCol = Integer.parseInt(st.nextToken());

            if( startRow == destRow && startCol == destCol) {
                System.out.println(0);
                continue;
            }
            arr = new int[N][N];
            dist = new int[N][N];
            check = new boolean[N][N];
            que = new ArrayDeque<>();
            que.addLast(new Node(startRow, startCol));
            check[startRow][startCol] = true;
            bfs();
            System.out.println(dist[destRow][destCol]);

        }

    }
    private static void bfs() {

        while(!que.isEmpty()) {
            Node node = que.pollFirst();
            for(int i = 0; i < dc.length; i++) {
                int nr = node.r + dr[i];
                int nc = node.c + dc[i];
                if(nr >= N || nc >= N || nr < 0 || nc < 0) {
                    continue;
                }

               if (!check[nr][nc]) {
                   check[nr][nc] = true;
                   que.addLast(new Node(nr, nc));
                   dist[nr][nc] = dist[node.r][node.c] + 1;
               }
            }
        }

    }
}

