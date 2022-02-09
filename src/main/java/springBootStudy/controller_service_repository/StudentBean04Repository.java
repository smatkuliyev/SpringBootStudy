package springBootStudy.controller_service_repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository                                                 // <KullanÄ±lacak model class,PK data type>
public interface StudentBean04Repository extends JpaRepository<StudentBean04, Long> {//repository'in ihtiyac duyacagÄ± id ile data alma vs bazÄ± methodlarÄ± kullanmak iÃ§inextend edildi


    Optional<StudentBean04> findStudentBean04ByEmail(String email);//e mail ile data bulan metod


}
