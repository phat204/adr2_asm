package ph37313.poly.asm_adr2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HoatDong {
    private int mahd;
    private String noidung;
    private String ngay;

    public HoatDong(String noiDung) {
    }

    public HoatDong(String noidung, String ngay) {
        this.noidung = noidung;
        this.ngay = ngay;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
