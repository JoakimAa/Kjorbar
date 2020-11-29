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

public class AlcoholUnitListRecyclerAdapter extends RecyclerView.Adapter<AlcoholUnitListRecyclerAdapter.AlcoholUnitViewHolder>{

    private List<AlcoholUnit> alcoholUnitList;
    private LayoutInflater inflater;
    private View.OnClickListener clickListener;

    public AlcoholUnitListRecyclerAdapter(Context context, List<AlcoholUnit> alcoholUnitList, View.OnClickListener clickListener) {
        this.inflater = LayoutInflater.from(context);
        this.alcoholUnitList = alcoholUnitList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public AlcoholUnitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.alcohol_unit_list_item, parent, false);
        return new AlcoholUnitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlcoholUnitViewHolder holder, int position) {
        AlcoholUnit alcoholUnitToDisplay = alcoholUnitList.get(position);
        holder.setAlcoholUnit(alcoholUnitToDisplay, clickListener);
    }

    @Override
    public int getItemCount() {
        return alcoholUnitList.size();
    }

    public static class AlcoholUnitViewHolder extends RecyclerView.ViewHolder {

        private TextView unitName, unitNumber, unitPercent, unitAmount;

        public AlcoholUnitViewHolder(@NonNull View itemView) {
            super(itemView);

            unitName = itemView.findViewById(R.id.unitName);
            unitPercent = itemView.findViewById(R.id.unitPercent);
            unitAmount = itemView.findViewById(R.id.unitAmount);
        }

        public void setAlcoholUnit(AlcoholUnit alcoholUnitToDisplay, View.OnClickListener clickListener) {
            unitName.setText(alcoholUnitToDisplay.getName());
            //unitNumber.setText(alcoholUnitToDisplay.get());
            unitPercent.setText(String.format("%s%% " , alcoholUnitToDisplay.getPercent()));
            unitAmount.setText(String.format("%s %s", alcoholUnitToDisplay.getAmount(), alcoholUnitToDisplay.getAmountType()));

            this.itemView.setOnClickListener(clickListener);
        }
    }
}
