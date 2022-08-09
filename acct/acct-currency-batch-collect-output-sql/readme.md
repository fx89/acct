# Integrating this plugin

Place the JAR within the classpath of the ***acct-currency-batch-collect***. The Spring bean defined here will be automatically loaded by the Spring Boot application.

This output channel requires a property file named ***acct.sql-inserts.channel.properties*** to be placed into the classpath. The properties file should contain the following properties:
1. **file.path** is the path where the SQL files should be placed
2. **file.namePrefix** is the prefix of the names of the SQL files

For example:
```
file.path = /var/logs/acct
file.namePrefix = currency-records
```

