#include <stdlib.h>
#include <stdio.h>

int main(void) {
    char x;
    int voc = 0;
    printf("Introduce una serie de caracteres: ");
    x = getchar();
    while(x != '#') {
        if(x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u') {
            voc++;
        }
         x = getchar();
    }
    printf("Vocales leidas: %d\n", voc);
}