package com.diequint.keeppass;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListElement> myData;
    private LayoutInflater myInflater;
    private Context context;

    public ListAdapter(List<ListElement> itemList, Context context) {
        this.myInflater = LayoutInflater.from(context);
        this.context = context;
        this.myData = itemList;
    }

    @Override
    public int getItemCount() { return myData.size(); }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = myInflater.inflate(R.layout.fragment_list_element,null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(myData.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView serviceName, userName, siteURL;

        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            serviceName = itemView.findViewById(R.id.serviceName);
            userName = itemView.findViewById(R.id.userName);
            siteURL = itemView.findViewById(R.id.siteURL);
        }

        void bindData(final ListElement item) {
            //iconImage.setColorFilter(Color.parseColor(item.getColour()), PorterDuff.Mode.SRC_IN);
            iconImage.setBackgroundColor(Color.parseColor(item.getColour()));
            serviceName.setText(item.getServiceName());
            userName.setText(item.getUserName());
            siteURL.setText(item.getSiteURL());
        }
    }
}
