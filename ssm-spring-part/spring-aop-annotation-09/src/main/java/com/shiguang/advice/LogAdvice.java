package com.shiguang.advice;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created By Shiguang On 2024/9/3 15:32
 * description: 增强类内部存储相应位置的增强代码
 * 实现步骤：
 *        1. 定义方法存储增强代码
 *           具体定义几个方法,根据插入的位置决定（前置增强,后置增强,异常增强...）
 *        2. 使用注解配置,指定插入目标方法的位置
 *           前置  @Before
 *           返回  @AfterReturning
 *           后置  @After
 *           异常  @AfterThrowing
 *           环绕  @Around
 *
 *           try{
 *               前置增强
 *               目标方法执行
 *               返回增强
 *           }catch(){
 *             异常增强
 *           }finally{
 *             后置增强
 *           }
 *
 *           TODO 切点表达式
 *                固定语法: execution(1 2 3.4.5(6))
 *                1. 访问修饰符
 *                   public / private / ...
 *                2. 方法返回参数类型
 *                   String / int / void / ...
 *                   如果不考虑访问修饰符和返回值,前两位合成一个 *
 *                   如果要不考虑,两个必须同时不考虑,不能 * String 或者 public *
 *                3. 包的位置
 *                   具体包： com.shiguang.service.impl
 *                   单层模糊: com.shiguang.service.*  (.* 单层模糊)
 *                   多层模糊: com..impl  (.. 任意层模糊)
 *                   细节：..不能开头
 *                   找到所有impl包： com..impl,不能写: ..impl,应写为: *..impl
 *                4. 类的名称
 *                   具体类: CalculatorPureImpl
 *                   模糊： *
 *                   部分模糊： *Impl, Calculator*
 *                5. 方法名: 语法和类名一致
 *                6. 形参列表
 *                   无参数: ()
 *                   有具体参数: (String), (String,int), 多个时区分先后顺序
 *                   模糊参数: (..) 有没有参数都可以,有多少个都可以
 *                   部分模糊：(String..) 首个参数String,后面有没有,是什么都可以
 *                           (..int) 最后一个参数是int
 *                           (String..int) 首个参数是String,末尾参数是int
 *
 *
 *        3. 配置切入点表达式(选择要切入的方法,即切点)
 *        4. 补全注解
 *           加入ioc容器 @Component
 *           配置切面  @Aspect (切点 + 增强)
 *        5. 开启 aspectj 注解的支持
 *           xml: <aop:aspectj-autoproxy/>
 *           配置类： @EnableAspectJAutoProxy
 */

@Component
@Aspect
@Order(20)
@EnableAspectJAutoProxy
public class LogAdvice {

    /**
     * 切点表达式的提取和复用
     * 1. 当前类中提取
     *    定义一个空方法
     *    添加 @Pointcut() 注解
     *    增强注解中引用切点表达式的方法即可
     * 2. 创建一个存储切点的类
     *    单独维护切点表达式
     *    其他类的切点方法 类的全限定符.方法名()
     */


    @Pointcut("execution(* com.shiguang.service.impl.*.*(..))")
    public void pointcut(){}


    @Before("pointcut()")
    public void start(){
        System.out.println("[AOP返回通知] 方法开始了!!");
    }

    @AfterReturning(value = "execution(public int com.shiguang.service.impl.CalculatorPureImpl.add(int,int))")
    public void printLogAfterSuccess() {
        System.out.println("[AOP返回通知] 方法成功返回了!!");
    }

    @After("com.shiguang.pointcut.MyPointCut.pointcut()")
    public void after(){
        System.out.println("[AOP返回通知] 方法结束了!!");
    }

    @AfterThrowing("execution(* com.shiguang.service.impl.*.*(..))")
    public void error(){
        System.out.println("[AOP返回通知] 方法出错了!!");
    }
}
