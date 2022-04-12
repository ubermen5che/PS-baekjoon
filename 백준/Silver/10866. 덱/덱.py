import sys

deq = []
ans = []
n = int(input())
input = sys.stdin.readline
for i in range(n):
    operation = input().split()
    if operation[0] == 'push_back':
        deq.append(operation[1])
    elif operation[0] == 'push_front':
        deq.insert(0, operation[1])
    elif operation[0] == 'pop_front':
        if len(deq) == 0:
            ans.append(-1)
        else:
            ans.append(deq[0])
            deq.remove(deq[0])
    elif operation[0] == 'pop_back':
        if len(deq) == 0:
            ans.append(-1)
        else:
            ans.append(deq.pop())
    elif operation[0] == 'size':
        ans.append(len(deq))
    elif operation[0] == 'empty':
        if len(deq) == 0:
            ans.append(1)
        else:
            ans.append(0)
    elif operation[0] == 'front':
        if len(deq) == 0:
            ans.append(-1)
        else:
            ans.append(deq[0])
    else:
        if len(deq) == 0:
            ans.append(-1)
        else:
            ans.append(deq[-1])

for i in ans:
    print(i)
