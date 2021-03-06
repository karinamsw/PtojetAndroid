package com.example.myelephant.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myelephant.R;
import com.example.myelephant.presentation.model.Elephant;
import com.squareup.picasso.Picasso;

import java.util.List;


    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
        private final List<Elephant> values;
        private final OnItemClickListner listner;

        public interface OnItemClickListner {
            void onItemClick(Elephant item);
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            ImageView mImage = itemView.findViewById(R.id.icon);
            TextView txtHeader;
            TextView txtFooter;
            View layout;

            ViewHolder(View v) {
                super(v);
                layout = v;
                txtHeader = (TextView) v.findViewById(R.id.firstLine);
                txtFooter = (TextView) v.findViewById(R.id.secondLine);
            }
        }

        public void add(int position, Elephant  item) {
            values.add(position, item);
            notifyItemInserted(position);
        }

        private void remove(int position) {
            values.remove(position);
            notifyItemRemoved(position);
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        ListAdapter(List<Elephant> myDataset, OnItemClickListner listner) {

            this.values = myDataset;
            this.listner = listner;
        }

        // Create new views (invoked by the layout manager)
        @NonNull
        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from( parent.getContext());
            View v = inflater.inflate(R.layout.row_layout, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final Elephant currentElephant = values.get(position);
            Picasso.get().load(currentElephant.getImage()).resize(300,300).into(holder.mImage);
            holder.txtHeader.setText(currentElephant.getName());
            holder.txtFooter.setText(currentElephant.getSex());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.onItemClick(currentElephant);
                }
            });


        }

        @Override
        public int getItemCount() {
            return values.size();
        }

    }


