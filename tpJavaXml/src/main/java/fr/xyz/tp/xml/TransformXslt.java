package fr.xyz.tp.xml;

import java.io.FileInputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TransformXslt {

	public static void main(String[] args) {
		if (args.length < 3) {
			System.err.println("usage: java fr.xyz.tp.xml.TransformXslt ficSrc.xml ficXslt.xsl ficRes.html/xml ");
			System.exit(1);
		}
		try {
			String ficSource = args[0];
			String ficXslt = args[1];
			String ficRes = args[2];
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer(new StreamSource(new FileInputStream(ficXslt)));
			transformer.transform(new StreamSource(new FileInputStream(ficSource)), new StreamResult(ficRes));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
