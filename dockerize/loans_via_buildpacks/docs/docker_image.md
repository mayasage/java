# Docker Image

This program is built using buildpacks.

Ensure this is in your pom.xml:

```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <image>
            <name>mayasage/${project.artifactId}:s1</name>
        </image>
        <excludes>
            <exclude>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
            </exclude>
        </excludes>
    </configuration>
</plugin>
```

The `<image>` tag is added extra, rest came out of the box with spring boot starter.

To build the image, run:

```shell
mvn spring-boot:build-image
```
