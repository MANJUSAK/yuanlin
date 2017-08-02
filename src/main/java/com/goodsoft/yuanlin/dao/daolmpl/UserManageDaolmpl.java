package com.goodsoft.yuanlin.dao.daolmpl;

import com.goodsoft.yuanlin.dao.UserManageDao;
import com.goodsoft.yuanlin.entity.user.Grade;
import com.goodsoft.yuanlin.entity.user.Rights;
import com.goodsoft.yuanlin.entity.user.UserInfo;
import com.goodsoft.yuanlin.entity.user.Users;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * function 登录注册模块访问数据库Java类
 *
 * @author 严彬荣
 */
@SuppressWarnings("ALL")
@Repository
public class UserManageDaolmpl implements UserManageDao {

    @Resource
    private SessionFactory factory;

    /**
     * 用户登陆验证dao方法
     *
     * @param user 用户登录信息
     * @return 登录结果
     */
    @Override
    public UserInfo login(Users user) {
        DetachedCriteria dc = DetachedCriteria.forClass(Users.class, "u");
        ProjectionList field = Projections.projectionList();
        field.add(Property.forName("u.uid").as("uid"));
        field.add(Property.forName("u.userName").as("userName"));
        field.add(Property.forName("u.roleId").as("roleId"));
        field.add(Property.forName("u.telOrEmail").as("telOrEmail"));
        field.add(Property.forName("u.IDcard").as("IDcard"));
        field.add(Property.forName("u.label").as("label"));
        field.add(Property.forName("u.dates").as("dates"));
        dc.add(Restrictions.or(Restrictions.eq("userName", user.getUserName()),
                Restrictions.eq("telOrEmail", user.getUserName())));
        dc.add(Restrictions.eq("passWord", user.getPassWord()));
        dc.add(Restrictions.eq("isNo", 0));
        dc.setProjection(field);
        return (UserInfo) dc.getExecutableCriteria(this.factory.getCurrentSession()).setResultTransformer(Transformers.aliasToBean(UserInfo.class)).uniqueResult();
    }

    // 检查用户名是否重复
    @Override
    public List checkUserName(String userName) {
        DetachedCriteria dc = DetachedCriteria.forClass(Users.class, "u");
        ProjectionList field = Projections.projectionList();
        field.add(Projections.property("u.uid").as("uid"));
        dc.add(Restrictions.eq("userName", userName));
        dc.setProjection(field);
        return dc.getExecutableCriteria(this.factory.getCurrentSession()).list();
    }

    // 检查手机号是否重复dao方法
    @Override
    public List checkUserTel(String tel) {
        DetachedCriteria dc = DetachedCriteria.forClass(Users.class, "u");
        ProjectionList field = Projections.projectionList();
        field.add(Projections.property("u.uid").as("uid"));
        dc.add(Restrictions.eq("telOrEmail", tel));
        dc.setProjection(field);
        return dc.getExecutableCriteria(this.factory.getCurrentSession()).list();
    }

    // 用户注册dao方法
    @Override
    public void register(Users user) throws Exception {
        this.factory.getCurrentSession().save(user);
        this.factory.getCurrentSession().clear();
    }

    /**
     * 功能：检查系统权限数据是否存在（防止服务器重启再次初始化权限数据）dao方法
     *
     * @return Boolean
     * @parameter 无
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Rights> queryRights() {
        DetachedCriteria dc = DetachedCriteria.forClass(Rights.class);
        return dc.getExecutableCriteria(this.factory.getCurrentSession()).list();
    }

    /**
     * 初始化用户具有系统权限dao接口
     *
     * @parameter var 用户权限等级
     * <p>
     * return 权限等级编号
     */
    @Override
    public Grade queryRightsById(int var) {
        DetachedCriteria dc = DetachedCriteria.forClass(Grade.class, "g");
        ProjectionList field = Projections.projectionList();
        field.add(Property.forName("g.roleId").as("roleId"));
        field.add(Property.forName("g.level").as("level"));
        dc.add(Restrictions.eq("level", var));
        dc.setProjection(field);
        return (Grade) dc.getExecutableCriteria(this.factory.getCurrentSession())
                .setResultTransformer(Transformers.aliasToBean(Grade.class))
                .uniqueResult();
    }

    /**
     * 功能：初始化系统权限管理dao接口
     *
     * @return 无
     * @parameter rights 权限信息，grade 权限等级信息
     */
    @Override
    public void rightsInitialization(List<Rights> rights, List<Grade> grade) throws Exception {
        for (int i = 0, length = rights.size(); i < length; ++i) {
            this.factory.getCurrentSession().save(rights.get(i));
            this.factory.getCurrentSession().save(grade.get(i));
            this.factory.getCurrentSession().clear();
        }
    }
}
