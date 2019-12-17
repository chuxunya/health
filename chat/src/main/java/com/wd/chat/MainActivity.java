package com.wd.chat;

import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.lizekai.mylibrary.base.BaseActivity;
import com.google.android.material.tabs.TabLayout;
import com.wd.chat.bean.DoctorBean;
import com.wd.chat.bean.FindDepartmentBean;
import com.wd.chat.contract.Contract;
import com.wd.chat.presenter.InquiryPresenter;
import com.wd.chat.utils.MySickAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @describe(描述)：MainActivity
 * @data（日期）: 2019/12/14
 * @time（时间）: 9:39
 * @author（作者）: xin
 **/
public class MainActivity extends BaseActivity<InquiryPresenter> implements Contract.IView {

    private static final String TAG = "MainActivity";
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.message)
    ImageView message;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.pager)
    ViewPager pager;
    private ArrayList<String> list=new ArrayList<>();

    @Override
    protected InquiryPresenter providePresenter() {
        return new InquiryPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.DepartmentP();
    }

    @Override
    public void onDepartmentSuccess(FindDepartmentBean findDepartmentBean) {
        Log.d(TAG, "onDepartmentSuccess: " + findDepartmentBean);
        if (findDepartmentBean.getStatus().equals("0000")){
            List<FindDepartmentBean.ResultBean> result = findDepartmentBean.getResult();
            if (result!=null){

                for (int i = 0; i < result.size(); i++) {
                    list.add(result.get(i).getDepartmentName());
                }

                tab.setupWithViewPager(pager);
                MySickAdapter mySickAdapter = new MySickAdapter(getSupportFragmentManager(), list, result);
                pager.setAdapter(mySickAdapter);
            }else {
                Toast.makeText(this, findDepartmentBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, findDepartmentBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onDepartmentFailure(Throwable e) {
        Log.d(TAG, "onDepartmentFailure: " + e.getMessage().toString());
    }

    @Override
    public void onDoctorSuccess(DoctorBean doctorBean) {

    }

    @Override
    public void onDoctorFailure(Throwable e) {

    }
}
