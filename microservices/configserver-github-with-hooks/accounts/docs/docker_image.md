# Docker Image

Firstly, put this in your pom.xml:

```xml
<plugin>
        <groupId>com.google.cloud.tools</groupId>
        <artifactId>jib-maven-plugin</artifactId>
        <version>3.4.5</version>
        <configuration>
                <container>
                        <mainClass>org.blacksage.learn.microservices.accounts.AccountsApplication</mainClass>
                </container>
                <from>
                        <image>eclipse-temurin:24-jre</image>
                </from>
                <to>
                        <image>mayasage/${project.artifactId}:s1
                        </image>
                </to>
        </configuration>
</plugin>
```

- Here, `<from>` tag is needed because the default base image was java 21, and the project was built with java 24.
- The `<to>` tag motive is to provide the image name.
- `<container>` tag was required because "ASM library" used by jib lags behind the latest Java versions, it can't find
  the main class. So, you have to provide it manually.

Then, run this command:

```shell
mvn compile jib:dockerBuild
```
