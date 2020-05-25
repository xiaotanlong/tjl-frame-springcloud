package com.tjl.work.service.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjl.work.service.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author tjl
 * @since 2020-05-25
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

}
