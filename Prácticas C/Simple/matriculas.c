#include <stdio.h>
#include <stdlib.h>

#define NUM 9

int cumple_Condiciones(const char *matr) { //Recibe un string
    int i = 0;
    int res = 0;
    if(matr[7] != 10) {
        res = 1;
    }
    while(i < 4 && res == 0) {
        if(matr[i] < 48 || matr[i] > 57) {
            res = 1;
        }
        i++;
    }
    while(i < NUM - 2 && res == 0) {
        if(matr[i] < 65 || matr[i] > 90) {
            res = 1;
        }
        i++;
    }
    return res;
}

int main (void) {
char matr[NUM];
int i = 0;
int res = 0; // Bucle infinito (Ctr + c para interrumpir)

while(res == 0) {
printf("Introducir matricula: \n");
while(i < NUM - 1) {
    scanf("%c", &matr[i]);
    i++;
}

i = 0;

if(cumple_Condiciones(matr) == 0) {
    printf("La matricula introducida es valida\n");
} else {
    printf("La matricula introducida es erronea\n");
        }
    }
}