T = int(input())
result = [0] * T

def NChooseK_fast(n, k):
    numerator = 1
    denominator = 1
    k = min(n-k, k) #조합의 대칭성을 이용하여 더 적은 수의 연산을 하기 위해서입니다.
    for i in range(1, k+1):
        denominator *= i
        numerator *= n+1-i
    return int(numerator/denominator)

for i in range(T):
    N,M = map(int, input().split())
    result[i] = NChooseK_fast(M,N)

for i in range(T):
    print(result[i])