package ph37313.poly.asm_adr2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HoatDongAdapter extends RecyclerView.Adapter<HoatDongAdapter.ViewHodelhoatdong> {

    private Context context;
    private ArrayList<HoatDong> list;
    HoatDongDao hoatDongDao;

    public HoatDongAdapter(Context context, ArrayList<HoatDong> list, HoatDongDao hoatDongDao) {
        this.context = context;
        this.list = list;
        this.hoatDongDao = hoatDongDao;
    }

    @NonNull
    @Override
    public ViewHodelhoatdong onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.list_menu, parent, false);
        return new ViewHodelhoatdong(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodelhoatdong holder, int i) {
        holder.txtmahd.setText(String.valueOf(list.get(i).getMahd()));
        holder.txtnd.setText(list.get(i).getNoidung());
        holder.txtngay.setText(String.valueOf(list.get(i).getNgay()));

        holder.txtdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                HoatDong hd = list.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setIcon(R.drawable.baseline_question_mark_24);
                builder.setMessage("Bạn có chắc chắn muốn xóa hoạt động này?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hoatDongDao = new HoatDongDao(context);
                        long result = hoatDongDao.delete(hd.getMahd());
                        if (result > 0) {
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            list.remove(position);
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        holder.txtupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(context, holder.getAdapterPosition());
            }

            public void update(Context context, int position) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.update_hd, null);
                builder.setView(view);

                Button btnudt = view.findViewById(R.id.btnudtsubmit);
                Button btncanner = view.findViewById(R.id.btnudtcanner);

                @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText txtnoidung = view.findViewById(R.id.udtnoidung);

                androidx.appcompat.app.AlertDialog dialog = builder.create();

                dialog.setCancelable(false);
                dialog.show();

                HoatDong hd = list.get(position);
                txtnoidung.setText(hd.getNoidung());

                btncanner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnudt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tnd = txtnoidung.getText().toString();

                        if (tnd.isEmpty()) {
                            Toast.makeText(context, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        hd.setNoidung(tnd);

                        long result = hoatDongDao.updatehd(hd);
                        if (result > 0) {
                            list.set(position, hd);
                            notifyItemChanged(position);
                            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodelhoatdong extends RecyclerView.ViewHolder {
        TextView txtmahd, txtnd, txtngay;
        TextView txtupdate, txtdelete;

        public ViewHodelhoatdong(@NonNull View itemView) {
            super(itemView);
            txtmahd = itemView.findViewById(R.id.txtmahd);
            txtnd = itemView.findViewById(R.id.txtnoidung);
            txtngay = itemView.findViewById(R.id.txtngay);
            txtupdate = itemView.findViewById(R.id.textupdate);
            txtdelete = itemView.findViewById(R.id.textdelete);
            txtdelete.setPaintFlags(txtdelete.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            txtupdate.setPaintFlags(txtupdate.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }
    }
}
