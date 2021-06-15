package com.example.login2.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Academy_Details extends AppCompatActivity {

    private String recevieracademy;
    private ImageView imageView;
    private TextView academyname,location,price,email,username,phone;
    Button Add;
    private DatabaseReference referencee;
    private static final int    REQUEST_CALL=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academy_details);

        referencee= FirebaseDatabase.getInstance().getReference().child("academy");


        recevieracademy=getIntent().getExtras().get("visite_academy_details").toString();

        imageView=findViewById(R.id.academyimage);
        Add=findViewById(R.id.add);
        phone=findViewById(R.id.P);
        academyname= findViewById(R.id.LN);
        location=findViewById(R.id.L);
        price=findViewById(R.id.PTJ);
        email=findViewById(R.id.E);
        username=findViewById(R.id.UN);


        RetriveInformation();



    }

    private void RetriveInformation() {
        referencee.child(recevieracademy).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    //   String IMAGE=snapshot.child("image ").getValue().toString();

                    String AcademyName=snapshot.child("academy name").getValue().toString();
                    String LOCATION=snapshot.child("location").getValue().toString();
                    String USERNAME=snapshot.child("person name").getValue().toString();
                    String PHONE=snapshot.child("phone number").getValue().toString();
                    String PRICE=snapshot.child("price").getValue().toString();
                    String EMAIL=snapshot.child("email").getValue().toString();

                    String image=snapshot.child("image").getValue().toString();
                    Picasso.get().load(image).placeholder(R.drawable.addtwo).into(imageView);


                    email.setText(EMAIL);
                    academyname.setText(AcademyName);
                    location.setText(LOCATION);
                    phone.setText(PHONE);
                    price.setText(PRICE);
                    username.setText(USERNAME);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
