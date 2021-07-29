package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;

import java.util.List;

import DataModels.Donor;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

  private List<Donor> dataSet;
  private Context context;

  public SearchAdapter(
          List<Donor> dataSet, Context context) {
    this.dataSet = dataSet;
    this.context = context;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.donor_card, parent, false);
    return new ViewHolder(view);
  }


  @Override
  public void onBindViewHolder(@NonNull final ViewHolder holder,
                               final int position) {
    String str = "Name: " + dataSet.get(position).getName();
    str += "\nLocation: " + dataSet.get(position).getLocation();
    holder.donorInfo.setText(str);
    holder.callButton.setOnClickListener(new OnClickListener() {
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

    TextView donorInfo;
    ImageView callButton;
    CardView cardImage;

    ViewHolder(final View itemView) {
      super(itemView);
      donorInfo = itemView.findViewById(R.id.donorInfo);
      cardImage = itemView.findViewById(R.id.cardImage);
      callButton = itemView.findViewById(R.id.call_button);
    }

  }

}
