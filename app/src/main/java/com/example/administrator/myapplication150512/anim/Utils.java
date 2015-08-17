package com.example.administrator.myapplication150512.anim;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by xqf on 2015/8/17.
 */
public class Utils {
    /**
     * 格式化
     */
    private static DecimalFormat dfs = null;

    public static DecimalFormat format(String pattern) {
        if (dfs == null) {
            dfs = new DecimalFormat();
        }
        dfs.setRoundingMode(RoundingMode.FLOOR);
        dfs.applyPattern(pattern);
        return dfs;
    }
}
