/*
 * hola.c
 *
 *  Created on: 24 feb. 2021
 *      Author: laura
 */

//Mirar LISTA ENLAZADA DE NODOS
// A OBJ--> B OBJ--> ...
// 7 OBJ--> 8 OBJ--> ...


#include <stdlib.h>
#include <stdio.h>

int main(void){
	int op1, op2, i;
	char op;
	char matricula[7];
	int frecuencia[5]={0,0,0,0,0};

	setvbuf(stdout, NULL, _IONBF, 0);

	printf(" 1) calculadora\n 2) contador vocales\n 3) comprobar matricula \n q) Salir\nIntroduce una opcion: \n");
	scanf(" %c",&op);
	while(op!='q'){
	if(op=='1') //Este codigo deberia llevarse a una funcion calculadora
	{		
		printf("Introduce la operacion (+,-,*,/) en notacion infija: ");
		if(scanf(" %d %c %d", &op1, &op, &op2)!=3)
			printf("Hay que introducir operando1 operdor operando2\n");
		else{
		switch(op){
		case '+': { printf("resultado =%d\n",op1 + op2); break;}
		case '-': { printf("resultado =%d\n", op1 - op2); break;}
		case '*': { printf("resultado =%d\n",op1 * op2); break;}
		case '/': { if (op2!=0)
						printf("resultado =%d\n",op1 / op2);
					else
						printf("Error: Division por 0.\n");
					break;
		}
		default: {
			printf("Opcion no soportada.\n");
		}

		}
		}
	}else if(op=='2'){//Este codigo se deberia llevarse a una funcion contarfrecuencia
		for(i=0; i<5; i++)
			frecuencia[i]=0;

		scanf("%c", &op);
		while(op!='#'){
			switch(op){
			case 'a': {frecuencia[0]++; break;}
			case 'e': {frecuencia[1]++; break;}
			case 'i': {frecuencia[2]++; break;}
			case 'o': {frecuencia[3]++; break;}
			case 'u': {frecuencia[4]++; break;}

				printf("leido = %c actualizado %d\n",op, frecuencia[op-97]);
			}
			scanf("%c", &op);
		}
		printf("Frecuencias a=%d e=%d i=%d o=%d u=%d\n", frecuencia[0],frecuencia[1],frecuencia[2],frecuencia[3],frecuencia[4]);
	}
	else if(op=='3'){		
		if (scanf("%7s", matricula)){
			
			i=0;
			op1=1; // aqui uso op1 como boolean para saber si se ha detectado un error en la matricula
			while(i<7 && op1){			
			//Los caracteres 0-3 tienen que ser numeros -> i<=3 && c>='0' && c <='9'
			//Los carateres 4-6 tienen que ser letras mayusculas -> i>3 && c>='A' && c<='Z'
			//La matricula es valida si se da alguna de las dos condiciones			
				if(!(i<=3 && matricula[i]>='0' && matricula[i]<='9') && !(i>3 && matricula[i]>='A' && matricula[i]<='Z')){
					op1 = 0;
				}
				i++;
			}		
			if(op1){
				printf("Matricula correcta\n");
			}else{
				printf("Matricula INCORRECTA\n");
			}
		}
	}
	else{
		printf("Opcion no soportada. \n");
	}
		printf(" 1) calculadora\n 2) contador vocales\n 3) comprobar matricula \n q) Salir\nIntroduce una opcion: \n");
		scanf(" %c",&op);
	}
  return EXIT_SUCCESS; //esta constante se define en stdlib.h
  //es igual a poner return 0; para indicar que el programa ha terminado correctamente
}


//int main (int argc, char *arg[])
// argc: numero de argumentos, siempre es >=1
// argv: array de cadenas de caracteres con cada uno de los argumentos que se han pasado
//argv[0] es el nombre del programa que se llama
