package springBootStudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller // SpringBoot bu class'i control layer olarak tanimlar
@RestController
//Controller annotasyonu Dispatcher servisinden mutlak üzere bir View alınması zorunlu iken RestController buna gereksinim duymaz.
// Herhangi bir Controller annotasyonu ile oluşturulmuş bir Controller'a ise sınıf bazında veya fonksiyon bazında ResponseBody
// annotastonu eklenerek Rest Response sağlanır ve herhangi bir 404 hatası alınmaz.
public class StudentBean01Controller {

    //  @RequestMapping(method = RequestMethod.GET, path = "/getRequest")  //sadece get işlemlerinde  ve bu adresten geldiyese bu metoduu çalıştır
    //  @ResponseBody //Methodun return ettigi datayi gosterir --- tavsiye edilmez
    //  public String getMethod1(){

    //      return "Get Request method calisti";
    //  }

    @GetMapping(path = "/getRequest") // Get Request'leri getMethod2 ile mapping yapip path'detki url calisir.
    public String getMethod2() {

        return "agam bu daha basarili";
    }
    //Controller annotasyonu Dispatcher servisinden mutlak üzere bir View alınması zorunlu ilen RestController buna gereksinim duymaz.
    // Herhangi bir Controller annotasyonu ile oluşturulmuş bir Controller'a ise sınıf bazında veya fonksiyon bazında ResponseBody
    // annotastonu eklenerek Rest Response sağlanır ve herhangi bir 404 hatası alınmaz.

    @GetMapping(path = "/getObject")
    // tight coupling yaptik --> bu ISTENMEZ
    public StudentBean01 getObj() {
        return new StudentBean01("islam", 33, "ig202233");
    }

    // Loose coupling...

    @Autowired            // StudentBean01 data type'de IOC container'e default obj create eder
            StudentBean01 s;      // bu loose coupling (new StudentBean01() --> kullanmadik), bu islemi @Component  yapiyor, "=" --> @Autowired yapiyor

    @GetMapping(path = "/getObjectLoose")
    public StudentBean01 getObjectLoose() {
        s.setAge(41);
        s.setId("gh202241");
        s.setName("Gokhan harika");
        return s;
    }

    // Tight coupling...Parametreli url--> url ile girilen parametreyi return edilen datada atanmsı
    @GetMapping(path = "/getObjectParametreli/{school}")
    public StudentBean01 getObjectParametreli(@PathVariable String school) {

        return new StudentBean01("erdem", 44, String.format("ce1987%s", school)); // %s bosluk acar ve parametreyi oraya koyar
    }

    // Tight coupling...
    @GetMapping(path = "/getObjectList")
    public List<StudentBean01> getObjectList() {

        return List.of((new StudentBean01("gokhan", 32, "asc")),
                (new StudentBean01("islam", 43, "zxc")),
                (new StudentBean01("hatice", 27, "***")));
    }


    // Loose coupling...
    @Autowired          // Trick:  @Autowired --> loose coupling  deki her variable icin kullanilmaz
            // StudentBean01 t;
            StudentBean02 t;

    @GetMapping(path = "/getStudy")
    public String getStudy01() {
        return t.study();
    }

    @Autowired
    @Qualifier(value = "studentBean02")//  @Qualifier-->@Autowired ile tanımlanan obj create edilecek data type'ni tanımlar.
            // Tanımlanacak data type annotation'a parametre olur
            //bu interfaceden oluşan deklarasyona IOC'deki studentBean01 veya studentBean02
            // adındaki bekleyen variable'lardan hangisini atayacağına karar veriyoruz qualifier ile
            StudentInterface u;

    @GetMapping(path = "/getStudy1")
    public String getStudy02() {
        return u.study();
    }

}
