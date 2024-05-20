package com.tabish.finance_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class TransactionAdapter extends BaseAdapter {

    private Context context;
    private List<Transaction> TransactionList;

    public TransactionAdapter(Context context, List<Transaction> transactionList) {
        this.context = context;
        this.TransactionList =transactionList;
    }

    @Override
    public int getCount() {
        return TransactionList.size();
    }

    @Override
    public Object getItem(int position) {
        return TransactionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_view_choose, parent, false);
            holder = new ViewHolder();
            holder.listImage = convertView.findViewById(R.id.listImage);
            holder.listName = convertView.findViewById(R.id.listName);
            holder.listAmount = convertView.findViewById(R.id.list_amount);
            holder.listTime = convertView.findViewById(R.id.listTime);
            holder.listNote = convertView.findViewById(R.id.listnote);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Transaction transaction = TransactionList.get(position);
        holder.listName.setText(transaction.getName());
        holder.listAmount.setText(String.valueOf(transaction.getAmount()));
        holder.listTime.setText(transaction.getSdate());
        holder.listImage.setImageResource(transaction.getImage());
        // Additional fields can be set similarly

        return convertView;
    }

    static class ViewHolder {
        ShapeableImageView listImage;
        TextView listName, listAmount, listTime, listNote;
    }
}
