set JAVA_HOME=C:\Prog\java\jdk\jdk1.6.7

set WK_DIR=D:\tp\Java_SOA_Xml\ws_java_2008\wksp_ws_2008
set SRC_DIR=%WK_DIR%\qcm_jaxb2\temp_jaxb

cd %SRC_DIR%
"%JAVA_HOME%\bin\xjc.exe"    ../src/tmp_ns/commande.xsd  

PAUSE