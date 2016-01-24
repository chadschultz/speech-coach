package com.inspiredpublicspeaking.speechcoach;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Display the criteria a speech is to be evaluated on. Show a list of groups of criteria that
 * can be expanded and collapsed to show the individual criteria in each group and their current
 * rating. Tap a criterion to view the detail screen for it and set/view the rating and other
 * information.
 *
 * Created by Chad Schultz on 1/11/2016.
 */
public class EvaluationCriterionListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private MainActivity mMainActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mMainActivity = (MainActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        //TODO: temp
        //        Bundle args = getArguments();
//        if (args != null) {
//            mLabel = args.getCharSequence("label", mLabel);
//        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.evaluation_criterion_group_list_fragment_menu, menu);

        // TODO: only have one expand/collapse button and animate it changing?

        // From http://stackoverflow.com/questions/26780046/menuitem-tinting-on-appcompat-toolbar
        // Tint
        // TODO: refactor tinting across Fragments
        for (int i = 0; i < menu.size(); i++) {
            final MenuItem menuItem = menu.getItem(i);
            Drawable drawable = menuItem.getIcon();
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawable, ContextCompat.getColor(mMainActivity, R.color.colorAppBarIcon));
            menuItem.setIcon(drawable);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_expand_all:
                ((EvaluationCriterionGroupAdapter) mAdapter).expandAllParents();
                break;
            case R.id.action_collapse_all:
                ((EvaluationCriterionGroupAdapter) mAdapter).collapseAllParents();
                break;
        }
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_evaluation_criterion_list, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.evaluation_criteria_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));

        mAdapter = new EvaluationCriterionGroupAdapter(this, Criteria.getCriteriaGroups(getActivity()));
//        adapter.setCustomParentAnimationViewId(R.id.criterion_group_expand_button);
//        adapter.setParentClickableViewAnimationDefaultDuration();
//        adapter.setParentAndIconExpandOnClick(true);
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    public void loadCriterion(EvaluationCriterion criterion) {
        mMainActivity.loadEvaluationCriterionDetailFragment(criterion);
    }
}
