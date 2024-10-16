package com.shiguang.ioc_03;

import org.springframework.stereotype.Service;

/**
 * Created By Shiguang On 2024/9/2 16:37
 */

@Service
public class NewUserServiceImpl implements UserService{
    @Override
    public String show() {
        String str = "NewUserServiceImpl.show()";
        System.out.println(str);
        return "str";
    }
}
