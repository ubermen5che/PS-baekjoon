#include <iostream>

using namespace std;

int N;
int height[11];
int line[11];
int visited[11];

void solve() {
	for (int i = 1; i <= N; i++) {
		int jump = height[i];
		int index = 0;
		while (1) {
			if (visited[index]) {
				index++;
			}
			else {
				if (jump == 0) {
					line[index] = i;
					visited[index] = 1;
					break;
				}
				else {
					jump--; index++;
				}
			}


		}
	}
	for (int i = 0; i < N; i++)
		cout << line[i]<< ' ';
}

int main() {
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> height[i];
	}
	solve();

}