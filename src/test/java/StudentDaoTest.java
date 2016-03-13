import config.DaoConfiguration;
import dao.StudentDao;
import domain.Student;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by peakone on 2015/9/28.
 */
public class StudentDaoTest {

    private AnnotationConfigApplicationContext context;
    private StudentDao studentDao;

    @Before
    public void setUp(){
        context = new AnnotationConfigApplicationContext();
        context.register(DaoConfiguration.class);
        context.refresh();
        studentDao = (StudentDao) context.getBean("studentDao");
    }


    @Test
    public void saveTest() throws Exception {
        Student student = new Student();
        student.setName("stu1");
        studentDao.persist(student);

        System.out.println(studentDao.findAll().get(0));
    }

}
