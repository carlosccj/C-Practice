#include <stdio.h>
#include <stdlib.h>

int main() {
    
    struct Point{ //creamos la estructura Point con dos variables int
        int x;
        int y;
    };

    struct Point *ppointer; // creamos un puntero de tipo Point

    struct Rect{ //creamos una estructura Rect que se compone de dos estructuras Point (p1 y p2)
        struct Point p1;
        struct Point p2;
    };

    struct Point p1; 
    p1.x = 1;
    p1.y = 6;

    struct Point p2; //definimos dos instancias de punto
    p2.x = 7;
    p2.y = 3;

    ppointer = &p1; //el puntero apunta a la direccion de memoria de p1
    printf("Valor de la variable x de punto: %d\n", ppointer->x);
    printf("Valor de la direccion de la variable x de punto: %x\n", &ppointer->x);
    printf("Valor de la variable y de punto: %d\n", ppointer->y);
    printf("Valor de la direccion de la variable y de punto: %x\n", &ppointer->y);

    printf("Punto de coordenadas: %d\n", p1.x);
    printf("Punto de ordenadas: %d\n", p1.y);

    printf("Punto p1 de Rect: (%d, %d)\n", p1.x, p1.y);
    printf("Punto p2 de Rect: (%d, %d)\n", p2.x, p2.y);
}