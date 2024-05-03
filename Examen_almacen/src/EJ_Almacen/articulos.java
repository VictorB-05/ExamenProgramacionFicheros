package EJ_Almacen;

/** CLASE ARTÍCULOS. 
 * Base para generar fichero aleatorio del almacén.
 * 
 * @author Juan Carlos
 * @version 1.0
 * @date Abr/2024
 *
 *
 * Nota: Se debe modificar la presente clase para utilizarla para el fichero "ALMACEN.DAT"
 */
public class articulos {
	
	/* ATRIBUTOS de la Clase ARTICULOS */
	
	/** Atributo; COD_ART. Identificación del artículo
	 *  Formato: 3 caracteres + 3 números 
	 *  Ejemplo: Artículo PAN: PAN001 
	 *  Ejemplo: Artículo LECHE: LEC001 .... etc 
	 *  TAMAÑO: 6 caracteres. */
	private String cod_art;
	
	/** Atributo DESCR. Descripción/Identificación del artículo 
	 * TAMAÑO: 20 caracteres. */
	private String descr;
	
	/** Atributo CARACT. Características del artículo. 
	 * TAMAÑO: 20 caracteres. */	
	private String caract;
	
	/** Atributo DEPART. Departamento al que pertenece el artículo 
	 * TAMAÑO: 10 caracteres. */
	private String depart;
	
	/** Atributo PRECIO. Precio en decimal(con dos decimales) del artículo */
	private Double precio;
	
	/** Atributo UNIDADES. Unidades disponibles del artículo. Numérico entero*/
	private int unidades;
	
	/** Atributo PROVEEDOR. Nombre/Identificación del proveedor del artículo 
	 * TAMAÑO: 20 caracteres. */
	private String proveedor;
	
	// Constante de cuantos bytes ocupa el objeto
	public static final int bytes =164;
	
	/* Constructores de la clase ARTICULO */
	
	public articulos() {
		super();
	}

	public articulos(String cod, String des, String cara, String dep, double p, int s, String prove) {
		super();
		setCod_art(cod);
		setDescr(des);
		setCaract(cara);
		setDepart(dep);
		setPrecio(p);
		setUnidades(s);
		setProveedor(prove);
	}
	
	/*Métodos Setter y Getter de los atributos */
	


	public String getCod_art() {
		return cod_art;
	}

	public void setCod_art(String cod_art) {
		this.cod_art = cod_art;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getCaract() {
		return caract;
	}

	public void setCaract(String caract) {
		this.caract = caract;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	
	/* Método toString predefinido por @author */
	@Override
	public String toString() {
		return "ARTICULO -- "+ cod_art + " \t " + descr + " - " + caract + " \t" + precio + "€ " + unidades + " unidades \n";
	}

	
	
	
}
