package com.example.tasbeehcounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    public static final String EXTRA_WORD="com.example.tasbeehcounter.EXTRA_WORD";
    public static final String EXTRA_COUNT="com.example.tasbeehcounter.EXTRA_COUNT";
    public static final String EXTRA_DIKHR_ID="com.example.tasbeehcounter.EXTRA_DIKHR_ID";
    public static final String EXTRA_DIKHR_ITEM_ID="com.example.tasbeehcounter.EXTRA_DIKHR_ITEM_ID";
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        DikhrAdapter adapter= new DikhrAdapter();
        DikhrViewModel dikhrViewModel= new ViewModelProvider(this).get(DikhrViewModel.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        dikhrViewModel.getAllDikhrs().observe(this, new Observer<List<Dikhr>>() {
            @Override
            public void onChanged(List<Dikhr> dikhrs) {
                adapter.submitList(dikhrs);
            }
        });

        adapter.SetOnItemClickListener(new DikhrAdapter.OnItemClickListener() {
            @Override
            public void onResumeClick(Dikhr dikhr) {
                Intent intent= new Intent(MainActivity2.this, MainActivity.class);
                intent.putExtra(EXTRA_WORD,dikhr.getWord());
                intent.putExtra(EXTRA_COUNT,dikhr.getCount());
                intent.putExtra(EXTRA_DIKHR_ID,2);
                intent.putExtra(EXTRA_DIKHR_ITEM_ID,dikhr.getId());
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(Dikhr dikhr) {
                dikhrViewModel.delete(dikhr);
            }
        });

    }
}