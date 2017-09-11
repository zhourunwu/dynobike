package com.jeeplus.modules.sports.common.utils;

import java.text.DecimalFormat;

/**
 * Created by admin on 2017/3/31.
 */
public final class ByteUtil {

    public static String bytes2kb(Long bytes) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if(bytes == null){
            fileSizeString = "-";
        } else if (bytes < 1024) {
            fileSizeString = df.format((double) bytes) + "B";
            if(fileSizeString.equals(".00B")){
                fileSizeString = "-";
            }
        } else if (bytes < 1048576) {
            fileSizeString = df.format((double) bytes / 1024) + "K";
        } else if (bytes < 1073741824) {
            fileSizeString = df.format((double) bytes / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) bytes / 1073741824) + "G";
        }
        return fileSizeString;
    }
}
