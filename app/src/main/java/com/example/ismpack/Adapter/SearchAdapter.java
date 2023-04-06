package com.example.ismpack.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ismpack.Models.SearchModel;
import com.example.ismpack.Models.Users;
import com.example.ismpack.R;
import com.example.ismpack.databinding.ExploresearchRecyclerviewSampleBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.viewHolder>{

    ArrayList<SearchModel> list;
    Context context;

    public SearchAdapter(ArrayList<SearchModel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.exploresearch_recyclerview_sample,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        SearchModel model = list.get(position);
        Picasso.get().load(model.getPostImage()).placeholder(R.drawable.placeholder).into(holder.binding.exploreSearchPicture);
        holder.binding.searchItem.setText(model.getPostItemName());
        holder.binding.searchUserName.setText(model.getPostedBy());





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ExploresearchRecyclerviewSampleBinding binding;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            binding = ExploresearchRecyclerviewSampleBinding.bind(itemView);

        }
    }
}
