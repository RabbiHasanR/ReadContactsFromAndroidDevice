package com.example.readcontacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CallLogsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CallLogsModel> callLogsModelArrayList;

    public CallLogsAdapter(Context context, ArrayList<CallLogsModel> callLogsModelArrayList) {

        this.context = context;
        this.callLogsModelArrayList=callLogsModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return callLogsModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
            return callLogsModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CallLogsAdapter.ViewHolder holder;

        if (convertView == null) {
            holder = new CallLogsAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.call_logs_item, null, true);

            holder.tvnumber = (TextView) convertView.findViewById(R.id.number);
            holder.tvtype = (TextView) convertView.findViewById(R.id.type);
            holder.tvdate = (TextView) convertView.findViewById(R.id.time);
            holder.tvduration = (TextView) convertView.findViewById(R.id.duration);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (CallLogsAdapter.ViewHolder)convertView.getTag();
        }
            holder.tvnumber.setText(callLogsModelArrayList.get(position).getNumber());
        holder.tvtype.setText(callLogsModelArrayList.get(position).getType());
        holder.tvdate.setText(String.valueOf(callLogsModelArrayList.get(position).getDate()));
        holder.tvduration.setText(callLogsModelArrayList.get(position).getDuration());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvnumber,tvtype,tvdate,tvduration;

    }
}
