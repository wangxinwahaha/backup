/*
 * www.unisinsight.com Inc.
 * Copyright (c) 2018 All Rights Reserved
 */
package com.unisinsight.framework.uuv.common.utils;

import com.unisinsight.framework.uuv.common.exception.ErrorCode;
import com.unisinsight.framework.uuv.common.exception.FrameworkException;

import java.util.ArrayList;
import java.util.List;

/**
 * description 字符串处理
 *
 * @author liuran [KF.liuran@h3c.com]
 * @date 2018/9/6 17:12
 * @since 1.0
 */
public class StringUtils {

    public static boolean isEmpty(String str){
        return org.springframework.util.StringUtils.isEmpty(str);
    }

    public static List<Integer> stringToList(String str){
        String[] array = str.split(",");
        List<Integer> ids = new ArrayList<>(array.length);
        for (String val : array) {
            try {
                ids.add(Integer.parseInt(val));
            } catch (Exception e) {
                throw FrameworkException.of(ErrorCode.BASE_PARAM_VALID_ERROR);
            }
        }

        return ids;
    }

    public static String underscoreName(String name) {
        if (org.springframework.util.StringUtils.isEmpty(name)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        result.append(name.substring(0, 1).toLowerCase());
        for (int i = 1; i < name.length(); ++i) {
            String s = name.substring(i, i + 1);
            String slc = s.toLowerCase();
            if (!(s.equals(slc))) {
                result.append("_").append(slc);
            } else {
                result.append(s);
            }
        }
        return result.toString();
    }

    public static String withoutUnderscoreName(String name) {
        if (org.springframework.util.StringUtils.isEmpty(name)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        result.append(name.substring(0, 1).toLowerCase());
        boolean underscore = false;
        for (int i = 1; i < name.length(); ++i) {
            String s = name.substring(i, i + 1);
            if ("_".equals(s)) {
                underscore = true;
                continue;
            } else {
                if (underscore) {
                    s = s.toUpperCase();
                }
                underscore = false;
            }
            result.append(s);
        }
        return result.toString();
    }

}
