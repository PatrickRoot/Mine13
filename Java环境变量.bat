@echo off
echo %JAVA_HOME%
echo %classPath%
echo %PATH%
set/p jdkhome=请输入JDK目录并按回车：
echo 输入为：%jdkhome%
pause
echo === 新创建环境变量 JAVA_HOME=%jdkhome%
echo === 新创建环境变量 classPath=.;%%JAVA_HOME%%\lib\tools.jar;%%JAVA_HOME%%\lib\dt.jar
echo === 新创建环境变量 PATH=%%JAVA_HOME%%\bin
pause
setx "JAVA_HOME" "%jdkhome%" -M
setx "classPath" ".;%%JAVA_HOME%%\lib\dt.jar;%%JAVA_HOME%%\lib\tools.jar" -M
setx "PATH" "%path%;%%JAVA_HOME%%\bin" -M
pause