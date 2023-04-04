package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary//加上这个会优先装配当前Bean
public class AlphaDaoMyBatisImpl implements AlphaDao{

    @Override
    public String select() {
        return "MyBatis";
    }
}
