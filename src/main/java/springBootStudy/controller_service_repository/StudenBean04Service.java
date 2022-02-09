package springBootStudy.controller_service_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudenBean04Service {
    private StudentBean04Repository studentRepo;//Repository layer'a ulaÅŸmak iÃ§in data type'nan obj create edildi.

    //obj degerini cons'dan alacak
    @Autowired
    public StudenBean04Service(StudentBean04Repository studentRepo) {
        this.studentRepo = studentRepo;
    }

    //Bu method id ile Ã¶grc return edecek
    public StudentBean04 selectStudentById(Long id) {
        // return studentRepo.findById(id).get();--> olmayan id hata verir code kÄ±rlrÄ± bununiÃ§in kontrol if Ã§alÄ±ÅŸmalÄ±
        if (studentRepo.findById(id).isPresent()) {

            return studentRepo.findById(id).get();
        }
        return new StudentBean04();//isteen id yoksa bos cons run edilecek
    }//service layer'de repository'den alÄ±nan datalar methodda Ã§alÄ±ÅŸtÄ±rÄ±ldÄ±. bu metthod controlle layer'da call edilmeli

}
