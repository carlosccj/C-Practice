#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "Componentes.h"
#include <errno.h>

/*
La rutina Lista_Vacia devuelve 1 si la lista que se le pasa
como parametro esta vacia y 0 si no lo esta.
*/
int Lista_Vacia(Lista lista) {
    int res = 0;
    if (lista == NULL) {
        res = 1;
    }
    return res;
}

/*Num_Elementos es una funcion a la que se le pasa un puntero a una lista 
y devuelve el numero de elementos de dicha lista.
*/
int Num_Elementos(Lista lista) {
    int num = 0;
    while (lista != NULL) {
        num++;
        lista = lista->sig;
    }
    return num;
}

/*
La rutina Adquirir_Componente se encarga de recibir los datos de un nuevo 
componente (codigo y texto) que se le introducen por teclado y devolverlos 
por los parametros pasados por referencia "codigo" y "texto".
*/
 void Adquirir_Componente(long *codigo, char *texto) {
    printf("Introducir codigo:\n ");
    scanf("%ld", codigo);
    printf("Introducir texto:\n ");
    scanf("%32s", texto);
 }

/*
La funcion Lista_Imprimir se encarga de imprimir por pantalla la lista 
enlazada completa que se le pasa como parametro.
*/
void Lista_Imprimir(Lista lista) {
    int elem = 0;
    if(lista == NULL) {
        printf("La lista esta vacia\n");
    } else {
        while(lista != NULL) {
        printf("El elemento %d de la lista tiene codigo: %ld\n", elem, lista->codigoComponente);
        printf("Su fabricante es: %s\n", lista->textoFabricante);
        lista = lista->sig;
        elem++;
        }
    }
}

/*
La funcion Lista_Salvar se encarga de guardar en el fichero binario 
"examen.dat" la lista enlazada completa que se le pasa como parametro. 
Para cada nodo de la lista, debe almacenarse en el fichero
el código y el texto de la componente correspondiente.
*/
void Lista_Salvar(Lista lista) {
    FILE *f;
    Lista ptr = lista;
    char *nombreFichero = "examen.dat";
    if ((f = fopen(nombreFichero, "wb")) == NULL) {
        perror("No se ha podido encontrar el fichero\n");
        exit(-1);
    } else {
        while (ptr != NULL) {
            fwrite(&(ptr->codigoComponente), sizeof(int), 1, f);
            fwrite(&(ptr->textoFabricante), MAX_CADENA * sizeof(char), 1, f);
            ptr = ptr->sig;
        }   
    }
    fclose(f);
}


/*
La funcion Lista_Crear crea una lista enlazada vacia
de nodos de tipo Componente.
*/
Lista Lista_Crear() {
    Lista lista = NULL;
    return lista;
}

/*
La funcion Lista_Agregar toma como parametro un puntero a una lista,
el código y el texto de un componente y  anyade un nodo al final de 
la lista con estos datos.
*/
void Lista_Agregar(Lista *lista, long codigo, char* textoFabricante) {
    Lista nuevoComponente = (Lista) malloc (sizeof(struct elemLista));
    nuevoComponente->codigoComponente = codigo;
    strcpy(nuevoComponente->textoFabricante, textoFabricante);
    nuevoComponente->sig = NULL;
    if (*lista == NULL) {
        *lista = nuevoComponente;
    } else {
        Lista curr = *lista; // Al poner esta igualdad modificas ambos
        while (curr->sig != NULL) {
            curr = curr->sig;
        }
        curr->sig = nuevoComponente;
    }
}

/*
Lista_Extraer toma como parametro un puntero a una Lista y elimina el
Componente que se encuentra en su ultima posicion.
*/

void Lista_Extraer(Lista *lista) {
    if (*lista == NULL) {
        perror("Error en Lista_Extraer: la lista esta vacia\n");
        exit(-1);
    
    } else if (((*lista)->sig) == NULL) {
        free(*lista);
        *lista = NULL;
    } else {
        Lista curr = *lista;
        Lista pos = curr->sig;
        while (pos->sig != NULL) {
            curr = pos;
            pos = pos->sig;
        }
        curr->sig = NULL;
        free(pos);
    }
}

/*
Lista_Vaciar es una funcion que toma como parametro un puntero a una Lista
y elimina todos sus Componentes.
*/
void Lista_Vaciar(Lista *lista) {
    Lista curr = (*lista)->sig;
    if (*lista == NULL) {
        perror("Error en Lista_Vaciar: la lista ya estaba vacia\n");
        exit(-1);
    } else if (curr == NULL) {
        free(*lista);
        *lista = NULL;
    } else {
        while (curr != NULL) {
            free(*lista);
            *lista = NULL;
            *lista = curr;
            curr = curr->sig;
        }
        free(*lista);
        *lista = NULL;
    }
}

void Lista_VaciarP(Lista *lista) {
    Lista aux=*lista;
    while(*lista!=NULL) {
        aux=*lista;
        *lista=(*lista)->sig;
        free(aux);
    }
}

//#endif /* COMPONENTES_H_ */

// Fin fichero
// ===========
