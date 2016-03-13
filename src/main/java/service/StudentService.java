package service;

import dao.StudentDao;
import domain.Student;

import java.util.List;

/**
 * Created by peakone on 2015/9/28.
 */
public class StudentService {

    private StudentDao studentDao;

    public StudentService(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    public void addStudent(Student student) throws Exception{
        studentDao.persist(student);
    }

    public List<Student> listAll() throws Exception{
        return studentDao.findAll();
    }

    public void deleteStudents(Long... ids) throws Exception{
        studentDao.deleteByIds(ids);
    }

    public Student findStudent(long id)  throws Exception{
        return studentDao.findById(id);
    }

    public void updateStudent(Student student) throws Exception{
        studentDao.update(student);
    }

    public Student login(Student student) throws Exception{
        return studentDao.findByNum(student);
    }
}
