package com.example.xinghua1.xinghua1__fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EditActivity extends ActionBarActivity {
    private static final String FILENAME = "file.sav";
    private EditText dateText;
    private EditText stationText;
    private EditText odometerText;
    private EditText fuelgText;
    private EditText fuelaText;
    private EditText fueluText;
    private  int finalpos;

    private ArrayList<Initialization> Initializations = new ArrayList<Initialization>();
    private ArrayAdapter<Initialization> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_entry);

        //get all the data from the same layout
        dateText = (EditText) findViewById(R.id.date);
        stationText = (EditText) findViewById(R.id.station);
        odometerText = (EditText) findViewById(R.id.odometer);
        fuelgText = (EditText) findViewById(R.id.fuelgrade);
        fuelaText = (EditText) findViewById(R.id.fuelamount);
        fueluText = (EditText) findViewById(R.id.fuelunitcost);
        Button saveButton = (Button) findViewById(R.id.save);
        Button cancelButton = (Button) findViewById(R.id.cancel);

        finalpos = getIntent().getExtras().getInt("finalpos");
        //save button for editing
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date datetext = null;
                //remove th entry first
                Initializations.remove(finalpos);
                setResult(RESULT_OK);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    datetext = df.parse(dateText.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String stationtext = stationText.getText().toString();
                String odometertext = odometerText.getText().toString();
                String fuelgtext = fuelgText.getText().toString();
                String fuelatext = fuelaText.getText().toString();
                String fuelutext = fueluText.getText().toString();
                Initialization latestEntry = new Initialization(datetext,stationtext,odometertext,fuelgtext,fuelatext,fuelutext);

                // after editing add the entry again
                Initializations.add(finalpos,latestEntry);
                adapter.notifyDataSetChanged();
                saveInFile();

            }
        });
        //cancel button for editing
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditActivity.this, ViewListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // load data  at the beginning
        loadFromFile();
        adapter = new ArrayAdapter<Initialization>(this,R.layout.adapterlist, Initializations);

        String dates = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dat = Initializations.get(finalpos).getDate();
        try{
            dates = df.format(dat);;
        }catch (Exception e) {
        }
        // use the class method to get all the original data
        String station = Initializations.get(finalpos).getStation();
        String odometer = String.valueOf(Initializations.get(finalpos).getOdometer());
        String fuelg = Initializations.get(finalpos).getFuelGrade();
        String fuela = String.valueOf(Initializations.get(finalpos).getFuelAmount());
        String fuelu = String.valueOf(Initializations.get(finalpos).getFuelUnitCost());

        dateText.setText(dates);
        stationText.setText(station);
        odometerText.setText(odometer);
        fuelgText.setText(fuelg);
        fuelaText.setText(fuela);
        fueluText.setText(fuelu);
    }
    //get loadFromfile  method code from the lab1
    //https:github.com/gimli001/lonelyTwitter/tree/w16Tuesday/app/src/main/java/ca/ualberta/cs/lonelytwitter
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 01-19 2016
            Type listType = new TypeToken<ArrayList<Initialization>>() {}.getType();
            Initializations = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Initializations = new ArrayList<Initialization>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
    //get saveInfile  method code from the lab1

    //https://github.com/gimli001/lonelyTwitter/tree/w16Tuesday/app/src/main/java/ca/ualberta/cs/lonelytwitter
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(Initializations, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
