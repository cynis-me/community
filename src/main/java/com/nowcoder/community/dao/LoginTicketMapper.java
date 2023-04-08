package com.nowcoder.community.dao;

import com.nowcoder.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LoginTicketMapper {
    //这个mapper没用xml文件，两种写mapper方法都可以

    @Insert({
            "insert into login_ticket(user_id,ticket,status,expired) ",
            "values(#{userId},#{ticket},#{status},#{expired})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);

    @Select({
            "select id,user_id,ticket,status,expired ",
            "from login_ticket where ticket=#{ticket}"
    })
    LoginTicket selectByTicket(String ticket);

    //这里的<script>和<if>是为了演示用这种方法写mapper的if怎么用
    @Update({
            "<script> ",
            "update login_ticket set status = #{status} where ticket=#{ticket} ",
            "<if test=\"ticket!=null\"> ",
            "and 1=1 ",//这里if完全没用，只是为了演示
            "</if> ",
            "</script>"
    })
    int updateStatus(String ticket, int status);

    //@Delete({})

}
