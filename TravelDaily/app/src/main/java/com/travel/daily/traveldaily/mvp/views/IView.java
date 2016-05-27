package com.travel.daily.traveldaily.mvp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

/**
 * Created on 16/5/19.
 */
public abstract class IView<D> extends FrameLayout implements View.OnClickListener{

    public IView(Context context) {
        super(context);
        init(context);
    }

    public IView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public abstract void init(Context context);

    public abstract void setData(D data);

}
