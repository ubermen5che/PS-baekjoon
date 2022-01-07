import sys
input = sys.stdin.readline

n=int(input())
graph=list(map(int, input().split()))

answer=[0]*n
for i in range(n):
    count=0
    maxCount=0
    for j in range(n):

        if graph[i]<=count and answer[j]==0:
            answer[j]=i+1
            break
        
        if answer[j]==0:
            count+=1


for i in answer:
    print(i, end=" ")