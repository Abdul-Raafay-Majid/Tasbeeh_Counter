package com.example.tasbeehcounter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class PldAdapter extends ListAdapter<PreLoadedDikhr, PldAdapter.PldHolder> {
    private OnItemClickListener listener;

    protected PldAdapter(@NonNull DiffUtil.ItemCallback<PreLoadedDikhr> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public PldHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PldHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pld_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PldHolder holder, int position) {
        PreLoadedDikhr current_pld=getItem(position);
        holder.Bind(current_pld);

    }

    class  PldHolder extends RecyclerView.ViewHolder{
        private TextView dikhr_pld;
        private TextView count_pld;
        private ImageButton resumeBtn;
        private ImageButton deleteBtn;

        public PldHolder(@NonNull View itemView) {
            super(itemView);
            dikhr_pld=itemView.findViewById(R.id.dikhr_pld);
            count_pld=itemView.findViewById(R.id.count_pld);
            resumeBtn=itemView.findViewById(R.id.pld_resume_button);
            deleteBtn=itemView.findViewById(R.id.pld_delete_button);

            resumeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    listener.onResumeClick(getItem(position));
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    listener.onDeleteClick(getItem(position));
                }
            });


        }

        public void Bind(PreLoadedDikhr pld){
            dikhr_pld.setText(pld.getPdikhr());
            count_pld.setText(String.valueOf(pld.getPcount()));
        }
    }

    public interface OnItemClickListener{
        void onResumeClick(PreLoadedDikhr pld);
        void onDeleteClick(PreLoadedDikhr pld);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    };
}
