package com.shiguang.ioc_03;

import org.springframework.stereotype.Service;

/**
 * Created By Shiguang On 2024/9/2 15:43
 */

@Service
public class UserServiceImpl implements UserService{
    @Override
    public String show() {
        String str = "UserServiceImpl.show()";
        System.out.println(str);
        return "str";
    }
}
