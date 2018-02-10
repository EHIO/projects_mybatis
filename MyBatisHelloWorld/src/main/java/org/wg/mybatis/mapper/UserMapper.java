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
interface UserMapper {

//method start

    /**
     * 用户信息综合查询
     *
     * @param userQueryVo
     * @return
     * @throws Exception
     */
    List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;


    /**
     * 用户信息综合查询总数
     *
     * @param userQueryVo
     * @return
     * @throws Exception
     */
    int findUserCount(UserQueryVo userQueryVo) throws Exception;


    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    User findUserById(int id) throws Exception;

    //

    /**
     * 根据id查询用户信息，使用resultMap输出
     *
     * @param id
     * @return
     * @throws Exception
     */
    User findUserByIdResultMap(int id) throws Exception;


    /**
     * 根据用户名列查询用户列表
     *
     * @param name
     * @return
     * @throws Exception
     */
    List<User> findUserByName(String name) throws Exception;


    /**
     * 插入用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    void insertUser(User user) throws Exception;

    /**
     * 删除用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    void deleteUser(int id) throws Exception;

    /**
     * 更新用户
     *
     * @param user
     * @throws Exception
     */
    void updateUser(User user) throws Exception;
//mehtod end
}
