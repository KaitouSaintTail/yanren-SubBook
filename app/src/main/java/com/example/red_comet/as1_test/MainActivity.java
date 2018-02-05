package com.example.red_comet.as1_test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private ListView subListView;


    private ArrayList<subin> subArrayList = new ArrayList<subin>();
    private ArrayAdapter<subin> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        subListView = (ListView) findViewById(R.id.subListView);
        subListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    final View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, subdetailActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);

            }
        });

    }
    public void addNewsub(View view) {
        Intent intent = new Intent(this, AddNewsubActivity.class);

        startActivity(intent);
    }
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();


        // reference: Java for Android  2nd Edition by Budi Kurniavan Chinese version Chapter 31.2
        adapter = new ArrayAdapter<subin>(this,
                android.R.layout.simple_list_item_1, subArrayList);
        //R.layout.list_item, counters);
        subListView.setAdapter(adapter);

        TextView countersTitle = (TextView) findViewById(R.id.countersTitle);
        String numberOfCounters = "Number of Subscription:" + subListView.getAdapter().getCount();
        countersTitle.setText(numberOfCounters);
}
    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<subin>>() {
            }.getType();
            subArrayList = gson.fromJson(in, listType);

            //google/gson/blob/master

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            subArrayList = new ArrayList<subin>();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(subArrayList, writer);
            writer.flush();


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
