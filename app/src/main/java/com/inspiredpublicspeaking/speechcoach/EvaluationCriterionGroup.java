package com.inspiredpublicspeaking.speechcoach;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chad Schultz on 1/19/2016.
 */
public class EvaluationCriterionGroup implements ParentListItem, Serializable {
    private static final long serialVersionUID = 8705163137623005309L;

    private String mName;
    private int iconResId;
    private List<EvaluationCriterion> mCriteria;

    public List<EvaluationCriterion> getCriteria() {
        return mCriteria;
    }

    public void setCriteria(List<EvaluationCriterion> criteria) {
        mCriteria = criteria;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public List<?> getChildItemList() {
        return getCriteria();
    }

    @Override
    public boolean isInitiallyExpanded() {
        // TODO: improve using sharedpreferences
        return false;
    }
}
