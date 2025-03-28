package com.example.simplecrud;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

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

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UpdateActivity.class);

                intent.putExtra("title", model.getTitle());
                intent.putExtra("description", model.getDescription());
                intent.putExtra("imageUrl", model.getImageUrl());
                intent.putExtra("itemKey", getRef(position).getKey());

                view.getContext().startActivity(intent);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = holder.getAbsoluteAdapterPosition();

                if (currentPosition != RecyclerView.NO_POSITION) {
                    String key = getRef(currentPosition).getKey();

                    FirebaseDatabase.getInstance().getReference()
                            .child("data")
                            .child(key)
                            .removeValue()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(view.getContext(), "Item Deleted", Toast.LENGTH_SHORT);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(view.getContext(), "Failed To Delete", Toast.LENGTH_SHORT);
                                }
                            });
                }
            }
        });
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
        ImageView editButton, deleteButton;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialization
            img = itemView.findViewById(R.id.img1);
            title = itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textDescription);
            editButton = itemView.findViewById(R.id.editIcon);
            deleteButton = itemView.findViewById(R.id.deleteIcon);
        }
    }
}