package dao;


import domain.Student;

public interface StudentDao extends BaseDao<Student>{

    public Student findByNum(Student student) ;
}
