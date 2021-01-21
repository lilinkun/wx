package com.android.wx.activity;

import android.view.View;

import com.android.wx.R;
import com.android.wx.adapter.MenuOrderAdapter;
import com.android.wx.adapter.TabPageAdapter;
import com.android.wx.base.activity.BaseActivity;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.db.DBManager;
import com.android.wx.event.EventCenter;
import com.android.wx.fragment.MenuFragment;
import com.android.wx.interf.IPreOrderListener;
import com.android.wx.model.MenuInfo;
import com.android.wx.model.MenuTypeBean;
import com.android.wx.presenter.PreOrderPresenter;
import com.android.wx.view.IPreOrderView;
import com.android.wx.weight.SpaceItemDecoration;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

public class PreOrderActivity extends MvpActivity<IPreOrderView, PreOrderPresenter> implements IPreOrderListener {


    @BindView(R.id.order_list_tablayou)
    SlidingTabLayout orderListTablayou;
    @BindView(R.id.order_list_vp)
    ViewPager orderListVp;
    @BindView(R.id.rv_menu_list)
    RecyclerView rvMenuList;

    private MenuFragment menuFragment;
    private MenuOrderAdapter menuOrderAdapter;
    private ArrayList<MenuInfo> menuInfos;


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

        MenuTypeBean menuTypeBean = new MenuTypeBean();
        menuTypeBean.setMenuType(getString(R.string.tv_menu_type1));
        menuTypeBean.setMenuTypeId("1231");
//        DBManager.getInstance(this).insertMenuTypeBean(menuTypeBean);


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
        rvMenuList.addItemDecoration(new SpaceItemDecoration(3,SpaceItemDecoration.LINEARLAYOUT));

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
            menuOrderAdapter = new MenuOrderAdapter(this,menuInfos);
            rvMenuList.setAdapter(menuOrderAdapter);
        }else {
            menuInfos.add(menuInfo);
            menuOrderAdapter.setData(menuInfos);
        }
    }
}
