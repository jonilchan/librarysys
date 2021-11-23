package com.gdufe.libsys.service.impl;

import com.gdufe.libsys.entity.User;
import com.gdufe.libsys.mapper.UserMapper;
import com.gdufe.libsys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jonil
 * @since 2021-11-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
