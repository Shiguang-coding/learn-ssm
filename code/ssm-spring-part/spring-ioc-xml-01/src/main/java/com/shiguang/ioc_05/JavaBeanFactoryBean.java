package com.shiguang.ioc_05;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created By Shiguang On 2024/9/1 21:16
 * description:制造javaBean的工厂Bean对象
 * <p>
 * 步骤：
 * 1、实现FactoryBean接口<返回值泛型>
 */
public class JavaBeanFactoryBean implements FactoryBean<JavaBean> {
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public JavaBean getObject() throws Exception {
        // 使用自己的方式实例化对象即可
        JavaBean javaBean = new JavaBean();

        javaBean.setName(value);
        return javaBean;
    }

    @Override
    public Class<?> getObjectType() {
        return JavaBean.class;
    }

}
