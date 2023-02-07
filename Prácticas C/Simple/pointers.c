#include <stdio.h>
#include <stdlib.h>

#define MAX 5;

int main () {

/*
char str[MAX];
printf("Introduce texto:\n");
scanf("%4s", &str);
printf("La cadena introducida es: %s", str);
*/

   int  var = 20; 
   int var2 = 50;
   int  *ip = (int*) malloc (sizeof(int));   

   ip = &var; // Reservamos la dirección de memoria 'var' en *ip
   int *ip2 = ip; // *ip2 ahora apunta a la misma dirección de memoria que *ip

   printf("**********\n");
   printf("Adress stored in ip variable: %x\n", ip);
   printf("Adress stored in ip2 variable: %x\n", ip2);
   printf("Adress of var: %x\n", &var);
   printf("Value of *ip variable: %d\n", *ip);
   printf("Value of *ip2 variable: %d\n", *ip2);
   printf("**********\n");

/* Modificamos el valor de var (20) para que sea el de var2 (50). La dirección almacenada
   por ambos punteros sigue siendo la misma (la de var), pero su valor ha cambiado.
*/   
   
   var = var2; 

   printf("Adress stored in ip variable: %x\n", ip);
   printf("Adress stored in ip2 variable: %x\n", ip2);
   printf("Adress of var2: %x\n", &var2);
   printf("Value of *ip variable: %d\n", *ip);
   printf("Value of *ip2 variable: %d\n", *ip2);
   printf("**********\n");

/* Cuando queremos hacer free de un puntero que apunta a la misma variable que otro es importante
   colocar ambos a NULL para no dejar basura en esas direcciones (dangling pointers). Primero
   colocamos la referencia del puntero principal (ip) a NULL. Luego liberamos el espacio de 
   memoria asignado haciendo free del puntero secundario (ip2). Colocar ip2 a NULL es opcional.
*/

   ip = NULL;
   free(ip2);
   ip2 = NULL;

   printf("Adress stored in ip variable: %x\n", ip);
   printf("Adress stored in ip2 variable: %x\n", ip2);
   //printf("Value of *ip variable: %d\n", *ip);
   //printf("Value of *ip2 variable: %d\n", *ip2);
   printf("**********\n");

/* Aritmética de punteros: podemos incrementar el valor de un puntero haciendo ip + 1. En ese
   caso apuntará a la siguiente dirección de memoria que resulte de sumar: 
   (dirección de ip + nº de bytes del data type de ip). 
   Para el caso de int serán 4 bytes. Para un char será un byte. Podemos usar esta técnica para 
   acceder a cualquier carcácter de un String.
*/

   char st[] = "Hola que tal";
   
   printf("String en st: %s\n", st);
   printf("Primer char de st: %c\n", *st);
   printf("Segundo char de st: %c\n", *(st + 1));
   printf("Imprimimos con un for a traves de aritmetica de punteros \n");
   for (int i = 0; i < 12; i++) {
      printf("%c", *(st + i));
   }

}