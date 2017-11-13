import java.io.File;
import java.util.ArrayList;

import javax.xml.transform.TransformerException;

public class Principal {

	public static void main(String[] args) {
		Parser parser = new Parser();
		parser.parseFicheroXml("fichero2.xml");
		parser.parseDocument();
		parser.print();



	}
}
