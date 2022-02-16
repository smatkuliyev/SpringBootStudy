package springBootStudy.basic_authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class StudentBean05controller {
    private StudentBean05Service stdSrvc;//service layer' ulaÅŸmak iÃ§in obj create edildi

    @Autowired
    public StudentBean05controller(StudentBean05Service stdSrvc) {
        this.stdSrvc = stdSrvc;
    }

    //bu method id ile ogrc returnn eden service methodu call edecek
    @GetMapping(path = "/selectStudentById/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
    public StudentBean05 selectStudentById(@PathVariable Long id) {

        return stdSrvc.selectStudentById(id);
    }

    @GetMapping(path = "/selectAll")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')") //Bu method'u role a gore yetkilendir
    public List<StudentBean05> selectAllStdnt() {
        return stdSrvc.selectAllStudents();
    }


    @PutMapping(path = "/updateFullyStudentById/{id}")
    @PreAuthorize("hasAuthority('student:write')") //Bu method'u permission a gore yetkilendir
    public StudentBean05 updateFullStudentIdIle(@PathVariable Long id, @Validated @RequestBody StudentBean05 newStd) {
        return stdSrvc.updateFullyStudentById(id, newStd);
    }

    @DeleteMapping(path = "/deleteStudentById/{id}")
    @PreAuthorize("hasAuthority('student:write')") //Bu method'u permission a gore yetkilendir
    public String deleteStdntById(@PathVariable Long id) {
        return stdSrvc.deleteStudentById(id);
    }

    @PatchMapping(path = "/updatePartiallyStudentById/{id}")
    @PreAuthorize("hasAuthority('student:write')") //Bu method'u permission a gore yetkilendir
    public StudentBean05 updatePartiallyStdntById(@PathVariable Long id, @Validated @RequestBody StudentBean05 newStd) {
        return stdSrvc.updatePatchStudentById(id, newStd);
    }

    @PostMapping(path = "/addStudent")
    @PreAuthorize("hasAuthority('student:write')") //Bu method'u permission a gore yetkilendir
    public StudentBean05 addStdnt(@Validated @RequestBody StudentBean05 newStdnt) throws SQLException, ClassNotFoundException {
        return stdSrvc.addStudent(newStdnt);
    }

}
