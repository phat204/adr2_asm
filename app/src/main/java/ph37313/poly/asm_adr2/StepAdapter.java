package ph37313.poly.asm_adr2;

import android.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private final List<Step> stepList;

    public StepAdapter(List<Step> stepList) {
        this.stepList = stepList;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_van_dong, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {
        Step step = stepList.get(position);

        holder.tvIndex.setText(String.valueOf(position + 1));
        holder.tvDate.setText(step.getDate());
        holder.tvSteps.setText(String.valueOf(step.getSteps()));
        holder.tvGoal.setText(String.valueOf(step.getGoal()));

        // Update the "Achieved" TextView based on steps and goal
        int steps = step.getSteps();
        int goal = step.getGoal();
        if (steps >= goal) {
            holder.tvAchieved.setText("Đạt");
            holder.tvAchieved.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_green_dark));
        } else {
            holder.tvAchieved.setText("Chưa đạt");
            holder.tvAchieved.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_red_dark));
        }

        holder.tvSteps.setOnClickListener(v -> {
            int randomSteps = new Random().nextInt(2000); // Random number for demo
            step.setSteps(randomSteps);
            notifyItemChanged(position);
        });

        holder.tvGoal.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Enter new goal");

            final EditText input = new EditText(v.getContext());
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            builder.setView(input);

            builder.setPositiveButton("OK", (dialog, which) -> {
                int newGoal = Integer.parseInt(input.getText().toString());
                step.setGoal(newGoal);
                notifyItemChanged(position);
            });
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    public static class StepViewHolder extends RecyclerView.ViewHolder {
        TextView tvIndex, tvDate, tvSteps, tvGoal, tvAchieved;

        public StepViewHolder(View itemView) {
            super(itemView);
            tvIndex = itemView.findViewById(R.id.tvIndex);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvSteps = itemView.findViewById(R.id.tvSteps);
            tvGoal = itemView.findViewById(R.id.tvGoal);
            tvAchieved = itemView.findViewById(R.id.tvAchieved); // Add this line to reference the "Achieved" TextView
        }
    }
}
