package org.blacksage.learn.easyschool.audit;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EasySchoolInfoContributor implements InfoContributor {
        @Override
        public void contribute(Builder builder) {
                Map<String, String> info = new HashMap<>();
                info.put("App name", "EasySchool");
                info.put("App version", "1.0.0");
                builder.withDetail("easyschool-info", info);
        }
}
