package com.sjcdjsq.libs.widgets;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.lib.cooby.R;

/**
 * Created by Administrator on 2017/6/8 0008.
 */

public class LoadingDialog extends DialogFragment {
    private Activity mActivity;
    private boolean mOutside = true;
    private String loadText;

    public static LoadingDialog newInstance() {
        LoadingDialog loadingDialog = new LoadingDialog();
        Bundle bundle = new Bundle();
        loadingDialog.setArguments(bundle);
        return loadingDialog;
    }

    public void init(Activity activity) {
        mActivity = activity;
    }

    public void setOutside(boolean outside) {
        mOutside = outside;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dlg_loading, null);
        getDialog().setCanceledOnTouchOutside(mOutside);
        setCancelable(mOutside);
        getDialog().getWindow().setDimAmount(0);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().getAttributes().windowAnimations = R.style.dlg_loading;
        return dialog;
    }

    public void showDialog() {
     try {
         FragmentManager manager = mActivity.getFragmentManager();
         manager.executePendingTransactions();
         if (!isAdded()){
             FragmentTransaction ft = manager.beginTransaction();
             ft.add(this, "loadingDialog");
             ft.commitAllowingStateLoss();
         }
     }catch (Exception e){

     }

    }

    public void dismissDialog() {
        try {
            if (mActivity != null)
                dismissAllowingStateLoss();
        }catch (Exception e){

        }
    }
    public void setLoadText(String loadText) {
        this.loadText = loadText;
    }

}
