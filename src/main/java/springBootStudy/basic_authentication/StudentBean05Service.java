package springBootStudy.basic_authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentBean05Service {
    private StudentBean05Repository studentRepo;//Repository layer'a ulasmak icin data type'nan obj create edildi.

    //obj degerini cons'dan alacak
    @Autowired
    public StudentBean05Service(StudentBean05Repository studentRepo) {
        this.studentRepo = studentRepo;
    }

    //Bu method id ile ogrc return edecek
    public StudentBean05 selectStudentById(Long id) {
        // return studentRepo.findById(id).get();--> olmayan id hata verir code kirlri bununicin kontrol if calismali
        if (studentRepo.findById(id).isPresent()) {

            return studentRepo.findById(id).get();
        }
        return new StudentBean05();//isteen id yoksa bos cons run edilecek
    }//service layer'de repository'den alinan datalar methodda calistirildi. bu metthod controlle layer'da call edilmeli

}
