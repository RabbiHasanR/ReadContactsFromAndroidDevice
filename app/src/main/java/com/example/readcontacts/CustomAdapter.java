package com.example.readcontacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ContactModel> contactModelArrayList;
    private ArrayList<SmsModel> smsModelArrayList;
//    private boolean isContact;

    public CustomAdapter(Context context, ArrayList<ContactModel> contactModelArrayList,ArrayList<SmsModel> smsModelArrayList) {

        this.context = context;
        this.contactModelArrayList = contactModelArrayList;
//        this.isContact=isContact; ,boolean isContact
        this.smsModelArrayList=smsModelArrayList;
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
        if(contactModelArrayList!=null){
            return contactModelArrayList.size();
        }
       else {
            return smsModelArrayList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        if(contactModelArrayList!=null){
            return contactModelArrayList.get(position);
        }
        else {
            return smsModelArrayList.get(position);
        }

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_item, null, true);

            holder.tvname = (TextView) convertView.findViewById(R.id.name);
            holder.tvnumber = (TextView) convertView.findViewById(R.id.number);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }
        if(contactModelArrayList!=null){
            holder.tvname.setText(contactModelArrayList.get(position).getName());
            holder.tvnumber.setText(contactModelArrayList.get(position).getNumber());
        }
        else {
            holder.tvname.setText(smsModelArrayList.get(position).getAddress());
            holder.tvnumber.setText(smsModelArrayList.get(position).getMessage());
        }


        return convertView;
    }

    private class ViewHolder {

        protected TextView tvname, tvnumber;

    }
}
