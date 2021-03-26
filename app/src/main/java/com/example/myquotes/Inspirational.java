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

public class Inspirational extends AppCompatActivity {
    Button insertdata;
    EditText IQT,IAU;
    DatabaseReference IdatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspirational);
        insertdata = findViewById(R.id.inspinsert);
        IQT=findViewById(R.id.inspqut);
        IAU=findViewById(R.id.inspauth);
        IdatabaseReference= FirebaseDatabase.getInstance().getReference("NewQuotes").child("Inspirational");

        insertdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qt=IQT.getText().toString();
                String au=IAU.getText().toString();
                if (qt.isEmpty())
                {
                    IQT.setError("Required");
                    IQT.requestFocus();
                    return;
                }
                if (au.isEmpty())
                {
                    IAU.setError("Required");
                    IAU.requestFocus();
                    return;
                }
                String ID=IdatabaseReference.push().getKey();
                Model model=new Model(ID,qt,au);
                IdatabaseReference.child(ID).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Quote Added",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Inspirational.this,MainActivity.class);
                            startActivity(intent);
                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Inspirational.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}