REM .bat à ecrire sous eclipse avec open with ... / text editor
REM .bat à lancer eclipse / windows avec open with ... / system editor
cd /d "%~dp0" 
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_211

set XMLPath=src/main/resources/with_xslt/commande.xml
set XSLPath=src/main/resources/with_xslt/commande_xhtml.xsl
set OutPath=src/main/resources/with_xslt/commande.html


REM penser a reconstruire si besoin une nouvelle version de target/tpJavaXml.jar
REM via mvn package ou bien Run as / Maven install d'eclipse .

"%JAVA_HOME%\bin\java" -cp target/tpJavaXml.jar fr.xyz.tp.xml.TransformXslt %XMLPath% %XSLPath% %OutPath%
pause
