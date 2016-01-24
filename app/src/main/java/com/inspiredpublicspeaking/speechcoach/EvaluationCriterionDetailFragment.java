package com.inspiredpublicspeaking.speechcoach;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * View and update details for a specific criterion.
 *
 * Created by Chad Schultz on 1/18/2016.
 */
public class EvaluationCriterionDetailFragment extends Fragment {
    // TODO: swipe left and right to change criteria, as Gmail changes messages
    // TODO: text memo
    // TODO: check off details for quick feedback, such as "Quiet" for the "Volume" criterion

    public final static String ARG_EVALUATION_CRITERION = "EVALUATION_CRITERION";

    private MainActivity mMainActivity;

    private EvaluationCriterion mCriterion;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mMainActivity = (MainActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mCriterion = (EvaluationCriterion) args.get(ARG_EVALUATION_CRITERION);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_evaluation_criterion_details, container, false);

        TextView criterionNameTextView = (TextView) v.findViewById(R.id.criterion_name_textview);
        criterionNameTextView.setText(mCriterion.getName());

        RatingBar ratingBar = (RatingBar) v.findViewById(R.id.rating_bar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.e("temp", "onRatingChanged: " + rating);
            }
        });
        Log.e("temp", "new rating: " + mCriterion.getRating() + " old rating: " + ratingBar.getRating());
        ratingBar.setRating(mCriterion.getRating());
//            ratingBar.setRating(4.0f);
        ratingBar.invalidate();

        return v;
    }
}
