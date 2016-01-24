package com.inspiredpublicspeaking.speechcoach;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chad Schultz on 1/4/2016.
 */
public class Evaluation implements Serializable {
    private static final long serialVersionUID = 6253293605960027865L;

    //TODO: does everything need to implement Serializable?

    private String mSpeakerName;
    private String mSpeechTitle;
    private String mEvaluationBody; //TODO
    private Date mSpeechDate;
    //TODO: should I do anything with these two?
    private Date mEvaluationCreationDate;
    private Date mEvaluationModifiedDate;

    public String getSpeakerName() {
        return mSpeakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.mSpeakerName = speakerName;
    }

    public String getSpeechTitle() {
        return mSpeechTitle;
    }

    public void setSpeechTitle(String speechTitle) {
        this.mSpeechTitle = speechTitle;
    }

    public String getEvaluationBody() {
        return mEvaluationBody;
    }

    public void setEvaluationBody(String evaluationBody) {
        this.mEvaluationBody = evaluationBody;
    }

    public Date getSpeechDate() {
        return mSpeechDate;
    }

    public void setSpeechDate(Date speechDate) {
        this.mSpeechDate = speechDate;
    }

    public Date getEvaluationCreationDate() {
        return mEvaluationCreationDate;
    }

    public void setEvaluationCreationDate(Date evaluationCreationDate) {
        mEvaluationCreationDate = evaluationCreationDate;
    }

    public Date getEvaluationModifiedDate() {
        return mEvaluationModifiedDate;
    }

    public void setEvaluationModifiedDate(Date evaluationModifiedDate) {
        mEvaluationModifiedDate = evaluationModifiedDate;
    }

    public String getSummary() {
        //TODO: temp
        return mEvaluationBody;
    }

    public Double getScore() {
        //TODO: temp
        return 0.7612345;
    }
}
