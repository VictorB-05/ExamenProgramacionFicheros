package EJ_Almacen;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.lang.NumberFormatException;


/** CLASE ALMACÉN. 
 * Clase para incluir todo el código
 * para generar fichero aleatorio del almacén.
 * 
 * @author Juan Carlos
 * @version 1.0
 * @date Abr/2024
 *
 * @fichero almacen.dat
 * 
 * Nombre de fichero: "ALMACEN.DAT"
 */
public class almacen {
	private static int contador = 0;
	private static Scanner insert = new Scanner(System.in);
	// INSERTAR TODO EL CóDIGO NECESARIO PARA GESTIONAR EL FICHERO
	// Y RESOLVER EJERCICIO EXAMEN.
	public static void main(String[] args) {
		boolean repetir = true;
		
		try {
			do {
				System.out.println("\t   OPCIONES DEL PROGRAMA");
				System.out.println("--------------------------------------------");
				System.out.println("\tInsertar por defecto ----> 1");
				System.out.println("\tListar articulos --------> 2");
				System.out.println("\tDar baja articulo -------> 3");
				System.out.println("\tVenta de articulo -------> 4");
				System.out.println("\tPrecio total ------------> 5");
				System.out.println("\tAceder a un elemento ----> 6");				
				System.out.println("\tSalir -------------------> 0");
				System.out.println("--------------------------------------------");


				int opcion = Integer.parseInt(insert.next());
				
				switch (opcion) {
				case 1: {
					insertarArtPrede();
					break;
				}
				case 2: {
					listarArt();
					break;
				}
				case 3: {
					System.out.println("Intrdouce el codigo del producto");
					String codigo = insert.next();
					darBaja(codigo);
					break;
				}
				case 4: {
					System.out.println("Intrdouce el codigo del producto");
					String codigo = insert.next();
					System.out.println("introduce la cantidad");
					int unidades = Integer.parseInt(insert.next());
					venta(codigo,unidades);
					break;
				}				
				case 5: {
					coste_almacen();
					break;
				}	
				case 6: {
					System.out.println("Introduce el numero");
					int num = Integer.parseInt(insert.next());
					getElemento(num);
					break;
				}	
				case 0: {
					 repetir = false;
					break;
				}
				default:
					System.out.println("Opción no valida");
				}
			}while (repetir);
		}catch (NumberFormatException e) {
			System.out.println("Valor no valido");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void insertarArtPrede() {
		articulos art = new articulos("PAM001", "Pan de molde blanco", "Peso neto 200g", "Panaderia", 1.50, 444, "Bimbo");
		escribir(art);
		articulos art1 = new articulos("PAM002", "Pan de molde integral", "Peso neto 220g", "Panaderia", 2.50, 33, "Bimbo");
		escribir(art1);
		articulos art2 = new articulos("YOG001", "Yougurt de fresa", "Peso neto 200g", "Postres", 4.50, 100, "Danone");
		escribir(art2);
		articulos art3 = new articulos("YOG002", "Yougurt griego", "Peso neto 200g", "Postres", 3.50, 55, "Danone");
		escribir(art3);
		articulos art4 = new articulos("GAL001", "Galletas con nata", "Peso neto 120g", "Postres", 1.50, 123, "Oreo");
		escribir(art4);
		articulos art5 = new articulos("GAL002", "Donuts de chocolate", "Peso neto 100g", "Postres", 1.40, 24, "Filipinos");
		escribir(art5);
		articulos art6 = new articulos("ZUM001", "Zumo de melocoton", "Cantidad neta 200ml", "Bebidas", 1.50, 200, "Hacendado");
		escribir(art6);
		articulos art7 = new articulos("ZUM002", "Batido de fresa", "Cantidad neta 200ml", "Bebidas", 1.50, 120, "Hacendado");
		escribir(art7);
		articulos art8 = new articulos("ZUM003", "Batido de chocolate", "Cantidad neta 200ml", "Bebidas", 1.50, 300, "Hacendado");
		escribir(art8);
		articulos art9 = new articulos("ZUM004", "Cocacola", "Cantidad neta 333ml", "Bebidas", 1.50, 600, "Cocacola");
		escribir(art9);
	}

	//Metodo para leer un articulo
	public static void listarArt() {
		try (RandomAccessFile raf = new RandomAccessFile("src/almacen.dat", "rw")) {
			contador = raf.readInt();
			System.out.println("Hay "+(contador+1)+" articulos en el sistema");
			//Leemos todos los datos del fichero
			while(true) {
				String cod_art = leerString(raf, 6);
				String descr = leerString(raf, 20);
				String caract = leerString(raf, 20);
				String depart = leerString(raf, 10);
				double precio = raf.readDouble();
				int unidades = raf.readInt();
				String proveedor = leerString(raf, 20);
				//Creamos un articulo y lo escribimos
				articulos art = new articulos(cod_art, descr, caract, depart, precio, unidades, proveedor);
				System.out.println(art.toString()); 
			}
		} catch (FileNotFoundException e) {
			System.out.println("Archivos no encontarado");
		}  catch (EOFException e) {
			System.out.println("Fin fichero");
		}	catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//Se duplica el penultimo por alguna razón
	public static void darBaja(String cod_articulo) {
		File file = new File("src/almacen.dat");
		File file1 = new File("src/auxiliar.dat");
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw");
			RandomAccessFile raf1 = new RandomAccessFile(file1, "rw")) {
			contador = raf.readInt();
			//escribimos el contador para el auxiliar
			raf1.writeInt(contador);
			//Leemos todos los datos del fichero hasta encontrar nueestro dato
			for(int contador1 = 0; contador1<=contador;contador1++) {
				String cod_art =null;
				cod_art = leerString(raf, 6);
				if(cod_art.equalsIgnoreCase(cod_articulo)) {
					//saltamos así los bytes para si se añaden más atributos a artiulos el siga siendo funcional
					raf.skipBytes((articulos.bytes-12));
					System.out.println("Salto");
				}
				else{
					//Cuando lo encontramos lo pasamos y nos ponemos a rescribirlo
					String descr = leerString(raf, 20);
					String caract = leerString(raf, 20);
					String depart = leerString(raf, 10);
					double precio = raf.readDouble();
					int unidades = raf.readInt();
					String proveedor = leerString(raf, 20);
					//Creamos un articulo y lo escribimos en el archivo
					articulos art = new articulos(cod_art, descr, caract, depart, precio, unidades, proveedor);
					System.out.println(art);
					escribir(art,raf1);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Archivos no encontarado");
		}  catch (EOFException e) {
			System.out.println("Articulo no encontrado");
		}	catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//cambiamos el auxiliar por el principal
		file.delete();
		if(file1.renameTo(file)) {
			//Y el auxiliar lo eliminamos si se ha remplazado
			file1.delete();
			System.out.println("Articulo correctamente eliminado");
		}else {
			System.out.println("Error");
		}
	}
	
	
	public static void venta(String cod_articulo, int unidades) {
		boolean rep = true;
		int contador = 0;
		try (RandomAccessFile raf = new RandomAccessFile("src/almacen.dat", "rw")) {
			//Leemos todos los datos del fichero hasta encontrar nuestro dato
			while(rep) {
				String cod_art = leerString(raf, 6);
				if(cod_art.equalsIgnoreCase(cod_articulo)) {
					rep = false;
					raf.skipBytes(108);
					int unidad = raf.readInt();
					raf.seek((articulos.bytes*contador));
					raf.skipBytes(112);
					double precio = raf.readDouble();
					raf.writeInt(unidad- unidades);
					System.out.println("Articulo vendido "+cod_art+" se han vendido "+unidades+" unidades a "+unidades*precio+"€");
				}
				//saltamos así los bytes para si se añaden más atributos a artiulos el siga siendo funcional
				raf.skipBytes(articulos.bytes-12);
				contador++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Archivos no encontarado");
		}  catch (EOFException e) {
			System.out.println("Articulo no encontrado");
		}	catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void coste_almacen() {
		double precioTotal = 0;
		try (RandomAccessFile raf = new RandomAccessFile("src/almacen.dat", "rw")) {
			//Leemos todos los datos del fichero
			while(true) {
				//Saltamos los bytes que no nos interesan
				raf.skipBytes(112);
				double precio = raf.readDouble();
				int unidades = raf.readInt();
				precioTotal += precio*unidades;
				//saltamos así los bytes para si se añaden más atributos a artiulos el siga siendo funcional
				raf.skipBytes(articulos.bytes-112-12);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Archivos no encontarado");
		}  catch (EOFException e) {
			System.out.println("Fin fichero");
		}	catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(precioTotal);
	}
	
	public static void getElemento(int num) {
		try (RandomAccessFile raf = new RandomAccessFile("src/almacen.dat", "rw")) {
			//Ponemos el puentero según el numero del articulo
			raf.seek(articulos.bytes*(num));
			String cod_art = leerString(raf, 6);
			String descr = leerString(raf, 20);
			String caract = leerString(raf, 20);
			String depart = leerString(raf, 10);
			double precio = raf.readDouble();
			int unidades = raf.readInt();
			String proveedor = leerString(raf, 20);
			//Creamos un articulo y lo escribimos
			articulos art = new articulos(cod_art, descr, caract, depart, precio, unidades, proveedor);
			System.out.println(art.toString()); 
		} catch (FileNotFoundException e) {
			System.out.println("Archivos no encontarado");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Metodo que inserta un articulo
	private static void escribir(articulos art){
		try (RandomAccessFile raf = new RandomAccessFile("src/almacen.dat", "rw")) {
			//reescribimos el contador
			raf.writeInt(contador);
			//Ponemos el puentero según el numero del articulo
			raf.seek(articulos.bytes*(contador)+4);
			escribirRString(raf, art.getCod_art(), 6);
			escribirRString(raf, art.getDescr(), 20);
			escribirRString(raf, art.getCaract(), 20);
			escribirRString(raf, art.getDepart(), 10);
			raf.writeDouble(art.getPrecio());
			raf.writeInt(art.getUnidades());
			escribirRString(raf, art.getProveedor(), 20);
			System.out.println("datos corectamente metidos");
			contador++;
		} catch (FileNotFoundException e) {
			System.out.println("Archivos no encontarado");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Metodo que inserta un articulo con un RandomAccessFile dado
	private static void escribir(articulos art, RandomAccessFile raf) throws IOException {
		// Ponemos el puentero según el numero del articulo
		escribirRString(raf, art.getCod_art(), 6);
		escribirRString(raf, art.getDescr(), 20);
		escribirRString(raf, art.getCaract(), 20);
		escribirRString(raf, art.getDepart(), 10);
		raf.writeDouble(art.getPrecio());
		raf.writeInt(art.getUnidades());
		escribirRString(raf, art.getProveedor(), 20);
		System.out.println("datos corectamente metidos");
	}
	
	//Metodos para leer e insertar strings 
	private static String leerString(RandomAccessFile raf, int tam) throws IOException {
		String cadena="";
			for(int i=0; i<tam;i++ ) {
				cadena += raf.readChar();
			}		
		return cadena;
	}
	
	private static void escribirRString(RandomAccessFile raf, String imp, int tam) {
		// metodo por el cual pasamos los strings por el string Buffer les damos una
		// longitud predeterminada y los escrbimos
		try {
			StringBuffer sb = new StringBuffer(imp);
			sb.setLength(tam);
			raf.writeChars(sb.toString());
		} catch (IOException e) {
			System.out.println("String no introducido correctamente");
		}
	}
}


//1 el tamaño del fichero es 164 B (Cada articulo) por la cantidad de articulos (10 en este caso es decir pesa 1640B)
//2 Este fichero actualmente tiene 164B
//3 el tamaño del articulo es 164 ya que los string son 2B por cada letra, el int 4B, el double 8B

