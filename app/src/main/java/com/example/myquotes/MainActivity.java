package com.example.myquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myquotes.view.Success;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String QT[]={"Success","Inspirational","Happy"};
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.mylist);
        arrayAdapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,QT);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    Intent intent=new Intent(MainActivity.this, Success.class);
                    startActivity(intent);
                }
                if(position==1)
                {
                    Intent intent=new Intent(MainActivity.this,Inspirational.class);
                    startActivity(intent);
                }
                if(position==2)
                {
                    Intent intent=new Intent(MainActivity.this,Happy.class);
                    startActivity(intent);
                }
            }
          });


    }
}