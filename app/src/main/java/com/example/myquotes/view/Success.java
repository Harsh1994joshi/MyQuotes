package com.example.myquotes.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myquotes.MainActivity;
import com.example.myquotes.Model;
import com.example.myquotes.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Success extends AppCompatActivity {

    Button insertdata;
    EditText QT,AU;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        insertdata = findViewById(R.id.succesinsert);
        QT=findViewById(R.id.successqut);
        AU=findViewById(R.id.succesauth);
        databaseReference= FirebaseDatabase.getInstance().getReference("NewQuotes").child("Success");

        insertdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qt=QT.getText().toString();
                String au=AU.getText().toString();
                if (qt.isEmpty())
                {
                    QT.setError("Required");
                    QT.requestFocus();
                    return;
                }
                if (au.isEmpty())
                {
                    AU.setError("Required");
                    AU.requestFocus();
                    return;
                }
                String ID=databaseReference.push().getKey();
                Model model=new Model(ID,qt,au);
                databaseReference.child(ID).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Quote Added",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Success.this, MainActivity.class);
                            startActivity(intent);

                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Success.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}