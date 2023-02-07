#include <stdio.h> //incluimos la biblioteca estandard de entrada salida

/* Metodo de entrada del programa.
   Devuelve un entero que indica si el programa ha terminado correctamente (0)
   o si se ha producido algun error (valor distinto de 0)
   En este caso no recibe argumentos de entrada, pero puede recibirlos
*/
int main(void){ 
    
    printf("Hola Mundo\n");//muestra por salida estandar el mensaje

    return 0; //Terminamos bien

}
/* Vamos a compilar desde el terminal
    1) Vamos con el terminar a la carpeta donde esta el fichero .c
    2) Llamamos al compilador gcc <fichero>.c -o <fichero ejecutable>. 
    En este ejemplo y para un sistema operativo windows:
    gcc helloworld.c -o helloworld.exe
    3) Si no hay errores de compilacion, ejecutamos en el terminal <fichero ejecutable>.
    En este ejemplo y para un sistema operativo windows:
    helloworld.exe


*/