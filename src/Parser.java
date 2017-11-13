import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {

	private Document dom = null;
	private ArrayList<Accion> acciones = null;

	public Parser() {
		acciones = new ArrayList<Accion>();
	}

	public void parseFicheroXml(String fichero) {
		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// creamos un documentbuilder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parseamos el XML y obtenemos una representaci�n DOM
			dom = db.parse(fichero);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	public void parseDocument() {
		// obtenemos el elemento ra�z
		Element docEle = dom.getDocumentElement();

		// obtenemos el nodelist de elementos
		NodeList nl = docEle.getElementsByTagName("accion");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {

				// obtenemos un elemento de la lista (persona)
				Element el = (Element) nl.item(i);

				// obtenemos una persona
				Accion a = getAccion(el);

				// lo a�adimos al array
				acciones.add(a);
			}
		}
	}

	private Accion getAccion(Element accionEle) {

		String nombre = getTextValue(accionEle, "nombre");
		ArrayList<Integer> lista1 = getCantidad(accionEle, "operaciones");
		ArrayList<Float> lista2 = getPrecio(accionEle, "operaciones");

		Accion ac1 = new Accion(nombre, lista1, lista2);

		return ac1;

	}

	private ArrayList<Integer> getCantidad(Element ele, String tag) {
		ArrayList<Integer> lista_cantidad = new ArrayList<Integer>();

		NodeList nl = ele.getElementsByTagName(tag);

		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				
				Element las_operaciones = (Element) nl.item(0);

				// Volvemos a hacer el nodelist porque operaciones es un
				// elemento
				// compuesto por compras
				NodeList nodelist_compras = las_operaciones.getElementsByTagName("compras");

				if (nodelist_compras != null && nodelist_compras.getLength() > 0) {
					Element la_compra = (Element) nodelist_compras.item(0);

					// Volvemos a hacer el nodelist porque compras es un
					// elemento
					// compuesto por compra
					NodeList nodelist_compra = la_compra.getElementsByTagName("compra");

					if (nodelist_compra != null && nodelist_compra.getLength() > 0) {
						Element la_cantidad1 = (Element) nodelist_compra.item(0);

						// Volvemos a hacer el nodelist porque compra es un
						// elemento
						// compuesto por cantidad
						NodeList nodelist_cantidad = la_cantidad1.getElementsByTagName("cantidad");
						if (nodelist_cantidad != null && nodelist_cantidad.getLength() > 0) {
							for (int x = 0; x < nodelist_cantidad.getLength(); x++) {
								Element la_cantidad = (Element) nodelist_cantidad.item(x);
								String cant = la_cantidad.getFirstChild().getNodeValue();
								int cantInt = Integer.parseInt(cant);
								lista_cantidad.add(cantInt);
							}
						}

					}
				}

			}
			
		}
		return lista_cantidad;
	}

	private ArrayList<Float> getPrecio(Element ele, String tag) {
		ArrayList<Float> lista_precios = new ArrayList<Float>();

		NodeList nl = ele.getElementsByTagName(tag);

		if (nl != null && nl.getLength() > 0) {
			Element las_operaciones = (Element) nl.item(0);

			// Volvemos a hacer el nodelist porque operaciones es un elemento
			// compuesto por compras
			NodeList nodelist_compras = las_operaciones.getElementsByTagName("compras");

			if (nodelist_compras != null && nodelist_compras.getLength() > 0) {

				Element la_compra = (Element) nodelist_compras.item(0);

				// Volvemos a hacer el nodelist porque compras es un elemento
				// compuesto por compra
				NodeList nodelist_compra = la_compra.getElementsByTagName("compra");

				if (nodelist_compra != null && nodelist_compra.getLength() > 0) {

					Element el_precio1 = (Element) nodelist_compra.item(0);

					// Volvemos a hacer el nodelist porque compra es un elemento
					// compuesto por precio
					NodeList nodelist_precio = el_precio1.getElementsByTagName("precio");

					if (nodelist_precio != null && nodelist_precio.getLength() > 0) {
						for (int i = 0; i < nodelist_precio.getLength(); i++) {
							Element el_precio = (Element) nodelist_precio.item(i);
							String prec = el_precio.getFirstChild().getNodeValue();
							float precInt = Float.parseFloat(prec);
							lista_precios.add(precInt);
						}
					}

				}
			}

		}
		return lista_precios;
	}

	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}
		return textVal;
	}

	private int getIntValue(Element ele, String tagName) {
		return Integer.parseInt(getTextValue(ele, tagName));
	}

	public void print() {

		Iterator it = acciones.iterator();
		while (it.hasNext()) {
			Accion a = (Accion) it.next();
			a.print();
			System.out.println("-----------------------------\n");
		}
	}

}