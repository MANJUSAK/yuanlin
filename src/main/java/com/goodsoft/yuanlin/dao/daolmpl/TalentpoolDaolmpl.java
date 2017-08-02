package com.goodsoft.yuanlin.dao.daolmpl;

import com.goodsoft.yuanlin.dao.TalentpoolDao;
import com.goodsoft.yuanlin.entity.talentpool.Corporation;
import com.goodsoft.yuanlin.sessionfactory.SessionFactoryDao;
import org.apache.poi.ss.formula.functions.T;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * function 人才库模块访问数据库Java类
 * <p>
 * date 2017-05-18
 *
 * @author 严彬荣
 */

@SuppressWarnings("ALL")
@Repository
public class TalentpoolDaolmpl extends SessionFactoryDao implements TalentpoolDao {

    /**
     * 查询人才库信息数据dao方法
     *
     * @param c 查询实体类
     * @return 查询结果
     */
    @Override
    public List queryTalentpoolDao(Class c) {
        // TODO Auto-generated method stub
        DetachedCriteria dc = DetachedCriteria.forClass(c);
        dc.addOrder(Order.desc("id"));
        return dc.getExecutableCriteria(super.getSessionfactroy()).list();
    }

    /**
     * 通过编号查询法人库信息数据dao方法
     *
     * @param id 营业证照编号
     * @return 查询结果
     */
    @Override
    public List queryCorporationDaoById(String id) {
        DetachedCriteria dc = DetachedCriteria.forClass(Corporation.class);
        dc.add(Restrictions.eq("registeredId", id));
        return dc.getExecutableCriteria(super.getSessionfactroy()).list();
    }

    /**
     * 人才库信息数据录入dao方法
     *
     * @param msg 录入的数据
     * @return 无
     */
    @Override
    public void saveDao(Object msg) throws Exception {
        // TODO Auto-generated method stub
        super.getSessionfactroy().save(msg);
        super.getSessionfactroy().clear();
    }

    /**
     * 人才库信息多数据录入dao方法
     *
     * @param msg 录入的多条数据
     * @return 无
     */
    @Override
    public void saveDao(List<T> msg) throws Exception {
        // TODO Auto-generated method stub
        for (int i = 0, length = msg.size(); i < length; ++i) {
            super.getSessionfactroy().save(msg.get(i));
            super.getSessionfactroy().clear();
        }
        msg.clear();
    }

}
