@echo off
echo %JAVA_HOME%
echo %classPath%
echo %PATH%
set/p jdkhome=������JDKĿ¼�����س���
echo ����Ϊ��%jdkhome%
pause
echo === �´����������� JAVA_HOME=%jdkhome%
echo === �´����������� classPath=.;%%JAVA_HOME%%\lib\tools.jar;%%JAVA_HOME%%\lib\dt.jar
echo === �´����������� PATH=%%JAVA_HOME%%\bin
pause
setx "JAVA_HOME" "%jdkhome%" -M
setx "classPath" ".;%%JAVA_HOME%%\lib\dt.jar;%%JAVA_HOME%%\lib\tools.jar" -M
setx "PATH" "%path%;%%JAVA_HOME%%\bin" -M
pause