package com.example.umar.fragment_tab;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class addNewContacts extends AppCompatActivity {

    ImageView iv;
    EditText name;
    EditText phone;
    Button saveButton;
    ImageButton backBtn;
    String photoPath ="";

    private static final int SELECTED_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contacts);
        iv = (ImageView) findViewById(R.id.imageView_picture);
        name = (EditText) findViewById(R.id.editText_name);
        phone = (EditText) findViewById(R.id.editText_phone);
        saveButton = (Button) findViewById(R.id.button_save);
//        backBtn = (ImageButton) findViewById(R.id.backBtn);

    }


    public void onImageClick(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, SELECTED_PICTURE);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                photoPath = getImagePath(imageUri);

                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                iv.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(addNewContacts.this, R.string.errorWhileFectcingImage, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(addNewContacts.this, R.string.ImageNotSelected, Toast.LENGTH_LONG).show();
        }
    }


    public void back(View v) {
        this.finish();
//        Intent i = new Intent(this , MainActivity.class);
//        startActivity(i);
    }


    public void setSaveButton(View view) {
        String nameString;
        if (name.getText().toString().length() == 0) {
            //name.setText();
            nameString = getResources().getString(R.string.DefaultUserName);
        } else {
            nameString = name.getText().toString();
        }

        if (phone.getText().toString().length() == 0) {
            Toast.makeText(addNewContacts.this, R.string.PhoneNumberNotPresent, Toast.LENGTH_SHORT).show();
        } else {

            if(contactPresentOrNot( phone.getText().toString())){
                showAlertDialog(getResources().getString(R.string.NumberPresent));
                //Toast.makeText(AddContactActivity.this, R.string.NumberPresent, Toast.LENGTH_SHORT).show();
            }
            else {
                showAlertDialog(getResources().getString(R.string.contactSaved));
                //Toast.makeText(AddContactActivity.this, R.string.contactSaved, Toast.LENGTH_SHORT).show();
                tab1_fragment.myBD.insertContact( photoPath , nameString , phone.getText().toString() );
                tab1_fragment.contacts.add(new Contact(photoPath, nameString, phone.getText().toString()));
            }
        }
//        Animation animation = new AlphaAnimation(1.0f,0.0f);
//        animation.setDuration(500);
//        saveButton.startAnimation(animation);
    }
    private String getImagePath(Uri selectedImage) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }


    private Boolean contactPresentOrNot(String phoneNumber){
        int i=0;
        while ( i != tab1_fragment.contacts.size()){
            Contact c = new Contact("" , "" ,  phoneNumber);
            if( tab1_fragment.contacts.get(i).isEqual(c) ){
                return true;
            }
            i++;
        }
        return false;
    }

    public void showAlertDialog(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.alertbox));
        alertDialog.setMessage(message);
        alertDialog.setIcon(R.drawable.addbtn);
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getApplicationContext(), "You have clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(getApplicationContext(), "You have clicked on Back", Toast.LENGTH_SHORT).show();
    }
}
