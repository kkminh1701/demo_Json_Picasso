package com.example.ka.demo_json_picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends ArrayAdapter<CaSi> {


    public ListAdapter(Context context, int resource, List<CaSi> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.item_list, null);
        }
        CaSi p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView text_view_HoTen = view.findViewById(R.id.text_view_HoTen);
            text_view_HoTen.setText(p.HoTen);
            TextView text_view_NamSinh = view.findViewById(R.id.text_view_NamSinh);
            text_view_NamSinh.setText(p.NamSinh+"");
            TextView text_view_Email = view.findViewById(R.id.text_view_Email);
            text_view_Email.setText(p.Email);

            ImageView imageView = view.findViewById(R.id.imageView);
            Picasso.with(getContext()).load(p.Hinh).into(imageView);

        }
        return view;
    }

}
