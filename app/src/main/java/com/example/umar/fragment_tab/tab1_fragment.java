package com.example.umar.fragment_tab;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Umar on 7/24/2017.
 */

public class tab1_fragment extends Fragment {

    static ArrayList<Contact> contacts = new ArrayList<>();
    ContactsAdapter customAdaper;
    ImageButton addButton;
    ListView list;
    static MyDataBaseHelper myBD ;
    private static  final String TAB = "TAB1Fragment";
    private Button btn1;
    private  ListView listView;
    private View thisView;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment,container,false);
        thisView = view;
//        //  addButton = (ImageButton) findViewById(R.id.button_add);
        myBD = new MyDataBaseHelper(getActivity());
        list = (ListView)view.findViewById(R.id.listview);
//        for (int i=0 ; i<20 ; i++){
//            myBD.insertContact("Umar" , "ali " + i);
//           // contacts.add(new Contact("  ","" + i ,"45 "));
//        }
        contacts = myBD.getAllCotacts();
        customAdaper = new ContactsAdapter(getActivity() , contacts);
        list.setAdapter(customAdaper);
//        btn1 = (Button)view.findViewById(R.id.btn1);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText( getActivity() , "Testing Button Click 1" , Toast.LENGTH_SHORT).show();
//            }
//        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity() , ShowContactINFO.class);
                MainActivity.mList.add(0  ,  tab1_fragment.contacts.get(i).getName() + MainActivity.SEPARATOR + tab1_fragment.contacts.get(i).getPhone());
                if(MainActivity.mList.size() > MainActivity.mListSize ){
                    MainActivity.mList.remove( MainActivity.mList.size()-1 );
                }
                intent.putExtra("index" , i);
                startActivity(intent);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {
                final Contact info = tab1_fragment.contacts.get(i);
                AlertDialog.Builder mBuider = new AlertDialog.Builder(getActivity());
                View mView = getActivity().getLayoutInflater().inflate(R.layout.custom_dialogbox , null);
                mBuider.setView(mView);
                final AlertDialog dialog = mBuider.create();
                TextView name = (TextView)mView.findViewById(R.id.dialog_name);
                TextView phone = (TextView)mView.findViewById(R.id.dialog_phone);
//                Toast.makeText(getActivity() , " " + i , Toast.LENGTH_SHORT).show();
                if(info != null) {
                    name.setText(info.getName());
                    phone.setText(info.getPhone());
                }
                final Button mBtnNo = (Button)mView.findViewById(R.id.button_no);
                Button mBtnYes = (Button)mView.findViewById(R.id.button_yes);
                mBtnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        mBtnNo.setText("hy");
                        dialog.cancel();
                        String message = "Phone Number not Deleted";
                        showSnactBar(thisView , message);
                    }
                });
                mBtnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                        String message = "Phone Number is deleted";
                        myBD.deleteContact(info.getID());
                        tab1_fragment.contacts.remove(info);
                        customAdaper.notifyDataSetChanged();
                        notifyTab2ForListViewChangeOrNot(info);
                        showSnactBar(thisView , message);
                    }
                });
                dialog.show();
                return false;
            }
        });
        return view;
    }

    private void notifyTab2ForListViewChangeOrNot(Contact info){
        for(int i=0 ; i < tab2_fragment.favouriteArratList.size()  ; i++){
            if(info.isEqual(tab2_fragment.favouriteArratList.get(i).getPhone())){
                tab2_fragment.favouriteArratList.remove(i);
                tab2_fragment.fAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
//        Toast.makeText(getActivity() , "Tab 1 toast message : onStart" , Toast.LENGTH_SHORT).show();
    }

    public void showSnactBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void deleteBtn(View view){

    }

    @Override
    public void onResume() {
        super.onResume();
//        Toast.makeText(getActivity() , "Tab 1 toast message : onResume" , Toast.LENGTH_SHORT).show();
        //ArrayList<Contact> c = myBD.getAllCotacts();
        //customAdaper = new ContactsAdapter(getActivity() , c);
        //list.setAdapter(customAdaper);
        customAdaper.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }
}
