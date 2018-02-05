package com.example.red_comet.as1_test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

/**
 * Created by Red_Comet on 2018/2/5.
 */

public class EditsubActivity extends AppCompatActivity{
    private static final String FILENAME = "file.sav";
    private ListView subListView;


    private ArrayList<subin> subArrayList = new ArrayList<subin>();
    private ArrayAdapter<subin> adapter;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_sub);

            /**
             * reference https://stackoverflow.com/questions/5644543/passing-listview-row-positions-through-intents-to-another-class
             */
            Intent intent = getIntent();
            final int position = intent.getIntExtra("position", 0);
            loadFromFile();


            final EditText nameEditText = (EditText) findViewById(R.id.nameEditText);               //counter name
            final EditText valueEditText = (EditText) findViewById(R.id.valueEditText);             //current value
            final EditText editText = (EditText) findViewById(R.id.editText);                       // comment
            final Button button3 = (Button) findViewById(R.id.button3);


            final subin counter = subArrayList.get(position);
            String name = counter.getName();
            String comment = counter.getComment();
            int currentValue = counter.getValue();



            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setResult(RESULT_OK);

                    counter.setName(nameEditText.getText().toString());
                    counter.setComment(editText.getText().toString());
                    counter.setDate();
                    counter.setValue(Integer.parseInt(valueEditText.getText().toString()));

                    subArrayList.set(position, counter);

                    saveInFile();
                    finish();
                }
            });


        }


        /**
         * load file, code from CMPUT301 Lab lonelytwitter source codes
         */

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

    /**
     * save file after adding new counter,code from CMPUT301 Lab lonelytwitter source codes
     */
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

