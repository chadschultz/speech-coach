package com.inspiredpublicspeaking.speechcoach;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import java.util.List;

/**
 * Manage the expanding/collapsing RecyclerView, including the animations for the rotating
 * expand/collapse arrow and the color shifting parent row title and icon. Trying to match the
 * official material design style for an expand/collapse list:
 * https://www.google.com/design/spec/components/lists-controls.html#lists-controls-types-of-list-controls
 *
 * Created by Chad Schultz on 1/11/2016.
 */
public class EvaluationCriterionGroupAdapter extends ExpandableRecyclerAdapter<EvaluationCriterionGroupAdapter.CriterionGroupViewHolder, EvaluationCriterionGroupAdapter.CriterionViewHolder> {
    private List<EvaluationCriterionGroup> mEvaluationCriteriaGroups;
    private EvaluationCriterionListFragment mFragment;

    static class CriterionGroupViewHolder extends ParentViewHolder {
        public ImageView mCriterionGroupIconImageView;
        public TextView mCriterionGroupNameTextView;
        public ImageView mCriterionGroupExpandImageView;

        public CriterionGroupViewHolder(View v) {
            super(v);
            mCriterionGroupIconImageView = (ImageView) v.findViewById(R.id.criterion_group_icon_imageview);
            mCriterionGroupNameTextView = (TextView) v.findViewById(R.id.criterion_group_name_textview);
            mCriterionGroupExpandImageView = (ImageView) v.findViewById(R.id.criterion_group_expand_imageview);
        }

        @Override
        public void onExpansionToggled(boolean collapsed) {
            super.onExpansionToggled(collapsed);

            final String KEY_TEXT = "text"; // Key to identify color being animated for TextView
            final String KEY_ICON = "icon"; // Key to identify color being animated for ImageView
            int collapsedTextColor = ContextCompat.getColor(mCriterionGroupNameTextView.getContext(), R.color.colorTextPrimaryDark);
            int expandedTextColor = ContextCompat.getColor(mCriterionGroupNameTextView.getContext(), R.color.colorAccentDark);
            int collapsedIconColor = ContextCompat.getColor(mCriterionGroupIconImageView.getContext(), R.color.colorIconDark);
            int expandedIconColor = expandedTextColor;
            int fromTextColor, toTextColor, fromIconColor, toIconColor;

            // For rotating expand/collapse icon
            float ROTATION_UP = 0;
            float ROTATION_DOWN = -180;
            float rotation;

            if (collapsed) {
                // Animate from expanded to collapsed
                rotation = ROTATION_UP;
                fromTextColor = expandedTextColor;
                toTextColor = collapsedTextColor;
                fromIconColor = expandedIconColor;
                toIconColor = collapsedIconColor;
            } else {
                // Animate from collapsed to expanded
                rotation = ROTATION_DOWN;
                fromTextColor = collapsedTextColor;
                toTextColor = expandedTextColor;
                fromIconColor = collapsedIconColor;
                toIconColor = expandedIconColor;
            }

            // Rotate expand/collapse icon
            mCriterionGroupExpandImageView.animate().rotation(rotation).start();

            // Animate color change for evaluation criterion group
            ArgbEvaluator argbEvaluator = new ArgbEvaluator();
            PropertyValuesHolder pvText = PropertyValuesHolder.ofObject(KEY_TEXT, argbEvaluator, fromTextColor, toTextColor);
            PropertyValuesHolder pvIcon = PropertyValuesHolder.ofObject(KEY_ICON, argbEvaluator, fromIconColor, toIconColor);
            ValueAnimator animation = ValueAnimator.ofPropertyValuesHolder(pvText, pvIcon);
            animation.setDuration(250);
            animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // Animate color changes for text and icon
                    // Why have two separate animations? They expand to the same color, but collapse
                    // to different colors, resulting in an unsettling jump if only one is used.
                    mCriterionGroupNameTextView.setTextColor((Integer) animation.getAnimatedValue(KEY_TEXT));
                    // TODO: refactor to a better way to tint ImageView
                    Drawable drawable = mCriterionGroupIconImageView.getDrawable();
                    DrawableCompat.setTint(drawable, (Integer) animation.getAnimatedValue(KEY_ICON));
                    mCriterionGroupIconImageView.setImageDrawable(drawable);
                }
            });
            animation.start();

        }
    }

    static class CriterionViewHolder extends ChildViewHolder implements View.OnClickListener {
        public ViewHolderOnClickListener mListener;
        public TextView mCriterionNameTextView;
        public RatingBar mRatingBar;

        public CriterionViewHolder(final View v, ViewHolderOnClickListener listener) {
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

    public EvaluationCriterionGroupAdapter(EvaluationCriterionListFragment fragment, List<EvaluationCriterionGroup> evaluationCriteriaGroups) {
        super(evaluationCriteriaGroups);
        mFragment = fragment;
        mEvaluationCriteriaGroups = evaluationCriteriaGroups;
    }

    @Override
    public CriterionGroupViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View v = LayoutInflater.from(parentViewGroup.getContext())
                .inflate(R.layout.list_item_evaluation_criterion_group, parentViewGroup, false);
        return new CriterionGroupViewHolder(v);
    }

    @Override
    public CriterionViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View v = LayoutInflater.from(childViewGroup.getContext())
                .inflate(R.layout.list_item_evaluation_criterion, childViewGroup, false);
        return new CriterionViewHolder(v, new CriterionViewHolder.ViewHolderOnClickListener() {

            @Override
            public void onClickItemPosition(View v, int position) {
                // The position is the absolute row number in the list, which includes parent rows
                // and expanded (but not collapsed) child rows. getListItem() translates this for us
                // and returns the proper type of object. Since we are in the child view holder,
                // the position must be for a child row.
                EvaluationCriterion criterion = (EvaluationCriterion) getListItem(position);
                mFragment.loadCriterion(criterion);
            }
        });
    }

    @Override
    public void onBindParentViewHolder(CriterionGroupViewHolder parentViewHolder, int position, ParentListItem parentObject) {
        EvaluationCriterionGroup criterionGroup = (EvaluationCriterionGroup) parentObject;
        parentViewHolder.mCriterionGroupIconImageView.setImageDrawable(mFragment.getActivity().getResources().getDrawable(criterionGroup.getIconResId()));
        Drawable drawable = parentViewHolder.mCriterionGroupIconImageView.getDrawable();
        int collapsedTextColor = ContextCompat.getColor(mFragment.getActivity(), R.color.colorTextPrimaryDark);
        int expandedTextColor = ContextCompat.getColor(mFragment.getActivity(), R.color.colorAccentDark);
        int collapsedIconColor = ContextCompat.getColor(mFragment.getActivity(), R.color.colorIconDark);
        int expandedIconColor = expandedTextColor;
        int textColor, iconColor;
        if (parentViewHolder.isExpanded()) {
            textColor = expandedTextColor;
            iconColor = expandedIconColor;
        } else {
            textColor = collapsedTextColor;
            iconColor = collapsedIconColor;
        }
        parentViewHolder.mCriterionGroupNameTextView.setTextColor(textColor);
        DrawableCompat.setTint(drawable, iconColor);
        parentViewHolder.mCriterionGroupIconImageView.setImageDrawable(drawable);
        parentViewHolder.mCriterionGroupNameTextView.setText(criterionGroup.getName());
    }

    @Override
    public void onBindChildViewHolder(CriterionViewHolder childViewHolder, int position, Object childObject) {
        EvaluationCriterion evaluationCriterion = (EvaluationCriterion) childObject;
        childViewHolder.mCriterionNameTextView.setText(evaluationCriterion.getName());
        childViewHolder.mRatingBar.setRating(evaluationCriterion.getRating());
    }

}
