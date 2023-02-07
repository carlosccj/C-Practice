#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include "cifrado.h"

    /* (0.5 puntos) funcion necesaria para crear un esquema de cifrado vacio, sin nodos*/
void crearEsquemaDeCifrado (TCifrado *cf) {
    *cf = NULL;
}

/* (3 puntos) funcion que pone un nodo al final de un esquema de cifrado, si es posible.
 * Se debe devolver en el ultimo parametro un valor logico que sea verdadero si ha sido posible
 * realizar la operacion. No se debe suponer que el valor de box.sig es valido*/
void insertarBox (TCifrado *cf, struct TBox box, unsigned char *ok) {
    TCifrado curr = *cf;
    while(curr != NULL && curr->sig != NULL) {
        curr = curr->sig;
    }
    if ((curr == NULL && box.esSBox == 1) || (curr != NULL && curr->esSBox != box.esSBox)) {
        *ok = 1;
        TCifrado nuevoNodo = (TCifrado) malloc (sizeof(struct TBox));
        if(nuevoNodo == NULL) {
            perror("Error en insertarBox: no se ha podido reservar memoria para el Nuevo Nodo\n");
            exit(-1);
        }
        nuevoNodo->esSBox = box.esSBox;
        nuevoNodo->bitACambiar = box.bitACambiar;
        nuevoNodo->valorASumar = box.valorASumar;
        nuevoNodo->sig = NULL;
        if (curr == NULL) {
            *cf = nuevoNodo;
        } else {
            curr->sig = nuevoNodo;
        }
    } else {
        *ok = 0;
    }
}

/* (1.5 puntos) funcion que dado un nodo y un valor, devuelve
 * el resultado de aplicar dicho nodo a dicho valor. Deberas
 * de tener en cuenta si el nodo es una SumaBox o una XORBox.
 * En el ultimo caso, necesitaras usar operadores logicos a
 * nivel de bit, como &, |, ^ o bien ~, asi como probablemente
 * usar constantes  numericas. */
unsigned char aplicarBox (struct TBox box, unsigned char valor) {
    if (box.esSBox == 1) {
        valor += box.valorASumar;
    } else if (box.esSBox == 0) {
        valor = (box.bitACambiar == 0) ? valor ^ 0x01 : valor ^ 0x80;
    }
    return valor;
}

/* (1.5 puntos) funcion que toma un esquema de cifrado y un valor,
 * y devuelve el resultado de aplicar dicho esquema de cifrado a
 * dicho valor, segun el metodo descrito anteriormente.*/
unsigned char aplicarEsquemaDeCifrado(TCifrado cf, unsigned char valor) {
    struct TBox box;
    while (cf != NULL) {
        box.esSBox = cf->esSBox;
        box.bitACambiar = cf->bitACambiar;
        box.valorASumar = cf->valorASumar;
        box.sig = cf->sig;
        valor = aplicarBox(box, valor);
        cf = cf->sig;
    }
    return valor;
}

/* (2.5 puntos) funcion que toma un nombre de fichero, en el que se
 *  escribiran en modo binario los datos correspondientes al esquema
 *  de cifrado que se pasa como parametro, de modo que el al final el
 *  fichero unicamente contenga dichos datos. Si no se puede abrir el
 *  fichero, se debe de mostrar un mensaje de error por pantalla.*/
void escribirAFichero(char *nm, TCifrado cf) {
    FILE *f = fopen(nm, "wb");
    if(fopen(nm, "wb") == NULL) {
        perror("Error en escribirAFichero: el fichero no ha podido ser abierto\n");
        exit(-1);
    }
    while(cf != NULL) {
        fwrite(&(cf->esSBox), sizeof(unsigned char), 1, f);
        fwrite(&(cf->bitACambiar), sizeof(unsigned char), 1, f);
        fwrite(&(cf->valorASumar), sizeof(unsigned char), 1, f);
        fwrite(&(cf->sig), sizeof(struct TBox), 1, f);
        cf = cf->sig;
    } 
    fclose(f);
}

/* (1.0 puntos) funcion que destruye un esquema de cifrado y libera la memoria que ocupa*/
void destruirEsquemaDeCifrado (TCifrado *cf) {
    TCifrado ant = *cf;
    while(*cf != NULL) {
        ant = *cf;
        *cf = (*cf)->sig;
        free(ant);
    }
}