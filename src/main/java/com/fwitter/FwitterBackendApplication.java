package com.fwitter;

import com.fwitter.models.ApplicationUser;
import com.fwitter.models.Role;
import com.fwitter.repositories.RoleRepository;
import com.fwitter.repositories.UserRepository;
import com.fwitter.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@SpringBootApplication
public class FwitterBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FwitterBackendApplication.class, args);
    }

   @Bean
    CommandLineRunner run(RoleRepository roleRepo, UserService userService){
        return args->{
            roleRepo.save(new Role(1,"USER"));
           /* ApplicationUser u=new ApplicationUser();

            u.setFirstName("unknown1");
            u.setLastName("PS");

            userService.registerUser(u);
*/

        };
   }

}
