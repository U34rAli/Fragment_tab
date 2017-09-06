package com.example.umar.fragment_tab;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umar.fragment_tab.R;

import java.util.ArrayList;

/**
 * Created by Umar on 7/24/2017.
 */

public class tab2_fragment extends Fragment {

    private static final String TAB = "TAB2Fragment";
    private ListView favouriteListView;
    static ArrayList<Contact> favouriteArratList;
    private ImageView favouriteImage;
    private TextView favouriteName;
    static FavouriteAdapter fAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment, container, false);
        favouriteListView = (ListView) view.findViewById(R.id.listview_favorities);
        favouriteArratList = new ArrayList<>();
        favouriteArratList = makeArrayListOfFavourite(tab1_fragment.contacts);
        fAdapter = new FavouriteAdapter();
        favouriteListView.setAdapter(fAdapter);
//      Toast.makeText(getActivity(), "  On Create  ", Toast.LENGTH_SHORT).show();
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        favouriteArratList = makeArrayListOfFavourite(tab1_fragment.contacts);
        fAdapter.notifyDataSetChanged();
    }

    private ArrayList<Contact> makeArrayListOfFavourite(ArrayList<Contact> list) {
        ArrayList<Contact> mList = new ArrayList<>();
        if (list.size() > 0) {
//            Toast.makeText(getActivity(), " Error is Here  " + list.size(), Toast.LENGTH_SHORT).show();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getFavourite() == 1) {
                    mList.add(list.get(i));
                }
            }
        }
        return mList;
    }

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }
    public class FavouriteAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return favouriteArratList.size();
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
            view = getActivity().getLayoutInflater().inflate(R.layout.favourite_list_items, null);
            favouriteImage = (ImageView) view.findViewById(R.id.favourite_image);
            favouriteName = (TextView) view.findViewById(R.id.favourite_name);
            byte[] image = favouriteArratList.get(i).getBitmap_image();
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0, image.length);
            favouriteImage.setImageBitmap(bm);
            favouriteName.setText(favouriteArratList.get(i).getName());
            return view;
        }
    }

}
