package org.winterframework.dashboard.api.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.winterframework.dashboard.api.user.entity.WechatOauth;
import org.winterframework.dashboard.api.user.mapper.WechatOauthMapper;

/**
 * @author Kyun
 * @since 2022-06-15
 */
@Service
public class WechatOauthService extends ServiceImpl<WechatOauthMapper, WechatOauth> implements IService<WechatOauth> {

}
