package com.inspiredpublicspeaking.speechcoach;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Chad Schultz on 1/11/2016.
 */
public class EvaluationCriterionAdapter extends RecyclerView.Adapter<EvaluationCriterionAdapter.ViewHolder> {
    private List<EvaluationCriterion> mEvaluationCriteria;
    private EvaluationCriterionListFragment mFragment;

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolderOnClickListener mListener;
        public TextView mCriterionNameTextView;
        public RatingBar mRatingBar;

        public ViewHolder(final View v, ViewHolderOnClickListener listener) {
            super(v);
            mListener = listener;
            v.setOnClickListener(this); // need to do this for every clickable view
            mCriterionNameTextView = (TextView) v.findViewById(R.id.criterion_name_textview);
            mRatingBar = (RatingBar) v.findViewById(R.id.criterion_ratingbar);
        }

        @Override
        public void onClick(View v) {
            // Can check the view for class or other identifying criteria and
            // call different listener methods based on that
            mListener.onClickItemPosition(v, getAdapterPosition());
        }

        /**
         * This pattern for handling RecyclerView clicks from
         * http://stackoverflow.com/a/24933117/967131
         */
        public static interface ViewHolderOnClickListener {
            public void onClickItemPosition(View v, int position);
        }

    }

    public EvaluationCriterionAdapter(List<EvaluationCriterion> evaluationCriteria, EvaluationCriterionListFragment fragment) {
        mFragment = fragment;
        mEvaluationCriteria = evaluationCriteria;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_evaluation_criterion, parent, false);
        //TODO: temp
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v, new ViewHolder.ViewHolderOnClickListener() {

            @Override
            public void onClickItemPosition(View v, int position) {
                mFragment.loadCriterion(mEvaluationCriteria.get(position));
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final EvaluationCriterion evaluationCriterion = mEvaluationCriteria.get(position);

        holder.mCriterionNameTextView.setText(evaluationCriterion.getName());
        holder.mRatingBar.setRating(evaluationCriterion.getRating());
    }

    @Override
    public int getItemCount() {
        return mEvaluationCriteria.size();
    }


}
