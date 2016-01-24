package com.inspiredpublicspeaking.speechcoach;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * List saved evaluations, whether created by the user or others. Show sorted by speaker evaluated.
 * Easily create a new evaluation or import an evaluation file.
 * Created by Chad Schultz on 1/1/2016.
 */
public class EvaluationListFragment extends Fragment {
    // TODO: save evaluations
    // TODO: message when no evaluations have been created yet containing basic instructions
    // TODO: tie in with Contacts to use contact images when possible

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

//        Bundle args = getArguments();
//        if (args != null) {
//            mLabel = args.getCharSequence("label", mLabel);
//        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.evaluation_list, menu);

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
            case R.id.action_open_file:
                // The user wants to add an evaluation from a file on the phone
                // TODO: temp
                Toast.makeText(mMainActivity, "open file", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_evaluation_list, container, false);
        //TODO: temp
//        View tv = v.findViewById(R.id.text);
//        ((TextView)tv).setText(mLabel != null ? mLabel : "(no label)");
//        tv.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.gallery_thumb));

        mRecyclerView = (RecyclerView) v.findViewById(R.id.evaluation_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));

        //TODO: temp junk rows for testing
        final List<Evaluation> evaluations = new ArrayList<Evaluation>();
        Evaluation tempEval = new Evaluation();
        for (int i = 0; i < 100; i++) {
            evaluations.add(tempEval);
        }
        // specify an adapter (see also next example)
        mAdapter = new EvaluationAdapter(evaluations);
        mRecyclerView.setAdapter(mAdapter);


        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.new_evaluation_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivity.loadEvaluationCriterionListFragment();
            }
        });

        return v;
    }

}