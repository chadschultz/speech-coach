package com.inspiredpublicspeaking.speechcoach;

import java.io.Serializable;

/**
 * Created by Chad Schultz on 1/11/2016.
 */
public class EvaluationCriterion implements Serializable {
    private static final long serialVersionUID = 7125368517801765521L;

    private String mName;
    private float mRating;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public float getRating() {
        return mRating;
    }

    public void setRating(float rating) {
        mRating = rating;
    }
}
