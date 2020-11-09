package fr.xyz.tp.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.xyz.tp.data.Commande;
import fr.xyz.tp.data.Produit;

public class AnalyserXmlViaJaxb2 {

	public static void main(String[] args) {
		System.out.println("Analyse simple de src/main/resources/produit.xml via JAXB2 basé sur data.Produit.java");
		analyserXmlViaJaxb2("src/main/resources/produit.xml");
		System.out.println("Analyse de src/main/resources/commande.xml via JAXB2 basé sur prosuit.xsd et classes data2... générées par xjc");
		analyserCommandeXmlViaJaxb2("src/main/resources/commande.xml");
	}
	
	private static void analyserCommandeXmlViaJaxb2(String fileName) {
		try {
			JAXBContext jctx =     
					JAXBContext.newInstance(Commande.class);
			Unmarshaller um = jctx.createUnmarshaller(); 
			// pour remonter des objets en mémoire // suite à la lecture d'un flux xml 
			
			Commande c = (Commande) um.unmarshal( new File( fileName ) );
			System.out.println(c);
			
			/*
			ObjectMapper objectMapper = new ObjectMapper(); //de jackson
			objectMapper.writeValue(new File("src/main/resources/commande.json"),c);
			*/
			Marshaller m = jctx.createMarshaller();// pour ecrire
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			c.setAdresse(c.getAdresse().toUpperCase());//modif en mémoire
			
			//écriture dans nouveau fichier f4:
			m.marshal(c, new File("src/main/resources/f4.xml"));//+Refresh eclipse
			System.out.println("le fichier f4.xml vient d'être généré dans src/main/resources");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	private static void analyserXmlViaJaxb2(String fileName) {
		try {
			JAXBContext jctx =     
					JAXBContext.newInstance(Produit.class);
			Unmarshaller um = jctx.createUnmarshaller(); 
			// pour remonter des objets en mémoire // suite à la lecture d'un flux xml 
			
			Produit p = (Produit) um.unmarshal( new File( fileName ) );
			System.out.println(p);
			
			Marshaller m = jctx.createMarshaller();// pour ecrire
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			p.setLabel(p.getLabel().toUpperCase());//modif en mémoire
			
			//écriture dans nouveau fichier f3:
			m.marshal(p, new File("src/main/resources/f3.xml"));//+Refresh eclipse
			System.out.println("le fichier f3.xml vient d'être généré dans src/main/resources");
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		
	}

}
