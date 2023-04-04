package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository("alphaDaoHibernate")//括号里为给当前命名，可以用这个名字强制返回当前bean，
// (正常情况不用加括号里的，只有容器用其他bean时且还有一小段用当前bean时使用)
public class AlphaDaoHibernateImpl implements AlphaDao{//Bean

    @Override
    public String select() {
        return "Hibernate";
    }
}
