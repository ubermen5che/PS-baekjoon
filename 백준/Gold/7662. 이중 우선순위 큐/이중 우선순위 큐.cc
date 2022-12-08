#include <iostream>
#include <climits>

using namespace std;
#define MAX (1000000 + 100)


typedef struct st1 {
    int value;
    int isDeleted;
} DATA;

typedef struct st2 {
    int value;
    int dataIdx;
} HEAP;

DATA hData[MAX];
HEAP maxHeap[MAX];
HEAP minHeap[MAX];

void pushMaxHeap(HEAP *heap, int &hn, HEAP x) {

    HEAP tmp;

    heap[++hn] = x;

    for (int i = hn; i > 1; i /= 2) {
        if (heap[i].value < heap[i / 2].value)
            return;

        tmp = heap[i / 2];
        heap[i / 2] = heap[i];
        heap[i] = tmp;
    }
}
void pushMinHeap(HEAP *heap, int &hn, HEAP x) {

    HEAP tmp;

    heap[++hn] = x;

    for (int i = hn; i > 1; i /= 2) {
        if (heap[i].value > heap[i / 2].value)
            return;

        tmp = heap[i / 2];
        heap[i / 2] = heap[i];
        heap[i] = tmp;
    }
}

HEAP popMaxHeap(HEAP *heap, int &hn) {

    HEAP tmp, ret;

	ret = heap[1];
	heap[1] = heap[hn];
	heap[hn--].value = 0x80000000; /* -2147483648 */

	for (int i = 1; i * 2 <= hn;)
	{
		if (heap[i].value > heap[i * 2].value && heap[i].value > heap[i * 2 + 1].value) break;
		else if (heap[i * 2].value > heap[i * 2 + 1].value)
		{
			tmp = heap[i * 2];
			heap[i * 2] = heap[i];
			heap[i] = tmp;

			i = i * 2;
		}
		else
		{
			tmp = heap[i * 2 + 1];
			heap[i * 2 + 1] = heap[i];
			heap[i] = tmp;

			i = i * 2 + 1;
		}
	}

	return ret;
}

HEAP popMinHeap(HEAP *heap, int &hn) {
	HEAP tmp, ret;

	ret = heap[1];
	heap[1] = heap[hn];
	heap[hn--].value = 0x7fffffff; /* 2147483647 */

	for (int i = 1; i * 2 <= hn;)
	{
		if (heap[i].value < heap[i * 2].value && heap[i].value < heap[i * 2 + 1].value) break;
		else if (heap[i * 2].value < heap[i * 2 + 1].value)
		{
			tmp = heap[i * 2];
			heap[i * 2] = heap[i];
			heap[i] = tmp;

			i = i * 2;
		}
		else
		{
			tmp = heap[i * 2 + 1];
			heap[i * 2 + 1] = heap[i];
			heap[i] = tmp;

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

    int T, N;

    cin >> T;

    for (int tc = 0; tc < T; tc++) {
        cin >> N;

        int maxhn = 0;
        int minhn = 0;
        int dCnt = 0;
        int dataCount = 0;

        for (int i = 0; i < N; i++)
        {
            char op;
            int value;

            cin >> op;
            cin >> value;

            if (op == 'I')
            {
                hData[dCnt].value = value;
                hData[dCnt].isDeleted = 0;
                HEAP x = {value, dCnt};
                pushMaxHeap(maxHeap, maxhn, x);
                pushMinHeap(minHeap, minhn, x);

                dCnt++;
                dataCount++;
            }
            else if (op == 'D')
            {
                
                if (dataCount == 0) {
                    maxhn = minhn = 0;
                    continue;
                }
                // 이전에 이미 삭제된 원소들 제거
                while (hData[maxHeap[1].dataIdx].isDeleted)
                    popMaxHeap(maxHeap, maxhn);
                
                while(hData[minHeap[1].dataIdx].isDeleted)
                    popMinHeap(minHeap, minhn);

                HEAP tmp;
                if (value == -1)
                { // 최소힙 pop
                    tmp = popMinHeap(minHeap, minhn);
                    hData[tmp.dataIdx].isDeleted = 1;
                }
                else
                {
                    tmp = popMaxHeap(maxHeap, maxhn);
                    hData[tmp.dataIdx].isDeleted = 1;
                }

                dataCount--;
            }
        }

        if (dataCount) {
            while (hData[maxHeap[1].dataIdx].isDeleted)
                popMaxHeap(maxHeap, maxhn);
            
            while(hData[minHeap[1].dataIdx].isDeleted)
                popMinHeap(minHeap, minhn);

            cout << popMaxHeap(maxHeap, maxhn).value << " " << popMinHeap(minHeap, minhn).value << "\n";
        } else {
            cout << "EMPTY" << "\n";
        }
    }
    
    return 0;
}