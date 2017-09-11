package com.jeeplus.modules.sports.common.utils;

/**
 * Created by sam on 2017/2/15.
 */
//受限模块
public enum LimitFunctionUrlUtils {
    PERMISSION("system/permission/permissionList.do", 1),
    MENU("system/function/functionList.do", 2),
    RESOURCE("system/resource/resourceList.do", 3);

    private String name;
    private int index;
    // 构造方法
    private LimitFunctionUrlUtils(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (LimitFunctionUrlUtils c : LimitFunctionUrlUtils.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    public static boolean isLimit(String name){
        for (LimitFunctionUrlUtils c : LimitFunctionUrlUtils.values()) {
            if (c.name.equals(name)) {
                return true;
            }
        }
        return false;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
