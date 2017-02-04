@echo OFF

cd cw
echo | call compile.bat
start cmd /k call run.bat

cd ../client
echo | call compiler.bat
call ClientRun.bat

pause