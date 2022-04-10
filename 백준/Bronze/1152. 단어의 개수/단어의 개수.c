#include <stdio.h>
#include <string.h>

int main() {
    char string[1000000] = { 0 };

    int len = 0;
    int cnt = 0;

    gets(string);
    len = strlen(string);

    for (int i = 0; i < len; i++) {
        if(string[i] == ' ') cnt++;
    }

    if (string[0] == ' ') cnt--;
    if (string[len - 1] == ' ') cnt--;
    printf("%d", cnt + 1);
}