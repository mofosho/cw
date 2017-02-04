@echo off
echo Compiling
"C:\Program Files\Java\jdk1.8.0_121\bin\javac.exe" -d bin -cp data/libs/*; -sourcepath src src/com/rs/*.java
echo Complete
pause