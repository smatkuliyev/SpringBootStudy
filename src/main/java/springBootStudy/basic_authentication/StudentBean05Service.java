package springBootStudy.basic_authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
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
        if (newStudent.getEmail() == null) {
            newStudent.setEmail("");
        }
        Optional<StudentBean05> emailOlanOgrc = studentRepo.findStudentBean05ByEmail(newStudent.getEmail());
        if (emailOlanOgrc.isPresent()) {
            throw new IllegalStateException("daha once bu email kullanildi");
        } else if (!newStudent.getEmail().contains("@") && newStudent.getEmail() != "") {
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
        } else if (!newStudent.getDob().equals(eskiOgrc.getDob())) {
            eskiOgrc.setDob(newStudent.getDob());
        }


        return studentRepo.save(eskiOgrc);
    }

    // ile ile data delete edecek
    public String deleteStudentById(Long id) {
        if (!studentRepo.existsById(id)) {
            throw new IllegalStateException(id + " li ogrc bulunamadi");
        }
        studentRepo.deleteById(id);
        ;

        return "The " + id + " who has deleted from system";
    }

    //This method update objects partially
    public StudentBean05 updatePatchStudentById(Long id, StudentBean05 newStudent) {
        StudentBean05 existingStudentById = studentRepo.findById(id).orElseThrow(() -> new IllegalStateException(id + " li ogrenci yok"));

        if (newStudent.getEmail() == null) {
            newStudent.setEmail("");
        }
        Optional<StudentBean05> emailOlanOgrc = studentRepo.findStudentBean05ByEmail(newStudent.getEmail());
        if (emailOlanOgrc.isPresent()) {
            throw new IllegalStateException("daha once bu email kullanildi");
        } else if (!newStudent.getEmail().contains("@") && newStudent.getEmail() != "") {
            throw new IllegalStateException("email @ icermeli");
        } else if (newStudent.getEmail() == null) {
            throw new IllegalStateException("email zorunlu !");
        } else if (!newStudent.getEmail().equals(existingStudentById.getEmail())) {
            existingStudentById.setEmail(newStudent.getEmail());
        } else {
            throw new IllegalStateException("email ayni, update edilmez !");
        }

        return studentRepo.save(existingStudentById);
    }


    //this method creates new StudentObject
    public StudentBean05 addStudent(StudentBean05 newStudent) throws ClassNotFoundException, SQLException {
        //ogrc email datasi girilecek
        Optional<StudentBean05> existingStudentByMail = studentRepo.findStudentBean05ByEmail(newStudent.getEmail());
        if (existingStudentByMail.isPresent()) {
            throw new IllegalStateException("This mail: " + newStudent.getEmail() + " has been used!");
        }

        //ogrc name datasi girilecek
        if (newStudent.getName() == null) {
            throw new IllegalStateException("Didnt entered name, please enter name!");
        }

        // yeni unique id datasi girilecek
        /*
        LOGIC : DB'de var olan max id get edip +1 hali yeni id olarak assign edilir
         */
        // db'ye JDBC connection yapacagiz
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
        Statement st = con.createStatement();
        // max id get icin SQL query komut yapacagiz
        String sqlQueryMaxId = "select max(id) from students";
        ResultSet result = st.executeQuery(sqlQueryMaxId); // normalde bir tane vermesi lazım, her ihtimala karsı while kullandik
        Long maxId = 0L;
        while (result.next()) {
            maxId = result.getLong(1);
        }
        newStudent.setId(maxId + 1);
        newStudent.setAge(newStudent.getAge());
        newStudent.setErrMsg("Student has been added!");

        return studentRepo.save(newStudent);
    }

}
