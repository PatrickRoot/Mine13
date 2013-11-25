@echo off
echo %CATALINA_HOME%
echo %CATALINA_BASE%
echo %PATH%
set/p tomcathome=请输入TOMCAT目录并按回车：
echo 输入为：%tomcathome%
pause
echo === 新创建环境变量 CATALINA_HOME=%tomcathome%
echo === 新创建环境变量 CATALINA_BASE=%tomcathome%
echo === 新创建环境变量 PATH=%%CATALINA_HOME%%\bin
pause
setx "CATALINA_HOME" "%tomcathome%" -M
setx "CATALINA_BASE" "%tomcathome%" -M
setx "PATH" "%path%;%%CATALINA_HOME%%\bin" -M
pause