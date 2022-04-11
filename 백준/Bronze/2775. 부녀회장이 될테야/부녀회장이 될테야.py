import sys

t = int(sys.stdin.readline())
dy = [[1] * 14 for _ in range(15)]

for i in range(14):
    dy[0][i] = i + 1

for i in range(1, 15):
    for j in range(1, 14):
        dy[i][j] = dy[i][j - 1] + dy[i - 1][j]

answer = []
for i in range(t):
    k = int(sys.stdin.readline())
    n = int(sys.stdin.readline())
    answer.append(dy[k][n - 1])

for i in range(t):
    print(answer[i])
