package fr.xyz.tp.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//l'api JAXB2 est utilisé en interne par l'api JAX-WS (Web service Soap)

//annotations de JAXB2 (package "javax.xml.bind..." et @Xml...() 
//JAXB2 est intégré dans la JVM à partir de java 6 (==> java 1.8 dans pom.xml)
@XmlRootElement(name = "produit", namespace = Produit.PROD_NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD) //pour demander à analyser les @Xml...()
                                      //au dessus des FIELD (souvent private)
public class Produit {
	
	public static final String PROD_NAMESPACE="http://www.example.org/produit";
	
	@XmlAttribute(name = "ref")
	private String ref;
	
	@XmlElement(name="label" , namespace = PROD_NAMESPACE)
	private String label;
	
	@XmlElement(name="prix" , namespace = PROD_NAMESPACE)
	private Double prix;
	
	public Produit() {
		super();
	}
	
	public Produit(String ref, String label, Double prix) {
		super();
		this.ref = ref;
		this.label = label;
		this.prix = prix;
	}
	
	@Override
	public String toString() {
		return "Produit [ref=" + ref + ", label=" + label + ", prix=" + prix + "]";
	}
	
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	
	
}
