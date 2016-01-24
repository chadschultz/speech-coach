package com.inspiredpublicspeaking.speechcoach;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private boolean mTwoPane;

    private Fragment mEvaluationListFragment;
    private Fragment mEvaluationCriterionListFragment;
    private Fragment mEvaluationCriterionDetailFragment;

    // TODO: always use a space after //

    // TODO: move classes into different packages

    // TODO: collapse animation plays when evaluation list first loads
    // TODO: change title in action bar from "Evaluation List"

    // TODO: bugs if you rotate and try to go back
    // TODO: bug if you go back and forth, tapping list items and hitting back?
    // TODO: swipe criteria left and right to change between them, like messages in Gmail

    // TODO: action bar button to expand/collapse all?

    // TODO: create evaluation will need fields for data like data, speaker name, speech title

    // TODO: fragment transitions - and make sure they work on back

    // TODO: remove EvaluationCriterionAdapter?

    // TODO: lines above and below expanded section of evaluation criteria, like in design guidelines?
    // TODO: and changing colors in expanded area

    // TODO: durable, unique IDs for criteria that work across devices and as evaluation forms change
    // TODO: how to handle optional criteria groups? It shouldn't hurt the speaker's score
    // if it's irrelevant

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar appBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(appBar);
        final ActionBar actionBar = getSupportActionBar();
        // Manual control of titles, so we can set it appropriately as user navigates on a tablet or phone
        actionBar.setTitle(getString(R.string.page_title_evaluation_list));

        // Determine whether we should use the wide, Master/Detail pane tablet layout or the
        // Single Fragment layout for phones by relying on the system to use the appropriate layout
        // file and checking for the existing of a view that only exists in one layout.
        mTwoPane = (findViewById(R.id.fragment_container_detail) != null);

        // Default: show the evaluation list
        //TODO: intelligent handling of where to pick up when the user opens the app
        loadEvaluationListFragment();

        // "Up" button handling from http://stackoverflow.com/a/20314570/967131
        // Listen for changes in the back stack
        getFragmentManager().addOnBackStackChangedListener(this);
        // Handle when activity is recreated like on orientation Change
        shouldDisplayHomeUp();
    }



    public void loadEvaluationListFragment() {
        boolean firstLoad = false;

        // Create new fragment
        if (mEvaluationListFragment == null) {
            mEvaluationListFragment = new EvaluationListFragment();
            firstLoad = true;
        }
        // TODO: pass args if necessary
        // TODO: should I set custom animations?

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        // Lay in a course for our fragment
        if (mTwoPane) {
            // Tablet layout
            // Move evaluation list fragment to overview (left) container and TBD to detail
            // (right) container
            transaction.replace(R.id.fragment_container_master, mEvaluationListFragment);
            // TODO: what should go in the detail container
        } else {
            // Phone layout
            // Change fragment in the only fragment container
            transaction.replace(R.id.fragment_container, mEvaluationListFragment);
        }

        if (!firstLoad) {
            // Allow the user to reverse the change with the back button
            transaction.addToBackStack(null);
        }
        // Make it so
        transaction.commit();
    }

    public void loadEvaluationCriterionListFragment() {
        // Create new fragment
        mEvaluationCriterionListFragment = new EvaluationCriterionListFragment();
        // TODO: am I supposed to use newInstance instead?
        //TODO: pass parameters
//        Bundle args = new Bundle();
//        args.putInt(ArticleFragment.ARG_POSITION, position);
//        newFragment.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Lay in a course for our fragment
        if (mTwoPane) {
            // Tablet layout
            // Move evaluation list fragment to overview (left) container and new evaluation fragment
            // to detail (right) container
            transaction.replace(R.id.fragment_container_master, mEvaluationListFragment);
            transaction.replace(R.id.fragment_container_detail, mEvaluationCriterionListFragment);
        } else {
            // Phone layout
            // Change fragment in the only fragment container
            transaction.replace(R.id.fragment_container, mEvaluationCriterionListFragment);
        }

        // Allow the user to reverse the change with the back button
        transaction.addToBackStack(null);
        // Make it so
        transaction.commit();
    }

    public void loadEvaluationCriterionDetailFragment(EvaluationCriterion criterion) {
        //TODO: logic to just UPDATE an existing fragment instead of needing to replace?

        mEvaluationCriterionDetailFragment = new EvaluationCriterionDetailFragment();
        // TODO: am I supposed to use newInstance instead?
        //TODO: pass parameters
        Bundle args = new Bundle();
        args.putSerializable(EvaluationCriterionDetailFragment.ARG_EVALUATION_CRITERION, criterion);
        mEvaluationCriterionDetailFragment.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Lay in a course for our fragment
        if (mTwoPane) {
            // Tablet layout
            // Move evaluation list fragment to overview (left) container and new evaluation fragment
            // to detail (right) container
            transaction.replace(R.id.fragment_container_master, mEvaluationCriterionListFragment);
            transaction.replace(R.id.fragment_container_detail, mEvaluationCriterionDetailFragment);
        } else {
            // Phone layout
            // Change fragment in the only fragment container
            transaction.replace(R.id.fragment_container, mEvaluationCriterionDetailFragment);
        }

        // Allow the user to reverse the change with the back button
        transaction.addToBackStack(null);
        // Make it so
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    public void shouldDisplayHomeUp(){
        if (getSupportActionBar() != null) {
            //Enable Up button only  if there are entries in the back stack
            boolean canback = getFragmentManager().getBackStackEntryCount() > 0;
            getSupportActionBar().setDisplayHomeAsUpEnabled(canback);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        getFragmentManager().popBackStack();
        return true;
    }

    @Override
    public boolean onNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        getFragmentManager().popBackStack();
        return true;
    }
}
