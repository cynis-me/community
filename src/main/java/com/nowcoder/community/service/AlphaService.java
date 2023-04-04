package com.nowcoder.community.service;//处理事务的包

import com.nowcoder.community.dao.AlphaDao;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
//@Scope("prototype")//默认值是singleton，只实例一次，改变了之后就是每次访问一次就会新实例一次
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;//dao是访问数据库的包，这里是调用dao

    public AlphaService() {
        System.out.println("实例化AlphaService");
    }

    @PostConstruct//表明这个方法在构造后再用
    public void init() {//初始化方法
        System.out.println("初始化AlphaService");
    }
    @PreDestroy//在销毁之前调用
    public void destory() {
        System.out.println("销毁AlphaService");
    }

    public String find() {//简单de查询
        return alphaDao.select();
    }
}
