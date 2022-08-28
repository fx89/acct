# Running the app

This JAR requires an external properties file containing the database connection details. For example:
```
spring.datasource.url      = jdbc:mysql://localhost:3306/acctdb?verifyServerCertificate=false&useSSL=false&requireSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useLegacyDatetimeCode=false
spring.datasource.username = acctuser
spring.datasource.password = acctpassword
```

The properties file should be referenced in the VM options. For example:
```
-Dspring.config.location=S:/Repos/acct/dbconfig.properties
```

The application uses [Jasypt](https://www.codejava.net/frameworks/spring-boot/spring-boot-password-encryption) to decrypt passwords. To enable this, set the password in the VM options, like this: 
```
-Djasypt.encryptor.password=MyJasyptKey
```

Then create the encrypted strings for the properties you want to encrypt like this:
```
mvn jasypt:encrypt-value -Djasypt.encryptor.password=MyJasyptKey -Djasypt.plugin.value=username
```

Then update the properties file like this:
```
spring.datasource.url      = jdbc:mysql://localhost:3306/acctdb?verifyServerCertificate=false&useSSL=false&requireSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useLegacyDatetimeCode=false
spring.datasource.username = ENC(e2ikdj23hdh230edjh230d23)
spring.datasource.password = ENC(rkj2j2f023j0f2ij3f02jfj0)
```

