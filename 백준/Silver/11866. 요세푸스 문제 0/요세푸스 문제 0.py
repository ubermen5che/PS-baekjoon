N, K = map(int, input().split())

num_list = [0] * (N + 1)

for i in range(1, N + 1):
    num_list[i] = i

cnt = 0
start_pos = 0
pos = 1
end_pos = 0

ans = []
# K <= N
# list를 계속해서 순회한다
# start_pos는 list를 k번 순회할 때 마다 바뀐다
#
while True:
    if (len(num_list) == 1):
        break
    cur = start_pos
    for _ in range(K):
        cur += 1
        if (cur >= len(num_list)):
            cur = 1

    ans.append(num_list[cur])
    del num_list[cur]
    start_pos = cur - 1
    if (start_pos < 1):
        start_pos = len(num_list)

print('<', end='')
for i in range(len(ans) - 1):
    print(ans[i], end=', ')
print(ans[len(ans) - 1], end='')
print('>')
