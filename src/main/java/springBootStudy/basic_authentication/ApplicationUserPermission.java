package springBootStudy.basic_authentication;

public enum ApplicationUserPermission {

    STUDENT_READ("student:read"), STUDENT_WRITE("student:write");
    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {//final oldugu icin setter i yok
        return permission;
    }
}
