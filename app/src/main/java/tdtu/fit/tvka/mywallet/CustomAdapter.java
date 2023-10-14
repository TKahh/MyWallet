package tdtu.fit.tvka.mywallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList expense_id, categories, amount, date;

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.expense_id_text.setText(String.valueOf(expense_id.get(position)));
        holder.categories_text.setText(String.valueOf(categories.get(position)));
        holder.amount_text.setText(String.valueOf(amount.get(position)));
        holder.date_text.setText(String.valueOf(date.get(position)));
    }

    @Override
    public int getItemCount() {
        return expense_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView expense_id_text, categories_text, amount_text, date_text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            expense_id_text = itemView.findViewById(R.id.expense_id_text);
            categories_text = itemView.findViewById(R.id.expense_name_text);
            amount_text = itemView.findViewById(R.id.amount_text);
            date_text = itemView.findViewById(R.id.date_text);
        }
    }
}
