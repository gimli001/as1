package com.example.xinghua1.xinghua1__fueltrack;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ViewListActivity extends ActionBarActivity {
    private static final String FILENAME = "file.sav";
    private ListView oldTweetsList;
    private ArrayList<Initialization> Initializations = new ArrayList<Initialization>();
    private ArrayAdapter<Initialization> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        oldTweetsList = (ListView) findViewById(R.id.listView);
    // set button for delete
        oldTweetsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                AlertDialog.Builder ADBuilder = new AlertDialog.Builder(ViewListActivity.this);
                ADBuilder.setMessage("Do you want to delete it?");
                ADBuilder.setCancelable(true);
                final int finalpos = pos;
                //yes button for remove the data
                ADBuilder.setPositiveButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Initializations.remove(finalpos);
                        adapter.notifyDataSetChanged();
                        saveInFile();
                    }
                });
                //no button for cancel
                ADBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                });
                ADBuilder.show();
            }
        });
 //long click button to make edit to the existing entry
        oldTweetsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                AlertDialog.Builder ADBuilder = new AlertDialog.Builder(ViewListActivity.this);
                ADBuilder.setMessage("Do you want to edit it?");
                ADBuilder.setCancelable(true);
                final int finalpos = pos;
                //yes button for editing
                ADBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(ViewListActivity.this, EditActivity.class);
                        intent.putExtra("finalpos", finalpos);
                        startActivity(intent);
                    }

                });
                // no button for cancel editing for this entry
                ADBuilder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                });
                ADBuilder.show();

                return false;
            }
        });

    }
        @Override
    protected void onStart() {
            super.onStart();
        //String[] tweets = loadFromFile();
            loadFromFile();
        adapter = new ArrayAdapter<Initialization>(this,R.layout.adapterlist, Initializations);
        oldTweetsList.setAdapter(adapter);
    }
    //get laodFromFile method code from the lab1
    // https://github.com/gimli001/lonelyTwitter/tree/w16Tuesday/app/src/main/java/ca/ualberta/cs/lonelytwitter
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
