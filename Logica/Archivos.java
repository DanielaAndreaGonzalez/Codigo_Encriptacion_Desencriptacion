package Logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Archivos {
	private String nombreArchivo;

	public Archivos(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	public String getNombreArchivo()
	{
		return this.nombreArchivo;
	}

	public ArrayList<String> obtenerDatos() {
		ArrayList<String> listaDatos = new ArrayList<String>();

		try {
			File file = new File(this.nombreArchivo);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				listaDatos.add(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaDatos;
	}

	public boolean existe() {
		File file = new File(this.nombreArchivo);
		if (file.exists() && !file.isDirectory()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean crearArchivo() {
		boolean bandera = false;
		try {
			File file = new File(this.nombreArchivo);
			if (!file.exists()) {
				file.createNewFile();
				bandera = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return bandera;
		}
		return bandera;
	}

	public String leerCodigo()
	{
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String linea = "";
		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(this.nombreArchivo);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			linea = br.readLine();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return linea;
	}
	
	
	public boolean guardarCodigo(String codigo) {
		boolean bandera = false;
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter(this.nombreArchivo);
			pw = new PrintWriter(fichero);						
			pw.print(codigo);			
			bandera = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return bandera;
	}

	/*public ArrayList<Habitacion> leerAutores() {
		ArrayList<Habitacion> listaAutoresLeida = new ArrayList<Habitacion>();
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File(this.nombreArchivo);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea = "";
			String separador = Pattern.quote(",");

			while ((linea = br.readLine()) != null) {
				// System.out.println(linea);
				String lineaDividida[] = linea.split(separador);
				// System.out.println(Arrays.asList(lineaDividida));

				// PENDIENTE
				// Habitacion obj = new
				// Habitacion(lineaDividida[0],lineaDividida[1],lineaDividida[2]);
				// listaAutoresLeida.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		 * for(Autor a:listaAutoresLeida) {
		 * System.out.println("Autores leidos del archivo");
		 * System.out.println("nombre:"+a.getNombre()+"nacionalidad:"+a.getNacionalidad(
		 * )+ "fechaNac:"+a.getFechaNacimiento()); }
		 
		return listaAutoresLeida;
	}*/

} // fin de la clase
