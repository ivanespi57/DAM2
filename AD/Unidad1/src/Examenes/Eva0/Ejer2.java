package Examenes.Eva0;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejer2 {
    public static void main(String[] args) {

    try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document documento = builder.parse(new File("videojuegos.xml"));
        NodeList catalogo = documento.getElementsByTagName("videojuego");

        for (int i = 0; i < catalogo.getLength(); i++){
            Node videojuego = catalogo.item(i);
            Node titulo = videojuego.getLastChild();

            if (titulo.getTextContent().equals("Hades")){
                Element elem = (Element) videojuego;
                elem.setAttribute("plataforma","Multiplataforma");
            }
        }

        Element videojuego = documento.createElement("videojuego");
        videojuego.setAttribute("id", "V004");
        videojuego.setAttribute("plataforma", "Switch");

        Element titulo = documento.createElement("titulo");
        titulo.setTextContent("The Legend of Zelda: Tears of the Kingdom");

        Element genero = documento.createElement("genero");
        genero.setTextContent("Aventura");

        Element desarrollador = documento.createElement("desarrollador");
        
        Element nombreDes = documento.createElement("nombre");
        nombreDes.setTextContent("Nintendo");
        Element paisDes = documento.createElement("pais");
        paisDes.setTextContent("Japón");

        videojuego.appendChild(titulo);
        videojuego.appendChild(genero);
        videojuego.appendChild(desarrollador);
        
        desarrollador.appendChild(nombreDes);
        desarrollador.appendChild(paisDes);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(documento);

        StreamResult result = new StreamResult("videojuegos_modificados.xml");

        transformer.transform(source, result);

        System.out.println("Fichero videojuegos_modificados.xml generado correctamente");
    } catch (Exception e) {
        System.out.println("Error al modificar el XML: " + e);
    }
    }
}
