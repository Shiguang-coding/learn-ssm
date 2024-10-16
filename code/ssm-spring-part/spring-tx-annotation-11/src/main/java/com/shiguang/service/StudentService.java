package com.shiguang.service;

import com.shiguang.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
@Transactional(timeout = 3)
public class StudentService {
    
    @Autowired
    private StudentDao studentDao;


    /**
     * 添加事务：@Transactional
     * 位置:  方法,类
     *     方法：当前方法有事务
     *     类: 类下的所有方法都有事务
     *
     * 1. 只读模式
     *    只读模式可以提升查询事务的效率,推荐事务中只有查询代码时使用只读模式
     *    默认： boolean readOnly() default false
     *    备注：一般情况下,都是在上添加注解开启事务,那么类下的所有方法都有事务
     *         查询方法可以通过再次添加注解并设置只读模式,提高效率
     * 2. 超时时间
     *    默认：永不超时 -1
     *    如何修改: timeout = 超时时间（ 单位：秒 ） 超出时间回滚事务并释放异常(TransactionTimedOutException)
     *    小思考：如果类上设置事务属性并指定超时时间,方法也设置事务属性不设置超时时间,方法会按照类上的超时时间吗？
     *    答案： 不会,方法上的设置会覆盖类上的设置
     * 3. 指定异常回滚 和 指定异常不会滚
     *    默认情况下,指定发生运行时异常事务才会回滚
     *    我们可以指定Exception异常来控制所有异常都回滚
     *    rollbackFor = Exception.class
     *    noRollbackFor = 回滚异常范围内,控制某个异常不回滚
     * 4. 隔离级别设置
     *    推荐设置第二个隔离级别 isolation = Isolation.READ_COMMITTED
     */

    @Transactional(readOnly = false,rollbackFor = Exception.class,isolation = Isolation.READ_COMMITTED)
    public void changeInfo() throws FileNotFoundException {
        studentDao.updateAgeById(100,1);
        new FileInputStream("xxx");
        System.out.println("-----------");
        studentDao.updateNameById("test1",1);
    }


    @Transactional(readOnly = true)
    public void getAll(){
        // 查询操作 没有必要开启事务
        // 若@Transactional添加在类上,那么该类下的所有方法默认开启事务,readOnly = false
        // 获取所有学生信息,查询操作,可以开启只读模式
    }


    /**
     * 声明两个独立修改数据库的事务业务方法
     * 1. propagation = Propagation.REQUIRED
     *    父方法有事务,我们就加入到父方法的事务,最终是同一个事务,推荐使用默认值
     * 2. propagation = Propagation.REQUIRES_NEW
     *    无论父方法是否有事务,最终都是独立的事务
     *
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changeAge(){
        studentDao.updateAgeById(99,1);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void changeName(){
        studentDao.updateNameById("ergouzi",1);
        int i = 1/0;
    }

}
