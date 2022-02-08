package springBootStudy.controller_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentBean03Controller {

    private StudentBean03Service std;

    @Autowired
    public StudentBean03Controller(StudentBean03Service std) {
        this.std = std;
    }

  //  @GetMapping(path = "/studentById")
  //  public StudentBean03 getStudentIle(){
  //      return std.getStudentById(106L);
  //  }

    @GetMapping(path = "/studentById/{id}")
    public StudentBean03 getStudentIle(@PathVariable Long id){
        return std.getStudentById(id);
    }

}
