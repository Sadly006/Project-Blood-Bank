package Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.PermissionChecker;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;

import java.util.List;

import DataModels.RequestDataModel;

import static android.Manifest.permission.CALL_PHONE;

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
        String str = "Name: " + dataSet.get(position).getName();
        //str += "\nContact: " + dataSet.get(position).getContact();
        str += "\nBloodGroup: " + dataSet.get(position).getBloodGroup();
        str += "\nLocation: " + dataSet.get(position).getLocation();
        holder.text.setText(str);

        holder.call_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (PermissionChecker.checkSelfPermission(context, CALL_PHONE)
                            == PermissionChecker.PERMISSION_GRANTED) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + dataSet.get(position).getContact()));
                        context.startActivity(intent);
                    } else {
                        ((Activity) context).requestPermissions(new String[]{CALL_PHONE}, 401);
                    }
                }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        ImageView imageView, call_button;

        ViewHolder(final View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.donorInfo);
            imageView = itemView.findViewById(R.id.image);
            call_button = itemView.findViewById(R.id.call_button);
        }

    }

}