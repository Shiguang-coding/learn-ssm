package com.shiguang.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

/**
 * Created By Shiguang On 2024/9/3 10:54
 * description: java配置类 替代 xml配置文件
 * 作用：
 * 1. 包扫描注解配置
 * 2. 引用外部配置文件
 * 3. 声明第三方依赖的bean组件
 * 实现步骤:
 * 1.添加 @Configuration 注解 声明配置类
 * 2.实现上述三个功能
 */

@ComponentScan({"com.shiguang.ioc_01", "com.shiguang.ioc_02"})
@PropertySource(value = "classpath:jdbc.properties")
@Configuration
public class JavaConfiguration {

    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    /**
     * 代替 <bean> 标签引入第三方库
     * 一个方法对应一个 <bean>标签
     * 方法的返回值类型 = bean 组件的类型或者它的接口或父类
     * 方法体可以自定义实现过程
     * 添加 @Bean 注解 才会真正添加到ioc容器
     *
     * 问题1： bean Name 问题
     *        默认: 方法名
     *        指定： name / value 属性指定,覆盖方法名
     *
     * 问题2：周期方法如何指定
     *       原有注解方案: PostConstruct + PreDestroy 注解指定
     *       bean 属性指定： initMethod / destroyMethod 指定
     *
     * 问题3：作用域
     *       和之前相同, @Scope注解 默认单例
     *
     * 问题4： 如何引用其他ioc组件
     *        1. 直接调用对方的bean方法即可（必须也是@Bean加入ioc容器中）
     *        2. 直接形参变量进行注入,要求必须有相应的组件,如果有多个,形参名 = 组件id标识即可
     * @return
     */


    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Bean(name = "zhangsan", initMethod = "",destroyMethod = "")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;

    }

    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Bean(name = "lisi")
    public DruidDataSource dataSource1() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;

    }


    public JdbcTemplate jdbcTemplate(DataSource zhangsan, DataSource lisi ){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        // 需要 ioc 容器的其他方法  DI DruidDataSource
        // 方案1: 如果其他组件也是@Bean方法,可以直接调用
        jdbcTemplate.setDataSource(dataSource());

        // 方案2: 形参列表声明想要的组件类型,可以是一个也可以是多个 ioc容器会自动注入
        // 要求必须有相应类型的组件,没有则抛异常
        // 若有多个,可以使用形参名称等同于bean id
        jdbcTemplate.setDataSource(zhangsan);

        return jdbcTemplate;
    }


}
