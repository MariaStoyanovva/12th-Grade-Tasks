public class UmlDiagram1 {    //Main
    public static void main(String[] args) {
        Administrator admin = new Administrator("A101", "securePass", "active", "Alice Johnson", "alice@example.com");

        System.out.println("Login verified: " + admin.verifyLogin());
        System.out.println("Catalog updated: " + admin.updateCatalog());
    }
}


class User {
    private String userId;
    private String password;
    private String loginStatus;

    //constructor
    public User(String userId, String password, String loginStatus) {
        this.userId=userId;
        this.password=password;
        this.loginStatus=loginStatus;
    }

    //getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId=userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus=loginStatus;
    }

    //method
    public boolean verifyLogin() {
        //login is valid if status is "active"
        return this.loginStatus.equalsIgnoreCase("active");
    }
}

class Administrator extends User {
    private String adminName;
    private String email;

    //constructor
    public Administrator(String userId, String password, String loginStatus, String adminName, String email) {
        super(userId, password, loginStatus);
        this.adminName = adminName;
        this.email = email;
    }

    //getters and setters
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName=adminName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    //method
    public boolean updateCatalog() {
        //allow update only if login is verified
        return verifyLogin();
    }
}
