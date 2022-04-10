n = int(input())

data = list(map(int, input().split()))
data.sort()
data.reverse()

max = data[0]

sum = 0

for i in range(len(data)):
    data[i] = data[i] / max * 100
    sum += data[i]

print(sum / n)