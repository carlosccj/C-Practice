#include <stdio.h>
#include <stdlib.h>

int calcular(int p_valor, char op, int s_valor) {
    int result = 0;
    switch(op) {
        case '+':
        result = p_valor + s_valor;
        break;
        case '-':
        result = p_valor - s_valor;
        break;
        case '*':
        result = p_valor * s_valor;
        break;
        case '/':
        result = p_valor / s_valor;
        break;
    }
    return result;
}

int main (void) {
    char control;
    printf("Haga un calculo (c) o termine la aplicacion (q):\n");
    scanf("%c", &control);
    while (control != 'q') {
        int p_valor, s_valor;
        char op;
        printf("Introduzca valor - operador - valor (solo enteros)\n");
        scanf("%d %c %d", &p_valor, &op, &s_valor);
        if(op == '/' && s_valor == 0) {
            printf("Division por 0\n");
        } else {
            printf("El resultado es: %d\n", calcular(p_valor, op, s_valor));
        }
        printf("Haga un calculo (c) o termine la aplicacion (q):\n");
        scanf(" %c", &control);
    }
    printf("El programa ha terminado");
}

