package com.android.wx.fragment;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.wx.R;
import com.android.wx.adapter.BusinessHoursAdapter;
import com.android.wx.base.Fragment.BaseFragment;
import com.android.wx.db.DBManager;
import com.android.wx.model.BusinessHoursBean;
import com.android.wx.utils.IWxConstant;
import com.android.wx.utils.UToast;
import com.android.wx.utils.WxUtil;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Visibility;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName BusinessHoursFragment
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/5 21:34
 */
public class BusinessHoursFragment extends BaseFragment {

    @BindView(R.id.rv_business_hours)
    RecyclerView rvBusinessHours;
    @BindView(R.id.et_business_name)
    EditText etBusinessName;
    @BindView(R.id.tv_business_start_time)
    TextView tvBusinessStartTime;
    @BindView(R.id.tv_business_end_time)
    TextView tvBusinessEndTime;
    @BindView(R.id.tv_business_start_week)
    TextView tvBusinessStartWeek;
    @BindView(R.id.tv_business_end_week)
    TextView tvBusinessEndWeek;
    @BindView(R.id.et_business_remarks)
    EditText etBusinessRemarks;

    private BusinessHoursAdapter businessHoursAdapter;
    private List<BusinessHoursBean> businessHoursBeans;

    @Override
    public int getlayoutId() {
        return R.layout.fragment_business_hours;
    }

    @Override
    public void initEventAndData() {


        businessHoursBeans = DBManager.getInstance(getActivity()).queryBusinessHours();

        businessHoursAdapter = new BusinessHoursAdapter(getActivity(),businessHoursBeans);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        rvBusinessHours.setLayoutManager(linearLayoutManager);

        rvBusinessHours.setAdapter(businessHoursAdapter);


    }

    @OnClick({R.id.tv_business_save,R.id.tv_business_start_week,R.id.tv_business_end_week,R.id.tv_business_start_time,R.id.tv_business_end_time})
    public void onClick(View view){
        Calendar c = Calendar.getInstance();
        switch (view.getId()){
            case R.id.tv_business_save:

                if (IWxConstant.isNull(etBusinessName) && tvBusinessStartTime.getText().toString().trim().length() > 0 && tvBusinessEndTime.getText().toString().trim().length() > 0
                        && tvBusinessStartWeek.getText().toString().trim().length() > 0 && tvBusinessEndWeek.getText().toString().trim().length() > 0){

                    BusinessHoursBean businessHoursBean = new BusinessHoursBean();
                    businessHoursBean.setBusinessName(etBusinessName.getText().toString());
                    businessHoursBean.setBusinessTime(tvBusinessStartTime.getText().toString() + "~" + tvBusinessEndTime.getText().toString());
                    businessHoursBean.setBusinessWeek(tvBusinessStartWeek.getText().toString() + "~" + tvBusinessEndWeek.getText().toString());
                    businessHoursBean.setBusinessOperate(etBusinessRemarks.getText().toString());

                    DBManager.getInstance(getActivity()).insertBusinessHours(businessHoursBean);


                    businessHoursBeans = DBManager.getInstance(getActivity()).queryBusinessHours();

                    businessHoursAdapter.setData(businessHoursBeans);

                }else {
                    UToast.show(getActivity(),R.string.get_incomplete);
                }

                break;

            case R.id.tv_business_start_week:

                new AlertDialog.Builder(getActivity())
                        .setItems(R.array.week_day, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tvBusinessStartWeek.setText(getActivity().getResources().getStringArray(R.array.week_day)[which]);
                            }
                        }).setPositiveButton("取消", null).setTitle("选择星期").show();


                break;

            case R.id.tv_business_end_week:

                new AlertDialog.Builder(getActivity())
                        .setItems(R.array.week_day, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tvBusinessEndWeek.setText(getActivity().getResources().getStringArray(R.array.week_day)[which]);
                            }
                        }).setPositiveButton("取消", null).setTitle("选择星期").show();



                break;

            case R.id.tv_business_start_time:
                new TimePickerDialog(getContext(),android.R.style.Theme_Holo_Light_Dialog_NoActionBar,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String select_hour = String.valueOf(hourOfDay);
                        String select_minute = String.valueOf(minute);
                        if(hourOfDay<10){
                            select_hour = "0"+hourOfDay;
                        }
                        if(minute<10){
                            select_minute = "0"+minute;
                        }
                        tvBusinessStartTime.setText(select_hour+":"+select_minute);
                    }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
                break;


            case R.id.tv_business_end_time:
                new TimePickerDialog(getContext(),android.R.style.Theme_Holo_Light,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String select_hour = String.valueOf(hourOfDay);
                        String select_minute = String.valueOf(minute);
                        if(hourOfDay<10){
                            select_hour = "0"+hourOfDay;
                        }
                        if(minute<10){
                            select_minute = "0"+minute;
                        }
                        tvBusinessEndTime.setText(select_hour+":"+select_minute);
                    }
                }, c.get(Calendar.HOUR), c.get(Calendar.MINUTE), true).show();

                break;


        }
    }

}
