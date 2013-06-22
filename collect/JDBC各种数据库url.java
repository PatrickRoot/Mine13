>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    PostgreSQL
PostgreSQL Native JDBC Driver
驱动程序包名：驱动程序类名: org.postgresql.Driver
JDBC URL: jdbc:postgresql://<host>:<port>/<database_name>
默认端口5432

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    MySQL（验证）
mysql-connector-java
驱动程序包名：驱动程序类名: com.mysql.jdbc.Driver
JDBC URL: jdbc:mysql://<host>:<port>/<database_name>[?user=user][&password=password][&useUnicode=true][&characterEncoding=GBK]
默认端口3306

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    Sqlite（验证）
sqlite-jdbc
驱动程序包名：sqlite-jdbc-3.7.2.jar
驱动程序类名: org.sqlite.JDBC
JDBC URL: jdbc:sqlite:<database_url>
Memory DB URL:"jdbc:sqlite::memory:"    
默认端口 未知，如果服务器使用默认端口则port可以省略
MySQL Connector/J Driver 允许在URL中添加额外的连接属性jdbc:mysql://<host>:<port>/<database_name>?property1=value1&property2=value2

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    Oracle
Oracle Thin JDBC Driver
驱动程序包名：ojdbc14.jar
驱动程序类名: oracle.jdbc.driver.OracleDriver
JDBC URL:
jdbc:oracle:thin:@//<host>:<port>/ServiceName
或
jdbc:oracle:thin:@<host>:<port>:<SID>

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    Microsoft SQL Server
Microsoft SQL Server JDBC Driver （一般用来连接 SQLServer 2000）
驱动程序包名：msbase.jar mssqlserver.jar msutil.jar
驱动程序类名: com.microsoft.jdbc.sqlserver.SQLServerDriver
JDBC URL: jdbc:microsoft:sqlserver://<server_name>:<port>
默认端口1433，如果服务器使用默认端口则port可以省略

Microsoft SQL Server 2005 JDBC Driver
驱动程序包名：sqljdbc.jar
驱动程序类名: com.microsoft.sqlserver.jdbc.SQLServerDriver
JDBC URL: jdbc:sqlserver://<server_name>:<port>
默认端口1433，如果服务器使用默认端口则port可以省略

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    ODBC  
Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );  
Connection cn = DriverManager.getConnection( "jdbc:odbc:" + sDsn, sUsr, sPwd );

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    IBM DB2
IBM DB2 Universal Driver Type 4
驱动程序包名：db2jcc.jar db2jcc_license_cu.jar
驱动程序类名: com.ibm.db2.jcc.DB2Driver
JDBC URL: jdbc:db2://<host>[:<port>]/<database_name>

IBM DB2 Universal Driver Type 2
驱动程序包名：db2jcc.jar db2jcc_license_cu.jar
驱动程序类名: com.ibm.db2.jcc.DB2Driver
JDBC URL: jdbc:db2:<database_name>

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    Informix
Informix JDBC Driver
驱动程序包名：ifxjdbc.jar
驱动程序类名: com.informix.jdbc.IfxDriver
JDBC URL: jdbc:informix-sqli://{<ip-address>|<host-name>}:<port-number>[/<dbname>]: INFORMIXSERVER=<server-name>

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    Sybase
Sybase Adaptive Server Enterprise JDBC Driver
驱动程序包名：jconn2.jar 或jconn3.jar
驱动程序类名: com.sybase.jdbc2.jdbc.SybDriver (com.sybase.jdbc3.jdbc.SybDriver)
JDBC URL: jdbc:sybase:Tds:<host>:<port>默认端口5000

Sybase Adaptive Server Anywhere or Sybase IQ JDBC Driver
驱动程序包名：jconn2.jar 或jconn3.jar
驱动程序类名: com.sybase.jdbc2.jdbc.SybDriver (com.sybase.jdbc3.jdbc.SybDriver)
JDBC URL: jdbc:sybase:Tds:<host>:<port>?ServiceName=<database_name>
默认端口2638

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    Teradata
Teradata Driver for the JDBC Interface
驱动程序包名：terajdbc4.jar tdgssjava.jar gui.jar
驱动程序类名: com.ncr.teradata.TeraDriver
JDBC URL:
Type 4: jdbc:teradata://DatabaseServerName/Param1,Param2,...
Type 3: jdbc:teradata://GatewayServerName:PortNumber
/DatabaseServerName/Param1,Param2,...

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    Netezza
Netezza JDBC Driver
驱动程序包名：terajdbc4.jar tdgssjava.jar gui.jar
驱动程序类名: org.netezza.Driver
JDBC URL: jdbc:netezza://<host>:<port>/<database_name>
