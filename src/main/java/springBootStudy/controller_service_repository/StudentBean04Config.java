package springBootStudy.controller_service_repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
//Bu class'da data create edip DB'deki tabloya datalar gonderilecek:SQL insert komutu ile de yapilabilir
public class StudentBean04Config {
    @Bean
//obj return edilen methodlar @bean annotation kullanilir..Ä°nterview :@Bean sadece method level de kullanilir
        //veriable class ve cons level de kullanilmaz
    CommandLineRunner clr1(StudentBean04Repository studentRepo) {


        return args -> studentRepo.saveAll(List.of(
                new StudentBean04(110L, "lutfullah bey", "le@cimeyil.com", LocalDate.of(1992, 4, 23)),
                new StudentBean04(111L, "mehmet bey", "mhtr@cimeyil.com", LocalDate.of(1990, 6, 3)),
                new StudentBean04(112L, "suleyman bey", "slmn@cimeyil.com", LocalDate.of(2000, 10, 12)),
                new StudentBean04(113L, "gokhan bey", "gkhn@cimeyil.com", LocalDate.of(1996, 9, 7))

        ));

    }

}
