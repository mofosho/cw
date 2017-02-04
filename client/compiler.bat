@echo off
title Client Compiler
echo starting...
"C:\Program Files\Java\jdk1.8.0_121\bin\javac" -cp lib/*; -d bin -sourcepath src src/*.java
@pause