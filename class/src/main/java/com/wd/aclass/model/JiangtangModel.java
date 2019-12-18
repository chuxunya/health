package com.wd.aclass.model;
/*
 *@auther:张安恒
 *@Date: 2019/12/13
 *@Time:16:32
 *@Description:model
 **/

import com.bawei.lizekai.mylibrary.utils.CommonObserver;
import com.bawei.lizekai.mylibrary.utils.CommonSchedulers;
import com.bawei.lizekai.mylibrary.utils.RetrofitManager;
import com.wd.aclass.api.IApi;
import com.wd.aclass.bean.JiangtangBean;
import com.wd.aclass.bean.VideoBean;
import com.wd.aclass.contract.JiangtangContract;

public class JiangtangModel implements JiangtangContract.Imodel {

    @Override
    public void jiangtang(final IModelCallBack iModelCallBack) {
        RetrofitManager.getInstance().create(IApi.class)
                .jiangtang()
                .compose(CommonSchedulers.<JiangtangBean>io2main())
                .subscribe(new CommonObserver<JiangtangBean>() {
                    @Override
                    public void onNext(JiangtangBean jiangtangBean) {
                        iModelCallBack.jiangtang(jiangtangBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModelCallBack.onFraily(e.getMessage());
                    }
                });

    }

    @Override
    public void JiangVideo(String userId, String sessionId, String categoryId, String page, String count, final IModelCallBack iModelCallBack) {
        RetrofitManager.getInstance().create(IApi.class)
                .jiangvideo(userId,sessionId,categoryId,page,count)
                .compose(CommonSchedulers.<VideoBean>io2main())
                .subscribe(new CommonObserver<VideoBean>() {
                    @Override
                    public void onNext(VideoBean videoBean) {
                        iModelCallBack.JiangVideo(videoBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModelCallBack.onFraily(e.getMessage());
                    }
                });
    }
}
