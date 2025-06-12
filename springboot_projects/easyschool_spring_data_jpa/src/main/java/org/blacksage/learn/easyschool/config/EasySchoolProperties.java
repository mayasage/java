package org.blacksage.learn.easyschool.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@Data
@ConfigurationProperties(prefix = "easyschool")
// easyschool.application.yaml will only work if you enable this.
// But then your profiles won't work, because this has a higher priority.
//@PropertySource(
//        value = "classpath:easyschool.application.yaml",
//        factory = EasySchoolProperties.YamlPropertySourceFactory.class
//)
@Validated
public class EasySchoolProperties {

        public static class YamlPropertySourceFactory implements PropertySourceFactory {
                @Override
                public org.springframework.core.env.PropertySource<?>
                createPropertySource(String propertySourceName,
                                     EncodedResource encodedResource) throws IOException {
                        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
                        yamlPropertiesFactoryBean.setResources(encodedResource.getResource());
                        System.out.println("propertySourceName = " + propertySourceName);
                        System.out.println("encodedResource = " + encodedResource);
                        System.out.println("encodedResourceFileName = " + encodedResource.getResource().getFilename());
                        System.out.println("yamlPropertiesFactoryBean.getObject() = " + yamlPropertiesFactoryBean.getObject());
                        return new PropertiesPropertySource(
                                Objects.requireNonNull(encodedResource.getResource().getFilename()),
                                Objects.requireNonNull(yamlPropertiesFactoryBean.getObject())
                        );
                }
        }

        @Min(value = 5, message = "must be between 5 and 25")
        @Max(value = 25, message = "must be between 5 and 25")
        @Name("page-size")
        private int pageSize;
        private Map<String, String> contact;
        private List<String> branches;
}
