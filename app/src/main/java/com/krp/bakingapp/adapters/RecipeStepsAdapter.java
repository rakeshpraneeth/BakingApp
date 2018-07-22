package com.krp.bakingapp.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.krp.bakingapp.R;
import com.krp.bakingapp.databinding.ItemRecipeStepBinding;
import com.krp.bakingapp.interfaces.OnRecipeStepsRvItemClicked;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rakesh Praneeth.
 */
public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsAdapter.RecipeStepsViewHolder> {

    private List<String> data;
    private OnRecipeStepsRvItemClicked onRecipeStepsRvItemClicked;

    public RecipeStepsAdapter(OnRecipeStepsRvItemClicked onRecipeStepsRvItemClicked) {
        data = new ArrayList<>();
        this.onRecipeStepsRvItemClicked = onRecipeStepsRvItemClicked;
    }

    public void setData(List<String> newData) {
        if (newData != null) {
            if (data != null) {
                data.clear();
            }
            data.addAll(newData);
            notifyDataSetChanged();
        }
    }

    public void add(String item) {
        if (item != null) {
            data.add(item);
            notifyItemChanged(data.size() - 1);
        }
    }

    @NonNull
    @Override
    public RecipeStepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecipeStepBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_recipe_step, parent, false);
        return new RecipeStepsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepsViewHolder holder, int position) {
        holder.bind(position);

        holder.itemView.setOnClickListener(v -> {
            if (onRecipeStepsRvItemClicked != null) {
                onRecipeStepsRvItemClicked.onItemClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecipeStepsViewHolder extends RecyclerView.ViewHolder {

        ItemRecipeStepBinding binding;

        public RecipeStepsViewHolder(ItemRecipeStepBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {

            binding.stepName.setText(data.get(position));
        }
    }
}
