package fr.xyz.tp.json;

import java.util.ArrayList;
import java.util.List;

import fr.xyz.tp.data.Commande;
import fr.xyz.tp.data.Produit;

public class MyAppJson {

	public static void main(String[] args) {
		LowLevelJsonUtil.demoSimpleJsonJava();
		JacksonJsonUtil.demoJsonJavaViaJacksonObjectMapper();
		/*
		//exemple en javascript (js):
		var jsonString = JSON.stringify(objetJs);
		var objJs= JSON.parse(jsonString);
		*/
		
		Produit p1 = new Produit("refP1" , "cahier" , 12.5);
		String p1AsJsonString = JacksonJsonUtil.stringify(p1);
		System.out.println(p1AsJsonString);
		
		//NB: chaine json volontairement sans prix et avec comment n'existant pas sur Produit.java :
		String p2AsJsonString = "{'ref':'p2' , 'label':'cahier' , 'comment': 'cc' }"
				                .replace('\'', '\"');
		Produit p2 = JacksonJsonUtil.parse(p2AsJsonString,Produit.class);
		System.out.println(p2.toString());
		
		Commande c = new Commande();
		c.setNum(2); c.setDate("2019-11-25");
		c.setAdresse("12 rue elle");
		List<Produit> listeProduits = new ArrayList<>();
		listeProduits.add(p2);
		listeProduits.add(p1);
		c.setProduits(listeProduits);
		System.out.println("commande java:" + c.toString());
		
		String cAsJsonString = JacksonJsonUtil.stringify(c); 
		//à tester avec ou sans @JsonIgnore
		//au dessus de produits dans Commande.java
		
		System.out.println("commande json:" + cAsJsonString);
	}

}
