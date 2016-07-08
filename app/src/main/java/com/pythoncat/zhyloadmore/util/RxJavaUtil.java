package com.pythoncat.zhyloadmore.util;

import rx.Subscription;

/**
 * Created by pythonCat on 2016/7/8.
 */
public class RxJavaUtil {

    public static void close(Subscription sc) {
        if (sc != null && !sc.isUnsubscribed()) {
            sc.unsubscribe();
        }
    }
}
