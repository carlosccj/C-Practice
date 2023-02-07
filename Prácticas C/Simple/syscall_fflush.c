/**
 * Syscall.c
 */

#include <stdio.h>
#include <unistd.h>
#include <string.h>

int main(int argvc, char ** argv) {
  //setvbuf(stdout, NULL, _IONBF, 0); //evitar buffering en stdout --eclipse para windows
  
  printf("Hola, mundo (desde printf)\t") ;
  //fflush(stdout); //descomentar para vaciar el buffer intermedio antes de hacer la llamada al sistema  
  write(1,"Adios, Mundo\n", strlen("Adios, Mundo\n")) ;
  // system nos permite hacer llamadas al sistema, tenemos que poner como una cadena el comando que queremos ejecutar
  //system("dir");
  return 0;
}
