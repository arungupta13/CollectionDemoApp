package com.example.demoproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> implements Filterable {
    private List<EmployeeData> employeeData;
    private List<EmployeeData> employeeDataFull;

    EmployeeAdapter(List<EmployeeData> employeeData) {
        this.employeeData = employeeData;
        employeeDataFull = new ArrayList<>(employeeData);
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, int position) {
        holder.textView.setText(employeeDataFull.get(position).getName());
        holder.imageView.setImageResource(employeeDataFull.get(position).getImgId());

    }

    @Override
    public int getItemCount() {
        if(employeeData != null){
            return employeeDataFull.size();
        } else {
            return 0;
        }
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    employeeDataFull = employeeData;
                } else {
                    List<EmployeeData> filteredList = new ArrayList<>();
                    for (EmployeeData data : employeeData) {
                        if (data.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(data);
                        }
                    }
                    employeeDataFull = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = employeeDataFull;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                employeeDataFull = (ArrayList<EmployeeData>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }

    public void onCloseSearchView(){
        employeeDataFull=employeeData;
        notifyDataSetChanged();
    }



}
