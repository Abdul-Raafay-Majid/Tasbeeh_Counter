package com.example.tasbeehcounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PldActivity extends AppCompatActivity {
    private PldAdapter pldAdapter;
    FloatingActionButton add_pld;
    Dialog pld_Dialog;
    DikhrViewModel dikhrViewModel;
    public static final String EXTRA_DIKHR_TITLE=" com.example.tasbeehcounter.EXTRA_DIKHR_TITLE";
    public static final String EXTRA_DIKHR_COUNT=" com.example.tasbeehcounter.EXTRA_DIKHR_COUNT";
    public static final String EXTRA_DIKHR_PLD_ID=" com.example.tasbeehcounter.EXTRA_DIKHR_PLD_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pld);
        add_pld=findViewById(R.id.add_pldButton);
        pld_Dialog=new Dialog(this);
        RecyclerView pldRecyclerView=findViewById(R.id.pld_recyclerView);
        pldAdapter= new PldAdapter(PreLoadedDikhr.itemCallback);
        dikhrViewModel= new ViewModelProvider(this).get(DikhrViewModel.class);
        pldRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pldRecyclerView.setAdapter(pldAdapter);
        dikhrViewModel.getAllPld().observe(this, new Observer<List<PreLoadedDikhr>>() {
            @Override
            public void onChanged(List<PreLoadedDikhr> preLoadedDikhrs) {
                    pldAdapter.submitList(preLoadedDikhrs);
            }
        });

        pldAdapter.setOnItemClickListener(new PldAdapter.OnItemClickListener() {
            @Override
            public void onResumeClick(PreLoadedDikhr pld) {
                Intent intent=new Intent(PldActivity.this,MainActivity.class);
                intent.putExtra(EXTRA_DIKHR_TITLE,pld.getPdikhr());
                intent.putExtra(EXTRA_DIKHR_COUNT,pld.getPcount());
                intent.putExtra(EXTRA_DIKHR_PLD_ID,1);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(PreLoadedDikhr pld) {
                dikhrViewModel.deletePld(pld);

            }
        });

        add_pld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPldDialog();
            }
        });
    }

    public void openPldDialog() {
        pld_Dialog.setContentView(R.layout.add_pld_dialog);
        pld_Dialog.getWindow().setBackgroundDrawableResource(R.drawable.pld_dialog_background);
        Button okButton = pld_Dialog.findViewById(R.id.pld_okButton);
        ImageButton closeButton = pld_Dialog.findViewById(R.id.pld_close_button);
        EditText countEditText = pld_Dialog.findViewById(R.id.add_countEditText);
        EditText dikhrEditText = pld_Dialog.findViewById(R.id.add_dikhrEditText);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dikhr=dikhrEditText.getText().toString();
                int count=Integer.parseInt(countEditText.getText().toString());
                if(!dikhr.isEmpty() && count>0) {
                    dikhrViewModel.insertPld(new PreLoadedDikhr(dikhr, count));
                    pld_Dialog.dismiss();
                }
                else{
                    Toast.makeText(PldActivity.this, "Please Enter Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pld_Dialog.dismiss();
            }
        });
        pld_Dialog.show();

    }
}