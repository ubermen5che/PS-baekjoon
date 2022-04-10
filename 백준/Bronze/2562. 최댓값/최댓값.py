numbers = []

for _ in range(9):
    num = int(input())
    numbers.append(num)

max = max(numbers)
index = numbers.index(max) + 1
print(max)
print(index)