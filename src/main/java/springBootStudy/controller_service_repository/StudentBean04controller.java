package springBootStudy.controller_service_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentBean04controller {
    private StudenBean04Service stdSrvc;//service layer' ulaÅŸmak iÃ§in obj create edildi

    @Autowired
    public StudentBean04controller(StudenBean04Service stdSrvc) {
        this.stdSrvc = stdSrvc;
    }

    //bu method id ile ogrc returnn eden service methodu call edecek
    @GetMapping(path = "/selectStudentById/{id}")
    public StudentBean04 selectStudentById(@PathVariable Long id) {

        return stdSrvc.selectStudentById(id);
    }
}
