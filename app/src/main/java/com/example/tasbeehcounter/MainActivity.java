package com.example.tasbeehcounter;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageButton resetButton;
    Button incrementButton;
    ImageButton decrementButton;
    FloatingActionButton progressButton;
    FloatingActionButton counterLimiterButton;
    Button saveButton;
    FloatingActionButton view_dikhrButton;
    TextView dikhrTextView;
    TextView counterTextView;
    TextView stopperLimit;
    TextView limit_textView;
    private int counter;
    private int dikhr_count;
    private int counter_limit=10000;
    private Dialog stopperDialog;
    ActivityResultLauncher<Intent> launchDikhr = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {

        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DikhrViewModel dikhrViewModel = new ViewModelProvider(this).get(DikhrViewModel.class);

        saveButton = findViewById(R.id.save_button);

        view_dikhrButton = findViewById(R.id.view_dikhrButton);

        resetButton = findViewById(R.id.reset_button);

        counterLimiterButton = findViewById(R.id.counter_limiter_button);

        incrementButton = findViewById(R.id.increment_button);

        progressButton = findViewById(R.id.progress_button);

        decrementButton = findViewById(R.id.decrement_button);

        counterTextView = findViewById(R.id.counter_textView);

        dikhrTextView = findViewById(R.id.dikhr_textView);

        limit_textView = findViewById(R.id.limit_textView);

        stopperLimit = findViewById(R.id.stopperLimit_textView);

        stopperDialog = new Dialog(this);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 0;
                counterTextView.setText(String.valueOf(counter));

            }
        });

        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counterTextView.getVisibility()==View.INVISIBLE){
                    counterTextView.setVisibility(View.VISIBLE);
                }
                counter++;
                if (counter == counter_limit) {
                    showCustomToast();
                }
                counterTextView.setText(String.valueOf(counter));
            }
        });

        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter>0) {
                    counter--;
                    counterTextView.setText(String.valueOf(counter));
                }
                else{
                    Toast.makeText(MainActivity.this, "Cannot go below 0", Toast.LENGTH_SHORT).show();
                }
            }
        });

        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter == 0) {
                    Toast.makeText(MainActivity.this, "Not enough dikhr", Toast.LENGTH_SHORT).show();
                } else {

                    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    Calendar cal = Calendar.getInstance();
                    Date date = cal.getTime();
                    String todaysdate = dateFormat.format(date);
                    String word = dikhrTextView.getText().toString();
                    int count = Integer.parseInt(counterTextView.getText().toString());
                    dikhrViewModel.insert(new Dikhr(todaysdate, word, counter - dikhr_count));
                    dikhr_count = 0;
                    Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    counter = 0;
                    counterTextView.setText(String.valueOf(counter));
                }
            }
        });


        view_dikhrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PldActivity.class);
                launchDikhr.launch(intent);

            }
        });

        counterLimiterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCounterDialog();


            }
        });


        Intent intent = getIntent();
        if (intent.getIntExtra(PldActivity.EXTRA_DIKHR_PLD_ID, 0) == 1) {
            dikhrTextView.setText(intent.getStringExtra(PldActivity.EXTRA_DIKHR_TITLE));
            int pld_count = intent.getIntExtra(PldActivity.EXTRA_DIKHR_COUNT, 0);
            counter = pld_count;
            stopperLimit.setText(String.valueOf(pld_count));
        } else if (intent.getIntExtra(MainActivity2.EXTRA_DIKHR_ID, 0) == 2) {
            dikhrTextView.setText(intent.getStringExtra(MainActivity2.EXTRA_WORD));
            dikhr_count = intent.getIntExtra(MainActivity2.EXTRA_COUNT, 0);
            counter = dikhr_count;
            counterTextView.setText(String.valueOf(dikhr_count));
        }


    }

    public void showCustomToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_limit_toast, (ViewGroup) findViewById(R.id.toast_root));
        final Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }

    public void openCounterDialog() {
        stopperDialog.setContentView(R.layout.limiter_dialog);
        stopperDialog.getWindow().setBackgroundDrawableResource(R.drawable.toast_background);
        Button okButton = stopperDialog.findViewById(R.id.ok_button);
        ImageButton closeButton = stopperDialog.findViewById(R.id.close_Button);
        EditText stopperEditText = stopperDialog.findViewById(R.id.stopper_editText);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(stopperEditText.getText().toString());
                 counter_limit = count;
                if (count == 0) {
                    limit_textView.setVisibility(View.INVISIBLE);
                    stopperLimit.setVisibility(View.INVISIBLE);
                } else {
                    stopperLimit.setText(String.valueOf(count));
                    limit_textView.setVisibility(View.VISIBLE);
                    stopperLimit.setVisibility(View.VISIBLE);
                }
                stopperDialog.dismiss();
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopperDialog.dismiss();
            }
        });
        stopperDialog.show();

    }


}