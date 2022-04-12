import sys
input = sys.stdin
n = int(input.readline())

cord_list = []
for i in range(n):
    cord_list.append(tuple(map(int, input.readline().split())))

cord_list.sort(key=lambda x: (x[0], x[1]))

for i in cord_list:
    print(i[0], i[1])