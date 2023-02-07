
#include <stdio.h>
#include <stdlib.h>

#define MAX 6
int main() {

      int day = 19;
      char c1,c2,c3, month[MAX];   
      //month es un array de caracteres de longitud MAX, para almacenar una cadena valida el ultimo caracter debe ser '\0'   
      int year = 2019;
      double temp = 15.7;
      int result;
    
	//En algunas versiones de Eclipse-C Windows es necesario porque no fucniona bien la consola
  	setvbuf(stdout, NULL, _IONBF, 0);
      /*Ejemplo scanf -- int scanf(char char *format, arg1,arg2,..., argn)
      * Los argumentos deben ser punteros a las variables donde se almacenan los valores leidos
      * Un puntero es una direccion de memoria
      */
      
      printf("Introduce el dia (int): ");
      result= scanf("%d",&day);  // &variable me devuelve la direccion de memoria donde se almacena esa variable
      printf("scanf valor devuelto: %d valor leido: %d\n", result, day);

      
      printf("Introduce la temperatura (double): ");
      scanf("%lf",&temp);
      printf("valor leido: %3lf\n", temp);
      
      printf("Introduce el mes (string sin espacios): ");
      //result = scanf("%4s",&month[0]);
      result = scanf("%6s",month);  
      //%s leera una cadena delimitada por un separador como espacio, tabulador o salto de linea
      // scanf puede leer mas caracteres de los que realmente podemos almacenar en la cadena -> desbordamiento de memoria
      // por eso he puesto que a lo sumo lea MAX caracteres
      printf("scanf valor devuelto %d valor leido: %s\n", result, month);
      
      printf("Introducir dia mes y año separados por espacios:\n");
      result = scanf("%d %s %d",&day, &month[0], &year);
      printf("scanf valor devuelto %d, leido dia: %d mes: %s año: %d\n", result, day, month, year);
      
     /*
      printf("Introduce 3 caracterecs (sin espacios)\n");
      scanf("%c%c%c", &c1,&c2,&c3);  //el primer caracter sera el salto de linea del scanf anterior
      printf("valores leidos : %c - %c - %c\n", c1,c2,c3);   
       */
      printf("Introduce 3 caracteres (v2)\n");
      scanf(" %c %c %c", &c1,&c2, &c3); //asi ignoramos el salto de linea y los espacios en blanco anteriores
      printf("valores leidos : %c - %c - %c\n", c1,c2,c3);     
      
      printf("Introduce una cadena con espacios: ");     
      while (scanf(" %s",month)==1){  //en windows salimos del bucle con ctrl+c   
      	printf("leido: %s\n", month);
      }
      printf("Hemos terminado!\n ");
     return 0; 
}
