package com.krp.bakingapp.adapters;

import com.krp.bakingapp.viewModels.RowViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rakesh Praneeth.
 */
public class RecipeAdapter extends BindableAdapter {

    private List<RowViewModel> data;

    public RecipeAdapter() {
        this.data = new ArrayList<>();
    }

    @Override
    protected Object getObjForPosition(int position) {
        return data.get(position);
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return data.get(position).getLayout();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setList(List<RowViewModel> newData) {
        if (data != null) {
            data.clear();
        }
        data.addAll(newData);
        notifyDataSetChanged();
    }
}