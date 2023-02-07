#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {

	setvbuf(stdout, NULL, _IONBF, 0); //evitar buffering en stdout --eclipse para windows
	int c;
	/* int getchar(void) gets a character (an unsigned char) from stdin*/
	printf("Introduce un caracter (f para salir): ");

	while((c=getchar())!='f') 
    	putchar(c); //solo muestra un caracter

	
	/* //aunque getchar devuelve un int tambien podemos usar una variable char para almacenar el valor devuelto
	char ch;  
	while((ch=getchar())!='f')
	{
		putchar(ch);
	}*/

  printf("Ahora leemos caracteres hasta EOF\n");
  while((c=getchar())!=EOF) 
  // en el terminal MAC EOF equivale a Ctrl + d
  // en el terminal Windows equivale a Ctrl + z
      putchar(c);

  return 0;
}
