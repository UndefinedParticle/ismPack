package com.example.ismpack.ui.Explore;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ismpack.Models.ExploreHomeModel;
import com.example.ismpack.Models.Users;
import com.example.ismpack.R;
import com.example.ismpack.databinding.FragmentExploreAddBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ExploreAddFragment extends Fragment {
    FragmentExploreAddBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;

    Uri uri;
    ProgressDialog progressDialog;
    String currentUserName;
    String saveCurrentTime;
    public ExploreAddFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        progressDialog = new ProgressDialog(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExploreAddBinding.inflate(inflater, container, false);

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("uploading");
        progressDialog.setMessage("please wait...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Users users = snapshot.getValue(Users.class);
                    currentUserName = users.getUserName();
                    binding.addUserName.setText(users.getUserName());
                    binding.addItemName.setText(users.getPostItemName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //I commented this because I want , when user add a photo then only post button enabled
        /*binding.addDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String description  = binding.addDescription.getText().toString();

                if(!description.isEmpty()){
                    binding.postButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.yellow_button));
                    binding.postButton.setTextColor(getContext().getResources().getColor(R.color.white));
                    binding.postButton.setEnabled(true);
                }else {
                    binding.postButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.active_button_color));
                    binding.postButton.setTextColor(getContext().getResources().getColor(R.color.disable));
                    binding.postButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/

        binding.addImageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

                startActivityForResult(intent,10);
            }
        });

        binding.postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                /*Calendar calFordData = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");

                Calendar calFordTime = Calendar.getInstance();
                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
                saveCurrentTime = currentTime.format(calFordData.getTime());*/
                final StorageReference reference = storage.getReference().child("Posts").child(FirebaseAuth.getInstance().getUid()).child(new Date().getTime() + "");

                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                /*SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                Date date = new Date();*/

                                ExploreHomeModel post = new ExploreHomeModel();

                                post.setPostItemName(binding.addItemName.getText().toString());
                                post.setPostImage(uri.toString());
                                post.setPostUserId(FirebaseAuth.getInstance().getUid());
                                post.setPostedBy(currentUserName);
                                post.setPostDescription(binding.addDescription.getText().toString());
                                post.setPostedAt(new Date().getTime());


                                database.getReference().child("Posts").push().setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getContext(), "Posted Successfully", Toast.LENGTH_SHORT).show();

                                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                database.getReference().child("Users").child(auth.getUid()).child("postImage").setValue(uri.toString());
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });


            }
        });

        return binding.getRoot();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.explore_fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data != null && data.getData() != null){
            uri = data.getData();

            binding.addPost.setImageURI(uri);
            binding.addPost.setVisibility(View.VISIBLE);

            binding.postButton.setBackgroundDrawable(ContextCompat.getDrawable(getContext(),R.drawable.yellow_button));
            binding.postButton.setTextColor(getContext().getResources().getColor(R.color.white));
            binding.postButton.setEnabled(true);
        }
    }
}