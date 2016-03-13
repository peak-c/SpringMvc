import config.DaoConfiguration;
import config.ServiceConfiguration;
import dao.StudentDao;
import domain.Student;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.StudentService;

/**
 * Created by peakone on 2015/9/28.
 */
public class StudentServiceTest {
    private AnnotationConfigApplicationContext context;
    private StudentService studentService;

    @Before
    public void setUp(){
        context = new AnnotationConfigApplicationContext();
        context.register(DaoConfiguration.class);
        context.register(ServiceConfiguration.class);
        context.refresh();
        studentService = (StudentService) context.getBean("studentService");
    }

    @Test
    public void saveTest() throws Exception {
        Student student = new Student();
        student.setName("stu1");
        studentService.addStudent(student);

        System.out.println(studentService.listAll().get(0));
    }
}
