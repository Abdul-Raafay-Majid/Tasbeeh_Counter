package com.example.tasbeehcounter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class DikhrAdapter extends ListAdapter<Dikhr,DikhrAdapter.DikhrViewHolder> {
    private OnItemClickListener listener;


    public DikhrAdapter() {
        super(DIFF_CALLBACK);
    }

    public static DiffUtil.ItemCallback<Dikhr> DIFF_CALLBACK=new DiffUtil.ItemCallback<Dikhr>() {
        @Override
        public boolean areItemsTheSame(@NonNull Dikhr oldItem, @NonNull Dikhr newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Dikhr oldItem, @NonNull Dikhr newItem) {
            return oldItem.getDate().equals(newItem.getDate()) && oldItem.getWord().equals(newItem.getWord()) && oldItem.getCount()==newItem.getCount();
        }
    };



    @NonNull
    @Override
    public DikhrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DikhrViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dikhr_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DikhrViewHolder holder, int position) {
        Dikhr dikhr=getItem(position);
        holder.bind(dikhr);

    }

    class DikhrViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView wordTextView;
        TextView countTextView;
        ImageButton resumeButton;
        ImageButton deleteButton;


        public DikhrViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView=itemView.findViewById(R.id.date_textView);
            wordTextView=itemView.findViewById(R.id.word_textView);
            countTextView=itemView.findViewById(R.id.count_textView);
            resumeButton=itemView.findViewById(R.id.resume_button);
            deleteButton=itemView.findViewById(R.id.delete_button);

            resumeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    if(listener!=null && position!=RecyclerView.NO_POSITION){
                    listener.onResumeClick(getItem(position));
                    }
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    if(listener!=null && position!=RecyclerView.NO_POSITION){
                        listener.onDeleteClick(getItem(position));
                    }

                }
            });
        }

        public void bind(Dikhr dikhr){
            dateTextView.setText(dikhr.getDate());
            wordTextView.setText(dikhr.getWord());
            int count=dikhr.getCount();
            countTextView.setText(String.valueOf(dikhr.getCount()));
        }
    }

    public interface OnItemClickListener{
        void onResumeClick(Dikhr dikhr);
        void onDeleteClick(Dikhr dikhr);
    }

    public void SetOnItemClickListener(OnItemClickListener listener){this.listener=listener;}
}
