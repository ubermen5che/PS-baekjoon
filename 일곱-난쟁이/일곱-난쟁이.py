n = 9
nine_dwarfs_height = []
for i in range(n):
    nine_dwarfs_height.append(int(input()))
nine_dwarfs_height.sort()
two_height_sum = 0
for i in range(n):
    for j in range(i+1, n):
        num1, num2 = nine_dwarfs_height[i], nine_dwarfs_height[j]
        if sum(nine_dwarfs_height) - (num1 + num2) == 100:
            nine_dwarfs_height.remove(num1)
            nine_dwarfs_height.remove(num2)
            break
    if len(nine_dwarfs_height) == 7:
        break
for height in nine_dwarfs_height:
    print(height)