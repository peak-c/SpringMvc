import org.junit.Test;


public class PathTest {

    @Test
    public void fun1(){
        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();

        System.out.println(path);
    }
}
