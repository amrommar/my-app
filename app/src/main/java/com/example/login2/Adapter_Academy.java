package com.example.login2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Adapter_Academy extends FirebaseRecyclerAdapter<academies, Adapter_Academy.academiesviewholder> {
    private DatabaseReference academyref, referencee;

    public Adapter_Academy(@NonNull FirebaseRecyclerOptions<academies> options) {
        super(options);
    }

    @NonNull
    @Override
    public Adapter_Academy.academiesviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Adapter_Academy.academiesviewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_display_layout, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull Adapter_Academy.academiesviewholder academiesviewholder, int position, @NonNull academies academies) {
        academyref = FirebaseDatabase.getInstance().getReference().child("academy");
        referencee = FirebaseDatabase.getInstance().getReference().child("academy");


        academiesviewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent profilintent = new Intent(v.getContext(),profileActivity.class);
                profilintent.putExtra("visite_academy_details", getRef(position).getKey());
                v.getContext().startActivity(profilintent);
                //startActivity(new Intent(getActivity(),profileActivity.class));
            }
        });


        String academy_id = getRef(position).getKey();
        referencee.child(academy_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String Lname = snapshot.child("academy name").getValue().toString();
                    String Locname = snapshot.child("location").getValue().toString();
                    String image = snapshot.child("image").getValue().toString();


                    academiesviewholder.academiesname.setText(Lname);
                    academiesviewholder.locations.setText(Locname);
                    if (image != null) {
                        Picasso.get().load(image).into(academiesviewholder.profimage);
                    } else {
                        academiesviewholder.profimage.setImageResource(R.drawable.ic_profile);
                    }

                } else {
//                    String Lname = snapshot.child("league name").getValue().toString();
//                    String Locname = snapshot.child("location").getValue().toString();//
//
//                    leaguesviewholder.leaguesname.setText(Lname);
//                    leaguesviewholder.locations.setText(Locname);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public static class academiesviewholder extends RecyclerView.ViewHolder {
        TextView academiesname, locations;
        ImageView profimage;

        public academiesviewholder(@NonNull View itemView) {
            super(itemView);

            academiesname = itemView.findViewById(R.id.league_name);
            locations = itemView.findViewById(R.id.location_name);
            profimage = itemView.findViewById(R.id.my_image);

        }
    }
}
