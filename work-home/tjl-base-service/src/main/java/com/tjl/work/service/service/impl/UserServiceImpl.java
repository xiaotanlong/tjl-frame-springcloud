package com.tjl.work.service.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjl.work.service.entity.UserDO;
import com.tjl.work.service.mapper.UserMapper;
import com.tjl.work.service.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author tjl
 * @since 2020-05-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

}
