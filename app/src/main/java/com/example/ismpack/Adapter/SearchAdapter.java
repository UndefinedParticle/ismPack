package com.example.ismpack.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.ismpack.ViewPostActivity;
import com.example.ismpack.databinding.ExploresearchRecyclerviewSampleBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends FirebaseRecyclerAdapter<SearchModel,SearchAdapter.viewHolder> {

    private Context context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public SearchAdapter(@NonNull FirebaseRecyclerOptions<SearchModel> options,Context context) {
        super(options);

        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull SearchModel model) {

        Picasso.get().load(model.getPostImage()).placeholder(R.drawable.placeholder).into(holder.binding.exploreSearchPicture);
        holder.binding.searchItem.setText(model.getPostItemName());
        holder.binding.searchUserName.setText(model.getPostedBy());

        holder.binding.exploreSearchPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewPostActivity.class);

                String post_Id = getRef(holder.getAdapterPosition()).getKey();

                intent.putExtra("post_Id",post_Id);
                /*intent1.putExtra("posted_By",model.getPostedBy());
                intent1.putExtra("post_item_name",model.getPostItemName());
                intent1.putExtra("posted_image",model.getPostImage());
                intent1.putExtra("post_like",model.getLikes());
                intent1.putExtra("post_comment_count",model.getCommentCount());
                intent1.putExtra("post_description",model.getPostDescription());
                intent1.putExtra("post_comments",model.getComments());*/

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exploresearch_recyclerview_sample,parent,false);

        return new viewHolder(view);
    }

    /*public SearchAdapter(ArrayList<SearchModel> list, Context context) {
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
    }*/

    public class viewHolder extends RecyclerView.ViewHolder{

        ExploresearchRecyclerviewSampleBinding binding;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            binding = ExploresearchRecyclerviewSampleBinding.bind(itemView);

        }
    }
}
