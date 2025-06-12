package mayasage.spring_start_here.models;

import mayasage.spring_start_here.services.LoggedUserManagementService;
import mayasage.spring_start_here.services.LoginCountService;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Data
public class LoginProcessor {
        private final LoggedUserManagementService loggedUserManagementService;
        private final LoginCountService loginCountService;
        private String username;
        private String password;

        public boolean login() {
                loginCountService.increment();
                String username = this.getUsername();
                String password = this.getPassword();
                if (username.equals("natasha") && password.equals("cherryblossom")) {
                        loggedUserManagementService.setUsername(username);
                        return true;
                }
                return false;
        }
}
