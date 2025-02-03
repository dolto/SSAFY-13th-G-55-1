#include<iostream>
#include<algorithm>
using namespace std;

// 아이디어
// 제일 아래쪽 주사위가 고정되면 주사위 윗면과 아랫면은 고정이고
// 각 주사위는 위 아래를 고정한 채 옆으로 90도, 180도, 또는 270도 돌릴 수 있기 때문에
// 6번의 반복을 통해 모든 경우의 수를 구할 수 있습니다.
int d[10001][6];
int main()
{
	freopen("input.txt", "r", stdin);
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	int n;
	cin >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < 6; j++) {
			cin >> d[i][j];
		}
	}

	// 답이 될 최댓값
	int ans = 0;
	for (int i = 0; i < 6; i++) {
		// 해당 회차의 합
		int sum = 0;
		
		int bottom = d[0][i];
		int top;
		if (i == 0) top=d[0][5];
		else if (i == 1) top = d[0][3];
		else if (i == 2) top = d[0][4];
		else if (i == 3) top = d[0][1];
		else if (i == 4) top = d[0][2];
		else if (i == 5) top = d[0][0];

		// 첫 번째 주사위 옆 면의 최댓값 구하기
		int maxside = 0;
		for (int j = 0; j < 6; j++) {
			if (d[0][j] != bottom && d[0][j] != top) {
				maxside = max(maxside, d[0][j]);
			}
		}
		sum += maxside;

		// 이제 top 값에 맞춰 주사위 배치하고 side 최대값 다시 구해서 더하기
		for (int j = 1; j < n; j++) {
			int bottom2 = top;
			int top2=0;

			for (int k = 0; k < 6; k++) {
				if (d[j][k] == bottom2) {
					if (k == 0) top2 = d[j][5];
					else if (k == 1) top2 = d[j][3];
					else if (k == 2) top2 = d[j][4];
					else if (k == 3) top2 = d[j][1];
					else if (k == 4) top2 = d[j][2];
					else if (k == 5) top2 = d[j][0];
				}
			}

			int maxside2 = 0;
			for (int k = 0; k < 6; k++) {
				if (d[j][k] != bottom2 && d[j][k] != top2) maxside2 = max(maxside2, d[j][k]);
			}
			sum += maxside2;
			top = top2;
		}
		ans = max(ans, sum);
	}
	cout << ans;
	return 0;
}