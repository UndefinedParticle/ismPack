package com.example.ismpack.ui.Mess;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ismpack.R;
import com.example.ismpack.databinding.FragmentMessBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MessFragment extends Fragment {
    FragmentMessBinding binding;
    FirebaseDatabase database;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMessBinding.inflate(inflater, container, false);

        //Sunday Menu
        database.getReference().child("Mess").child("Sunday").child("Breakfast").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.sundayBreakfastItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("Mess").child("Sunday").child("Lunch").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.sundayLunchItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("Mess").child("Sunday").child("Dinner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.sundayDinnerItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Monday Menu
        database.getReference().child("Mess").child("Monday").child("Breakfast").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.mondayBreakfastItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("Mess").child("Monday").child("Lunch").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.mondayLunchItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("Mess").child("Monday").child("Dinner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.mondayDinnerItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Tuesday Menu
        database.getReference().child("Mess").child("Tuesday").child("Breakfast").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.tuesdayBreakfastItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("Mess").child("Tuesday").child("Lunch").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.tuesdayLunchItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("Mess").child("Tuesday").child("Dinner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.tuesdayDinnerItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        //Wednesday Menu
        database.getReference().child("Mess").child("Wednesday").child("Breakfast").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.wednesdayBreakfastItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("Mess").child("Wednesday").child("Lunch").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.wednesdayLunchItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("Mess").child("Wednesday").child("Dinner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.wednesdayDinnerItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //Thrusday Menu
        database.getReference().child("Mess").child("Thrusday").child("Breakfast").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.thrusdayBreakfastItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("Mess").child("Thrusday").child("Lunch").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.thrusdayLunchItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("Mess").child("Thrusday").child("Dinner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.thrusdayDinnerItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Friday Menu
        database.getReference().child("Mess").child("Friday").child("Breakfast").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.fridayBreakfastItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("Mess").child("Friday").child("Lunch").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.fridayLunchItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("Mess").child("Friday").child("Dinner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.fridayDinnerItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Saturday Menu
        database.getReference().child("Mess").child("Saturday").child("Breakfast").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.saturdayBreakfastItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("Mess").child("Saturday").child("Lunch").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.saturdayLunchItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("Mess").child("Saturday").child("Dinner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class);

                binding.saturdayDinnerItem.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return binding.getRoot();
    }
}