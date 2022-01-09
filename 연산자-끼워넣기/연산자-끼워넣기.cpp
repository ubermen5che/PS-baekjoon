#include <iostream>

using namespace std;
int N;
int minVal = 100000001, maxVal = -100000001;
int oper[4];
int report[11];

void solution(int result, int x){
    if(x == N){
        if(minVal > result) minVal = result;
        if(maxVal < result) maxVal = result;
        return;
    }
    for(int i = 0; i < 4; i++){
        if(oper[i] > 0){
            oper[i]--;
            if(i == 0) solution(result + report[x], x+1);
            else if(i == 1) solution(result - report[x], x+1);
            else if(i == 2) solution(result * report[x], x+1);
            else solution(result / report[x], x+1);
            oper[i]++;
        }
    }
}
int main()
{
    int val;
    cin >> N;
    for(int i = 0; i < N; i++){
        cin >> report[i];
    }
    for(int i = 0; i < 4; i++){
        cin >> oper[i];
    }
    solution(report[0], 1);
    cout << maxVal << "\n" << minVal;
}

