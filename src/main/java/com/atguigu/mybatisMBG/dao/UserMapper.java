package com.atguigu.mybatisMBG.dao;

import com.atguigu.mybatisMBG.pojo.User;
import com.atguigu.mybatisMBG.pojo.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table _user
     *
     * @mbg.generated Sun Nov 14 09:12:18 CST 2021
     */
    long countByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table _user
     *
     * @mbg.generated Sun Nov 14 09:12:18 CST 2021
     */
    int deleteByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table _user
     *
     * @mbg.generated Sun Nov 14 09:12:18 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table _user
     *
     * @mbg.generated Sun Nov 14 09:12:18 CST 2021
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table _user
     *
     * @mbg.generated Sun Nov 14 09:12:18 CST 2021
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table _user
     *
     * @mbg.generated Sun Nov 14 09:12:18 CST 2021
     */
    List<User> selectByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table _user
     *
     * @mbg.generated Sun Nov 14 09:12:18 CST 2021
     */
    User selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table _user
     *
     * @mbg.generated Sun Nov 14 09:12:18 CST 2021
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table _user
     *
     * @mbg.generated Sun Nov 14 09:12:18 CST 2021
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table _user
     *
     * @mbg.generated Sun Nov 14 09:12:18 CST 2021
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table _user
     *
     * @mbg.generated Sun Nov 14 09:12:18 CST 2021
     */
    int updateByPrimaryKey(User record);

    /**
     * ????????????????????????
     * @return List<user>
     */
    List<User> findAllByPageHelp();

    /**
     * ????????????????????????
     * @param user
     */
    void addUsers(User user);
}