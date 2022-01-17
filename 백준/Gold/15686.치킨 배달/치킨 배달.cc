#include <stdio.h>

int house[105][2],hnum;
int chicken[15][2],cnum;
int select[15],snum;
int n,m;
int ans = 987654321;

int diff(int x1, int y1, int x2, int y2){
	int num = 0;
	
	if(x1<x2) num += x2-x1;
	else num += x1-x2;
	
	if(y1<y2) num += y2-y1;
	else num += y1-y2;
	
	return num;
}

int result(){
	int min,sum=0,p;
		
	for(int i=0;i<hnum;i++){
		min = 987654321;
		for(int j=0;j<m;j++){
			p = diff(house[i][0],house[i][1],chicken[select[j]][0],chicken[select[j]][1]);
			
			if(min>p) min = p;
		}
		sum += min;
	}
	
	return sum;
}

void chicken_select(int k){
	if(snum==m){
		int res = result();
		if(ans>res) ans = res;
		return;
	}
	
	for(int i=k;i<cnum;i++){
		select[snum++] = i;
		chicken_select(i+1);
		snum--;
	}
	
	return;
}

int main(){
	int x;
	
	scanf("%d %d",&n,&m);
	
	for(int i=1;i<=n;i++)
		for(int j=1;j<=n;j++){
			scanf("%d",&x);
			if(x==1){
				house[hnum][0] = i;
				house[hnum++][1] = j;
			}
			else if(x==2){
				chicken[cnum][0] = i;
				chicken[cnum++][1] = j;
			}
		}
	
	chicken_select(0);
	
	printf("%d",ans);
	
	return 0;
}