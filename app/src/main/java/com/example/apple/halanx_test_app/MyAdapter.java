package com.example.apple.halanx_test_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Listitem> listitems;
    private Context context;

    public MyAdapter(List<Listitem> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listitem,viewGroup,false);
        return new ViewHolder(v);




    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Listitem listitem=listitems.get(i);

        viewHolder.textViewname.setText(listitem.getName());
        viewHolder.textViewrent.setText(listitem.getRent_from());
        viewHolder.textViewdeposite.setText(listitem.getSecurity_deposit_from());
        viewHolder.textViewaccomd.setText(listitem.getAccomodation_allowed_str());
        viewHolder.textViewbed.setText(listitem.getAvailable_bed_count()+" Beds");

        String state_city= listitem.getCity()+","+listitem.getState();
        viewHolder.textViewcity.setText(state_city);










        // Picasso library to load image in ImageView
        Picasso.with(context)
                .load(listitem.getCover_pic_url())
                .into(viewHolder.imageView);


    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewname,textViewcity,textViewstate,textViewrent,textViewdeposite,textViewaccomd,textViewbed;
        public ImageView imageView;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewname= (TextView) itemView.findViewById(R.id.name_tv);
            textViewcity= (TextView) itemView.findViewById(R.id.city_tv);
            textViewrent= (TextView) itemView.findViewById(R.id.rent_tv);
            textViewdeposite= (TextView) itemView.findViewById(R.id.deposite_tv);
            textViewaccomd= (TextView) itemView.findViewById(R.id.accomd_tv);
            textViewbed= (TextView)itemView.findViewById(R.id.bed_tv);

            imageView =(ImageView) itemView.findViewById(R.id.image_View);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
