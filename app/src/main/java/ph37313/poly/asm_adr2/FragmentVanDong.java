package ph37313.poly.asm_adr2;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentVanDong extends Fragment implements SensorEventListener {

    private StepAdapter adapter;
    private final List<Step> stepList = new ArrayList<>();
    private DbHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_van_dong, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rclView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new StepAdapter(stepList);
        recyclerView.setAdapter(adapter);

        dbHelper = new DbHelper(getContext());

        loadStepsFromDatabase();

        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadStepsFromDatabase() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("steps", null, null, null, null, null, null);

        stepList.clear();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
            @SuppressLint("Range") int steps = cursor.getInt(cursor.getColumnIndex("steps"));
            @SuppressLint("Range") int goal = cursor.getInt(cursor.getColumnIndex("goal"));
            stepList.add(new Step(id, date, steps, goal));
        }
        cursor.close();

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Handle sensor data if needed
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Handle sensor accuracy changes if needed
    }
}
