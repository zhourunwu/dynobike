package com.jeeplus.modules.sports.common;

/**
 * Created by sam on 2017/7/26.
 */
public interface CustomHttpHeaderConst {

    /**
     * app访问服务时提供的头
     */
    String Token="token";

    /**
     * token状态
     */
    String TokenStatus="TokenStatus";

    /**
     * 服务器版本，校验开发、测试、正式环境。
     */
    String ServerEdition="serverEdition";

    /**
     * 渠道
     */
    String Channel="channel";
    /**
     * 当前app版本
     */
    String Version="version";
}
