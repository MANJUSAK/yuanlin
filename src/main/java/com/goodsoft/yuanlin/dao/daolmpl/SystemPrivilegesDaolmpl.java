package com.goodsoft.yuanlin.dao.daolmpl;

import com.goodsoft.yuanlin.dao.SystemPrivilegesDao;
import com.goodsoft.yuanlin.entity.user.Grade;
import com.goodsoft.yuanlin.sessionfactory.SessionFactoryDao;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

/**
 * function 系统权限管理数据库操作类
 * <p>
 * Created by 严彬荣 on 2017/7/25.
 */
@SuppressWarnings("ALL")
@Repository
public class SystemPrivilegesDaolmpl extends SessionFactoryDao implements SystemPrivilegesDao {

    /**
     * function 系统权限判断dao方法
     *
     * @return 权限等级
     * @parameter var 权限编号
     * <p>
     */
    @Override
    public Grade judgeRights(String var) {
        DetachedCriteria dc = DetachedCriteria.forClass(Grade.class, "g");
        ProjectionList field = Projections.projectionList();
        field.add(Property.forName("g.level").as("level"));
        dc.add(Restrictions.eq("roleId", var));
        dc.setProjection(field);
        return (Grade) dc.getExecutableCriteria(super.getSessionfactroy())
                .setResultTransformer(Transformers.aliasToBean(Grade.class))
                .uniqueResult();
    }
}
