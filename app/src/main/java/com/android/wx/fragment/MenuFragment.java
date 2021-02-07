package com.android.wx.fragment;

import com.android.wx.R;
import com.android.wx.adapter.PreOrderMenuAdapter;
import com.android.wx.base.Fragment.BaseFragment;
import com.android.wx.interf.IPreOrderListener;
import com.android.wx.model.MenuInfo;
import com.android.wx.weight.SpaceItemDecoration;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class MenuFragment extends BaseFragment implements PreOrderMenuAdapter.OnItemPreOrderClick {

    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;

    private IPreOrderListener iPreOrderListener;
    private PreOrderMenuAdapter preOrderMenuAdapter;
    private ArrayList<MenuInfo> menuInfos;

    @Override
    public int getlayoutId() {
        return R.layout.fragment_menu;
    }

    @Override
    public void initEventAndData() {

        menuInfos = new ArrayList<>();

        for (int i = 0; i<20;i++) {
            MenuInfo menuInfo = new MenuInfo("促销活动","至尊蔬菜沙拉"+i,"asdadsa",5000.00,"123456",false,null,1,"","");
            menuInfos.add(menuInfo);
        }

        preOrderMenuAdapter = new PreOrderMenuAdapter(getActivity(),menuInfos);

        preOrderMenuAdapter.setPreOrderListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),5);
        rvMenu.addItemDecoration(new SpaceItemDecoration(30, SpaceItemDecoration.GRIDLAYOUT));
        rvMenu.setLayoutManager(gridLayoutManager);

        rvMenu.setAdapter(preOrderMenuAdapter);

    }


    public void setOnPreOrderClickListener(IPreOrderListener onPreOrderClickListener){
        this.iPreOrderListener = onPreOrderClickListener;
    }

    @Override
    public void onItemOrderClick(int positon) {
        menuInfos.get(positon).setChoose(!menuInfos.get(positon).isChoose());
        preOrderMenuAdapter.notifyDataSetChanged();
        iPreOrderListener.onPreOrderClick(menuInfos.get(positon));
    }

    public void onRemoveFood(MenuInfo menuInfo){
        for (int i = 0;i<menuInfos.size();i++){
            if (menuInfos.get(i) == menuInfo){
                menuInfos.get(i).setChoose(false);
                preOrderMenuAdapter.notifyDataSetChanged();
            }
        }
    }

    public void onAllRemoveFood(){
        for (int i = 0;i<menuInfos.size();i++){
            menuInfos.get(i).setChoose(false);
            preOrderMenuAdapter.notifyDataSetChanged();
        }
    }
}
