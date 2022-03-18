package com.example.bottleui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView text;
    BottleDispenser dispenser = null;
    SeekBar seekBar;
    Spinner spinner;
    int sbMoney = 0;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dispenser = BottleDispenser.getInstance();
        text = (TextView) findViewById(R.id.textView2);
        ArrayList<Bottle> bottle_arraylist = dispenser.readDispenser();
        spinner = (Spinner) findViewById(R.id.spinner);
        context = MainActivity.this;
        dropdownMenu(spinner);

        seekBar = findViewById(R.id.seekBar);
        TextView seekbarView = findViewById(R.id.seekBarAmount);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onChange(SeekBar seekBar, int progress, boolean fromuser) {
                sbMoney = progress;
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromuser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String s = seekBar.getProgress()+ "€";
                seekbarView.setText(s);

            }
        });
    }

    public void addingMoney(View v){
        dispenser.addmoreMoney(text, seekBar.getProgress());
        seekBar.setProgress(0);
    }

    public void buySoda(View v){
        Bottle bottle = (Bottle) spinner.getSelectedItem();
        dispenser.buyBottle(text, bottle);
        try {
            OutputStreamWriter outstream = new OutputStreamWriter(context.openFileOutput("kuitti.txt", Context.MODE_PRIVATE));
            String s = "Kuitti\n" + bottle.getName()+ "\n" + bottle.getPrize() ;
            outstream.write(s);
            outstream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("IOException", "Virhe tiedostonkäsittelyssä");
        }
    }

    public void removeMoney(View v){
        dispenser.returnMoney(text);
    }

    public void dropdownMenu(Spinner spinner){
        ArrayList<Bottle> bottlelist = dispenser.readDispenser();
        ArrayAdapter<Bottle> data = new ArrayAdapter<Bottle>(MainActivity.this, android.R.layout.simple_spinner_item, bottlelist);
        data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(data);
    }

}