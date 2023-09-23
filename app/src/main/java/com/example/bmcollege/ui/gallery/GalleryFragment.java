package com.example.bmcollege.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bmcollege.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class GalleryFragment extends Fragment {
    RecyclerView otherRecycle, independenceRecycle, convRecycle;
    GalleryAdapter adapter;

    DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);


        convRecycle = view.findViewById(R.id.convRecycle);
        independenceRecycle = view.findViewById(R.id.independanceRecycle);
        otherRecycle = view.findViewById(R.id.otherRecycle);

        reference = FirebaseDatabase.getInstance().getReference().child("gallery");

        getConvImage();
        getOtherImage();
        getIndependenceImage();
        return view;

    }

    private void getConvImage() {

        reference.child("Convocation").addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    String data =  Objects.requireNonNull(snapshot.getValue()).toString();
                    imageList.add(data);
                }

                adapter = new GalleryAdapter(getContext(), imageList);
                convRecycle.setLayoutManager(new GridLayoutManager(getContext(), 3));
                convRecycle.setHasFixedSize(true);
                convRecycle.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void getIndependenceImage() {

        reference.child("Independence Day").addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    String data;
                    data = Objects.requireNonNull(snapshot.getValue()).toString();
                    imageList.add(data);
                }

                adapter = new GalleryAdapter(getContext(), imageList);
                independenceRecycle.setLayoutManager(new GridLayoutManager(getContext(), 3));
                independenceRecycle.setHasFixedSize(true);
                independenceRecycle.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void getOtherImage() {
        reference.child("Other Events");
        reference.addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {


                    String data = Objects.requireNonNull(snapshot.getValue()).toString();
                    imageList.add(data);
                }

                adapter = new GalleryAdapter(getContext(), imageList);
                otherRecycle.setLayoutManager(new GridLayoutManager(getContext(), 3));
                otherRecycle.setHasFixedSize(true);
                otherRecycle.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();

            }
        });

    }


}