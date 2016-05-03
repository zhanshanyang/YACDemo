package com.yangaiche.yackeeper.order.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yangaiche.yackeeper.R;
import com.yangaiche.yackeeper.base.Constants;
import com.yangaiche.yackeeper.base.MVPBaseActivity;
import com.yangaiche.yackeeper.bean.Inspection;
import com.yangaiche.yackeeper.bean.Order;
import com.yangaiche.yackeeper.bean.Product;
import com.yangaiche.yackeeper.order.adapter.OrderDetailsAdapter;
import com.yangaiche.yackeeper.order.presenter.OrderDetailsPresenter;
import com.yangaiche.yackeeper.utils.MySharedPreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class OrderDetailsActivity extends MVPBaseActivity<IOrderDetailsView, OrderDetailsPresenter> implements Constants, IOrderDetailsView, View.OnClickListener {

	@Bind(R.id.recyclerview)
	XRecyclerView recyclerview;
	@Bind(R.id.consumer_contact_tv)
	TextView consumer_contact_tv;
	@Bind(R.id.phone_call_img)
	ImageView phone_call_img;
	@Bind(R.id.sms_call_img)
	ImageView sms_call_img;
	@Bind(R.id.gps_toggle_img)
	ImageView gps_toggle_img;
	@Bind(R.id.paying_bill_bt)
	Button paying_bill_bt;
	@Bind(R.id.progress)
	MaterialProgressBar progress;
	@Bind(R.id.text_empty)
	TextView text_empty;

	private Order mOrder;
	private long mOrder_id;
	private List<Product> mProducts;
	private OrderDetailsAdapter orderDetailsAdapter;
	private boolean suppliesFlag = false, additionsFlag = false;
	private Product mPick_car_Pd, mFirst_check_Pd, mAll_check_Pd, mReturn_car_Pd;

	public static Intent makeIntent(Context context, Long order_id, int code){
		Intent intent = new Intent(context, OrderDetailsActivity.class);
		intent.putExtra(ORDER_ID, order_id);
		intent.putExtra("from_code", code);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_details);
		ButterKnife.bind(this);
		mOrder_id = getIntent().getLongExtra(ORDER_ID, -1);
		initView();
	}

	protected void initView() {
		phone_call_img.setOnClickListener(this);
		sms_call_img.setOnClickListener(this);
		gps_toggle_img.setOnClickListener(this);
		/*if(App.gpsOn && (mOrder_id - App.gpsOrderid)==0){
			gps_toggle_img.setImageResource(R.drawable.gps_location_pressed);
		}*/
		paying_bill_bt.setOnClickListener(this);

		initProducts();
		StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
		recyclerview.setLayoutManager(gridLayoutManager);
		orderDetailsAdapter = new OrderDetailsAdapter(this, mProducts);
		recyclerview.setAdapter(orderDetailsAdapter);
		recyclerview.setLoadingMoreEnabled(false);
		recyclerview.setEmptyView(text_empty);
		recyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
		recyclerview.setArrowImageView(R.drawable.iconfont_downgrey);
		recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
			@Override
			public void onRefresh() {
				initNetData(mOrder_id);
			}

			@Override
			public void onLoadMore() {

			}
		});
		recyclerview.setRefreshing(true);
	}

	private void initProducts() {
		mProducts = new ArrayList<Product>();
		mPick_car_Pd = new Product(R.drawable.grid_pick_car, "接车", false);
		mFirst_check_Pd = new Product(R.drawable.grid_first_check, "初检", false);
		mAll_check_Pd = new Product(R.drawable.grid_all_check, "全检", false);
		mReturn_car_Pd = new Product(R.drawable.grid_return_car, "还车", false);
	}

	private void initNetData(long order_id) {
		mPresenter.loadOrderData(USER_TYPE, order_id);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
//		case R.id.phone_call_img:
//			if(mOrder.client_basic != null)
//				Utils2.phoneCallCustomer(this, mOrder.client_basic.phone_number);
//			break;
//		case R.id.sms_call_img:
//			if(mOrder.client_basic != null)
//				Utils2.sms2Customer(this, mOrder.client_basic.phone_number);
//			break;
//		case R.id.paying_bill_bt:
//			// go to paying page.
//			Intent intent = new Intent(this, AccountActivity.class);
//			intent.putExtra(ORDER_ID, mOrder_id);
//			startActivity(intent);
//			break;
//		case R.id.call_service_img:
//			if(mOrder.operator != null && mOrder.operator.phone_number == null){
//				Toast.makeText(OrderDetailsActivity.this, "此客服电话没有登记", Toast.LENGTH_SHORT).show();
//			}else if(mOrder.operator != null){
//				Utils2.phoneCallCustomer(this, mOrder.operator.phone_number);
//			}
//			break;
//		case R.id.gps_toggle_img:
//			gpsToggleClick();
//			break;
		default:
			break;
		}
	}

	/*private void gpsToggleClick() {
		Intent intent = new Intent(this, BDMapLocationService.class);
		if(App.gpsOn && (mOrder_id - App.gpsOrderid)==0){//需要关闭
			stopService(intent);
			mGps_toggle_img.setImageResource(R.drawable.gps_location_normal);
			App.gpsOn = false;
			App.gpsOrderid = 0;
		}else*//* if(!App.gpsOn && (mOrder_id - App.gpsOrderid)!=0)*//*{//需要打开
			intent.putExtra("order_id", mOrder_id);
			startService(intent);
			mGps_toggle_img.setImageResource(R.drawable.gps_location_pressed);
			App.gpsOn = true;
			App.gpsOrderid = mOrder_id;
		}
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent();
		intent.putExtra(ORDER_ID, mOrder_id);
		if(position == 0){
			intent.setClass(this, PickCarActivity.class);
			intent.putExtra(FROM_CODE, PICKCAR_FLAG);
		}else if(position ==1){
			intent.setClass(this, FirstCheckActivity.class);
		}else if(position ==2){
			intent.setClass(this, AllCheckActivity.class);
		}else if(position ==3){
			intent.setClass(this, PickCarActivity.class);
			intent.putExtra(FROM_CODE, RETURNCAR_FLAG);
		}else if(suppliesFlag && position ==4){
			intent.setClass(this, SuppliesActivity.class);
		}else if(additionsFlag && position == myGridAdapter.getCount() - 1){
			intent.setClass(this, AdditionsActivity.class);
		}else{
			intent.setClass(this, ProductCheckActivity.class);
			intent.putExtra(PRODUCT_ID, mProducts.get(position).id);
		}
		startActivity(intent);
	}*/

	@Override
	protected OrderDetailsPresenter createPresenter() {
		return new OrderDetailsPresenter();
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
	public void stopRefresh() {
		recyclerview.refreshComplete();
	}

	@Override
	public void updateOrderData(Order order) {
		mOrder = order;
		if(order.client_basic != null){
			String name = order.client_basic.name;
//			mActionbar_title.setText(getString(R.string.orderdetails_str, name));
			consumer_contact_tv.setText(getString(R.string.consumer_contact_str, name));
		}
//		if(mOrder.operator != null)
//			call_service_tv.setText(getString(R.string.call_service_str, (mOrder.operator.name == null)?"":mOrder.operator.name));
		updatePrducts(order);
		orderDetailsAdapter.updateData(mProducts, suppliesFlag, additionsFlag);
	}

	private void updatePrducts(Order order) {
		if(mProducts != null){
			mProducts.clear();
			suppliesFlag = false;
		}
		if(order != null){
			if(!TextUtils.isEmpty(order.take_time)){
				mPick_car_Pd.complete = true;
				mPick_car_Pd.product_name = "完成接车";
			}
			for(Inspection inspection: order.inspections){
				String type = inspection.car_inspection_type;
				if("surface".equals(type)){
					mFirst_check_Pd.complete = inspection.complete;
				}else if("garage".equals(type)){
					mAll_check_Pd.complete = inspection.complete;
				}
			}
			if(!TextUtils.isEmpty(order.give_back_start_time) && TextUtils.isEmpty(order.give_back_time)){
				mReturn_car_Pd.complete = false;
				mReturn_car_Pd.product_name = "正在还车";
			}else if(!TextUtils.isEmpty(order.give_back_time)){
				mReturn_car_Pd.complete = true;
				mReturn_car_Pd.product_name = "完成还车";
			}
			mProducts.add(mPick_car_Pd);
			mProducts.add(mFirst_check_Pd);
			mProducts.add(mAll_check_Pd);
			mProducts.add(mReturn_car_Pd);
			// 修理厂
			if(order.suppliers != null && order.suppliers.size() > 0){
				Product product = new Product(R.drawable.grid_supplier, "修理厂");
				suppliesFlag = true;
				mProducts.add(product);
			}
			for (Product product : order.products) {
				if (product.disabled) {
					continue;
				}
				mProducts.add(product);
			}
			//TODO  增项
			additionsFlag = false;
			if(order.increase_products != null && order.increase_products.size() > 0){//说明已经提交过增项，那么就有叉
				for(Product item: order.increase_products){
					if(!item.disabled && item.selection_mode == 3){
						mProducts.add(item);
					}
				}
				Product product = new Product(R.drawable.grid_addition, "增项");
				product.complete = true;
				additionsFlag = true;
				mProducts.add(product);
			}else if(!TextUtils.isEmpty(MySharedPreferences.getInstance(this).getStr(ADDITIONS_STR+mOrder_id))){//如果不为空，那么就有增项,但是还没有提交
				Product product = new Product(R.drawable.grid_addition, "增项");
				product.complete = false;
				additionsFlag = true;
				mProducts.add(product);
			}
		}
	}
}
