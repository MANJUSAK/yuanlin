package com.goodsoft.yuanlin.dao.daolmpl;

import com.goodsoft.yuanlin.dao.Filedao;
import com.goodsoft.yuanlin.entity.file.FileData;
import com.goodsoft.yuanlin.sessionfactory.SessionFactoryDao;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * function 文件管理dao接口实现类
 * Created by 严彬荣 on 2017/8/1.
 */
@SuppressWarnings("ALL")
@Repository
public class FileDaolmpl extends SessionFactoryDao implements Filedao {

    /**
     * 文件查询dao方法
     *
     * @return 图片路径
     * @parameter id 文件编号
     */
    @Override
    public List<FileData> qureyFileDao(String id) {
        DetachedCriteria dc = DetachedCriteria.forClass(FileData.class, "f");
        ProjectionList field = Projections.projectionList();
        field.add(Property.forName("f.path").as("path"));
        dc.add(Restrictions.eq("fileId", id));
        dc.setProjection(field);
        return dc.getExecutableCriteria(super.getSessionfactroy()).list();
    }

    /**
     * 文件保存dao方法
     *
     * @return 无
     * @parameter msg 文件路径，var 文件数据
     */
    @Override
    public void saveFileDao(List<String> msg, FileData var) throws Exception {
        for (int i = 0, length = msg.size(); i < length; ++i) {
            var.setPath(msg.get(i));
            super.getSessionfactroy().save(var);
            super.getSessionfactroy().clear();
        }
        msg.clear();
    }
}
