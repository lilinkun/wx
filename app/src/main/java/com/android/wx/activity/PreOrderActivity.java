package com.android.wx.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.wx.R;
import com.android.wx.adapter.MenuOrderAdapter;
import com.android.wx.adapter.TabPageAdapter;
import com.android.wx.base.MyApplication;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.db.DBManager;
import com.android.wx.event.EventCenter;
import com.android.wx.fragment.MenuFragment;
import com.android.wx.interf.IPreOrderListener;
import com.android.wx.model.MenuInfo;
import com.android.wx.model.MenuTypeBean;
import com.android.wx.model.OrderInfoBean;
import com.android.wx.model.Table;
import com.android.wx.presenter.PreOrderPresenter;
import com.android.wx.contract.IPreOrderView;
import com.android.wx.utils.UToast;
import com.android.wx.utils.WxUtil;
import com.android.wx.view.PreOrderChooseNumDialog;
import com.android.wx.view.PreOrderNumberDialog;
import com.android.wx.weight.SpaceItemDecoration;
import com.flyco.tablayout.SlidingTabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;

public class PreOrderActivity extends MvpActivity<IPreOrderView, PreOrderPresenter> implements IPreOrderListener, PreOrderNumberDialog.OnPreOrderListener, MenuOrderAdapter.OnMenuOrderListener, PreOrderChooseNumDialog.OnOreOrderChooseNumListener {

    @BindView(R.id.order_list_tablayou)
    SlidingTabLayout orderListTablayou;
    @BindView(R.id.order_list_vp)
    ViewPager orderListVp;
    @BindView(R.id.rv_menu_list)
    RecyclerView rvMenuList;
    @BindView(R.id.tv_no_food)
    TextView tvNoFood;
    @BindView(R.id.tv_preorder_time)
    TextView tvPreorderTime;
    @BindView(R.id.tv_preorder_date)
    TextView tvPreorderDate;
    @BindView(R.id.ll_preorder_bottom)
    LinearLayout llPreorderBottom;
    @BindView(R.id.tv_change_start_order)
    TextView tvChangeStartOrder;
    @BindView(R.id.tv_preorder_total_price)
    TextView tvPreorderTotalPrice;
    @BindView(R.id.tv_tax_price)
    TextView tvTaxPrice;
    @BindView(R.id.tv_preorder_total)
    TextView tvPreorderTotal;
    @BindView(R.id.tv_goods_num)
    TextView tvGoodsNum;
    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.tv_pre_order_customer_num)
    TextView tvPreOrderCustomerNum;
    @BindView(R.id.tv_unsave)
    TextView tvUnsave;
    @BindView(R.id.tv_pre_order_table_num)
    TextView tvPreOrderTableNum;
    @BindView(R.id.tv_orderer)
    TextView tvOrderer;

    private MenuFragment menuFragment;
    private MenuOrderAdapter menuOrderAdapter;
    private List<MenuInfo> menuInfos;
    private PreOrderNumberDialog preOrderNumberDialog;
    private MenuInfo clickFoodMenu;
    private Table table;
    private int num;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd yyyy");
    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");

//    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyymmdd");

    @Override
    public View getLoadingTargeView() {
        return null;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_pre_order;
    }

    @Override
    protected void onReceiverEvent(EventCenter eventCenter) {

    }

    @Override
    public void initData() {

        table = (Table)getIntent().getSerializableExtra("table");

        MenuTypeBean menuTypeBean = new MenuTypeBean();
        menuTypeBean.setMenuType(getString(R.string.tv_menu_type1));
        menuTypeBean.setMenuTypeId("1231");
        DBManager.getInstance(this).insertMenuTypeBean(menuTypeBean);

        tvPreOrderTableNum.setText(table.getTableNum() + getString(R.string.table));

        if (MyApplication.userInfo != null){
            tvOrderer.setText(MyApplication.userInfo.getName());
        }

        Date date = new Date();
        WxUtil.orderNum += 1;
        tvOrderId.setText(date.getTime()/1000 +""+ WxUtil.orderNum);
        tvPreorderDate.setText(simpleDateFormat.format(date));
        tvPreorderTime.setText(simpleDateFormat1.format(date));

        List<MenuTypeBean> menuTypeBeans = DBManager.getInstance(this).queryMenuTypeBean();

        ArrayList<String> mTitles = new ArrayList<>();

        mTitles.add(menuTypeBeans.get(0).getMenuType());

        List<Fragment> fragments = new ArrayList<>();

        menuFragment = new MenuFragment();

        menuFragment.setOnPreOrderClickListener(this);

        fragments.add(menuFragment);

        TabPageAdapter pageAdapter = new TabPageAdapter(getSupportFragmentManager(), fragments, mTitles);
        orderListVp.setAdapter(pageAdapter);
        orderListTablayou.setViewPager(orderListVp);
        orderListVp.setCurrentItem(0, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        rvMenuList.setLayoutManager(linearLayoutManager);
        rvMenuList.addItemDecoration(new SpaceItemDecoration(10,SpaceItemDecoration.LINEARLAYOUT));

        preOrderNumberDialog = new PreOrderNumberDialog(this,this);
        preOrderNumberDialog.show();

    }

    @NonNull
    @Override
    public PreOrderPresenter createPresenter() {
        return new PreOrderPresenter();
    }

    @Override
    public void onPreOrderClick(MenuInfo menuInfo) {


        if (menuOrderAdapter == null) {
            menuInfos = new ArrayList<>();
            menuInfos.add(menuInfo);
            menuOrderAdapter = new MenuOrderAdapter(this,menuInfos,true);
            menuOrderAdapter.setListener(this);
            rvMenuList.setAdapter(menuOrderAdapter);

            tvNoFood.setVisibility(View.GONE);
            llPreorderBottom.setVisibility(View.VISIBLE);
            tvChangeStartOrder.setVisibility(View.GONE);
        }else {

            if(menuInfos.contains(menuInfo)){
                if (clickFoodMenu != null) {
                    if (clickFoodMenu == menuInfo) {
                        menuOrderAdapter.setClickItem(null);
                        clickFoodMenu = null;
                    }
                }
                menuInfos.remove(menuInfo);
            }else {
                menuInfos.add(menuInfo);
            }
            menuOrderAdapter.setData(menuInfos);


            if (menuInfos.size() > 0){
                tvNoFood.setVisibility(View.GONE);
                tvChangeStartOrder.setVisibility(View.GONE);
                llPreorderBottom.setVisibility(View.VISIBLE);
            }else {
                tvNoFood.setVisibility(View.VISIBLE);
                tvChangeStartOrder.setVisibility(View.VISIBLE);
                llPreorderBottom.setVisibility(View.GONE);
            }
        }
        settle();

    }

    @Override
    public void onCustomerNum(int num) {
        this.num = num;
        tvPreOrderCustomerNum.setText(num + getString(R.string.person));
    }

    @Override
    public void onItemClick(int position) {
        clickFoodMenu = menuInfos.get(position);
        menuOrderAdapter.setClickItem(menuInfos.get(position));
        settle();
    }

    @Override
    public void onRemarksListener(int position, String remarks) {
        List<String> strings = menuInfos.get(position).getMenuRemarks();
        if (strings == null){
            strings = new ArrayList<>();
        }
        strings.add(remarks);
        menuInfos.get(position).setMenuRemarks(strings);
        settle();
    }

    @OnClick({R.id.tv_pre_order_plus,R.id.tv_pre_order_reduce,R.id.tv_pre_order_num,R.id.tv_pre_order_change_price,R.id.tv_preorder_exit,R.id.tv_preorder_delete,R.id.tv_preorder_go_kitchen,R.id.tv_preorder_save
            ,R.id.tv_customer_num,R.id.tv_pre_order_report})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_pre_order_plus:
                if(clickFoodMenu == null){
                    UToast.show(this,R.string.no_choose_food_tip);
                }else {
                    for(int i = 0;i<menuInfos.size();i++){
                        if (menuInfos.get(i) == clickFoodMenu){
                            menuInfos.get(i).setMenuFoodNum(menuInfos.get(i).getMenuFoodNum() + 1);
                            menuOrderAdapter.setData(menuInfos);
                        }
                    }
                }
                settle();
                break;
            case R.id.tv_pre_order_reduce:
                if(clickFoodMenu == null){
                    UToast.show(this,R.string.no_choose_food_tip);
                }else {
                    for(int i = 0;i<menuInfos.size();i++){
                        if (menuInfos.get(i) == clickFoodMenu){
                            if (menuInfos.get(i).getMenuFoodNum() == 1){
                                UToast.show(this,R.string.no_reduce_food);
                            }else {
                                menuInfos.get(i).setMenuFoodNum(menuInfos.get(i).getMenuFoodNum() - 1);
                                menuOrderAdapter.setData(menuInfos);
                            }
                        }
                    }
                }
                settle();
                break;
            case R.id.tv_pre_order_num:
                if(clickFoodMenu == null){
                    UToast.show(this,R.string.no_choose_food_tip);
                }else {
                    PreOrderChooseNumDialog preOrderChooseNumDialog = new PreOrderChooseNumDialog(this,this,0);
                    preOrderChooseNumDialog.show();
                }

                break;
            case R.id.tv_pre_order_change_price:
                break;

            case R.id.tv_preorder_exit:

                finish();

                break;
            case R.id.tv_preorder_delete:


                if(clickFoodMenu == null) {
                    UToast.show(this, R.string.delete_food_tip);
                }else {
                    for (int i = 0; i < menuInfos.size(); i++) {
                        if (menuInfos.get(i) == clickFoodMenu) {

                            if (menuFragment != null){
                                menuFragment.onRemoveFood(clickFoodMenu);
                            }

                            menuInfos.remove(clickFoodMenu);
                            menuOrderAdapter.setData(menuInfos);

                        }
                    }
                }
                settle();

                break;

            case R.id.tv_preorder_go_kitchen:

                if (num == 0){
                    UToast.show(this,R.string.please_input_customer_num);
                    return;
                }


                if (menuInfos != null && menuInfos.size() > 0) {

                    for (MenuInfo menuInfo : menuInfos) {
                        menuInfo.setOrderId(tvOrderId.getText().toString());
                        DBManager.getInstance(this).insertOrderInfo(menuInfo);

                    }

                    table.setStatue(getString(R.string.preorder_go_kitchen));
                    long timeStr = (new Date()).getTime();
                    table.setTime(timeStr);
//                    table.setTimeOld();
                    table.setOrderNumber(tvOrderId.getText().toString());
                    table.setTotalAmountPrice(Double.valueOf(tvPreorderTotalPrice.getText().toString()));
                    table.setPersionNo(num+"");
                    DBManager.getInstance(this).updateTable(table);

                    OrderInfoBean orderInfoBean = new OrderInfoBean(tvOrderId.getText().toString(),String.valueOf(timeStr),
                            Integer.valueOf(tvPreorderTotal.getText().toString()),Integer.valueOf(tvGoodsNum.getText().toString()),Double.valueOf(tvPreorderTotalPrice.getText().toString()));
                    DBManager.getInstance(this).insertOrder(orderInfoBean);

                    setResult(RESULT_OK);
                    finish();

                }else {
                    UToast.show(this,getString(R.string.pleasr_choose_food));
                }

                break;

            case R.id.tv_preorder_save:

                tvUnsave.setText(R.string.preorder_save);

                break;

            case R.id.tv_customer_num:

                preOrderNumberDialog.show();

                break;

            case R.id.tv_pre_order_report:

                Intent intent = new Intent(this,ReportActivity.class);
                startActivity(intent);


                break;
        }
    }


    //结算价格
    private void settle(){
        double totalPrice = 0;
        int stypeInt = 0;
        int totalInt = 0;

        if (menuInfos != null && menuInfos.size() > 0) {
            for (MenuInfo menuInfo : menuInfos) {
                stypeInt++;
                totalInt += menuInfo.getMenuFoodNum();
                totalPrice += menuInfo.getMenuPrice() * menuInfo.getMenuFoodNum();
            }
        }
        tvPreorderTotalPrice.setText(totalPrice + "");
        tvTaxPrice.setText(totalPrice * 0.1 + "");
        tvPreorderTotal.setText(stypeInt + "");
        tvGoodsNum.setText(totalInt + "");
    }

    @Override
    public void onChooseNumClick(int num) {
        clickFoodMenu.setMenuFoodNum(num);
        for(int i = 0;i<menuInfos.size();i++){
            if (menuInfos.get(i) == clickFoodMenu){
                menuInfos.get(i).setMenuFoodNum(num);
                menuOrderAdapter.setData(menuInfos);
            }
        }
        settle();
    }
}
