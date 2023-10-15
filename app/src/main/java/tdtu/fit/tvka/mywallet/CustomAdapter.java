package tdtu.fit.tvka.mywallet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList expense_id, categories, amount, date;

    Animation translate_anim;

    CustomAdapter(Context context, ArrayList expense_id, ArrayList categories, ArrayList amount,
                  ArrayList date ) {
        this.context = context;
        this.expense_id = expense_id;
        this.categories = categories;
        this.amount = amount;
        this.date = date;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.expense_id_text.setText(String.valueOf(expense_id.get(position)));
        holder.categories_text.setText(String.valueOf(categories.get(position)));
        holder.amount_text.setText(String.valueOf(amount.get(position)));
        holder.date_text.setText(String.valueOf(date.get(position)));
        holder.layout_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(expense_id.get(position)));
                intent.putExtra("expenses", String.valueOf(categories.get(position)));
                intent.putExtra("amount", String.valueOf(amount.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return expense_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView expense_id_text, categories_text, amount_text, date_text;
        LinearLayout layout_history;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            expense_id_text = itemView.findViewById(R.id.expense_id_text);
            categories_text = itemView.findViewById(R.id.expense_name_text);
            amount_text = itemView.findViewById(R.id.amount_text);
            date_text = itemView.findViewById(R.id.date_text);
            layout_history = itemView.findViewById(R.id.layout_history);
            //Set Animation in RecyclerView
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.my_anim);
            layout_history.setAnimation(translate_anim);

        }
    }
}
