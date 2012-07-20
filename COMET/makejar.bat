@ECHO OFF
E:
CD \COMET
"C:\Program Files\Java\jdk1.6.0_12\bin\jar.exe" cvfm GravitySoccer.jar gsman.txt *.class gameutil/*.class
"C:\Program Files\Java\jdk1.6.0_12\bin\jar.exe" cvfm Comet.jar cman.txt *.class gameutil/*.class
PAUSE
