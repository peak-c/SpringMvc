package dao.impl;

import dao.BaseDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

/**
 * Created by peakone on 2015/9/24.
 */
@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {

    @Resource(name = "hibernateTemplate")//自动装配
    public HibernateTemplate hibernateTemplate;
    private Class<T> clazz;
    private String className;

    public BaseDaoImpl(){
        // 使用反射技术得到T的真实类型
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
        className = clazz.getSimpleName();
    }
    @Override
    @Transactional(readOnly = true,value = "transactionManager")
    public List<T> findAll() throws Exception{
        return (List<T>) hibernateTemplate.find("from "+className);
    }
    @Override
    public void persist(T t) throws Exception{
        hibernateTemplate.persist(t);
    }
    @Override
    public void update(T t) throws Exception{
        hibernateTemplate.update(t);
    }
    @Override
    public void delete(T t) throws Exception{
        hibernateTemplate.delete(t);
    }

    @Override
    public void deleteByIds(Long... ids) throws Exception{
        String hql = "DELETE "+className+" WHERE id IN ( :ids)";

        hibernateTemplate.execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                query.setParameterList("ids", ids);

                return query.executeUpdate();
            }
        });
    }

    @Override
    public T findById(Long id) {
        return hibernateTemplate.get(clazz, id);
    }

}
