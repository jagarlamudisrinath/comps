# COMPS backend setup

Prerequisites
- git
- Linux 
- Openjdk 11
- postgresql

clone the comps backend repo to the linux server.

```git clone <repo url>```

##Steps to set up Database.

1. Create a database chat_engine in postgresql DB.
2. ddl.sql and dml.sql files are located in source code. src/main/resources/db/postgres
3. Execute the ddl.sql and dml.sql scripts on chat_engine db.
4. create a postgres db user and password and grant the full permissions on chat_engine database.


##Steps to set up application server.

1. go to the backed source code run the command ./gradlew bootdistZip
2. it will generate the comps.zip file in path(build/distributions/comps.zip).
3. create the comps directory in /opt.
4. unzip build/distributions/comps.zip in /opt/comps
5. create the directory logs and config in /opt/comps
6. copy the file application.properties from src/main/resources/application.properties to /opt/comps/config
7. modify the application.properties with newly created database username and password as below.


```
spring.datasource.url=jdbc:postgresql://localhost:5432/<dtabasename>
spring.datasource.username=<database username>
spring.datasource.password=<database password>
```
    

8. Create a startup.sh file in /opt/comps with following content.

```
export JAVA_OPTS="-Xms4G -Xmx4G -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/comps/logs"
export SPRING_CONFIG_NAME=application
export SPRING_CONFIG_LOCATION=file:///opt/comps/config/
sh /opt/comps/comps/bin/comps &
```

9. start the application with executing the startup.sh file.



##verify the application server health.

```curl http://localhost:8080/login```







