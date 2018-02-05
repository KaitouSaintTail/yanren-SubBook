package com.example.red_comet.as1_test;

import android.content.Context;
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

public class AddNewsubActivity extends AppCompatActivity{

    private static final String FILENAME = "file.sav";
    private ListView subListView;


    private ArrayList<subin> subArrayList = new ArrayList<subin>();
    private ArrayAdapter<subin> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_sub);
        loadFromFile();

        final EditText counterNameEditText = (EditText) findViewById(R.id.counterNameEditText);
        final EditText setInitialValueEditText = (EditText) findViewById(R.id.setInitialValueEditText);
        final EditText commenntEditText = (EditText) findViewById(R.id.commenntEditText);
        final Button button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // When an activity exits, it can call setResult(int), int RESULT_OK
                setResult(RESULT_OK);

                String name = counterNameEditText.getText().toString();

                // reference https://stackoverflow.com/questions/15037465/converting-edittext-to-int-android answer by Harshid
                int initialValue = Integer.parseInt(setInitialValueEditText.getText().toString());

                // if no comment was provided, would add empty comment
                String comment = commenntEditText.getText().toString();
                subArrayList.add(new subin(name, comment, initialValue));
                saveInFile();

                finish();
            }
        });}





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



