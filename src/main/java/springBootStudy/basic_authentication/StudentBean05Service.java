package springBootStudy.basic_authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

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

    // bu method tum ogrencileri getirir
    public List<StudentBean05> selectAllStudents() {
        return studentRepo.findAll();
    }

    // bu method var olan ogrc tum verilerini(PUT:fully update) gunceller
    public StudentBean05 updateFullyStudentById(Long id, StudentBean05 newStudent) { //kullanıcıdan gelen id ve yeni bilgilerle id li studenti güncelliyecez
        StudentBean05 eskiOgrc = studentRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("girdiginiz " + id + "li orgenci mevcut degil"));

        if (newStudent.getName() == null) {
            eskiOgrc.setName(null);
        } else if (!eskiOgrc.getName().equals(newStudent.getName())) {
            eskiOgrc.setName(newStudent.getName());
        }

        /* BRD:
        1) email unique olacak, degise -> exception
        2) email gecerli(@ icermeli) olmali -> exception
        3) email null olamaz -> exception
        4) email eski ve yeni ayni ise update etmemeli
         */
        if (newStudent.getEmail()==null){
            newStudent.setEmail("");
        }
        Optional<StudentBean05> emailOlanOgrc = studentRepo.findStudentBean05ByEmail(newStudent.getEmail());
        if (emailOlanOgrc.isPresent()) {
            throw new IllegalStateException("daha once bu email kullanildi");
        } else if (!newStudent.getEmail().contains("@") && newStudent.getEmail()!="") {
            throw new IllegalStateException("email @ icermeli");
        } else if (newStudent.getEmail() == null) {
            throw new IllegalStateException("email zorunlu !");
        } else if (!newStudent.getEmail().equals(eskiOgrc.getEmail())) {
            eskiOgrc.setEmail(newStudent.getEmail());
        } else {
            throw new IllegalStateException("email ayni, update edilmez !");
        }

        // dob update edilecek
        /*
        1) girilen dob gelecekten almamali
        2) girilen dob ayni olmamali
         */
        if (Period.between(newStudent.getDob(), LocalDate.now()).isNegative()) {
            throw new IllegalStateException("hatali dob girdiniz");
        } else if(!newStudent.getDob().equals(eskiOgrc.getDob())){
            eskiOgrc.setDob(newStudent.getDob());
        }


        return studentRepo.save(eskiOgrc);
    }


}
