#include <iostream>
#include <climits>

int N, cnt;
int min_hn, max_hn;
int min_heap[100001];
int max_heap[100001];

using namespace std;

void pushMinHeap(int &hn, int x);
int popMinHeap(int &hn);
void pushMaxHeap(int &hn, int x);
int popMaxHeap(int &hn);

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;

    for (int i = 1; i <= N; i++) {
        int in;

        cin >> in;
        // 첫 input이면 minHeap에다가 Push
        if (i == 1) {
            pushMinHeap(min_hn, in);
            cout << in << "\n";
            continue;
        }

        int x;
        // 총 입력 데이터수가 홀수이면 minHeap에 우선 넣는것을 고려.
        if (i % 2 == 1) {
            // maxHeap[1]값과 비교후 input값이 크다면
            if (max_heap[1] > in) {
                // minheap[1]을 pop하고 maxHeap에 push한다. 그 후 input값을 minHeap에 push
                x = popMaxHeap(max_hn);
                pushMinHeap(min_hn, x);
                pushMaxHeap(max_hn, in);
                cout << min_heap[1] << "\n";
            } else { // maxheap에 넣어줌
                pushMinHeap(min_hn, in);
                cout << min_heap[1] << "\n";
            }
        } else { // 짝수라면 maxHeap에 넣는것을 고려. 우선순위를 고려해서 적절히 push
            if (min_heap[1] < in) {
                x = popMinHeap(min_hn);
                pushMaxHeap(max_hn, x);
                pushMinHeap(min_hn, in);
                if (min_heap[1] > max_heap[1]) {
                    cout << max_heap[1] << "\n";
                } else {
                    cout << min_heap[1] << "\n";
                }
            } else {
                pushMaxHeap(max_hn, in);
                if (min_heap[1] > max_heap[1]) {
                    cout << max_heap[1] << "\n";
                } else {
                    cout << min_heap[1] << "\n";
                }
            }
        }

        // cout << "min_heap"
        //      << "\n";
        // for (int j = 0; j < 10; j++)
        // {
        //     cout << min_heap[j] << " ";
        // }

        // cout << "\n";

        // cout << "max_heap"
        //      << "\n";
        // for (int j = 0; j < 10; j++)
        // {
        //     cout << max_heap[j] << " ";
        // }
    }
}

void pushMinHeap(int &hn, int x) {
    register int tmp;

    min_heap[++hn] = x;

    for (int i = hn; i > 1; i /= 2) {
        if (min_heap[i / 2] < min_heap[i])
            return;

        tmp = min_heap[i / 2];
        min_heap[i / 2] = min_heap[i];
        min_heap[i] = tmp;
    }
}

void pushMaxHeap(int &hn, int x) {
    register int tmp;

    max_heap[++hn] = x;

    for (int i = hn; i > 1; i /= 2) {
        if (max_heap[i / 2] > max_heap[i])
            return;

        tmp = max_heap[i / 2];
        max_heap[i / 2] = max_heap[i];
        max_heap[i] = tmp;
    }
}

int popMinHeap(int &hn) {
    register int tmp;
    register int ret = min_heap[1];
    min_heap[1] = min_heap[hn];
    min_heap[hn--] = INT_MAX;

    for (int i = 1; i * 2 <= hn;) {
        if (min_heap[i] < min_heap[i * 2] && min_heap[i] < min_heap[i * 2 + 1])
            break;

        if (min_heap[i * 2] < min_heap[i * 2 + 1]) {
            tmp = min_heap[i];
            min_heap[i] = min_heap[i * 2];
            min_heap[i * 2] = tmp;
            i = i * 2;
        } else {
            tmp = min_heap[i];
            min_heap[i] = min_heap[i * 2 + 1];
            min_heap[i * 2 + 1] = tmp;
            i = i * 2 + 1;      
        }
    }

    return ret;
}

int popMaxHeap(int &hn) {
    register int tmp;
    register int ret = max_heap[1];
    max_heap[1] = max_heap[hn];
    max_heap[hn--] = INT_MIN;

    for (int i = 1; i * 2 <= hn;) {
        if (max_heap[i] > max_heap[i * 2] && max_heap[i] > max_heap[i * 2 + 1])
            break;

        if (max_heap[i * 2] > max_heap[i * 2 + 1]) {
            tmp = max_heap[i];
            max_heap[i] = max_heap[i * 2];
            max_heap[i * 2] = tmp;
            i = i * 2;
        } else {
            tmp = max_heap[i];
            max_heap[i] = max_heap[i * 2 + 1];
            max_heap[i * 2 + 1] = tmp;
            i = i * 2 + 1;      
        }
    }

    return ret;
}