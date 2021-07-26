package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;

import java.util.List;

import DataModels.RequestDataModel;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    private List<RequestDataModel> dataSet;
    private Context context;

    public RequestAdapter(
            List<RequestDataModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donor_card, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.text.setText(dataSet.get(position).getMessage());
        holder.call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        ImageView imageView, call_button, share_button;

        ViewHolder(final View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.donorInfo);
            imageView = itemView.findViewById(R.id.image);
            call_button = itemView.findViewById(R.id.call_button);
            share_button = itemView.findViewById(R.id.share_button);
        }

    }

}