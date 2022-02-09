package springBootStudy.basic_authentication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
//Bu class'da data create edip DB'deki tabloya datalar gonderilecek:SQL insert komutu ile de yapilabilir
public class StudentBean05Config {
    @Bean
//obj return edilen methodlar @bean annotation kullanilir..Ä°nterview :@Bean sadece method level de kullanilir
        //veriable class ve cons level de kullanilmaz
    CommandLineRunner clr1(StudentBean05Repository studentRepo) {


        return args -> studentRepo.saveAll(List.of(
                new StudentBean05(110L, "lutfullah bey", "le@cimeyil.com", LocalDate.of(1992, 4, 23)),
                new StudentBean05(111L, "mehmet bey", "mhtr@cimeyil.com", LocalDate.of(1990, 6, 3)),
                new StudentBean05(112L, "suleyman bey", "slmn@cimeyil.com", LocalDate.of(2000, 10, 12)),
                new StudentBean05(113L, "gokhan bey", "gkhn@cimeyil.com", LocalDate.of(1996, 9, 7))

        ));

    }

}
