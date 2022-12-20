#include <iostream>
#include <climits>

using namespace std;

int N;
int heap[100001];
int hn;
int ans;

void push(int *heap, int &hn, int x)
{
    register int tmp;

    heap[++hn] = x;

    for (int i = hn; i > 1; i /= 2) {
        if (heap[i / 2] < heap[i])
            return;

        tmp = heap[i / 2];
        heap[i / 2] = heap[i];
        heap[i] = tmp;
    }
}

int pop(int *heap, int &hn) {
    register int tmp;
    register int ret = heap[1];
    heap[1] = heap[hn];
    heap[hn--] = INT_MAX;

    for (int i = 1; i * 2 <= hn;) {
        if (heap[i] < heap[i * 2] && heap[i] < heap[i * 2 + 1])
            break;

        if (heap[i * 2] < heap[i * 2 + 1]) {
            tmp = heap[i];
            heap[i] = heap[i * 2];
            heap[i * 2] = tmp;

            i = i * 2;
        } else {
            tmp = heap[i];
            heap[i] = heap[i * 2 + 1];
            heap[i * 2 + 1] = tmp;

            i = i * 2 + 1;   
        }
    }

    return ret;
}

int main()
{

    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;

    for (int i = 0; i < N; i++) {
        int x;

        cin >> x;
        push(heap, hn, x);
    }

    if (hn == 1) {
        cout << 0 << "\n";
        return 0;
    } else {
        int a, b;
        while (hn > 0)
        {
            if (hn >= 2) {
                a = pop(heap, hn);
                b = pop(heap, hn);

                ans += a + b;
                push(heap, hn, a + b);
            } else {
                break;
            }
        }
        cout << ans << "\n";
    }
}


