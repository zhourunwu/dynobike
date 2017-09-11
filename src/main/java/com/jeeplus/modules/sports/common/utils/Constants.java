package com.jeeplus.modules.sports.common.utils;

import java.text.SimpleDateFormat;

public class Constants
{
  public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  public static final Integer LAST_MOMENT = -15;
  public static final String USER_ADMIN_ACCOUNT = "admin";
  public static final String USER_SUPER_ADMIN_ACCOUNT = "administrator";
  public static final String USER_SESSION_RESOURCE = "userResources";
  public static final String USER_INFO = "userInfo";
  public static final String THE_REALM_NAME = "userRealm";


  public static final String USER_RESOURCES = "resources";



  public static final String DEFAULT_USER_PASSWORD = "123456";
  public static final int DEFAULT_PAGE_SIZE = 10;



  public static final Integer RESULT_SUCCESS = 200;
  public static final String RESULT_SUCCESS_MESSAGE = "load data success";
  public static final Integer RESULT_FAILED = 201;
  public static final String RESULT_FAILED_MESSAGE = "load data failed";
  public static final Integer RESULT_ERROR = 202;
  public static final String RESULT_ERROR_MESSAGE = "load data error";
  public static final Integer RESULT_EXCEPTION = 203;
  public static final String RESULT_EXCEPTION_MESSAGE = "load data exception";
  public static final Integer RESULT_PARAMS = 204;
  public static final String RESULT_PARAMS_MESSAGE = "param is not invalid";

  public static final Integer USER_TYPE_CUSTOMER = 0;
  public static final Integer USER_TYPE_ADMIN = 1;
  public static final Integer USER_TYPE_SUPERADMIN = 1;

  public static final Integer USER_STATUS_UNRECOGNIZE = 0;
  public static final Integer USER_STATUS_EFFICTIVE = 1;
  public static final Integer USER_STATUS_LOCKED = 2;
  public static final Integer USER_STATUS_DELETE = 3;


  public static final Integer FLAG_LOCKED = 0;
  public static final Integer FLAG_UNLOCK = 1;
  public static final Integer STATUS_LOCKED = 0;
  public static final Integer STATUS_EFFICTIVE = 1;

  public static final Integer USER_NOT_DELETE = 0;
  public static final Integer USER_DELETED = 1;

  public static final Integer ROLE_TYPE_CUSTOMER = 0;
  public static final Integer ROLE_TYPE_ADMIN = 1;

  public static final Integer IO_PORT = 0;
  public static final Integer VIDEO_PORT = 1;
  public static final Integer AUDIO_PORT = 2;

  public static final Integer SERVER_DISABLE = 0;
  public static final Integer SERVER_ENABLE= 1;
  public static final Integer SERVER_NOT_CONNECTED = 2;

  public static final String SERVER_TYPE_GPU = "GPU";
  public static final String SERVER_TYPE_JANUS= "GATEWAY";
  public static final String SERVER_TYPE_GSHARE= "GSHARE";

  public static final Integer GVM_DISABLE = 0;
  public static final Integer GVM_ENABLE= 1;
  public static final Integer GVM_DELETED = 2;
  public static final Integer GVM_NOT_ALLOW_LINK = 3;


  public static final Integer VIDEO_UNCHECK = 0;
  public static final Integer VIDEO_CHECK_PASS= 1;
  public static final Integer VIDEO_CHECK_UNPASS = 2;
  public static final Integer VIDEO_DELETEED = 3;


  public static final Integer RESULT_CODE_INPUT_NULL = 2;
  public static final Integer RESULT_CODE_GVM_DELETE = 3;
  public static final Integer RESULT_CODE_INSTANCE_NULL = 4;
  public static final Integer RESULT_CODE_USER_NOT_EXIST = 5;
  public static final Integer RESULT_CODE_NOT_ALLOW_LINK = 6;
  public static final Integer RESULT_CODE_OTHER_USER_KICK = 7;
  public static final Integer RESULT_CODE_USER_DELETE = 8;
  public static final Integer RESULT_CODE_USER_DISABLE = 9;

  public static final Integer LICENSE_CODE_NULL = 0;
  public static final Integer LICENSE_CODE_SUCCESS = 1;
  public static final Integer LICENSE_CODE_NUMBER_ZERO = 2;
  public static final Integer LICENSE_CODE_PERMIT_NUMBER_ZERO = 3;
  public static final Integer LICENSE_CODE_PERMIT_NUMBER_MAX = 4;


  public static final Integer STATIC_MODULE_ID = 99999;

  public static final String STATIC_VM_STATUS_RUNNING_NAME= "RUNNING";
  public static final Integer STATIC_VM_STATUS_RUNNING_VALUE = 1;
  public static final String STATIC_VM_STATUS_HALTED_NAME = "HALTED";
  public static final Integer STATIC_VM_STATUS_HALTED_VALUE = 0;
}