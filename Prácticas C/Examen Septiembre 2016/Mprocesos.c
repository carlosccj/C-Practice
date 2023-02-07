#include <stdlib.h>
#include <stdio.h>
#include "Mprocesos.h"

void Crear(LProc *lroundrobin) {
    *lroundrobin = NULL;
}

void AnadirProceso(LProc *lroundrobin, int idproc) {
    LProc curr = *lroundrobin;
    LProc nuevoNodo = (LProc) malloc (sizeof(struct Nodo));
    if (nuevoNodo == NULL) {
        perror("Error en AnadirProceso: no se ha podido reservar memoria para el nuevo nodo\n");
        exit(-1);
    }
    nuevoNodo->pId = idproc;
    if(curr == NULL) {
        nuevoNodo->sig = nuevoNodo;
        *lroundrobin = nuevoNodo;
    } else {
        nuevoNodo->sig = curr->sig;
        curr->sig = nuevoNodo;
        *lroundrobin = nuevoNodo;
    } 
}

void EjecutarProcesos(LProc lroundrobin) {
    if (lroundrobin != NULL) {
    LProc curr = lroundrobin->sig;
    int i = 0;
        while (curr->pId != (lroundrobin)->pId || i == 0) {
        printf("Proceso %d con identificador %d ejecutandose\n", i, curr->pId);
        curr = curr->sig;
        i++;
    }
    printf("Proceso %d con identificador %d ejecutandose\n", i + 1, curr->pId);
    } else {
        printf("La lista esta vacia\n");
    }
}


void EliminarProceso(int id, LProc *lista) {
    LProc ant = *lista;
    LProc curr = ant->sig;
    while(curr->pId != id) {
        ant = curr;
        curr = curr->sig;
    }
    ant->sig = curr->sig;
    if(curr->pId == (*lista)->pId) { // si eliminamos el ultimo proceso tenemos que cambiar *lista
        *lista = ant;
    }
    free(curr);
}

int calcProc (LProc lista) {
    LProc curr = lista->sig;
    int res = 1;
    while(curr->pId != lista->pId) {
        res++;
    }
    return res;
}

void EscribirFichero (char *nomf, LProc *lista) {
    FILE *f = fopen(nomf, "wb");
    LProc curr = *lista;
    if (fopen(nomf, "wb") == NULL) {
        perror("Error en EscribirFichero: no se ha podido abrir el fichero\n");
        exit(-1);
    }
    int nproc = calcProc(*lista);
    fwrite(&nproc, sizeof(int), 1, f);
    while (nproc != 0) {
        fwrite(&((*lista)->pId), sizeof(int), 1, f);
        curr = *lista;
        *lista = (*lista)->sig;
        free(curr);
        nproc--;
    }
    fclose(f);
}