num_list = []
num_cnt = [0] * 10

product = 1

for _ in range(3):
    num = int(input())
    num_list.append(num)
    product *= num

char_list = list(str(product))

for i in range(len(char_list)):
    num_cnt[int(char_list[i])] += 1

for i in range(len(num_cnt)):
    print(num_cnt[i])