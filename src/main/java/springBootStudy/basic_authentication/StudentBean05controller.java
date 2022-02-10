package springBootStudy.basic_authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentBean05controller {
    private StudentBean05Service stdSrvc;//service layer' ulaÅŸmak iÃ§in obj create edildi

    @Autowired
    public StudentBean05controller(StudentBean05Service stdSrvc) {
        this.stdSrvc = stdSrvc;
    }

    //bu method id ile ogrc returnn eden service methodu call edecek
    @GetMapping(path = "/selectStudentById/{id}")
    public StudentBean05 selectStudentById(@PathVariable Long id) {

        return stdSrvc.selectStudentById(id);
    }

    @PutMapping(path = "/updateFullyStudentById/{id}")
    public StudentBean05 updateFullStudentIdIle(@PathVariable Long id, @RequestBody StudentBean05 newStd){
        return stdSrvc.updateFullyStudentById(id, newStd);
    }

}
