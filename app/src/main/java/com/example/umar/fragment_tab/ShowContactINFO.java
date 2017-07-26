package com.example.umar.fragment_tab;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import static android.R.drawable.btn_star_big_off;
import static android.R.drawable.btn_star_big_on;

public class ShowContactINFO extends AppCompatActivity {

    Contact info = null;
    ImageView imageView_image;
    ImageView imageView_favourite;
    int favourite = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact_info);

        imageView_image = (ImageView) findViewById(R.id.imageView_info);
        imageView_favourite = (ImageView) findViewById(R.id.imageView_favourite);


        int index = -1;
        if (getIntent() != null) {
            index = getIntent().getIntExtra("index", -1);
        }
        if (index != -1) {
            info = tab1_fragment.contacts.get(index);
            loadImage(info.getImage());
            favourite = info.getFavourite();
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
            imageView_image.setImageBitmap(myBitmap);
        }
    }

}
