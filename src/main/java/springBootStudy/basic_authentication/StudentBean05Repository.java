package springBootStudy.basic_authentication;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository                                     // <Kullanilacak model class, PK data type>
public interface StudentBean05Repository extends JpaRepository<StudentBean05, Long> {//repository'in ihtiyac duyacagi id ile data alma
    // vs bazi methodlari kullanmak icin extend edildi


    Optional<StudentBean05> findStudentBean05ByEmail(String email);//e mail ile data bulan metod


}
