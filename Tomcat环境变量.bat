@echo off
echo %CATALINA_HOME%
echo %CATALINA_BASE%
echo %PATH%
set/p tomcathome=������TOMCATĿ¼�����س���
echo ����Ϊ��%tomcathome%
pause
echo === �´����������� CATALINA_HOME=%tomcathome%
echo === �´����������� CATALINA_BASE=%tomcathome%
echo === �´����������� PATH=%%CATALINA_HOME%%\bin
pause
setx "CATALINA_HOME" "%tomcathome%" -M
setx "CATALINA_BASE" "%tomcathome%" -M
setx "PATH" "%path%;%%CATALINA_HOME%%\bin" -M
pause