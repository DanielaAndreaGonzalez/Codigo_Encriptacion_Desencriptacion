/**
 * 
 */
package Logica;

import java.util.Scanner;

/**
 * @author GonzalezHDanielaA
 * @since 16/09/2022
 * @version 1.0
 *
 */
public class Codificacion {
	//Vector que contiene los caracteres en hexadecimal 
	public static final char[] EXTENDED = { 0x00C7, 0x00FC, 0x00E9, 0x00E2,
            0x00E4, 0x00E0, 0x00E5, 0x00E7, 0x00EA, 0x00EB, 0x00E8, 0x00EF,
            0x00EE, 0x00EC, 0x00C4, 0x00C5, 0x00C9, 0x00E6, 0x00C6, 0x00F4,
            0x00F6, 0x00F2, 0x00FB, 0x00F9, 0x00FF, 0x00D6, 0x00DC, 0x00A2,
            0x00A3, 0x00A5, '×', 0x0192, 0x00E1, 0x00ED, 0x00F3, 0x00FA,
            0x00F1, 0x00D1, 0x00AA, 0x00BA, 0x00BF, 0x2310, 0x00AC, 0x00BD,
            0x00BC, 0x00A1, 0x00AB, 0x00BB, 0x2591, 0x2592, 0x2593, 0x2502,
            0x2524, 0x2561, 0x2562, 0x2556, 0x2555, 0x2563, 0x2551, 0x2557,
            0x255D, 0x255C, 0x255B, 0x2510, 0x2514, 0x2534, 0x252C, 0x251C,
            0x2500, 0x253C, 0x255E, 0x255F, 0x255A, 0x2554, 0x2569, 0x2566,
            0x2560, 0x2550, 0x256C, 0x2567, 0x2568, 0x2564, 0x2565, 0x2559,
            0x2558, 0x2552, 0x2553, 0x256B, 0x256A, 0x2518, 0x250C, 0x2588,
            0x2584, 0x258C, 0x2590, 0x2580, 0x03B1, 0x00DF, 0x0393, 0x03C0,
            0x03A3, 0x03C3, 0x00B5, 0x03C4, 0x03A6, 0x0398, 0x03A9, 0x03B4,
            0x221E, 0x03C6, 0x03B5, 0x2229, 0x2261, 0x00B1, 0x2265, 0x2264,
            0x2320, 0x2321, 0x00F7, 0x2248, 0x00B0, 0x2219, 0x00B7, 0x221A,
            0x207F, 0x00B2, 0x25A0, 0x00A0 };

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		String palabraClave = leerCadena("Ingrese la palabra clave").toUpperCase();
		//String nombreArchivo = leerCadena("Ingrese el nombre del archivo").toUpperCase();
		Archivos miDocumento =  new Archivos("COMPUTADORA");
		miDocumento.crearArchivo();
		String codigoEncriptado = codigoEntriptado(palabraClave, miDocumento.getNombreArchivo());
		miDocumento.guardarCodigo(codigoEncriptado);
		String desencriptado = miDocumento.leerCodigo();
		imprimirMensaje(codigoDesencriptado(desencriptado, miDocumento.getNombreArchivo()));

		
		
	}
	/**
	 * Metodo que desencripta una palabra a apartir de caracteres ascii
	 * @param codigoEncriptado
	 * @param nombreArchivo
	 * @return el codigo o la palabra desencriptada
	 */
	public static String codigoDesencriptado(String codigoEncriptado, String nombreArchivo)
	{
		String codigoDesencriptado = "";
		int codigoArchivo = 0;
		int codigo_encriptado=0;
		int resta = 0;
		char caracter = ' ';
		
		for(int i=0; i<=codigoEncriptado.length()-1; i++)
		{
			codigoArchivo = codigo_ASCII(nombreArchivo.charAt(i));
			
			if((int) codigoEncriptado.charAt(i)<127)
			{
				codigo_encriptado = codigo_ASCII(codigoEncriptado.charAt(i));				
			}
			else
			{
				codigo_encriptado = codigoASCII_extendido(codigoEncriptado.charAt(i));				
			}
			
			resta = (codigo_encriptado - codigoArchivo) ;
			caracter = entero_a_caracter(resta);
			codigoDesencriptado += caracter;
			resta =0;
		}
		return codigoDesencriptado;
	}
	/**
	 * Metodo que encripta un mensaje dado
	 * @param palabra
	 * @param archivo
	 * @return un mensaje encriptado
	 */
	public static String codigoEntriptado(String palabra, String archivo) {
		String codigoEncriptado = "";
		int codigoArchivo = 0;
		int codigoPalabra = 0;
		int suma = 0;
		char caracter = ' ';
		for (int i = 0; i <= palabra.length()-1; i++) {
			codigoArchivo = codigo_ASCII(archivo.charAt(i));
			codigoPalabra = codigo_ASCII(palabra.charAt(i));
			suma = (codigoArchivo + codigoPalabra);

			if (isExtentendido(suma)) {
				caracter = getAsciiExtendido(suma);
			} else {
				caracter = entero_a_caracter(suma);
			}
			codigoEncriptado +=caracter;
			suma = 0;
		}
		return codigoEncriptado;
	}
	
	/**
	 * Metodo que permite saber si el codigo dado es ascii extendido o no
	 * @param codigo
	 * @return un true si es ascii extendido
	 */
	public static boolean isExtentendido(int codigo)
	{
		boolean isExt = false;
		if(codigo > 127)
		{
			isExt = true;
		}
		return isExt;
	}
	/**
	 * Metodo que permite obtener un caracter del ascii extendido
	 * @param code
	 * @return
	 */
	public static final char getAsciiExtendido(int code) {
        if (code >= 0x80 && code <= 0xFF) {
            return EXTENDED[(code - 0x7F)-1];
        }
        return (char) code;
    }
	
	/**
	 * Método que convierte a caracter ascii
	 * @param codigo
	 * @return caracter
	 */
	public static char entero_a_caracter(int codigo)
	{
		char caracter = (char) codigo;
		return caracter;
	}
	
	/**
	 *Metodo que convierte caracter a codigo ascii extendido
	 * @param caracter
	 * @return el codigo ascii extendido
	 */
	public static int codigoASCII_extendido(char caracter)
	{
		int contador=0;
		for(char i: EXTENDED)
		{			
			if(i==caracter)
			{				
				break;
			}
			contador++;
		}
		return (contador+1)+127;
	}
	
	/**
	 * Método que devuelve el código ascii de una letra mayuscula
	 * @param caracter
	 * @return un entero equivalente a 
	 */
	public static int codigo_ASCII(char caracter)
	{
		int codigo =0;
		codigo = (int) caracter;
		return codigo;
	}
	
	/**
	 * Método que valida si la palabra tiene un total de 12 caracteres
	 * @param palabra
	 * @return un true si los caracteres son 14
	 */
	public static boolean validarCaracteres(String palabra) {
		boolean bandera;
		int contador = 0;
		
		for (int i = 0; i <palabra.length(); i++) {
			if (palabra.charAt(i) != ' ' && (palabra.charAt(i) >= 65 && palabra.charAt(i) <=90)) {
				contador++;
			}
		}
		bandera = (contador<=14 ? true : false);
		return bandera;
		// imprimirMensaje(bandera?"Correcto":"¡Incorrecto! ");
	}
	
	/**
	 * Método que imprime un mensaje
	 * @param mensaje
	 */
	public static void imprimirMensaje(String mensaje)
	{
		System.out.println(mensaje);
	}
	/**
	 * Método que lee una cadena
	 * @param mensaje
	 * @return
	 */
	public static String leerCadena(String mensaje)
	{
		String cadena="";
		Scanner dato = new Scanner(System.in);
		System.out.println(mensaje);
		cadena = dato.next();
		return cadena;
	}
	/**
	 * Método que lee un entero 
	 * @param mensaje
	 * @return
	 */
	public static int leerEntero(String mensaje)
	{
		int numero=0;
		Scanner dato = new Scanner(System.in);
		System.out.println(mensaje);
		numero = dato.nextInt();
		return numero;
	}
	
	

}
