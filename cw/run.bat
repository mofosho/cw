@echo off
title runserver
"C:\Program Files\Java\jdk1.8.0_121\bin\java.exe" -Xmx815m -cp bin;lib/*; com.rs.Launcher true true false
pause