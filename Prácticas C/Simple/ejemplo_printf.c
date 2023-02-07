
//Macros del Preprocesador  comienzan con #
#include <stdio.h> //incluimos la biblioteca de entrada salida
#include <stdlib.h> //incluimos la biblioteca estandard de C
//Definición de una constante con directivas de preprocesado
#define MAX 23;

/* Metodo de entrada del programa.
   Devuelve un entero que indica si el programa ha terminado correctamente (0)
   o si se ha producido algun error (valor distinto de 0).
   argc dice cuantos argumentos de entrada se han recibido, y argv es un array con los
   argumentos de entrada como cadena de caracteres.
*/
int main(int argc, char *argv[])
{
  //En algunas versiones de Eclipse-C en Windows es necesario porque no fucniona bien la consola
   setvbuf(stdout, NULL, _IONBF, 0);
  
  //definimos algunas variables de diferentes tipos 
  float f = 23.4568;
  double d = 23.4568;
  int i = 100;
  char c = 'a';
  char nombre[20] = "Laura"; //nombre[0]='L' ....nombre[4]= 'a' nombre[5]='\0'

// conversion de tipos implicito
  i +='a'; //suma a i el codigo ascii que representa el caracter 'a'
  int h = c += 15; //suma al ascii de 'c' (99) el entero 15 
  

  printf("***************** Hola Mundo! *************************\n");

  //Para cada arg tenemos que especificar el formato/conversion para representarlo
  printf("variable entera i = %d variable char c = %c\n", i,c);
  printf("variable entera h = %d\n", h);
  /*
   El simbolo % indica que se trata de una conversion: %d, %c, etc.
   El simbolo \ indica caracter de escape: 
        \n salto de linea
        \t tabulador
        \\ para mostrar por pantalla\
        \a alerta - suena un bip
  */
  
//Formatos para representar diferentes tipos
/*********** Enteros ***************/
printf("*********** Enteros ***************\n");
  printf("int %d, el caracter de escape \\n no tiene que ir pegado al caracter de conversion\n", i);  //%d o %i -> decimal con signo
  printf("int (ancho minimo 4):%4d\n", i); //como i se representa con 3 digitos introduce un espacio a la izquierda
  printf("int (ancho minimo 2):%2d\n", i);
  printf("int (modificador 0, ancho minimo 4):%04d\n", i);//el modificador 0 añade 0's en vez de espacios si no se llega al ancho minimo
  printf("int (precision 6):%.6d\n", i);
  printf("int (precision 2):%.2d\n", i);
  
  printf("hex int %4x\n", i);     //hexadecimal enteros %X usa letras mayusculas
  printf("hex int (modificador #):%#4x\n", i);    //hexadecimal añade 0x
  printf("oct int %4o\n", i);     //octal  
  
/************ Punto Flotante y double **********/
printf("************ Punto Flotante y double **********\n");  
  printf("float %f\n", f);   // %f decimal punto flotante
  printf("float (ancho minimo 9 precision 2) %9.2f\n", f);
  printf("float (ancho minimo 9 precision 9) %9.9f\n", f);
  printf("hex float %a\n", f);    //hexadecimal flotantes %A usa letras mayusculas
  printf("double %lf\n", d); //flotante largo
  printf("double (exponencial) %e\n", d);  //%E notacion exponencial en mayusculas
  printf("double (notacion mas corta) %g\n", d);  //%g (%G) representacion mas corta entre %f y %e (%F y %G )
  printf("double (ancho 9 precision 2):%9.2lf\n", d);
  printf("double (ancho 9 precision 9)%9.9lf\n", d);


 /*********** Caracteres *************/ 
printf("************* Caracteres ***********\n");
  printf("caracter:%c\n", c);
  printf("caracter (ancho 4):%4c\n", c);
  
  printf("caracter como entero %d\n", c);
  printf("entero como caracter %c\n", i);

printf("************* String ***********\n");
  printf("string %s\n",nombre);     //'\0'
  printf("string (ancho 10):%10s\n", nombre); // Salen 6 espacios delante del stinrg 'Laura'
  printf("string (precision 3)%.3s\n", nombre);  
  

 /*
      * int printf(const char *format, arg1, arg2, ..., argn)
      * Si tiene exito, devulve el numero total de caracteres escritos,
      * en otro caso devuelve un numero negativo
      */
      char cadena[50];
      int result;
      result = printf("variable entera i = %d variable char c = %c\n", i, c);
      printf("printf valor devuelto %d\n", result); //Numero de caracteres del printf de arriba


      /* sprintf -- int sprintf(char *str, const char *format, arg1, arg2, ..., argn)
       * almacena el resultado en un string (str) en vez de mostrarlo por pantalla.
       * Si tiene exito, devulve el numero total de caracteres escritos,
       * en otro caso devuelve un numero negativo       
       */
      
      result = sprintf(cadena,"variable entera i = %d variable char c = %c\n", i,c );
      printf("sprintf valor devuelto %d\n", result);
      printf("sprintf cadena: %s", cadena);  

  return 0 ;
}
