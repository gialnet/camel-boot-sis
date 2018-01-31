# Apache Camel 2.20.1 with Spring Boot 1.5.9
Apache Camel 2.20.1 with Spring Boot 1.5.9 

The motivation of the project is to be able to read a file in XML format that reaches us through an FTP server and save its values in a table of a database.
It has been raised through a configuration application.properties file so that it is easily adaptable to any scenario.

Spring-boot automatic configuration files have been used, not those of Camel.
```java
application.properties
data.sql
schema.sql
```
Apache Camel routes are used both for reading and writing.

```java
from("ftp.server").to("jdbc:dataSource")
```

To run the application from the command line

```java
mvn spring-boot:run
```

![Screenshot](https://github.com/gialnet/camel-boot-sis/blob/master/Captura%20console%20h2.PNG)

url DATABASE
jdbc:h2:mem:testdb

url console
/h2-console

I used this file on the FTP server for the tests

```java
<?xml version="1.0"?>
<company>
     	<id>1001</id>
     	<name>Antonio Perez</name>
	<dob>18nob1971</dob>
	<salary>100000</salary>
</company>
```

Run test
```java
mvn test -Dtest=RouteFtpTest
```