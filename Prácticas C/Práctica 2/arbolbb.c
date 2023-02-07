#include "arbolbb.h"
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>

// Crea la estructura utilizada para gestionar el árbol.
void crear(T_Arbol* arbol) {
    *arbol = NULL;
}
// Destruye la estructura utilizada y libera la memoria.
void destruir(T_Arbol* arbol) {
    if(*arbol != NULL) {
        destruir(&((*arbol) -> izq));
        destruir(&((*arbol) -> der));
        free(*arbol);
    }
}
// Inserta num en el árbol. Si ya está insertado, no hace nada
void insertar(T_Arbol* arbol, unsigned num) {
    if (*arbol != NULL) {
        if (num < (*arbol)->dato) {
            insertar(&((*arbol)->izq), num);
        } else if (num > (*arbol)->dato) {
            insertar(&((*arbol)->der), num);
        }
    } else {
        //creamos nuevo nodo con dato = NULL
        *arbol = malloc(sizeof(struct T_Nodo));
        if(*arbol == NULL) {
            perror("Error en insertar al insertar memoria");
            exit(-1);
        } else {
            (*arbol)->dato = num;
            (*arbol)->izq = NULL;
            (*arbol)->der = NULL;
        }
    }
}
// Muestra el contenido del árbol en InOrden
void mostrar(T_Arbol arbol) {
    if(arbol != NULL) {
        mostrar(arbol->izq);
        printf("%d ", arbol->dato);
        mostrar(arbol->der);
    }
}
// Guarda en disco el contenido del fichero
void salvar(T_Arbol arbol, FILE* fichero) {
    if (arbol!=NULL) {
        salvar (arbol->izq,fichero);
        fwrite(&(arbol->dato),sizeof(int),1,fichero);
        salvar (arbol->der,fichero);
    }
}


//#endif /* ARBOLBB_H_ */
    