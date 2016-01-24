package com.inspiredpublicspeaking.speechcoach;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Chad Schultz on 1/4/2016.
 */
public class EvaluationAdapter extends RecyclerView.Adapter<EvaluationAdapter.ViewHolder> {
    private List<Evaluation> mEvaluations;

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(final View v) {
            super(v);
            mAvatarImageView = (ImageView) v.findViewById(R.id.avatar_imageview);
            mSpeakerTextView = (TextView) v.findViewById(R.id.speaker_textview);
            mTitleTextView = (TextView) v.findViewById(R.id.speech_title_textview);
            mSummaryTextView = (TextView) v.findViewById(R.id.summary_textview);
            mDateTextView = (TextView) v.findViewById(R.id.date_textview);
            mScoreTextView = (TextView) v.findViewById(R.id.score_textview);
        }

        public ImageView mAvatarImageView;
        public TextView mSpeakerTextView;
        public TextView mTitleTextView;
        public TextView mSummaryTextView;
        public TextView mDateTextView;
        public TextView mScoreTextView;
    }

    public EvaluationAdapter(List<Evaluation> evaluations) {
        mEvaluations = evaluations;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_evaluation, parent, false);
        //TODO: temp
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Evaluation evaluation = mEvaluations.get(position);

        //TODO: make text thinner/lighter/sharper, like Gmail

        Context context = holder.mAvatarImageView.getContext();

        holder.mAvatarImageView.setImageDrawable(generateAvatar(context, evaluation.getSpeakerName()));

        //TODO: actually use rows
//        final String scoreString = NumberFormat.getPercentInstance().format(evaluation.getScore());
//        holder.mScoreTextView.setText(scoreString);
    }

    private Drawable generateAvatar(Context context, String name) {
        // TODO: make it more performant. Should the drawable be part of the model?

        // TODO: beware of color ending up too light--safeguard to prevent all three colors from being really high.
        // TODO: base it on name, not random

        // TODO:
//        String avatarText = name.substring(0,1);
        String avatarText = "S";
        // TODO: remember to convert to uppercase

        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        int color = Color.argb(255, red, green, blue);

        // TODO: use avatar image size dimen
        Bitmap bitmap = Bitmap.createBitmap(40, 40, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        // TODO: temp
//        canvas.drawColor(ContextCompat.getColor(context, R.color.colorAccent));
        canvas.drawColor(color);
        Paint textPaint = new Paint();
        textPaint.setColor(ContextCompat.getColor(context, R.color.colorAvatarText));
        textPaint.setTextAlign(Paint.Align.CENTER);
        //setTypeface()
        textPaint.setTextSize(26);
        textPaint.setAntiAlias(true);
        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));
        canvas.drawText(avatarText, xPos, yPos, textPaint);
        BitmapDrawable drawable = new BitmapDrawable(context.getResources(), bitmap);

        return drawable;
    }

    @Override
    public int getItemCount() {
        return mEvaluations.size();
    }


}
