package com.yangaiche.yackeeper.order.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.yangaiche.yackeeper.R;
import com.yangaiche.yackeeper.base.Constants;
import com.yangaiche.yackeeper.base.MVPBaseActivity;
import com.yangaiche.yackeeper.bean.Order;
import com.yangaiche.yackeeper.order.presenter.OrderCommentPresenter;
import com.yangaiche.yackeeper.utils.TimeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by Administrator on 2015/9/23.
 */
public class OrderCommentActivity extends MVPBaseActivity<IOrderCommentView, OrderCommentPresenter> implements Constants, IOrderCommentView{

    private static final String ORDER_ID = "order_id";
    @Bind(R.id.empty_view)
    TextView empty_view;
    @Bind(R.id.comment_ll)
    LinearLayout comment_ll;
    @Bind(R.id.user_icon_img)
    ImageView user_icon_img;
    @Bind(R.id.user_name_tv)
    TextView user_name_tv;
    @Bind(R.id.time_tv)
    TextView time_tv;
    @Bind(R.id.comment_tv)
    TextView comment_tv;
    @Bind(R.id.service_rating)
    RatingBar service_rating;
    @Bind(R.id.keeper_rating)
    RatingBar keeper_rating;
    @Bind(R.id.service_rating_tv)
    TextView service_rating_tv;
    @Bind(R.id.keeper_rating_tv)
    TextView keeper_rating_tv;
    @Bind(R.id.progress)
    MaterialProgressBar progress;

    private Long mOrder_id;

    public static Intent makeIntent(Context context, Long order_id){
        Intent intent = new Intent(context, OrderCommentActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(ORDER_ID, order_id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOrder_id = getIntent().getLongExtra(ORDER_ID, -1);
        setContentView(R.layout.activity_order_comment);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.loadOrderData(USER_TYPE, mOrder_id);
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void updateOrderData(Order order) {
        if (order != null && order.client_feedback != null) {
            if(!order.client_feedback.if_feedback_committed){
                empty_view.setVisibility(View.VISIBLE);
            }else {
                comment_ll.setVisibility(View.VISIBLE);
                if (order.client_basic != null) {
                    user_name_tv.setText(order.client_basic.name);
                }
                if (order.end_time != null && !TextUtils.isEmpty(order.end_time)) {
                    String[] strs = TimeUtils.getDateFromUTC(order.end_time);
                    if (strs != null && strs.length == 2)
                        time_tv.setText(strs[0] + "  " + strs[1]);
                }
                comment_tv.setText(order.client_feedback.comment);
                keeper_rating.setProgress(order.client_feedback.keeper_stars);
                keeper_rating_tv.setText(getString(R.string.ratting_msg_str, order.client_feedback.keeper_stars));
                service_rating.setProgress(order.client_feedback.order_stars);
                service_rating_tv.setText(getString(R.string.ratting_msg_str, order.client_feedback.order_stars));
            }
        }
    }

    @Override
    protected OrderCommentPresenter createPresenter() {
        return new OrderCommentPresenter();
    }
}
