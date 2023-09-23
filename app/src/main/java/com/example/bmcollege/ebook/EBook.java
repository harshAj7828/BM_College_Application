package com.example.bmcollege.ebook;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bmcollege.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class EBook extends AppCompatActivity {

    private RecyclerView ebookRecycler;
    private DatabaseReference reference;
    private List<EbookData> list;
    private EbookAdapter adapter;
    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout simmerLayout;
    EditText search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ebooks");

        ebookRecycler = findViewById(R.id.ebookRecycle);
        search = findViewById(R.id.search);
        reference = FirebaseDatabase.getInstance().getReference().child("pdf");
        shimmerFrameLayout = findViewById(R.id.simmer);
        simmerLayout = findViewById(R.id.shimmer_layout);

        getData();
    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    EbookData data = snapshot.getValue(EbookData.class);
                    list.add(data);


                adapter = new EbookAdapter(EBook.this,list);
                ebookRecycler.setLayoutManager(new LinearLayoutManager(EBook.this));
                ebookRecycler.setAdapter(adapter);
                ebookRecycler.setHasFixedSize(true);
                shimmerFrameLayout.stopShimmer();
                simmerLayout.setVisibility(View.GONE);

                search.setVisibility(View.VISIBLE);}
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EBook.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    filter(s.toString());
            }
        });
    }
    private void filter(String text){
       ArrayList<EbookData> filterList = new ArrayList<>();
       for(EbookData item: list){
           if(item.getName().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
               filterList.add(item);

           }
       }
       if(filterList.isEmpty()){
           Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
       }else {

       adapter.FilteredList(filterList);
       }

    }
    @Override
    protected void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();
    }

    @Override
    protected void onResume() {
        shimmerFrameLayout.startShimmer();
        super.onResume();
    }
}