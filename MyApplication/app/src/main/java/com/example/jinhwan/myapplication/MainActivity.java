package com.example.jinhwan.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {
    final int _ADD_MENU_CODE = 1;
    final int _SHOW_MENU_CODE = 2;
    EditText search ;
    ListView listView ;
    Button select;
    ArrayList<Information> infoList = new ArrayList<Information>();
    RestAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        select = (Button)findViewById(R.id.btnSelect);

        setTitle("나의 맛집");
        setListView();
        search = (EditText)findViewById(R.id.etSearch);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                String search_string = s.toString();
                System.out.println("search : " +search_string);
                if(search_string.length()>0) {
                    listView.setFilterText(search_string);
                }
                else{
                    listView.clearTextFilter();
                }
            }
        });
    }

    //어댑터 만들기 (화면과 데이터 결정)
    public void setListView(){
        listView = (ListView)findViewById(R.id.listview);
        adapter = new RestAdapter(infoList, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                intent.putExtra("show",infoList.get(position));
                startActivityForResult(intent,_SHOW_MENU_CODE);
            }
        });
    }

    public void onClick(View v) {
            if (v.getId() == R.id.btnAddList) {
                Intent intent = new Intent(this, Main2Activity.class);
                startActivityForResult(intent, _ADD_MENU_CODE);
            } else if (v.getId() == R.id.btnNameSort) {
                adapter.setNameAscSort();
        } else if (v.getId() == R.id.btnTypeSort) {
            adapter.setCategoryAscSort();
        } else if (v.getId() == R.id.btnSelect) {
            if (select.getText().toString().equals("선택")) {
                select.setText("삭제");
                adapter.showCheckBox();
            } else {
                select.setText("선택");
                adapter.deleteData();

            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == _ADD_MENU_CODE){
            if(resultCode == RESULT_OK){
                Information temp = data.getParcelableExtra("add");
                infoList.add(temp);
                adapter.notifyDataSetChanged();
            }
        }
            super.onActivityResult(requestCode, resultCode, data);
    }
}
