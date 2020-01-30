package com.example.patternbuilderfactory.RecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.patternbuilderfactory.AbstractCommons.AntivirusSoftware;
import com.example.patternbuilderfactory.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private List<AntivirusSoftware> mAntivirusSoftwares;


    public MyRecyclerViewAdapter(List<AntivirusSoftware> mAntivirusSoftwares) {
        this.mAntivirusSoftwares = mAntivirusSoftwares;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final AntivirusSoftware antivirusSoftware = mAntivirusSoftwares.get(position);
        holder.mNameTextView.setText(antivirusSoftware.getName());
        holder.mSubtitleTextView.setText(antivirusSoftware.getInfo());


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                holder.progressBar.setVisibility(View.INVISIBLE);
            }
        }).start();
    }


    @Override
    public int getItemCount() {
        return mAntivirusSoftwares.size();
    }

    public void clearAll() {
        mAntivirusSoftwares.clear();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameTextView;
        private TextView mSubtitleTextView;
        private ProgressBar progressBar;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.textViewLarge);
            mSubtitleTextView = itemView.findViewById(R.id.textViewSmall);
            progressBar = itemView.findViewById(R.id.progressBar);


        }

    }
}
//    do {
//            WindowManager.LayoutParams.mayUseInputMethod(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//            }while(holder.progressBar.getVisibility()==View.VISIBLE);


