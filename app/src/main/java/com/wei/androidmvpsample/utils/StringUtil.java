package com.wei.androidmvpsample.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by ${wei} on 2017/3/20.
 */

public class StringUtil {


    /**
     * 转换日期格式
     *
     * @param date     日期字符串
     * @param oldRegex 原来的日期格式 必须和日期格式保持一致
     * @param newRegex 新的日期格式
     * @return
     */
    public static String formatDate(String date, String oldRegex, String newRegex) {
        if (TextUtils.isEmpty(oldRegex) || TextUtils.isEmpty(newRegex)) {
            throw new IllegalArgumentException("日期格式不能为空");
        }
        if (!Pattern.matches(oldRegex, date))
            throw new IllegalArgumentException("日期格式不匹配");
        SimpleDateFormat format = new SimpleDateFormat(oldRegex);
        date = format.format(date);

        format = new SimpleDateFormat(newRegex);
        try {
            Date parse = format.parse(date);
            return format.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }
}
