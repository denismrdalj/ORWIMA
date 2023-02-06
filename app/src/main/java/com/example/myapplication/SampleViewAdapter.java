package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SampleViewAdapter extends RecyclerView.Adapter<SampleViewAdapter.MyViewHolder>{
    private ArrayList<Track> tracks;
    private ItemClickListener mListener;


    public SampleViewAdapter(ArrayList<Track> tracks, ItemClickListener clickListener) {
        this.tracks=tracks;
        this.mListener=clickListener;
    }

    @NonNull
    @Override
    public SampleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_mini,parent,false);
        return new MyViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SampleViewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(tracks.get(0)!=null) {
            holder.imageView.setImageResource(tracks.get(position).getImage());
            holder.tvTitle.setText(tracks.get(position).getTitle());
            holder.tvArtist.setText(tracks.get(position).getArtist());
            holder.tvBpm.setText(String.valueOf(tracks.get(position).getBpm()));
            holder.tvBpm1.setText("BPM");
            holder.tvKey.setText(tracks.get(position).getKey());
            holder.tvKey1.setText("KEY");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(tracks.get(position));
                }
            });
        }
        if(tracks.get(0)==null)

            holder.tvNull.setText("Track contains original samples.");
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvArtist, tvTitle, tvBpm, tvKey, tvBpm1, tvKey1, tvNull;
        ItemClickListener mListener;

        public MyViewHolder(@NonNull View itemView, ItemClickListener mListener) {
            super(itemView);
            this.mListener=mListener;
            imageView=itemView.findViewById(R.id.imageViewTrack);
            tvArtist=itemView.findViewById(R.id.tv_artist);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvBpm=itemView.findViewById(R.id.tv_bpm);
            tvBpm1=itemView.findViewById(R.id.tv_bpm1);
            tvKey=itemView.findViewById(R.id.tv_key);
            tvKey1=itemView.findViewById(R.id.tv_key1);
            tvNull=itemView.findViewById(R.id.null_message);
        }
    }

    public interface ItemClickListener{
        void onItemClick(Track track);
    }

    @Override
    public int getItemCount() { return tracks.size(); }


}


