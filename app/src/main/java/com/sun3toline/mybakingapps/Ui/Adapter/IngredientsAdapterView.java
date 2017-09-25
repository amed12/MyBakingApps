package com.sun3toline.mybakingapps.Ui.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sun3toline.mybakingapps.Model.Bahan;
import com.sun3toline.mybakingapps.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by coldware on 9/24/17.
 */

public class IngredientsAdapterView extends RecyclerView.Adapter<IngredientsAdapterView.IngredientsViewHolder>{

    private Context context;
    private List<Bahan> ingredients;

    public IngredientsAdapterView(List<Bahan> ingredients){
        this.ingredients = new ArrayList<>();
        this.ingredients.addAll(ingredients);
    }

    @Override
    public IngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        IngredientsViewHolder ingredientsViewHolder = new IngredientsViewHolder(view);
        return ingredientsViewHolder;
    }

    @Override
    public void onBindViewHolder(IngredientsViewHolder holder, int position) {
        final Bahan ingredient = ingredients.get(position);
        holder.name.setText(String.format(context.getString(R.string.ingredients_detail)
                , ingredient.getQuantity(), ingredient.getMeasure(), ingredient.getIngredient()));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    static class IngredientsViewHolder extends RecyclerView.ViewHolder {

        @BindView(android.R.id.text1)
        TextView name;

        public IngredientsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

