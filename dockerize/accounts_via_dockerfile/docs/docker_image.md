# Docker Image

It was manually created in this program.

First, create the JAR file:

```shell
mvn clean install
```

You'll see a JAR file in the `target` directory.

Then, write the Docker image file to copy that JAR into the image::

```Dockerfile
FROM openjdk:24-jdk-slim

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]
```
