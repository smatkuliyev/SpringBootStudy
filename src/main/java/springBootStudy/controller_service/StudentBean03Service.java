package springBootStudy.controller_service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class StudentBean03Service {

    List<StudentBean03> listOfStudent = List.of(
            new StudentBean03(101L,"Zekeriya","zek@gmail.com", LocalDate.of(1980,1,14)),
            new StudentBean03(102L,"hatice","hatice@gmail.com", LocalDate.of(1984,12,18)),
            new StudentBean03(103L,"yildiz","yildiz@gmail.com", LocalDate.of(1976,6,21)),
            new StudentBean03(104L,"osman","osman@gmail.com", LocalDate.of(1978,4,7))
    );

    // id ile obj datasi veren method
    public StudentBean03 getStudentById(Long id){
        if (listOfStudent.stream().filter(t -> t.getId() == id).collect(Collectors.toList()).isEmpty()){
            return new StudentBean03(); // p'siz constructor return et
        }

        return listOfStudent.stream().filter(t -> t.getId() == id).findFirst().get();
    }




}
