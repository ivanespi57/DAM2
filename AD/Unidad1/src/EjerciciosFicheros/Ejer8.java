package EjerciciosFicheros;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejer8 {
    public static void main(String[] args) {

    try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document documento = builder.parse(new File("libros.xml"));
        NodeList libros = documento.getElementsByTagName("libro");
        int cont = 0;

        for (int i = 0; i < libros.getLength(); i++){
            Node node = libros.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element libroElem = (Element) node;
                NodeList propiedadesLibro = libroElem.getChildNodes();
                
                Node titulo = propiedadesLibro.item(0);
                Node autor = propiedadesLibro.item(1);
                Node precio = propiedadesLibro.item(2);

                System.out.println(titulo.getNodeName() + ": " + titulo.getTextContent());
                System.out.println(autor.getNodeName() + ": " + autor.getTextContent());
                System.out.println(precio.getNodeName() + ": " + precio.getTextContent());
                cont++;
            }
        }
        System.out.println("El número de libros es: " + cont);
    } catch (Exception e) {
        System.out.println("Error al leer el XML");
    }
    }
}