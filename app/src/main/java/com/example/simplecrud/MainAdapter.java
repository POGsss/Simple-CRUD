package com.example.simplecrud;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel, MainAdapter.myViewHolder> {

    // Constructor
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model) {
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());

        Glide.with(holder.img.getContext())
                .load(model.getImageUrl())
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .circleCrop()
                .into(holder.img);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView title, description;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialization
            img = itemView.findViewById(R.id.img1);
            title = itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textDescription);
        }
    }
}
