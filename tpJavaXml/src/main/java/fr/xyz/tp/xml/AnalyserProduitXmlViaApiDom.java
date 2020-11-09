package fr.xyz.tp.xml;

import java.io.FileOutputStream;

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
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class AnalyserProduitXmlViaApiDom {
	
	static final String JAXP_SCHEMA_LANGUAGE ="http://java.sun.com/xml/jaxp/properties/schemaLanguage"; 
	static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema"; 
	public static void main(String[] args) {
		analyserFichierXml("src/main/resources/produit.xml");
	}
	public static void analyserFichierXml(String fileName) {
		try {
			DocumentBuilderFactory docBuilderFactory =  
					DocumentBuilderFactory.newInstance(); 
			docBuilderFactory.setNamespaceAware(true);
			docBuilderFactory.setValidating(true); //valider en tenant compte d'un dtd ou xsd
			docBuilderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA); 
			/* Fabriquer un parseur DOM: */ 
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			docBuilder.setErrorHandler(new ErrorHandler() {
				public void error(SAXParseException e) throws SAXException {
					System.err.println(e);	System.err.println("document xml pas valide -- arret du parsing"); 
					System.exit(1);
				}
				public void fatalError(SAXParseException e) throws SAXException {
					System.err.println(e); System.exit(1);
				}
				public void warning(SAXParseException e) throws SAXException {
					System.out.println(e);
				}
			});
			/* Déclencher le parsing et récupérer une référence sur l'arbre DOM: */ 
			Document xmlDoc = docBuilder.parse(fileName);
			Element docElement = xmlDoc.getDocumentElement();//accès  à la balise principale
			System.out.println("namespace=" + docElement.getNamespaceURI());
			String valeurAttributRef = docElement.getAttribute("ref");
			System.out.println("valeurAttributRef="+valeurAttributRef);
			NodeList nodeList = docElement.getChildNodes();
			for(int i=0;i<nodeList.getLength();i++) {
				Node n = nodeList.item(i); 
				//attention Node = noeud quelconque (Comment , Text , Element)
				if(n.getNodeType()==Node.ELEMENT_NODE) {
					Element elt = (Element) n;
					System.out.println(elt.getNodeName()+":"+elt.getTextContent());
				}
			}
			//acces direct au prix:
			String sPrix= ((Element)docElement.getElementsByTagName("prix").item(0)).getTextContent();
			System.out.println("en acces direct, prix du produit="+sPrix);
			
			//ajouter <caracteristique>bonne qualite</caracteristique>
			//en dessous docElement reférençant ici le noeud principal <produit> : 
			Element eltCar = xmlDoc.createElement("caracteristique"); //à rattacher
			docElement.appendChild(eltCar);
			eltCar.appendChild(xmlDoc.createTextNode("bonne qualite"));
			//avec <element name="caracteristique" type="string"  minOccurs="0" />
			//dans produit.xsd
			
			//générer un fichier produit2.xml à partir de l'arbre modifié/agrandi:
			TransformerFactory trFactory = TransformerFactory.newInstance();  
			Transformer tr = trFactory.newTransformer();   
			tr.transform(new DOMSource(xmlDoc),
					   new StreamResult(new FileOutputStream("src/main/resources/f2.xml") )); 
			System.out.println("le fichier f2.xml vient d'être généré dans src/main/resources");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
