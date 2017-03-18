package com.hmmelton.kibbble;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hmmelton.kibbble.models.Profile;
import com.hmmelton.kibbble.utils.JsonUtil;
import com.hmmelton.kibbble.views.Card;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.swipeView)
    SwipePlaceHolderView mSwipeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.swipe_out_msg_view));


        for(Profile profile : JsonUtil.loadProfiles(this.getApplicationContext())){
            mSwipeView.addView(new Card(this, profile, mSwipeView));
        }


    }
}
