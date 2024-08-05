package ph37313.poly.asm_adr2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class HoatDongDao {
    private DbHelper dBhelper;

    public HoatDongDao(Context context) {
        dBhelper = new DbHelper(context);
    }
    public ArrayList<HoatDong> getALLHD() {
        ArrayList<HoatDong> list = new ArrayList<>();
        SQLiteDatabase database = dBhelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM HoatDong", null);

        while (cursor.moveToNext()) {
            HoatDong hoatDong = new HoatDong(
                    cursor.getString(1), // NOIDUNG
                    cursor.getString(2)); // NGAY
            hoatDong.setMahd(cursor.getInt(0)); // MAHD
            list.add(hoatDong);
        }
        cursor.close();
        return list;
    }

    public long addhd(HoatDong hoatDong) {
        SQLiteDatabase database = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Kiểm tra nếu giá trị không null
        if (hoatDong.getNoidung() != null) {
            values.put("NOIDUNG", hoatDong.getNoidung());
        } else {
            // Xử lý trường hợp giá trị là null, ví dụ: gán giá trị mặc định hoặc trả về lỗi
            return -1; // Hoặc xử lý khác tùy theo yêu cầu
        }

        // Không cần thêm giá trị cho "NGAY" nếu đã được tự động cập nhật trong cơ sở dữ liệu
        return database.insert("HoatDong", null, values);
    }


    public long updatehd(HoatDong hoatDong){
        SQLiteDatabase database = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NOIDUNG", hoatDong.getNoidung());
        // Nếu bạn muốn cập nhật ngày, thêm ngày hiện tại vào values
        values.put("NGAY", "datetime('now')");
        return database.update("HoatDong", values, "MAHD=?", new String[]{
                String.valueOf(hoatDong.getMahd())
        });
    }

    public long delete(int mahd){
        SQLiteDatabase database=dBhelper.getWritableDatabase();
        long check=database.delete("HoatDong","MAHD=?",new String[]{
                String.valueOf(mahd)
        });
        return  check;
    }

    public long addsig(Login login){
        SQLiteDatabase database=dBhelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("TENDANGKY",login.getTendangnhap());
        values.put("USERNAME",login.getUsername());
        values.put("PASSWORD",login.getPassword());
        return database.insert("sig",null,values);
    }

    public boolean checklogin(String username, String password) {
        SQLiteDatabase database = dBhelper.getReadableDatabase();
        // Tạo câu truy vấn SQL để lấy dữ liệu từ bảng 'sig' với 'USERNAME' và 'PASSWORD' đã cung cấp
        String sql = "SELECT * FROM sig WHERE USERNAME = ? AND PASSWORD = ?";
        Cursor cs = database.rawQuery(sql, new String[]{username, password});
        // Đếm số lượng dòng trong Cursor
        int count = cs.getCount();
        // Đóng Cursor
        cs.close();
        // Trả về true nếu có ít nhất một dòng được trả về từ cơ sở dữ liệu, ngược lại trả về false
        return (count > 0);
    }
    public boolean checkIfEmailExists(String email) {
        // đọc dữ liệu
        SQLiteDatabase db = this.dBhelper.getReadableDatabase();
        String query = "SELECT * FROM sig" + " WHERE USERNAME = ?";
        // Thực thi câu truy vấn và trả về đối tượng Cursor
        Cursor cursor = db.rawQuery(query, new String[]{email});
        // Kiểm tra xem có ít nhất một dòng được trả về từ cơ sở dữ liệu hay không
        boolean exists = cursor.moveToFirst();
        cursor.close();
        // Trả về true nếu có ít nhất một dòng được trả về từ cơ sở dữ liệu, ngược lại trả về false
        return exists;
    }
}
