REM @echo off

set FOP_DIR=C:\Prog\fop-0.20.5


%FOP_DIR%\fop.bat -xml biblio.xml -xsl biblio-fo.xsl -pdf biblio.pdf

pause

