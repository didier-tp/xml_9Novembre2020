package fr.xyz.tp.data;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

//l'api JAXB2 est utilisé en interne par l'api JAX-WS (Web service Soap)

//annotations de JAXB2 (package "javax.xml.bind..." et @Xml...() 
//JAXB2 est intégré dans la JVM à partir de java 6 (==> java 1.8 dans pom.xml)

//Attention: JAXB2 n'est plus livré dans les jdk >=11 .
//Il faut le récupérer via des dépendances maven

@XmlRootElement(name = "commande", namespace = Commande.CMDE_NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD) //pour demander à analyser les @Xml...()
                                      //au dessus des FIELD (souvent private)
public class Commande {
	
	public static final String CMDE_NAMESPACE="http://www.example.org/commande";
	
	@XmlAttribute(name="num")
	private Integer num;
	
	@XmlAttribute(name="date" )
	private String date;
	
	@XmlElement(name="adresse" , namespace = CMDE_NAMESPACE)
	private String adresse;
	
	@XmlElement(name="produit" , namespace = Produit.PROD_NAMESPACE)
	//@JsonIgnore
	private Collection<Produit> produits;
	
	public Commande() {
		super();
	}

	@Override
	public String toString() {
		return "Commande [num=" + num + ", date=" + date + ", adresse=" + adresse + ", produits=" + produits + "]";
	}

	public final Integer getNum() {
		return num;
	}

	public final void setNum(Integer num) {
		this.num = num;
	}

	public final String getDate() {
		return date;
	}

	public final void setDate(String date) {
		this.date = date;
	}

	public final String getAdresse() {
		return adresse;
	}

	public final void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public final Collection<Produit> getProduits() {
		return produits;
	}

	public final void setProduits(Collection<Produit> produits) {
		this.produits = produits;
	}
	
	
	
	
}
