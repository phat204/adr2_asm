package ph37313.poly.asm_adr2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class FragmentHoatDong extends Fragment {

    private RecyclerView recyclerView;
    private HoatDongAdapter adapter;
    private ArrayList<HoatDong> list;
    private HoatDongDao hoatDongDao;
    private ImageView btnAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hoat_dong, container, false);

        recyclerView = view.findViewById(R.id.rclview);
        btnAdd = view.findViewById(R.id.imwadd);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        hoatDongDao = new HoatDongDao(getContext());
        list = hoatDongDao.getALLHD();
        adapter = new HoatDongAdapter(getContext(), list, hoatDongDao);
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddHoatDongDialog();
            }
        });

        return view;
    }

    private void showAddHoatDongDialog() {
        EditText txtNoiDung;
        Button btnSubmit, btnCancel;
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.add_hd, null);

        txtNoiDung = view.findViewById(R.id.edtnoidung);
        btnSubmit = view.findViewById(R.id.btnaddsubmit);
        btnCancel = view.findViewById(R.id.btncanner);

        builder.setView(view);
        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noiDung = txtNoiDung.getText().toString().trim();

                if (noiDung.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                String ngay = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

                HoatDong hoatDong = new HoatDong(noiDung, ngay);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long result = hoatDongDao.addhd(hoatDong);

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (result > 0) {
                                    list.clear();
                                    list.addAll(hoatDongDao.getALLHD());
                                    adapter.notifyDataSetChanged();

                                    Toast.makeText(getContext(), "Thêm hoạt động thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(getContext(), "Thêm hoạt động không thành công", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
