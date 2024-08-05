package ph37313.poly.asm_adr2;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Log_in extends AppCompatActivity {
    EditText user,pass;
    Button button;
    TextView textView,txtregister;
    HoatDongDao hoatdongdao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);

        user=findViewById(R.id.sign_username);
        pass=findViewById(R.id.sign_password);
        button=findViewById(R.id.sign_button);
        textView=findViewById(R.id.loginRead);
        txtregister=findViewById(R.id.loginRegister);
        txtregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Log_in.this,Register.class);
                startActivity(intent);
            }
        });
        hoatdongdao = new HoatDongDao(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtus = user.getText().toString();
                String txtpsw = pass.getText().toString();

                if (TextUtils.isEmpty(txtus) || TextUtils.isEmpty(txtpsw)) {
                    Toast.makeText(Log_in.this, "Không được để trống thông tin ", Toast.LENGTH_SHORT).show();
                    return;
                }
                Boolean login = hoatdongdao.checklogin(txtus, txtpsw);
                if (login) {
                    // Hiển thị thông báo "Đăng nhập thành công"
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    // Chuyển đến màn hình chính
                    Intent intent = new Intent(getApplicationContext(), FragmentHoatDong.class);
                    startActivity(intent);
                } else {
                    // Hiển thị thông báo "Tên đăng nhập hoặc mật khẩu không đúng"
                    Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtregister.setPaintFlags(txtregister.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}