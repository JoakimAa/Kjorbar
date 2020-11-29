package no.hiof.kjorbar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import no.hiof.kjorbar.R;
import no.hiof.kjorbar.model.AlcoholUnit;

public class AlcoholUnitRecyclerAdapter extends RecyclerView.Adapter<AlcoholUnitRecyclerAdapter.AlcoholUnitViewHolder>{

    public List<AlcoholUnit> alcoholUnitList;
    public LayoutInflater inflater;
    private View.OnClickListener clickListener;

    public AlcoholUnitRecyclerAdapter(Context context, List<AlcoholUnit> alcoholUnitList, View.OnClickListener clickListener) {
        this.inflater = LayoutInflater.from(context);
        this.alcoholUnitList = alcoholUnitList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public AlcoholUnitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View itemView = inflater.inflate(R.layout.unit_list_item, parent, false);
        return new AlcoholUnitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlcoholUnitViewHolder viewHolder, int position) {
        AlcoholUnit alcoholUnitToDisplay = alcoholUnitList.get(position);
        viewHolder.setAlcoholUnit(alcoholUnitToDisplay, clickListener);
    }

    @Override
    public int getItemCount() {
        return alcoholUnitList.size();
    }

    public static class AlcoholUnitViewHolder extends RecyclerView.ViewHolder {

        private TextView unitName, unitNumber, unitPercent, unitAmount;
        //private Button btnDelete;

        public AlcoholUnitViewHolder(@NonNull View itemView) {
            super(itemView);

            unitName = itemView.findViewById(R.id.unitName);
            //unitNumber = itemView.findViewById(R.id.unitNumber);
            unitPercent = itemView.findViewById(R.id.unitPercent);
            unitAmount = itemView.findViewById(R.id.unitAmount);
            //btnDelete = itemView.findViewById(R.id.btnDelete);
        }

        public void setAlcoholUnit(AlcoholUnit alcoholUnitToDisplay, View.OnClickListener clickListener) {
            unitName.setText(alcoholUnitToDisplay.getName());
            //unitNumber.setText(alcoholUnitToDisplay.get());
            unitPercent.setText(String.format("%s%%" , alcoholUnitToDisplay.getPercent()));
            unitAmount.setText(String.format("%s %s", alcoholUnitToDisplay.getAmount(), alcoholUnitToDisplay.getAmountType() ));
            itemView.setOnClickListener(clickListener);
        }
    }
}
