# Maven

Edit configuration, create new configuration.

I used these three configurations (Don't worry about secrets, they won't work anyway):

```shell
# easyschool [clean_install]
clean install -Dmaven.test.skip=true -DAZURE_DB_PASS=tiredOfPasswords111 -DAZURE_DB_URL=jdbc:sqlserver://rockpoor.database.windows.net:1433;database=iamflyingforfree -DAZURE_DB_USER=freeman2000 -f pom.xml

# easyschool [run]
spring-boot:run "-Dspring-boot.run.jvmArguments=-DAZURE_DB_PASS=tiredOfPasswords111 -DAZURE_DB_URL=jdbc:sqlserver://rockpoor.database.windows.net:1433;database=iamflyingforfree -DAZURE_DB_USER=freeman2000"

# easyschool [run_uat]
spring-boot:run "-Dspring-boot.run.jvmArguments=-DAZURE_DB_PASS=tiredOfPasswords111 -DAZURE_DB_URL=jdbc:sqlserver://rockpoor.database.windows.net:1433;database=iamflyingforfree -DAZURE_DB_USER=freeman2000 -Dspring.profiles.active=uat"

# easyschool [run_prod]
spring-boot:run "-Dspring-boot.run.jvmArguments=-DAZURE_DB_PASS=tiredOfPasswords111 -DAZURE_DB_URL=jdbc:sqlserver://rockpoor.database.windows.net:1433;database=iamflyingforfree -DAZURE_DB_USER=freeman2000 -Dspring.profiles.active=prod"
```