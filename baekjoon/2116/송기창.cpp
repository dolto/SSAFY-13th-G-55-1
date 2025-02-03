#include<iostream>
#include<algorithm>
using namespace std;

// ���̵��
// ���� �Ʒ��� �ֻ����� �����Ǹ� �ֻ��� ����� �Ʒ����� �����̰�
// �� �ֻ����� �� �Ʒ��� ������ ä ������ 90��, 180��, �Ǵ� 270�� ���� �� �ֱ� ������
// 6���� �ݺ��� ���� ��� ����� ���� ���� �� �ֽ��ϴ�.
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

	// ���� �� �ִ�
	int ans = 0;
	for (int i = 0; i < 6; i++) {
		// �ش� ȸ���� ��
		int sum = 0;
		
		int bottom = d[0][i];
		int top;
		if (i == 0) top=d[0][5];
		else if (i == 1) top = d[0][3];
		else if (i == 2) top = d[0][4];
		else if (i == 3) top = d[0][1];
		else if (i == 4) top = d[0][2];
		else if (i == 5) top = d[0][0];

		// ù ��° �ֻ��� �� ���� �ִ� ���ϱ�
		int maxside = 0;
		for (int j = 0; j < 6; j++) {
			if (d[0][j] != bottom && d[0][j] != top) {
				maxside = max(maxside, d[0][j]);
			}
		}
		sum += maxside;

		// ���� top ���� ���� �ֻ��� ��ġ�ϰ� side �ִ밪 �ٽ� ���ؼ� ���ϱ�
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