package com.unipi.diodeir.unipi_audio_stories;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;

    ArrayList<ListItems> list;

    public MyAdapter(Context context, ArrayList<ListItems> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_items,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ListItems listItems = list.get(position);
        LanguageManager languageManager = new LanguageManager(context);
        languageManager.updateResource(languageManager.getLang());
        if (languageManager.getLang().equals("en")){
            holder.Title.setText(listItems.getTitle_ENG());
        }
        else if (languageManager.getLang().equals("el")){
            holder.Title.setText(listItems.getTitle_GR());
        }
        else if (languageManager.getLang().equals("fr")){
            holder.Title.setText(listItems.getTitle_FR());
        }
        Picasso.get().load(listItems.getImage_Url()).fit().centerCrop().into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Story.class);
                intent.putExtra("Title",listItems.getTitle_ENG());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Title;
        ImageView imageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.Title);
            imageView = itemView.findViewById(R.id.imageView3);
        }
    }
}
