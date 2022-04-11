import sys

input = sys.stdin.readline

A, B, V = map(int, input().split())
left = 1
right = 1000000000

mid = 0
ans = 0
while (left <= right):
    mid = int((left + right) / 2)
    h = int((mid * A) - (mid - 1) * B)
    if (h == V):
        break
    elif (h > V):
        right = mid - 1
        ans = mid
    else:
        left = mid + 1

if (left == right or h == V):
    print(mid)
elif (h > V):
    print(ans)
else:
    print(ans)