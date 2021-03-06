package springBootStudy.controller_service_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudenBean04Service {
    private StudentBean04Repository studentRepo;//Repository layer'a ulasmak icin data type'nan obj create edildi.

    //obj degerini cons'dan alacak
    @Autowired
    public StudenBean04Service(StudentBean04Repository studentRepo) {
        this.studentRepo = studentRepo;
    }

    //Bu method id ile ogrc return edecek
    public StudentBean04 selectStudentById(Long id) {
        // return studentRepo.findById(id).get();--> olmayan id hata verir code kirlri bununicin kontrol if calismali
        if (studentRepo.findById(id).isPresent()) {

            return studentRepo.findById(id).get();
        }
        return new StudentBean04();//isteen id yoksa bos cons run edilecek
    }//service layer'de repository'den alinan datalar methodda calistirildi. bu metthod controlle layer'da call edilmeli

}
