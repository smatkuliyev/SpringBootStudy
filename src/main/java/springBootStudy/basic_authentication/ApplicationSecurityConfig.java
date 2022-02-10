package springBootStudy.basic_authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration // Class'i config olarak tanimlar
@EnableWebSecurity
// Tanimli oldugu class'da form based security yerine(basic authentication) configure(ayarlama) eder
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordConfig passEncode;  // final obj bir deger almali, bu degeri alacagi autowired cons create edilmeli

    @Autowired
    public ApplicationSecurityConfig(PasswordConfig passEncode) {
        this.passEncode = passEncode;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //   super.configure(http);
        http.
                csrf().disable(). // ey springBoot "put delete patch post" calistir, sorumluluk benim, default ayarları kaldırdık.
                authorizeRequests().   // Request'ler icin yetki sorgula (get post put patch delete)
                antMatchers("/", "index", "/css/*", "/js/*").permitAll().  // antMatchers() method parametresindeki url'lere izin ver
                // free sayfalari bu sekilde yapariz, antMatchers() and permitAll()
                anyRequest().       // her request icin
                authenticated().    // kullanici sorgula
                and().              // neye gore
                httpBasic();        // httpBasic'e gore
        // her request'te APP username ve pass(security) control edilmeli logout yapmaya gerek kalmayacak
        // Benim istedigim kisiler apt girdikten sonra herkes yetki sahibi olsun
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() { // kullanici create etme method'u
        //  return super.userDetailsService();

        //  UserDetails student1 = User.builder().username("haluk").password("1453").roles("sefil AGA", " diger meslek").build();
        //  UserDetails student2 = User.builder().username("ipek").password("1979").roles("Ipek Hanim ").build();
        //  UserDetails admin = User.builder().username("admin").password("nimda").roles("ADMIN").build(); // convensions tanim

        UserDetails student1 = User.builder().username("haluk").password(passEncode.passSifrele().encode("1453")).roles("sefil AGA", " diger meslek").build();
        UserDetails student2 = User.builder().username("ipek").password(passEncode.passSifrele().encode("1979")).roles("Ipek Hanim ").build();
        UserDetails admin = User.builder().username("admin").password(passEncode.passSifrele().encode("nimda")).roles("ADMIN").build(); // convensions tanim

        // return student1; hatali type, kullanmayiz
        return new InMemoryUserDetailsManager(student1, student2, admin); // burada yeni sey  gorduk
    }
}
