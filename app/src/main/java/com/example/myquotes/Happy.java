package com.example.myquotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Happy extends AppCompatActivity {

    Button insertdata;
    EditText HQT,HAU;
    DatabaseReference HdatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy);
        insertdata = findViewById(R.id.happyinsert);
        HQT=findViewById(R.id.happyqut);
        HAU=findViewById(R.id.happyauth);
        HdatabaseReference= FirebaseDatabase.getInstance().getReference("NewQuotes").child("Happy");

        insertdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qt=HQT.getText().toString();
                String au=HAU.getText().toString();
                if (qt.isEmpty())
                {
                   HQT.setError("Required");
                    HQT.requestFocus();
                    return;
                }
                if (au.isEmpty())
                {
                    HAU.setError("Required");
                    HAU.requestFocus();
                    return;
                }
                String ID=HdatabaseReference.push().getKey();
                Model model=new Model(ID,qt,au);
                HdatabaseReference.child(ID).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Quote Added",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Happy.this,MainActivity.class);
                            startActivity(intent);
                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Happy.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}