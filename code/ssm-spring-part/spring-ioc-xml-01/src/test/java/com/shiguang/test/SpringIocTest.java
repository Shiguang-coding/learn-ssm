package com.shiguang.test;

import com.shiguang.ioc_03.HappyComponent;
import com.shiguang.ioc_03.MyInterface;
import com.shiguang.ioc_04.JavaBean2;
import com.shiguang.ioc_04.JavaBean3;
import com.shiguang.ioc_05.JavaBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created By Shiguang On 2024/9/1 15:51
 * description: ioc容器创建和读取组件测试类
 */
public class SpringIocTest {

    /**
     * 讲解如何创建ioc容器并读取配置文件
     */
    public void createIoc() {

        /**
         * 创建容器时,选择合适的容器实现即可
         * 接口:
         *      BeanFactory
         *      ApplicationContext
         * 实现类:
         *      可以直接通过构造函数实例化
         *      ClassPathXmlApplicationContext 读取类路径下的xml配置方式(classes目录)
         *      FileSystemXmlApplicationContext 读取指定文件位置的xml配置方式
         *      AnnotationConfigApplicationContext 读取配置类方式的ioc容器
         *      WebApplicationContext               web项目专属的配置的ioc容器
         *
         */

        // 方式1（推荐）: 直接创建容器并指定配置文件即可
        // 构造函数(String... 配置文件),配置文件可以指定一个或多个
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-03.xml");

        // 方式2: 先创建ioc容器对象,再指定配置文件,再刷新
        // 通常为源码的配置过程 创建容器（Spring负责）和配置文件(自己指定)分开指定
        ClassPathXmlApplicationContext applicationContext1 = new ClassPathXmlApplicationContext();
        applicationContext1.setConfigLocations("spring-03.xml");
        applicationContext1.refresh(); // 调用ioc和di的流程

    }


    /**
     * 讲解如何在ioc容器中获取组件Bean
     */
    @Test
    public void getBeanFromIoc() {

        // 1、创建ioc容器对象
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        applicationContext.setConfigLocations("spring-03.xml");
        applicationContext.refresh();


        // 2、读取ioc容器的组件
        // 方式1 (不推荐) : 直接根据 beanId 获取,返回值类型为Object,需要强转
        HappyComponent happyComponent = (HappyComponent) applicationContext.getBean("happyComponent");

        // 方式2 : 根据 beanId,同时指定bean的类型（Class）
        HappyComponent happyComponent1 = applicationContext.getBean("happyComponent", HappyComponent.class);

        // 方式3 : 直接根据类型获取
        // TODO 根据bean的类型获取时,同一个类型在ioc容器中只能有一个bean
        // TODO 如果ioc容器存在多个同类型bean,会出现 NoUniqueBeanDefinitionException
        HappyComponent happyComponent2 = applicationContext.getBean(HappyComponent.class);


        // TODO ioc的配置一定是实现类,但是可以根据接口类获取值 getBean(类型); instanceof ioc容器的类型 == true
        MyInterface happyComponent3 = applicationContext.getBean(MyInterface.class);

        // 使用组件对象
        happyComponent2.doWork();


        // 通过三种方式获取的对象是同一个对象
        System.out.println(happyComponent == happyComponent1);
        System.out.println(happyComponent2 == happyComponent1);

        // 结束ioc容器
        applicationContext.close();

    }


    @Test
    public void testScope() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-04.xml");
        JavaBean2 bean = applicationContext.getBean(JavaBean2.class);
        JavaBean2 bean1 = applicationContext.getBean(JavaBean2.class);

        JavaBean3 bean2 = applicationContext.getBean(JavaBean3.class);
        JavaBean3 bean3 = applicationContext.getBean(JavaBean3.class);

        System.out.println(bean == bean1);

        System.out.println(bean2 == bean3);


        // 结束ioc容器
        applicationContext.close();

    }


    /**
     * 读取使用factoryBean工厂配置的组件对象
     */
    @Test
    public void testFactoryBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-05.xml");
        JavaBean javaBean = applicationContext.getBean("javaBean", JavaBean.class);

        System.out.println("javaBean = " + javaBean);

        // TODO FactoryBean工厂也会加入到ioc容器, 名字为 &id
        Object factoryBean = applicationContext.getBean("&javaBean");
        System.out.println("factoryBean = " + factoryBean);

        // 结束ioc容器
        applicationContext.close();

    }
}
