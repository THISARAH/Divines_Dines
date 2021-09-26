package com.example.loginversion_03;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapterAdmin extends FirebaseRecyclerAdapter <MainModelAdmin,MainAdapterAdmin.myViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapterAdmin(@NonNull FirebaseRecyclerOptions<MainModelAdmin> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull MainModelAdmin model) {   //MainAdapterAdmin.
        holder.name.setText(model.getfName());
        holder.price.setText(model.getfPrice());



        Glide.with(holder.img.getContext())
                .load(model.getFurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);


        holder.btnEditAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                         .setContentHolder(new ViewHolder(R.layout.update_popup_admin))
                         .setExpanded(true,1200)
                         .create();

                 //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText name = view.findViewById(R.id.txtNamef);
                EditText price = view.findViewById(R.id.txtPricef);
                EditText url = view.findViewById(R.id.txtUrlf);

                Button btnUpdate = view.findViewById(R.id.btnUpdateAdmin);

                name.setText(model.getfName());
                price.setText(model.getfPrice());
                url.setText(model.getFurl());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("fName",name.getText().toString());
                        map.put("fPrice",price.getText().toString());
                        map.put("furl",url.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Food_Details")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.name.getContext(),"Error While Updating",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                });
                     }
                });


            }
        });

        holder.btnDeleteAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't be Undo!");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Food_Details")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.name.getContext(),"Canclelled !",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });







    }

    @NonNull
    //@org.jetbrains.annotations.NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item_admin,parent,false);

        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView name,price;

        Button btnEditAdmin, btnDeleteAdmin;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.img1Admin);
            name = (TextView)itemView.findViewById(R.id.fNametext);
            price = (TextView)itemView.findViewById(R.id.fPricetext);

            btnEditAdmin = (Button)itemView.findViewById(R.id.btnEditAdmin);
            btnDeleteAdmin = (Button)itemView.findViewById(R.id.btnDeleteAdmin);

        }
    }

}
