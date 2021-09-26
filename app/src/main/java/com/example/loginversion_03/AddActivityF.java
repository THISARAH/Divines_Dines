package com.example.loginversion_03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivityF extends AppCompatActivity {

    EditText name,price,url;
    Button btnAddF, btnBackF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_f);

        name = (EditText)findViewById(R.id.txtNamef);
        price = (EditText)findViewById(R.id.txtPricef);
        url = (EditText)findViewById(R.id.txtUrlf);

        btnAddF = (Button)findViewById(R.id.btnAddF);
        btnBackF = (Button)findViewById(R.id.btnBackF);

        btnAddF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDataF();
                clearAll();

            }
        });

        btnBackF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void insertDataF(){
        Map<String,Object> map = new HashMap<>();
        map.put("fName",name.getText().toString());
        map.put("fPrice",price.getText().toString());
        map.put("furl",url.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Food_Details").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivityF.this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Toast.makeText(AddActivityF.this,"Error While Insertion",Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void clearAll(){
        name.setText("");
        price.setText("");
        url.setText("");

    }



}