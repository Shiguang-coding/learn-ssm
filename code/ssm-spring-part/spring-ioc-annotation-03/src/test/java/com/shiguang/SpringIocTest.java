package com.shiguang;

import com.shiguang.ioc_01.XxxDao;
import com.shiguang.ioc_02.JavaBean;
import com.shiguang.ioc_03.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created By Shiguang On 2024/9/2 14:28
 */
public class SpringIocTest {


    @Test
    public void testIoc_01() {
        //1. 创建ioc容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-01.xml");

        //2. 获取组件
        // 根据类型获取组件
        XxxDao xxxDao = applicationContext.getBean(XxxDao.class);

        // 根据id获取组件 添加ioc注解后,bean id默认为组件类首字母小写
        Object xxxService = applicationContext.getBean("xxxService");


        //3. 使用组件
        System.out.println("xxxDao = " + xxxDao);
        System.out.println("xxxService = " + xxxService);


        //4. 关闭ioc

        applicationContext.close();
    }


    @Test
    public void testScope() {
        //1. 创建ioc容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-02.xml");
        //2. 获取组件
        JavaBean bean = applicationContext.getBean(JavaBean.class);
        JavaBean bean1 = applicationContext.getBean(JavaBean.class);
        //3. 使用组件
        System.out.println("bean == bean1 ? : " + (bean == bean1));
        //4. 关闭ioc

        applicationContext.close();
    }


    /**
     * 测试@Autowired DI
     */
    @Test
    public void testIoc_03() {
        //1. 创建ioc容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-03.xml");
        //2. 获取组件
        UserController bean = applicationContext.getBean(UserController.class);

        //3. 使用组件
        // 场景1: ioc容器中有且仅有一个UserService接口对应的实现类对象
        // 场景2: ioc容器中没有对应类型如何处理
        //       【默认】默认情况下至少要求有一个bean,否则报错
        //       【佛系】可以设置@Autowired(required=false),但是后期调用时会出现空指针异常,不推荐

        // 场景3： ioc容器中有多个相同类型的对象,会报错
        // 解决方案1: 同类型多个时,根据成员属性名查找 private UserService newUserServiceImpl;
        // 解决方案2: @Qualifier("userServiceImpl") 使用该注解获取bean的id,该注解不能单独使用,必须配置@Autowired一起使用
        // 优化点:  @Autowired(required=true) + @Qualifier(value = "userServiceImpl") = @Resource(name = "userServiceImpl")

        bean.show();

        //4. 关闭ioc
        applicationContext.close();

    }

    /**
     * 测试@Value获取外部配置文件信息
     */
    @Test
    public void testValue() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-04.xml");
        com.shiguang.ioc_04.JavaBean javaBean = applicationContext.getBean(com.shiguang.ioc_04.JavaBean.class);
        System.out.println("javaBean = " + javaBean);

    }
}
