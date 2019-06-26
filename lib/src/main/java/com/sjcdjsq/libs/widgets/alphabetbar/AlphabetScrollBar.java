package com.sjcdjsq.libs.widgets.alphabetbar;

/**
 * Created by Administrator on 2016/12/16 0016.
 */

import android.content.Context;
import android.util.AttributeSet;

import com.lib.cooby.R;

public class AlphabetScrollBar extends VerticalScrollBar {
    protected final void aLZ() {
        this.iuV = new String[]{"↑", "☆", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
        this.iuT = 1.3f;
        this.iuU = 79;
    }

    public AlphabetScrollBar(Context context) {
        super(context);
    }

    public AlphabetScrollBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected final int aMa() {
        return R.layout.show_head_toast;
    }
}