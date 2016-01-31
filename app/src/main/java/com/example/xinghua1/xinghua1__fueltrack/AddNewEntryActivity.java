package com.example.xinghua1.xinghua1__fueltrack;

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

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.support.v7.app.ActionBarActivity;

public class AddNewEntryActivity extends ActionBarActivity {
    private static final String FILENAME = "file.sav";
    private EditText dateText;
    private EditText stationText;
    private EditText odometerText;
    private EditText fuelgText;
    private EditText fuelaText;
    private EditText fueluText;



    private ArrayList<Initialization> Initializations = new ArrayList<Initialization>();
    private ArrayAdapter<Initialization> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_entry);
     // variables for  get the all the data on the layout
        dateText = (EditText) findViewById(R.id.date);
        stationText = (EditText) findViewById(R.id.station);
        odometerText = (EditText) findViewById(R.id.odometer);
        fuelgText = (EditText) findViewById(R.id.fuelgrade);
        fuelaText = (EditText) findViewById(R.id.fuelamount);
        fueluText = (EditText) findViewById(R.id.fuelunitcost);

        Button saveButton = (Button) findViewById(R.id.save);
        Button cancelButton = (Button) findViewById(R.id.cancel);





        //button for save data
        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Date datetext = null;
                setResult(RESULT_OK);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                try {
                   datetext = df.parse(dateText.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //change to the string form of all the data
                String stationtext = stationText.getText().toString();
                String odometertext = odometerText.getText().toString();
                String fuelgtext = fuelgText.getText().toString();
                String fuelatext = fuelaText.getText().toString();
                String fuelutext = fueluText.getText().toString();
                Initialization latestEntry = new Initialization(datetext,stationtext,odometertext,fuelgtext,fuelatext,fuelutext);

                // clear screen after saving
                Initializations.add(latestEntry);
                adapter.notifyDataSetChanged();
                saveInFile();
                dateText.getText().clear();
                stationText.getText().clear();
                odometerText.getText().clear();
                fuelgText.getText().clear();
                fuelaText.getText().clear();
                fueluText.getText().clear();



            }
        });
        //cancel adding
        cancelButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);

                dateText.getText().clear();
                stationText.getText().clear();
                odometerText.getText().clear();
                fuelgText.getText().clear();
                fuelaText.getText().clear();
                fueluText.getText().clear();


            }
        });

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Initialization>(this,
                R.layout.adapterlist, Initializations);


        //String[] tweets = loadFromFile();

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
    //https:github.com/gimli001/lonelyTwitter/tree/w16Tuesday/app/src/main/java/ca/ualberta/cs/lonelytwitter
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
