@echo off
echo %JAVA_HOME%
echo %CLASSPATH%
echo %PATH%
set/p jdkhome=������JDKĿ¼�����س���
echo ����Ϊ��%jdkhome%
pause
echo === �´����������� JAVA_HOME=%jdkhome%
echo === �´����������� CLASSPATH=.;%%JAVA_HOME%%\lib\tools.jar;%%JAVA_HOME%%\lib\dt.jar;%%JAVA_HOME%%\lib\
echo === �´����������� PATH=%%JAVA_HOME%%\bin
pause
setx "JAVA_HOME" "%jdkhome%" -M
setx "CLASSPATH" ".;%%JAVA_HOME%%\lib\dt.jar;%%JAVA_HOME%%\lib\tools.jar;%%JAVA_HOME%%\lib\" -M
setx "PATH" "%path%;%%JAVA_HOME%%\bin" -M
pause