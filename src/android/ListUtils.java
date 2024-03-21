package org.apache.cordova.youprinter;

import java.util.List;

/**
 * Created by hangeng on 2018/7/17.
 */

public class ListUtils {
    public static <D> boolean isEmpty(List<D> list) {
        return list == null || list.isEmpty();
    }
}
