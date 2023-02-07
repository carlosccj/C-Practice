/*
 * principal.c
 *
 *  Created on: 21 mar. 2019
 *      Author: Laura
 */

#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <time.h>
#include "arbolbb.h"

void crearFicheroAleatorios(char *nombreFich, int TAM)
{
	//Abrir fichero binario
	FILE *b;
	int i, num;
	if((b = fopen(nombreFich, "wb")) == NULL) {
		perror("crearFicheroAleatorio: error al abrir el fichero");
		exit(-1);
	}
	//generar semilla
	srand(time(NULL));
	//generar TAM numeros aleatorios al escribir el fichero
	for(i=0; i<TAM; i++) {
		num = rand()%TAM;
		fwrite(&num, sizeof(int), 1, b);
	}
	//cerrar fichero
	fclose(b);
}
void mostrarFicheroAleatorios(char *nombreFich)
{
	FILE *b;
	int num;
	//Abrir fichero binario en modo lectura
	if((b = fopen(nombreFich, "rb")) == NULL) {
		perror("mostrarFicheroAleatorio: error al abrir el fichero");
		exit(-1);
	}
	
	//Mientras el fichero tenga datos, mostrarlos por pantalla
	printf("Contenido del fichero\n");
	while(fread(&num, sizeof(int), 1, b)) {
		num = fread(&num, sizeof(int), 1, b);
		printf("%d\t", num);
	}
	printf("\n");
	fclose(b);
}

void cargaFichero(char* nfichero, T_Arbol* miarbol)
{
	FILE* fich;
	if(fopen(nfichero, "rb") == NULL) {
		perror("Error en cargarFichero\n");
		exit(-1);
	} else {
		int num, result;
		result = fread(&num, sizeof(int), 1, fich);
		while(result == 1) {
			insertar(miarbol, num);
			printf ("He metido en arbol: %d \n", num);
			result = fread(&num, sizeof(int), 1, fich);
		}
	}
	fclose(fich);
}

int main(int argc, char **argv)
{
	int TAM;
	char nombreFich[30];

	setvbuf(stdout, NULL, _IONBF, 0);
	printf("Introduzca nombre fichero:\n");
	scanf("%s", nombreFich);

	printf("Introduzca tamaño:\n");
	scanf("%d", &TAM);

	crearFicheroAleatorios(nombreFich, TAM);
	mostrarFicheroAleatorios(nombreFich);

	printf ("\n Ahora lo cargamos en el arbol\n");
	T_Arbol miarbol;
	crear (&miarbol);
	cargaFichero(nombreFich,&miarbol);
	printf ("\n Y lo mostramos ordenado\n");
	mostrar(miarbol);

	printf("\n Ahora lo guardamos ordenado\n");
	FILE * fich;
	fich = fopen (nombreFich, "wb");
	salvar (miarbol, fich);
	fclose (fich);
	printf("\n Y lo mostramos ordenado\n");
	mostrarFicheroAleatorios(nombreFich);
	destruir (&miarbol);
}













































