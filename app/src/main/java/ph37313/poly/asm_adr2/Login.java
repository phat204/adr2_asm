package ph37313.poly.asm_adr2;

public class Login {
    private String tendangnhap;
    private String username;
    private String password;


    public Login(String tendangnhap, String username, String password) {
        this.tendangnhap = tendangnhap;
        this.username = username;
        this.password = password;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
