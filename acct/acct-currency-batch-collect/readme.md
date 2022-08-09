# Running

To run this app, one would need to put the jar file in the same directory ast the following modules:
* one or more implementations of acct-currency-spec (i.e. acct-currency-bnr or acct-currency-bt)
* if using acct-currency-bnr or acct-currency-bt, acct-util-xml also has to be included
* one or more implementations of acct-currency-batch-collect-output-spec (i.e. acc-currency-batch-collect-output-sql)

Various modules may require additional files.


# Example build listing
```
08/10/2022  12:10 AM    <DIR>          .
08/09/2022  11:32 PM    <DIR>          ..
08/09/2022  11:31 PM             6,943 acct-currency-batch-collect-output-sql.jar
08/10/2022  12:06 AM         8,899,348 acct-currency-batch-collect.jar
08/09/2022  11:54 PM             7,386 acct-currency-bnr.jar
08/09/2022  11:54 PM             6,863 acct-currency-bt.jar
08/10/2022  12:06 AM             2,940 acct-util-xml.jar
08/09/2022  11:35 PM                66 acct.sql-inserts.channel.properties
```

Read the [readme file of the acct-currency-batch-collect-output-sql](../acct-currency-batch-collect-output-sql/readme.md) for details about the properties file.

To run the build, cd into the directory and run the following command:
```
java -Dloader.path="." -jar acct-currency-batch-collect.jar
```