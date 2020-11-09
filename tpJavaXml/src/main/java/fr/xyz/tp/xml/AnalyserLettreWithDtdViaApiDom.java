package fr.xyz.tp.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class AnalyserLettreWithDtdViaApiDom {
	
	public static void main(String[] args) {
		analyserFichierXml("src/main/resources/with_dtd/lettre.xml");
		//analyserFichierXml("src/main/resources/with_dtd/lettreInvalid.xml");
	}
	public static void analyserFichierXml(String fileName) {
		try {
			DocumentBuilderFactory docBuilderFactory =  
					DocumentBuilderFactory.newInstance(); 
			//docBuilderFactory.setNamespaceAware(true);
			docBuilderFactory.setValidating(true); //valider en tenant compte d'un dtd ou xsd
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
