package org.wg.mybatis.mapper;

import org.wg.mybatis.po.User;
import org.wg.mybatis.po.UserCustom;
import org.wg.mybatis.po.UserQueryVo;

import java.util.List;

/**
 * mapper接口，相当 于dao接口，用户管理
 *
 * @author wg
 * @version 1.0
 * @date 2015-4-22 2:45:12
 */
interface User2Mapper {


    /**
     * 根据用户名列查询用户列表
     *
     * @return
     * @throws Exception
     */
    List<User> listUserByName(String username) throws Exception;

    List<User> listUser(User user) throws Exception;
}
