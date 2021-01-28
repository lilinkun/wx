package com.android.wx.activity;

import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.wx.R;
import com.android.wx.adapter.HistoryVipAdapter;
import com.android.wx.adapter.VipSearchAdapter;
import com.android.wx.base.MyApplication;
import com.android.wx.base.activity.MvpActivity;
import com.android.wx.contract.IVipView;
import com.android.wx.db.DBManager;
import com.android.wx.event.EventCenter;
import com.android.wx.model.VipInfoBean;
import com.android.wx.presenter.VipPresenter;
import com.android.wx.utils.IWxConstant;
import com.android.wx.utils.UToast;
import com.android.wx.view.CustomerDialog;
import com.android.wx.view.HistoryVipDialog;
import com.android.wx.view.PreOrderChooseNumDialog;
import com.android.wx.weight.MyCalendar;
import com.android.wx.weight.MyDateLinear;
import com.android.wx.weight.SpaceItemDecoration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName VipActivity
 * @Description TODO
 * @Author liguo
 * @Date 2021/1/23 23:02
 */
public class VipActivity extends MvpActivity<IVipView,VipPresenter> implements MyDateLinear.MyDateLinearListener, PreOrderChooseNumDialog.OnOreOrderChooseNumListener {

    @BindView(R.id.tv_register_date)
    TextView tvRegisterDate;
    @BindView(R.id.tv_login_time)
    TextView tvLoginTime;
    @BindView(R.id.tv_login_date)
    TextView tvLoginDate;
    @BindView(R.id.tv_add_vip)
    TextView tvAddVip;
    @BindView(R.id.ll_add_vip)
    LinearLayout llAddVip;
    @BindView(R.id.ll_add_second)
    LinearLayout llAddSecond;
    @BindView(R.id.mydate)
    MyDateLinear myDateLinear;
    @BindView(R.id.et_vip_card)
    EditText etVipCard;
    @BindView(R.id.et_vip_username)
    EditText etVipUsername;
    @BindView(R.id.et_vip_address)
    EditText etVipAddress;
    @BindView(R.id.et_vip_balance)
    EditText etVipBalance;
    @BindView(R.id.et_vip_birthday)
    EditText etVipBirthday;
    @BindView(R.id.et_vip_email)
    EditText etVipEmail;
    @BindView(R.id.et_vip_integral)
    EditText etVipIntegral;
    @BindView(R.id.et_vip_name)
    EditText etVipName;
    @BindView(R.id.et_vip_surname)
    EditText etVipSurName;
    @BindView(R.id.et_vip_tel)
    EditText etVipTel;
    @BindView(R.id.et_vip_edit)
    EditText etVipEdit;
    @BindView(R.id.tv_register_effective_date)
    TextView tvRegisterEffectiveDate;
    @BindView(R.id.tv_surname)
    TextView tvSurName;
    @BindView(R.id.tv_username)
    TextView tvUserName;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_tel)
    TextView tvTel;
    @BindView(R.id.rv_vip)
    RecyclerView rvVip;
    @BindView(R.id.ll_add_vip_left)
    LinearLayout llAddVipLeft;
    @BindView(R.id.ll_search_vip)
    LinearLayout llSearchVip;
    @BindView(R.id.rl_vip_list)
    RelativeLayout rlVipList;
    @BindView(R.id.ll_see_vipcard_info)
    LinearLayout llSeeVipcardInfo;
    @BindView(R.id.tv_see_start_time)
    TextView tvSeeStartTime;
    @BindView(R.id.tv_see_effective_date)
    TextView tvSeeEffectiveDate;
    @BindView(R.id.tv_see_integral)
    TextView tvSeeIntegral;
    @BindView(R.id.tv_see_balance)
    TextView tvSeeBalance;
    @BindView(R.id.tv_see_username)
    TextView tvSeeUserName;
    @BindView(R.id.tv_see_vip_card)
    TextView tvSeeVipCard;

    private VipSearchAdapter vipSearchAdapter;
    private MyCalendar calendar;
    private int wm_width, wm_height;
    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    private String mSearchVipCardId;
    private VipInfoBean seeVipInfoBean;

    @NonNull
    @Override
    public VipPresenter createPresenter() {
        return new VipPresenter();
    }

    @Override
    public View getLoadingTargeView() {
        return null;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_vip;
    }

    @Override
    protected void onReceiverEvent(EventCenter eventCenter) {

    }

    @Override
    public void initData() {


        Display display = IWxConstant.display(this);
        wm_width = display.getWidth();
        wm_height = display.getHeight();

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd yyyy");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");

        tvLoginDate.setText(simpleDateFormat.format(date));
        tvLoginTime.setText(simpleDateFormat1.format(date));

        tvRegisterDate.setText(simpleDateFormat2.format(date));
        tvLoginDate.setText(simpleDateFormat.format(date));
        tvLoginTime.setText(simpleDateFormat1.format(date));

        calendar = calendar.getInstance();
        myDateLinear.setMyDateLinearListener(this);
        Calendar c = Calendar.getInstance();
        myDateLinear.setMinYear(calendar.get(Calendar.YEAR));
//        myDateLinear.setMinYear(1950);
        myDateLinear.init();
        myDateLinear.init(2021,
                3,
                1);
    }

    @OnClick({R.id.tv_add_vip,R.id.tv_register_effective_date,R.id.tv_vip_save,R.id.tv_search,R.id.tv_add_new_vip_card,R.id.tv_see_history,R.id.tv_back,R.id.tv_exit,
            R.id.tv_customer_info,R.id.tv_see_recharge})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_add_vip:

                if (tvAddVip.getText().toString().equals(getString(R.string.add_other_info))) {
                    tvAddVip.setText(R.string.add_vip);
                    llAddSecond.setVisibility(View.VISIBLE);
                    llAddVip.setVisibility(View.GONE);
                }else {
                    tvAddVip.setText(R.string.add_other_info);
                    llAddSecond.setVisibility(View.GONE);
                    llAddVip.setVisibility(View.VISIBLE);
                }

                break;

            case R.id.tv_register_effective_date:

                myDateLinear.init(2021,3,1);
                Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.fenxiang_weiyi_input);
                myDateLinear.setAnimation(animation);
                myDateLinear.setVisibility(View.VISIBLE);
                myDateLinear.setListYear();

                break;

            case R.id.tv_vip_save:

                if(IWxConstant.isNull(etVipCard)){
                    List<VipInfoBean> vipInfoBeans = DBManager.getInstance(this).queryVipCard(etVipCard.getText().toString());
                    if (vipInfoBeans != null && vipInfoBeans.size() > 0){
                        UToast.show(this,R.string.vip_exist);
                        return;
                    }
                }


                if (IWxConstant.isNull(etVipCard) && IWxConstant.isNull(etVipAddress) && IWxConstant.isNull(etVipBalance) && IWxConstant.isNull(etVipBirthday) &&
                   IWxConstant.isNull(etVipEmail) && IWxConstant.isNull(etVipIntegral) && IWxConstant.isNull(etVipName) && IWxConstant.isNull(etVipSurName) &&
                   IWxConstant.isNull(etVipName) && IWxConstant.isNull(etVipTel)){
                    String operator = "";
                    if (MyApplication.userInfo!=null){
                        operator = MyApplication.userInfo.getName();
                    }

                    VipInfoBean vipInfoBean = new VipInfoBean(etVipCard.getText().toString(),etVipUsername.getText().toString(),etVipBalance.getText().toString(),etVipIntegral.getText().toString(),
                            (new Date()).getTime()+"",tvRegisterDate.getText().toString(),etVipName.getText().toString(),etVipSurName.getText().toString(),etVipBirthday.getText().toString(),etVipTel.getText().toString(),
                            etVipEmail.getText().toString(),etVipAddress.getText().toString(),operator,0,0);

                    DBManager.getInstance(this).insertVipCard(vipInfoBean);

                    UToast.show(this,R.string.vip_complete);
                }else {
                    UToast.show(this,R.string.vip_incomplete);
                }


                break;

            case R.id.tv_exit:
                IWxConstant.isEmpty(etVipCard);
                IWxConstant.isEmpty(etVipAddress);
                IWxConstant.isEmpty(etVipBalance);
                IWxConstant.isEmpty(etVipBirthday);
                IWxConstant.isEmpty(etVipEmail);
                IWxConstant.isEmpty(etVipIntegral);
                IWxConstant.isEmpty(etVipName);
                IWxConstant.isEmpty(etVipSurName);
                IWxConstant.isEmpty(etVipName);
                IWxConstant.isEmpty(etVipTel);
                tvRegisterDate.setText("");

                break;

            case R.id.tv_search:

                List<VipInfoBean> vipInfoBeans = DBManager.getInstance(this).queryVipCard(etVipEdit.getText().toString());


                if (vipInfoBeans != null && vipInfoBeans.size() > 0) {

                    VipInfoBean vipInfoBean = vipInfoBeans.get(0);


                    tvSurName.setText(vipInfoBean.getSurName());
                    tvEmail.setText(vipInfoBean.getEmail());
                    tvTel.setText(vipInfoBean.getTelphone());
                    tvUserName.setText(vipInfoBean.getUserName());

                    vipSearchAdapter = new VipSearchAdapter(this, vipInfoBeans);

                    llAddVipLeft.setVisibility(View.GONE);
                    llSearchVip.setVisibility(View.VISIBLE);

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

                    rvVip.addItemDecoration(new SpaceItemDecoration(20, SpaceItemDecoration.GRIDLAYOUT));
                    rvVip.setLayoutManager(gridLayoutManager);
                    rvVip.setAdapter(vipSearchAdapter);

                    vipSearchAdapter.setListener(new VipSearchAdapter.OnItemClick() {
                        @Override
                        public void onItemClick(int position) {
                            rlVipList.setVisibility(View.GONE);
                            llSeeVipcardInfo.setVisibility(View.VISIBLE);

                            long date = Long.valueOf(vipInfoBeans.get(position).getStartTime());

                            tvSeeStartTime.setText(simpleDateFormat2.format(new Date(date)));
                            tvSeeEffectiveDate.setText(vipInfoBeans.get(position).getRegisterDate());
                            tvSeeIntegral.setText(vipInfoBeans.get(position).getIntegral());
                            tvSeeBalance.setText(vipInfoBeans.get(position).getBalance());
                            tvSeeUserName.setText(vipInfoBeans.get(position).getUserName());
                            tvSeeVipCard.setText(vipInfoBeans.get(position).getCardId());

                            seeVipInfoBean = vipInfoBeans.get(position);
                        }
                    });
                }else {
                    UToast.show(this,R.string.vip_unexist);
                }

                break;

            case R.id.tv_add_new_vip_card:

                llAddVipLeft.setVisibility(View.VISIBLE);
                llSearchVip.setVisibility(View.GONE);

                break;

            case R.id.tv_see_history:

                HistoryVipDialog historyVipDialog = new HistoryVipDialog(this,etVipEdit.getText().toString());
                historyVipDialog.show();

                break;

            case R.id.tv_back:

                finish();

                break;

            case R.id.tv_customer_info:

                CustomerDialog customerDialog = new CustomerDialog(this,seeVipInfoBean);
                customerDialog.show();

                break;

            case R.id.tv_see_recharge:

                PreOrderChooseNumDialog preOrderChooseNumDialog = new PreOrderChooseNumDialog(this,this,1);
                preOrderChooseNumDialog.show();

                break;
        }
    }

    @Override
    public int getscreenWidth() {
        return wm_width;
    }

    @Override
    public int getscreenHeight() {
        return wm_height;
    }

    @Override
    public void cancle() {
        myDateLinear.setVisibility(View.GONE);
    }

    @Override
    public void sure(String date) {
        tvRegisterEffectiveDate.setText(date);
        myDateLinear.setVisibility(View.GONE);
    }

    @Override
    public void realTime(String data) {
//        tvRegisterEffectiveDate.setText(data);
    }

    @Override
    public void onChooseNumClick(int num) {
        seeVipInfoBean.setReCharge(num);
        double balance = Double.valueOf(seeVipInfoBean.getBalance());
        seeVipInfoBean.setBalance(balance+num+"");
        DBManager.getInstance(this).insertVipCard(seeVipInfoBean);
    }
}
