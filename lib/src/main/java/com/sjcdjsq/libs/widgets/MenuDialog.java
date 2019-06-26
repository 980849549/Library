package com.sjcdjsq.libs.widgets;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sjcdjsq.libs.utils.DensityUtil;
import com.lib.cooby.R;


/**
 * Created by Administrator on 2016/11/10 0010.
 */

public class MenuDialog extends DialogFragment {
    private int textColor, textSize;

    private OnTextClickListening onTextClickListening;

    public void setOnTextClickListening(OnTextClickListening onTextClickListening) {
        this.onTextClickListening = onTextClickListening;
    }

    public static MenuDialog newInstance(String[] topList, String[] bottomList, int textSize, int textColor) {
        MenuDialog menuDialog = new MenuDialog();
        Bundle bundle = new Bundle();
        bundle.putStringArray("toplist", topList);
        bundle.putStringArray("bottomlist", bottomList);
        bundle.putInt("size", textSize);
        bundle.putInt("color", textColor);
        menuDialog.setArguments(bundle);
        return menuDialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dlg_menu, null);
        getDialog().setCanceledOnTouchOutside(true);
        initView(view);
        setDlgParams();
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnim;
        return dialog;
    }

    private void initView(View view) {
        String[] topList = getArguments().getStringArray("toplist");
        String[] bottomList = getArguments().getStringArray("bottomlist");
        textSize = getArguments().getInt("size");
        textColor = getArguments().getInt("color");
        if (textSize == 0)
            textSize = 18;
        LinearLayout topLin = (LinearLayout) view.findViewById(R.id.lin_top);
        LinearLayout bottomLin = (LinearLayout) view.findViewById(R.id.lin_bottom);
        if (topList != null && topList.length > 0) {
            for (int i = 0; i < topList.length; i++) {
                TextView textView = new TextView(getActivity());
                String str = topList[i];
                textView.setTag(str);
                setTextP(textView, str, i);
                topLin.addView(textView);
            }
        } else {
            topLin.setVisibility(View.GONE);
        }
        if (bottomList != null && bottomList.length > 0) {
            for (int i = 0; i < bottomList.length; i++) {
                TextView textView = new TextView(getActivity());
                final String str = bottomList[i];
                textView.setTag(str);
                if (topList != null && topList.length > 0) {
                    setTextP(textView, bottomList[i], topList.length + i);
                } else {
                    setTextP(textView, bottomList[i], i);
                }
                bottomLin.addView(textView);
            }
        } else {
            bottomLin.setVisibility(View.GONE);
        }
    }


    private void setTextP(TextView textView, final String text, final int position) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        textView.setTextColor(getResources().getColor(textColor));
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(0, DensityUtil.dip2px(getActivity(), 10), 0, DensityUtil.dip2px(getActivity(), 10));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTextClickListening != null) {
                    onTextClickListening.onClick(position);
                }
                dismissAllowingStateLoss();
            }
        });
    }

    private void setDlgParams() {
        WindowManager.LayoutParams lay = getDialog().getWindow().getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        getDialog().getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
        Rect rect = new Rect();
        View view = getDialog().getWindow().getDecorView();
        view.getWindowVisibleDisplayFrame(rect);
        lay.gravity = Gravity.BOTTOM;
        lay.width = DensityUtil.getWindowWidth(getActivity()) - DensityUtil.dip2px(getActivity(), 30);
    }


    public interface OnTextClickListening {
        void onClick(int s);
    }

    public void showDialog(Activity aty) {
        aty.getFragmentManager().executePendingTransactions();
        if (!isAdded())
            show(aty.getFragmentManager(), "menudialog");
    }

    public void dismissDialog() {
        dismissAllowingStateLoss();
    }

}
