package dao.impl;

import dao.StudentDao;
import domain.Student;

import java.util.List;

/**
 * Created by peakone on 2015/9/28.
 */
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {
    @Override
    public Student findByNum(Student student) {
        String hql = "select s from Student s " +
                " inner join s.numbers as n where n.num = ? and s.password=?";

        List<Student> list = (List<Student>) hibernateTemplate.find(hql, new String[]{student.getNumbers().getNum(),student.getPassword()});

        System.out.println("dao_list:"+list);
        if(list!=null && list.size()>0) {
            System.out.println("dao_list:"+list.get(0));
            return list.get(0);
        }
        return null;
    }
}
