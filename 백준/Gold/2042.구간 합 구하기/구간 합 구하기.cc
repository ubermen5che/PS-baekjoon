#include <iostream>
#include <vector>
#include <cmath>
using namespace std;
typedef long long ll;

ll buildSegtree(vector<ll>& A, vector<ll>& tree, int n, int s, int e)
{
    if (s == e) return tree[n] = A[s];
    
    int m = (s + e) / 2;
    return tree[n] = buildSegtree(A, tree, 2 * n, s, m) + buildSegtree(A, tree, 2 * n + 1, m + 1, e);
}

ll _sum(vector<ll>& tree, int n, int s, int e, int l, int r)
{
    if (e < l || r < s) return 0;
    if (l <= s && e <= r) return tree[n];
    
    int m = (s + e) / 2;
    return _sum(tree, 2 * n, s, m, l, r) + _sum(tree, 2 * n + 1, m + 1, e, l ,r);
}

void _update(vector<ll>& tree, int n, int s, int e, int i, ll diff)
{
    if (i < s || e < i) return;
    
    tree[n] += diff;
    if (s != e)
    {
        int m = (s + e) / 2;
        _update(tree, 2 * n, s, m, i, diff);
        _update(tree, 2 * n + 1, m + 1, e, i, diff);
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    
    int N, M, K; cin >> N >> M >> K;
    
    int h = (int) ceil(log2(N)); // 트리의 높이
    vector<ll> A(N + 1), tree(1 << (h + 1));
    for(int i = 1; i <= N; i++) cin >> A[i];
    buildSegtree(A, tree, 1, 1, N);
    
    for(int i = 0; i < M + K; i++)
    {
        ll a, b, c; cin >> a >> b >> c;
        if (a == 1)
        {
            _update(tree, 1, 1, N, b, c - A[b]);
            A[b] = c;
        }
        else cout << _sum(tree, 1, 1, N, b, c) << "\n";
    }
}