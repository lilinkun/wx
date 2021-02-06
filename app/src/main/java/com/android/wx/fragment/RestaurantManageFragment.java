package com.android.wx.fragment;

import com.android.wx.R;
import com.android.wx.adapter.TabPageAdapter;
import com.android.wx.base.Fragment.BaseFragment;
import com.android.wx.weight.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @ClassName RestaurantManageFragment
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/5 12:07
 */
public class RestaurantManageFragment extends BaseFragment {

    @BindView(R.id.vp_rest_manager)
    CustomViewPager vpRestManager;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public int getlayoutId() {
        return R.layout.fragment_restaurant_manager;
    }

    @Override
    public void initEventAndData() {

        ArrayList<String> mTitles = new ArrayList<>();
        fragments.add(new BusinessHoursFragment());
        for (int i = 0; i < fragments.size();i++){
            mTitles.add("");
        }
        TabPageAdapter pageAdapter = new TabPageAdapter(getActivity().getSupportFragmentManager(), fragments, mTitles);
        vpRestManager.setAdapter(pageAdapter);


    }
}
