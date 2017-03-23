package com.dom.red.ui.Adapter;

/**
 * Created by dom4j on 2017/3/8.
 */

public interface MulitItemTypeSupport<T> {

    int getLayoutID(int ItemType);

    int getItemViewType(int position, T o);
}
