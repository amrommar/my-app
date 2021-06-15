package com.example.login2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login2.ADD_LEAGUE;
import com.example.login2.Adapter_Academy;
import com.example.login2.Add_Academy;
import com.example.login2.R;
import com.example.login2.adapter_league;
import com.example.login2.leagues;
import com.example.login2.ui.Academy_Details;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AcademyFragment extends Fragment {
    private View academyView;
    private adapter_league adapter;
    private RecyclerView myacademylist;
    private DatabaseReference academyref;
    FirebaseRecyclerOptions<leagues> options;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        academyView = inflater.inflate(R.layout.academy_fragment, container, false);
        myacademylist = (RecyclerView) academyView.findViewById(R.id.academy_list);
        myacademylist.setLayoutManager(new LinearLayoutManager(getActivity()));
        academyref = FirebaseDatabase.getInstance().getReference().child("academy");


        Query queries = academyref;
        options = new FirebaseRecyclerOptions.Builder<leagues>()
                .setQuery(queries, leagues.class)
                .build();
        adapter = new adapter_league(options);
        myacademylist.setAdapter(adapter);
        queries.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    adapter.startListening();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "there is problem", Toast.LENGTH_SHORT).show();
            }
        });

        return academyView;
    }


    //enable options menu in this fragment
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    //inflate the menue
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        menu.findItem(R.id.action_settings);
        super.onCreateOptionsMenu(menu, inflater);
    }

    // handle item clicks on menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addLeague:
                Intent intent = new Intent(getActivity(), Add_Academy.class);
                startActivity(intent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}
