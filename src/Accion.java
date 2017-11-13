import java.util.ArrayList;

public class Accion {

	private String nombre;
	private ArrayList<Integer> cantidad;
	private ArrayList<Float> precio;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Integer> getCantidad() {
		return cantidad;
	}

	public void setCantidad(ArrayList<Integer> cantidad) {
		this.cantidad = cantidad;
	}

	public ArrayList<Float> getPrecio() {
		return precio;
	}

	public void setPrecio(ArrayList<Float> precio) {
		this.precio = precio;
	}

	public Accion(String n, ArrayList<Integer> c, ArrayList<Float> p) {
		this.nombre = n;
		this.cantidad = c;
		this.precio = p;
	}
	
	public void print() {
		
		System.out.println("Nombre: "+nombre);
		
		System.out.println("Precios: ");
		for(int i=0;i<precio.size();i++)
			System.out.println(precio.get(i));
		System.out.println("Cantidades: ");
		for(int i=0;i<cantidad.size();i++)
			System.out.println(cantidad.get(i));
		
	}
}
