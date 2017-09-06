package com.example.umar.fragment_tab;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umar.fragment_tab.R;

/**
 * Created by Umar on 7/24/2017.
 */

public class tab3_fragment extends Fragment {

    private static final String TAB = "TAB3Fragment";
    private Button btn3;
    private TextView savedData;
    TextView recentName;
    TextView recentPhone;
    private ListView listView;
    EditText myText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_fragment, container, false);

        listView = view.findViewById(R.id.recetly_viewed);
        RecentList list_adapter = new RecentList();
        listView.setAdapter(list_adapter);

        return view;
    }

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    public class RecentList extends BaseAdapter {
        @Override
        public int getCount() {
            return MainActivity.mList.size();
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
            view = getActivity().getLayoutInflater().inflate(R.layout.recent_viewed_items, null);
            recentName = (TextView) view.findViewById(R.id.recent_name);
            recentPhone = (TextView) view.findViewById(R.id.recent_phone);
            String[] s = MainActivity.mList.get(i).split(MainActivity.SEPARATOR);
            recentName.setText(s[0]);
            recentPhone.setText(s[1]);
            return view;
        }

    }

}
