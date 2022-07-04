package org.winterframework.dashboard.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsBeanConfig {

    @Bean
    public Snowflake snowflake() {
        long workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr()) >> 16 & 31;
        long datacenterId = 1;//数据中心ID
        return IdUtil.getSnowflake(workerId, datacenterId);
    }
}
