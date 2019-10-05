package com.semisky.autotesttool.pattern_m;

public interface ICatchAndroidLogModel {

    /**
     * 请求启动记录日志
     * @return
     */
    ICatchAndroidLogModel reqRecordLogStart();
    /**
     * 设置过滤日志优先级
     *
     * @param filterLogPriority
     * @return
     */
    ICatchAndroidLogModel setmFilterLogPriority(String filterLogPriority);

    /**
     * 设置过滤日志TAG
     *
     * @param filterLogTag
     * @return
     */
    ICatchAndroidLogModel setmFilterLogTag(String filterLogTag);
}
