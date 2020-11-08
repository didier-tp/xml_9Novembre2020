<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
               xmlns:xsl="..." >
                            
<xsl:output method="html"
            version="1.0"
            indent="yes"
            encoding="UTF-8"/>                 
               
<xsl:template match="/">
   <html>
			<head>
			   <title> commande </title>
			   <style type="text/css">
			        h3 { color:red; font-family:Verdana }
			        h4 { color:green; font-family:Verdana }			        
			        th { background:yellow;font-family:Verdana } 
			        td { color:blue; font-family:arial }
                    label { display: inline-block; width: 8em; }					
			   </style>
			</head>
	<body>
	
	<h3> commande n° ... du ... </h3>
	<hr/>				
	<xsl:apply-templates select="commande/cli..." />
    <hr/>
     <h4>liste des produits commandés</h4>	 
         <table border="1" width="100%">
			<tr>
			    <th>reference</th>
				<th>designation</th>
				<th>prix</th>
				<th>quantite</th>
			</tr>
            <xsl:... select="....">
			   <tr>
				...
			   </tr>
			</xsl:...> 			      
		</table>			
	</body>
   </html>
</xsl:template>


<!-- table des matières -->
<xsl:template match="cli....">
   <h4>client </h4>   
   <label>numero:</label> <b>....</b> <br/>
   <label>prenom et nom:</label><b>...
		 ...</b> <br/>
   <label>email:</label> <b>...</b> <br/>
   <label>adresse:</label> <b><xsl:value-of select="adresse"/></b> <br/>
</xsl:template>

</xsl:stylesheet>
