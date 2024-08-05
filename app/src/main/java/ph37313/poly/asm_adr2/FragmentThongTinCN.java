package ph37313.poly.asm_adr2;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentThongTinCN extends Fragment {

    private EditText edChieuCao, edCanNang;
    private TextView txtBMI, txtChiSo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_tin_c_n, container, false);

        edChieuCao = view.findViewById(R.id.edchieucao);
        edCanNang = view.findViewById(R.id.edcannang);
        txtBMI = view.findViewById(R.id.txtbmi);
        txtChiSo = view.findViewById(R.id.txtchiso);

        TextWatcher bmiTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateBMI();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing
            }
        };

        edChieuCao.addTextChangedListener(bmiTextWatcher);
        edCanNang.addTextChangedListener(bmiTextWatcher);

        return view;
    }

    private void calculateBMI() {
        String heightStr = edChieuCao.getText().toString();
        String weightStr = edCanNang.getText().toString();

        if (!heightStr.isEmpty() && !weightStr.isEmpty()) {
            double heightCm = Double.parseDouble(heightStr);
            double weight = Double.parseDouble(weightStr);

            // Convert height from cm to meters
            double height = heightCm / 100.0;

            double bmi = weight / (height * height);
            txtBMI.setText(String.format("%.2f", bmi));

            String bmiDescription;
            if (bmi < 18.5) {
                bmiDescription = "Bạn đang gặp phải tình trạng thiếu cân.";
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                bmiDescription = "Bạn đang sở hữu cân nặng khỏe mạnh.";
            } else if (bmi >= 25 && bmi <= 29.9) {
                bmiDescription = "Bạn đang trong tình trạng thừa cân.";
            } else {
                bmiDescription = "Bạn đang bị béo phì.";
            }

            txtChiSo.setText(bmiDescription);
        } else {
            txtBMI.setText("XX");
            txtChiSo.setText("Chỉ số BMI ở trên cho thấy bạn bình thường");
        }
    }
}
