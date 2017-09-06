package com.example.umar.fragment_tab;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.NoSuchElementException;

import static android.R.drawable.btn_star_big_off;
import static android.R.drawable.btn_star_big_on;

public class ShowContactINFO extends AppCompatActivity {
    Contact info = null;
    ImageView imageView_image;
    ImageView imageView_favourite;
    EditText info_name , info_phone , info_email , info_address;
    Button info_save_button;
    int index = -1;

    int favourite = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_info);
        imageView_image = (ImageView) findViewById(R.id.imageView_info);
        imageView_favourite = (ImageView) findViewById(R.id.imageView_favourite);

        info_name = (EditText)findViewById(R.id.editText_info_name);
        info_phone = (EditText)findViewById(R.id.editText_info_phone);
        info_email = (EditText)findViewById(R.id.editText_info_email);
        info_address = (EditText)findViewById(R.id.editText_info_address);
        info_save_button = (Button)findViewById(R.id.button_info_save);


        info_phone.setEnabled(false);




        if (getIntent() != null) {
            index = getIntent().getIntExtra("index", -1);
        }
        if (index != -1) {
            info = tab1_fragment.contacts.get(index);

            loadImage(info.getImage());
            favourite = info.getFavourite();
            info_name.setText(info.getName());
            info_phone.setText(info.getPhone());
            info_email.setText(info.getEmail());
            info_address.setText(info.getAddress());


//            Toast.makeText(getApplicationContext() , info.getEmail()+" have" , Toast.LENGTH_SHORT).show();

            if (favourite == 0) {
                imageView_favourite.setImageResource(btn_star_big_off);

            } else {
                imageView_favourite.setImageResource(btn_star_big_on);
            }
        }
        imageView_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favourite == 0) {
                    imageView_favourite.setImageResource(btn_star_big_on);
                    favourite = 1;
                    Log.e("favourite", " " + favourite + " ");
                    Log.e("User ID", " " + info.getID() + " ");
                    tab1_fragment.myBD.updateContactFavourite((Integer) info.getID(), favourite);
                    info.setFavourite(favourite);

                } else {
                    imageView_favourite.setImageResource(btn_star_big_off);
                    favourite = 0;
                    tab1_fragment.myBD.updateContactFavourite((Integer) info.getID(), favourite);
                    info.setFavourite(favourite);
                }
            }
        });


        info_save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                tab1_fragment.myBD.updateContact();
                tab1_fragment.contacts.get(index).setEmail(info_email.getText().toString());
                tab1_fragment.contacts.get(index).setName(info_name.getText().toString());
                tab1_fragment.contacts.get(index).setAddress(info_address.getText().toString());
//                tab1_fragment.contacts.get(index).setEmail(info_email.getText().toString());
                Snackbar.make(view , "Data Saved" , Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
    private void loadImage(String path) {
        File imgFile = new File(path);
        if (imgFile.exists()) {
//            Toast.makeText(mContext, " " + path, Toast.LENGTH_SHORT).show();
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
           // Glide.with(this).load(myBitmap).into(imageView_image);
            Glide.with(this).load(path).into(imageView_image);
//            imageView_image.setImageBitmap(myBitmap);
        }
    }


    private int dpToPx(int dp) {
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }

}
