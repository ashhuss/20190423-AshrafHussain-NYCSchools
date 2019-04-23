package com.project.nycschools.schoolList.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.project.nycschools.R;
import com.project.nycschools.data.AppConstant;
import com.project.nycschools.schoolDetail.SatScoreActivity;
import com.project.nycschools.schoolList.model.SchoolModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SchoolListAdapter extends RecyclerView.Adapter<SchoolListAdapter.ViewHolder> {

    private static final String TAG = SchoolListAdapter.class.getName();
    private Context context;
    private List<SchoolModel> schoolModelList;
    private int lastPosition = -1;


    SchoolListAdapter(List<SchoolModel> schoolModelList) {
        this.schoolModelList = schoolModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_school_list, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        setAnimation(holder.itemView, position);
        final SchoolModel schoolModel = schoolModelList.get(position);
        holder.tvTitle.setText(schoolModel.getSchoolName());
        holder.cvSchoolListAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SatScoreActivity.class);
                i.putExtra(AppConstant.SCHOOL_DETAIL, schoolModel);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return schoolModelList == null ? 0 : schoolModelList.size();
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_adp_school_title)
        TextView tvTitle;
        @BindView(R.id.cv_school_list)
        CardView cvSchoolListAdapter;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}