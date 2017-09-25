package com.sun3toline.mybakingapps.Ui.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.sun3toline.mybakingapps.Model.SelectedPosition;
import com.sun3toline.mybakingapps.Model.Step;
import com.sun3toline.mybakingapps.R;
import com.sun3toline.mybakingapps.Ui.ResepStepDetailActivity;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by coldware on 9/24/17.
 */

public class StepsAdapterView extends RecyclerView.Adapter<StepsAdapterView.StepsViewHolder> {

    private Context context;
    private List<Step> steps = new ArrayList<>();
    private boolean mTwoPane = false;

    public interface OnStepAdapterViewListener{
        void onStepAdapterViewSelected(int position);
    }

    private OnStepAdapterViewListener onStepAdapterViewListener;

    public StepsAdapterView(List<Step> steps, boolean twoPane, OnStepAdapterViewListener onStepAdapterViewListener){
        this.steps = new ArrayList<>();
        this.steps.addAll(steps);
        mTwoPane = twoPane;
        this.onStepAdapterViewListener = onStepAdapterViewListener;
    }

    @Override
    public StepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.step_adapter_view, parent, false);
        StepsViewHolder stepsViewHolder = new StepsViewHolder(view);
        return stepsViewHolder;
    }

    @Override
    public void onBindViewHolder(StepsViewHolder holder, final int position) {
        final Step step = steps.get(position);

        if (step.getThumbnailURL() != null && !TextUtils.isEmpty(step.getThumbnailURL())){
            Picasso.with(context)
                    .load(step.getThumbnailURL())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.ivStepImage);
        } else {
            holder.ivStepImage.setImageResource(R.mipmap.ic_launcher);
        }

        holder.tvShortDiscriptoin.setText(step.getShortDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStepAdapterViewListener.onStepAdapterViewSelected(position);
                if (mTwoPane){
                    EventBus.getDefault().post(new SelectedPosition(position));
                } else {
                    Intent intent = new Intent(context, ResepStepDetailActivity.class);
                    Type type = new TypeToken<List<Step>>(){}.getType();
                    String ssteps = new GsonBuilder().create().toJson(steps, type);
                    intent.putExtra(ResepStepDetailActivity.STEPS, ssteps);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    static class StepsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.step_image)
        ImageView ivStepImage;
        @BindView(R.id.step_short_description)
        TextView tvShortDiscriptoin;

        public StepsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

