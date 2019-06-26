package com.sjcdjsq.libs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.lib.cooby.R;
import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;


/**
 * Created by Karry on 2017/3/7 0007.
 */

public class PermissionUtil {
    private Object mActivity;
    private OnPermissionResultListener mOnPermissionResultListener;

    public PermissionUtil(Object activity) {
        this.mActivity = activity;
    }

    public void requestPermission(int requestCode, String... permission) {
        if (AndroidUtils.isSixApi()){
            if (mActivity instanceof Activity)
                AndPermission.with((Activity) mActivity).requestCode(requestCode).permission(permission).rationale(rationaleListener).send();
            else if (mActivity instanceof Fragment)
                AndPermission.with((Fragment) mActivity).requestCode(requestCode).permission(permission).rationale(rationaleListener).send();
            else if (mActivity instanceof android.app.Fragment)
                AndPermission.with((android.app.Fragment) mActivity).requestCode(requestCode).permission(permission).rationale(rationaleListener).send();
        }else {
            if (mOnPermissionResultListener != null)
                mOnPermissionResultListener.onResultSuccess(requestCode, null);
        }

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        AndPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, mPermissionListener);
    }

    PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantPermissions) {
            if (mOnPermissionResultListener != null)
                mOnPermissionResultListener.onResultSuccess(requestCode, grantPermissions);
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (mOnPermissionResultListener != null)
                mOnPermissionResultListener.onResultFailed(requestCode, deniedPermissions);
            Context context = null;
            if (mActivity instanceof Fragment)
                context = ((Fragment) mActivity).getActivity();
            else if (mActivity instanceof Activity)
                context = (Activity) mActivity;
            else if (mActivity instanceof android.app.Fragment)
                context = ((android.app.Fragment) mActivity).getActivity();
            ToastUtils.showToast(context, "获取权限失败");
            if (mActivity instanceof Activity)
                if (AndPermission.hasAlwaysDeniedPermission((Activity) mActivity, deniedPermissions)) {
                    AndPermission.defaultSettingDialog((Activity) mActivity, 0).show();
                }
            if (mActivity instanceof Fragment)
                if (AndPermission.hasAlwaysDeniedPermission((Fragment) mActivity, deniedPermissions)) {
                    AndPermission.defaultSettingDialog((Fragment) mActivity, 0).show();
                }
        }
    };
    private RationaleListener rationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
            Context context = null;
            if (mActivity instanceof Fragment)
                context = ((Fragment) mActivity).getActivity();
            else if (mActivity instanceof Activity)
                context = (Activity) mActivity;
            else if (mActivity instanceof android.app.Fragment)
                context = ((android.app.Fragment) mActivity).getActivity();
            AlertDialog.build(context)
                    .setTitle(context.getString(com.lib.cooby.R.string.dlg_tips))
                    .setMessage(context.getString(R.string.refuse_permission))
                    .setPositiveButton(context.getString(com.lib.cooby.R.string.dlg_go_on), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.resume();
                        }
                    })
                    .setNegativeButton(context.getString(com.lib.cooby.R.string.dlg_refuse), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.cancel();
                        }
                    }).show();
        }
    };

    public void setOnPermissionResultListener(@Nullable OnPermissionResultListener onPermissionResultListener) {
        mOnPermissionResultListener = onPermissionResultListener;
    }

    public interface OnPermissionResultListener {
        void onResultSuccess(int requestCode, List<String> grantPermissions);

        void onResultFailed(int requestCode, List<String> deniedPermissions);
    }
}
