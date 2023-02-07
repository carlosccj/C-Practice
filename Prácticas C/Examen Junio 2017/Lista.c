#include <stdlib.h>
#include <stdio.h>
#include "Lista.h"

/*
 * Inicializa la lista de puntos creando una lista vacía
 *
 */
void crearLista(TLista *lista) {
    *lista = NULL;
}


/**
 * Inserta el punto de forma ordenada (por el valor de la abscisa x)
 * en la lista siempre que no esté repetida la abscisa.
 *  En ok, se devolverá un 1 si se ha podido insertar, y  0 en caso contrario.
 *  Nota: utiliza una función auxiliar para saber
 *   si ya hay un punto en la lista con la misma abscisa punto.x
 *
 */
void insertarPunto(TLista *lista, struct Punto punto, int *ok) {
    TLista curr = *lista;
    TLista ant = NULL;
    while(curr != NULL && curr->punto.x < punto.x) {
        ant = curr;
        curr = curr->sig;
    }
    if (curr != NULL && curr->punto.x == punto.x) {
        *ok = 0;
    } else {
        *ok = 1;
        TLista nuevoNodo = (TLista) malloc (sizeof(struct Nodo));
        if (nuevoNodo == NULL) {
            perror("Error en insertarPunto: no se ha podido reservar memoria para el nuevo nodo\n");
            exit(-1);
        }

        nuevoNodo->punto.x = punto.x;
        nuevoNodo->punto.y = punto.y;
        nuevoNodo->sig = curr;
        
        if (ant == NULL) { // el nodo va el primero, ya sea porque la lista estaba vacia o porque es el 'x' mas pequeño
            *lista = nuevoNodo;
        } else { // el nodo va en medio o al final
            ant->sig = nuevoNodo;
        }
    }
}

/*
 * Elimina de la lista el punto con abscisa x de la lista.
 * En ok devolverá un 1 si se ha podido eliminar,
 * y un 0 si no hay ningún punto en la lista con abscisa x
 *
 */
void eliminarPunto(TLista *lista, float x, int *ok) {
    TLista curr = *lista;
    TLista ant = NULL;
    *ok = 0;
    while (curr != NULL) {
        if (curr->punto.x == x) {
            *ok = 1;
            if (ant == NULL) {
                free(curr);
            } else {
                ant->sig = curr->sig;
                free(curr);
            }
        }
        ant = curr;
        curr = curr->sig;
    }
}

 /**
 * Muestra en pantalla el listado de puntos
 */
void mostrarLista(TLista lista) {
    int i = 0;
    while(lista != NULL) {
        printf("El punto %d de la lista tiene abcisa %f y ordenada %f\n", i, lista->punto.x, lista->punto.y);
        lista = lista->sig;
        i++;
    }
}

/**
 * Destruye la lista de puntos, liberando todos los nodos de la memoria.
 */
void destruir(TLista *lista) {
    TLista curr = *lista;
    while(*lista != NULL) {
        curr = *lista;
        *lista = (*lista)->sig;
        free(curr);
    }
}

/*
 * Lee el contenido del archivo binario de nombre nFichero,
 * que contiene una secuencia de puntos de una función polinómica,
 *  y lo inserta en la lista.
 *
 */
void leePuntos(TLista *lista, char *nFichero) {
    FILE *f = fopen(nFichero, "rb");
    int *ok = 0;
    if ((fopen(nFichero, "rb")) == NULL) {
        perror("Error en leePuntos: no se ha podido abrir el fichero binario\n");
        exit(-1);
    }
    while(!feof(f)) {
        struct Punto punto;
        fread(&(punto.x), sizeof(double), 1, f);
        fread(&(punto.y), sizeof(double), 1, f);
        insertarPunto(lista, punto, ok);
    }
}