package com.example.umar.fragment_tab;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.umar.fragment_tab.R;
import com.example.umar.fragment_tab.Contact;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Umar on 7/19/2017.
 */

public class ContactsAdapter extends BaseAdapter {

    Activity mContext;
    ArrayList<Contact> contacts;
    //    ArrayList<String> nContacts;
    ImageView ImageView_image;

    public ContactsAdapter(Activity mContext, ArrayList<Contact> contacts) {
        this.mContext = mContext;
        this.contacts = contacts;
    }


    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = mContext.getLayoutInflater().inflate(R.layout.customlist, null);
        ImageView_image = (ImageView) view.findViewById(R.id.imageView_person);
        TextView textView_name = (TextView) view.findViewById(R.id.textView_name);
        TextView textView_phone = (TextView) view.findViewById(R.id.textView_phone);
//          ImageView_image.setImageResource(contacts.get(i).getImage());
//          loadImage(contacts.get(i).getImage());
        byte[] image = contacts.get(i).getBitmap_image();
        Bitmap bm = BitmapFactory.decodeByteArray(image, 0, image.length);
        ImageView_image.setImageBitmap(bm);
//            Toast.makeText(mContext , " " + contacts.get(i).getImage() , Toast.LENGTH_SHORT).show();
        textView_name.setText(contacts.get(i).getName());
        textView_phone.setText(contacts.get(i).getPhone());
        return view;
    }

    private void loadImage(String path) {
        File imgFile = new File(path);
        if (imgFile.exists()) {
//            Toast.makeText(mContext, " " + path, Toast.LENGTH_SHORT).show();
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ImageView_image.setImageBitmap(getResizedBitmap(myBitmap, 100, 100));
        }
    }


    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);
        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }


}
