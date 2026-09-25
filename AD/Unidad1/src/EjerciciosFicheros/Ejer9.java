package EjerciciosFicheros;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejer9 {
    public static void main(String[] args) {

    try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document documento = builder.parse(new File("alumnos.xml"));
        NodeList alumnos = documento.getElementsByTagName("alumno");
        int cantidadAlumno = alumnos.getLength();
        

        for (int i = 0; i < alumnos.getLength(); i++){
            Node node = alumnos.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element alumno = (Element) node;           
                String id = alumno.getAttribute("id");
                
                if (id.equals("2")) {
                    NodeList propiedadesAlumno = alumno.getChildNodes();

                    for (int j = 0; j < propiedadesAlumno.getLength(); j++){
                        Node n = propiedadesAlumno.item(j);
                        if (n.getNodeType() == Node.ELEMENT_NODE){
                            Element e = (Element) n;
                            System.out.println(e.getNodeName()+": " + e.getTextContent());
                        }
                    }
                }
            }
        }
        System.out.println("El número de alumno es: " + cantidadAlumno);
    } catch (Exception e) {
        System.out.println("Error al leer el XML");
    }
    }
}