package com.example.red_comet.as1_test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
import static com.example.red_comet.as1_test.R.id.positionTextView;
/**
 * Created by Red_Comet on 2018/2/5.
 */

public class subdetailActivity extends AppCompatActivity{

    private static final String FILENAME = "file.sav";
    private ListView subListView;


    private ArrayList<subin> subArrayList = new ArrayList<subin>();
    private ArrayAdapter<subin> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subdetail);
        Intent intent = getIntent();
        final int position = intent.getIntExtra("position", 0);
        loadFromFile();

        final subin subin = subArrayList.get(position);
        String name = subin.getName();
        String comment = subin.getComment();
        int currentValue = subin.getValue();

        // text views and buttons
        TextView counterName = (TextView) findViewById(R.id.counterName);
        TextView commentTextView = (TextView) findViewById(R.id.commentTextView);
        final TextView currentValueTextView = (TextView) findViewById(R.id.currentValueTextView);

        Button edit_button = (Button) findViewById(R.id.edit_button);
        Button delete_button = (Button) findViewById(R.id.delete_button);
        counterName.setText(name);
        commentTextView.setText(comment);
        TextView counterNameTextView = (TextView) findViewById(positionTextView);
        counterNameTextView.setText(String.valueOf(position));

        currentValueTextView.setText(String.valueOf(currentValue));

        delete_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_OK);
                //counters.set(position,counter);
                subArrayList.remove(position);
                saveInFile();
                finish();
            }
        });
        /**
         * reset counter and update date
         */

        edit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_OK);
                Intent intent = new Intent(subdetailActivity.this, EditsubActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
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
