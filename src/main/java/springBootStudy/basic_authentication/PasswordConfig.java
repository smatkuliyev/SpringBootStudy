package springBootStudy.basic_authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passSifrele() {     // return type'i obj olan bir method.
        // Bu method password encode ettigi icin password olan class'a call edildi
        return new BCryptPasswordEncoder(10);   // crypto guvenlik seviyesi, genelde 8-10 kullanilir
    }

}
