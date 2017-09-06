package com.example.umar.fragment_tab;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final String FILENAME = "MYFILE_2";
    private static final String TAG = "MainActivity";
    private SectionsPageAdapeter mSectionPageAdapter;
    ImageButton plusBtn;
    private ViewPager viewPager;
    static  final String SEPARATOR = "`";
    public static ArrayList<String> mList = new ArrayList<>();
    public static final int mListSize = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting");
//        plusBtn = (ImageButton)findViewById(R.id.imageButton);
//        plusBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        mSectionPageAdapter = new SectionsPageAdapeter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, addNewContacts.class));
            }
        });

            readFile();

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapeter adapter = new SectionsPageAdapeter(getSupportFragmentManager());
        adapter.addFragment(new tab1_fragment(), "Contacts");
        adapter.addFragment(new tab2_fragment(), "Favorities");
        adapter.addFragment(new tab3_fragment(), "Recent View");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveRecentFile(mList);
    }

    private void saveRecentFile(ArrayList<String> mlist) {
        String string = "";
        if( mList.size() != 0 ) {
            for (int i = 0; i < MainActivity.mList.size() ; i++) {
                string = string + mlist.get(i) + ",";
            }
        }
        if (string.length() > 0 ){
            string = string.substring(0 , string.length()-1);
        }

        FileOutputStream outputStream;
        try {
            File file = new File(getApplicationContext().getFilesDir(), FILENAME);
            if (file.exists()) {
                Toast.makeText(getApplicationContext() , "On Destory " , Toast.LENGTH_SHORT ).show();
                file.delete();
            } else {
                Toast.makeText(getApplicationContext() , "Not Exist " , Toast.LENGTH_SHORT ).show();
            }

            Toast.makeText(getApplicationContext() , " File Created " , Toast.LENGTH_SHORT ).show();
            file.createNewFile();
            outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readFile() {
        try {
            File file = new File(getApplicationContext().getFilesDir(), FILENAME);
            if (file.exists()) {
                ArrayList<String> list = new ArrayList<>();
                FileInputStream fis = openFileInput(FILENAME);
                int read = -1;
                StringBuffer buffer = new StringBuffer();
                while ((read = fis.read()) != -1) {
                    buffer.append((char) read);
                }
                if (buffer.toString().length() != 0) {
                    Toast.makeText(getApplicationContext() , " Read File " , Toast.LENGTH_SHORT ).show();
                    String[] mString = buffer.toString().split(",");
                    for (int i = 0; i < mString.length; i++) {
                        list.add(mString[i]);
                    }
                    fis.close();
                    mList = list;
                    return;
                }
                fis.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
        }
        return;
    }


}
