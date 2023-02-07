#include "gestion_memoria.h"
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>

const int MAX = 999;

/* Crea la estructura utilizada para gestionar la memoria disponible. Inicialmente, sólo un nodo desde 0 a MAX */
	void crear(T_Manejador* manejador) {
        T_Manejador nuevoNodo = (T_Manejador) malloc (sizeof(struct T_Nodo));
        // Creamos un puntero de tipo T_Manejador llamado nuevoNodo. Este puntero estará apuntando a
        // la primera posición de un bloque con un numero de bytes igual al de la estructura T_Nodo.
        
        if(nuevoNodo == NULL) { // En este caso la memoria no puede ser reservada (error en malloc).
            perror("Error reservando memoria \n");
            exit(-1);
        } else { // Se inicializan los valores del único trozo de memoria disponible en nuestra lista.
            nuevoNodo->inicio = 0;
            nuevoNodo->fin = MAX;
            nuevoNodo->sig = NULL;
            *manejador = nuevoNodo; // manejador = &nuevoNodo;
        }
    }

/* Destruye la estructura utilizada (libera todos los nodos de la lista. El parámetro manejador debe terminar apuntando a NULL */
	void destruir(T_Manejador* manejador) {
        T_Manejador cus = *manejador;
        T_Manejador aux;
        while(cus != NULL) {
            aux = cus;
            free(aux);
            cus = cus->sig;
        }
    }

/* Devuelve en 'dir' la dirección de memoria 'simulada' (unsigned) donde comienza el trozo de memoria continua de tamaño 'tam' 
solicitada. Si la operación se pudo llevar a cabo, es decir, existe un trozo con capacidad suficiente, devolvera TRUE (1) en 'ok'; 
FALSE (0) en otro caso.
 */
	void obtener(T_Manejador *manejador, unsigned tam, unsigned* dir, unsigned* ok) {
        T_Manejador cus, ant;
        ant = NULL;
        cus = *manejador;
        while(cus != NULL && ((cus->fin - cus->inicio) < tam)) { // no tenemos espacio en el bloque i
            ant = cus;
            cus = cus->sig;
        }

        if(cus == NULL) { // no hay espacio en ningun lugar de la memoria
            *ok = 0;
        } else { // tenemos espacio
            *ok = 1;
            *dir = cus->inicio;
            if((cus->fin - cus->inicio + 1) > tam) { // tamaño del bloque superior a la porcion requerida
                cus->inicio = cus->fin - tam + 1;
            } else { // tamaño del bloque igual a la porcion requerida
                if(ant == NULL) { // liberar bloque inicial
                    *manejador = (*manejador)->sig;
                    free(cus);
                } else { // liberar bloque intermedio
                    ant->sig = cus->sig;
                    free(cus);
                }
            }

            printf("\nSe ha obtenido un bloque de tamano %d en la dir inicial %d\n", tam, *dir);
        }
    }

/* Muestra el estado actual de la memoria, bloques de memoria libre */
	void mostrar (T_Manejador manejador) {
        T_Manejador cus = manejador;
        printf("\nBloques de memoria libre\n");
        int i = 0;
        while(cus != NULL) {
            printf("****** Bloque %d ****** \n", i);
            printf("INICIO: %d, FIN: %d\n", cus->inicio, cus->fin);
            printf("************\n");
            cus = cus->sig;
            i++;
        }
    }

/* Devuelve el trozo de memoria continua de tamaño 'tam' y que
 * comienza en 'dir'. Se puede suponer que se trata de un trozo obtenido previamente.
 */
	void devolver(T_Manejador *manejador,unsigned tam,unsigned dir) {
        T_Manejador cus, ant;
        ant = NULL;
        cus = *manejador;

        T_Manejador nuevoNodo = (T_Manejador) malloc (sizeof(struct T_Nodo));
        // como se va a liberar un espacio en la memoria, debemos crear un nuevo nodo

        if(nuevoNodo == NULL) { // la memoria no puede ser reservada (error en malloc)
            perror("Error liberando memoria\n"); 
            exit(-1);
        } else { // establecemos el inicio y el fin del nuevo nodo (que nos vienen dados en los parametros de la funcion)
            nuevoNodo->inicio = dir;
            nuevoNodo->fin = dir + tam - 1;

            while(cus != NULL && cus->inicio < dir) { // iteramos por la lista para saber cual sera el nodo siguiente al nuevo
                ant = cus;
                cus = cus->sig;
            }

            if(ant == NULL) { // el nuevo nodo será el primero de la lista
                nuevoNodo->sig = *manejador;
                *manejador = nuevoNodo;
            } else { // el nuevo nodo irá situado en algún lugar en medio de la lista
                nuevoNodo->sig = cus;
                ant->sig = nuevoNodo;
            }

            printf("\nSe ha liberado un nodo de tamano %d en la pos %d", tam, dir);
        }
    }
    

    void compactarPersonalizado(T_Manejador *manejador) {
        T_Manejador cus = *manejador;
        T_Manejador pos = cus->sig;
        T_Manejador ant = NULL;

        while(cus != NULL && pos != NULL) {
            if(cus->fin == (pos->inicio - 1)) {
                T_Manejador nuevoNodo = (T_Manejador) malloc(sizeof(struct T_Nodo));
                nuevoNodo->inicio = cus->inicio;
                nuevoNodo->fin = pos->fin;
                nuevoNodo->sig = pos->sig;
                if(ant == NULL) {
                    *manejador = nuevoNodo;
                } else {
                    ant->sig = nuevoNodo;
                }
                free(cus);
                free(pos);
                cus = nuevoNodo;
                pos = nuevoNodo->sig;
            } else {
                ant = cus;
                cus = pos;
                pos = pos->sig;
            }
        }
    }

    void compactar(T_Manejador *manejador) {
		T_Manejador curr, ant;
		curr = *manejador;
		ant = NULL;
		while(curr!=NULL){
			if(ant!=NULL && (curr->inicio - ant->fin ==1)) {
				ant->fin = curr->fin;
				ant->sig = curr->sig;

				free(curr);
				curr = ant->sig;
			} else {
				ant = curr;
				curr = curr->sig;
			}
		}
	}

        void escribirTxt(char *nombreFichero, T_Manejador manejador) {
            FILE *f;
            T_Manejador ptr = manejador;
            if((f = fopen(nombreFichero, "wt")) == NULL) {
                perror("escribirTxt error al abrir");
                exit(-1);
            } else {
                int i = 0;
                while(ptr != NULL) {
                    fprintf(f, "BLOQUE %d: Inicio: %d, Fin: %d\n", i, ptr->inicio, ptr->fin);
                    ptr = ptr->sig;
                    i++;
            }
        }
            fclose(f);
        }

	    void escribirBin(char *nombreFichero, T_Manejador manejador) {
             FILE *f;
            T_Manejador ptr = manejador;
            if((f = fopen(nombreFichero, "wb")) == NULL) {
                perror("escribirBin error al abrir");
                exit(-1);
            } else {
            while(ptr != NULL) {
                fwrite(&(ptr->inicio), sizeof(int), 1, f);
                fwrite(&(ptr->fin), sizeof(int), 1, f);
                ptr = ptr->sig;
            }
        }
            fclose(f);
        }
        
    