REM .bat à ecrire sous eclipse avec open with ... / text editor
REM .bat à lancer eclipse / windows avec open with ... / system editor
cd /d "%~dp0" 
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_231

REM xjc va analyser commande.xsd et générer des classes java dans le package data2
"%JAVA_HOME%\bin\xjc" -d src/main/java -p fr.afcepf.al34.tp.data2  src/main/resources/commande.xsd
pause

REM refresh eclipse pour visualiser les classes java générées