package springBootStudy.basic_authentication;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static springBootStudy.basic_authentication.ApplicationUserPermission.STUDENT_READ;
import static springBootStudy.basic_authentication.ApplicationUserPermission.STUDENT_WRITE;


public enum ApplicationUserRoles {// bir app'de sabit datalarin saklandigi yapidir

    STUDENT(Sets.newHashSet(STUDENT_READ)), ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE));

    private final Set<ApplicationUserPermission> permissions;   //STUDENT_READ("student:read"), STUDENT_WRITE("student:write"),
    // bu datalari call etmek icin ve datalar unique oldugu icin set data type'inde obj create edildi


    ApplicationUserRoles(Set<ApplicationUserPermission> permissions) { //final olan variable initialize olmasi icin p'li cons
        this.permissions = permissions;
    }

    //   public Set<ApplicationUserPermission> izinleriGetirenMethod() { // permissions obj data field(STUDENT_READ,STUDENT_WRITE) okumak icin get
    //       return permissions;
    //   }


    public Set<SimpleGrantedAuthority> izinOnayla() {
        /*
        Bu method STUDENT_READ("student:read"), STUDENT_WRITE("student:write");
        Role obje'deki "student:read" ve "student:write" izinleri onaylanmis set'ini return eder
        SimpleGrantedAuthority: Authentication obj verilen bir Authority'i (izin yetki) String degerini saklar.
         */

        Set<SimpleGrantedAuthority> onaylananIzinler = permissions  // permission enumdaki datalarin fieldlerini("student:read", "student:write") return eder
                .stream()
                .map(t -> new SimpleGrantedAuthority(t.getPermission())) // akistaki field'lar springBoot security geregi onaylaniyor
                .collect(Collectors.toSet());

        onaylananIzinler.add(new SimpleGrantedAuthority("ROLE_" + this.name())); //SpringBoot formati geregi onaylanan set'teki izinleri

        return onaylananIzinler;
    }


}
