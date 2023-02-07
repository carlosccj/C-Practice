//practica1.c gestion_memoria.c -o practica1.exe´
/*
 ============================================================================
 Name        : practica1.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description :
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include "gestion_memoria.h"

int main(void) {

	T_Manejador manej;
	unsigned ok;
	unsigned dir;

	crear(&manej);
	mostrar(manej);

	obtener(&manej,500,&dir,&ok); /* Se ha hecho una foto. Se necesita memoria */
	if (ok) {
		mostrar(manej);
		printf("\nSe han reservado 500 bloques desde la direccion: %d\n", dir);
	} else {
		printf("No es posible reservar 500 bloques de memoria\n");
	}

	devolver(&manej, 200,0); /* Se ha enviado parte de la foto. Ya se puede borrar */
	mostrar (manej);


	obtener(&manej,250,&dir,&ok); /* Se ha hecho otra foto */
	if (ok) {
		mostrar(manej);
		printf("\nSe han reservado 250 bloques desde la direccion: %d\n", dir);
	} else {
		printf("\nNo es posible reservar 250 bloques de memoria\n");
	}

	devolver(&manej,100,500); /* Se ha enviado parte de la foto. Ya se puede borrar */
	mostrar(manej);

 	obtener(&manej,250,&dir,&ok); /* Se ha hecho otra foto----------------------(+1 en función¿?)-------------- */
	if (ok) {
		mostrar(manej);
		printf("\nSe han reservado 250 bloques desde la direccion: %d\n", dir);
	} else {
		printf("\nNo es posible reservar 250 bloques memoria\n");
	}

	devolver(&manej, 50, 250); /*-------------(Se libera memoria ya libre¿?)--------- Se ha enviado parte de la foto. Ya se puede borrar */
	mostrar(manej);

	escribirTxt("nombreFichero.txt", manej);
	escribirBin("nombreFichero.bin", manej);

	//vamos a devolver memoria que falta para que al compactar salga un único bloque
	devolver(&manej, 50, 200); //linea alterada para que encaje con mi input
	devolver(&manej, 200, 300);
	devolver(&manej, 150, 600);
	mostrar(manej);
	
	printf("Vamos a compactar los bloques libre de memoria\n");
	compactarPersonalizado(&manej);
	mostrar(manej);	
	
	destruir(&manej);
	mostrar (manej);

	// implementar funcion de lectura

	printf("\nFin Programa\n");

	return EXIT_SUCCESS;
}
