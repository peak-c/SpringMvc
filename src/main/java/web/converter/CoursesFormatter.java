package web.converter;

import domain.Course;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by peakone on 2015/9/30.
 */
public class CoursesFormatter implements Formatter<List<Course>> {
    @Override
    public List<Course> parse(String text, Locale locale) throws ParseException {

        String[] names = text.split(";");
        List<Course> courses = new ArrayList<>();

        for(String name : names){
            Course course = new Course();
            course.setName(name);
            courses.add(course);
        }

        return courses;
    }

    @Override
    public String print(List<Course> courses, Locale locale) {

        List<String> names = courses.parallelStream()
                .map(course -> course.getName())
                .collect(Collectors.toList());
//        String[] names = new String[courses.size()];
//
//        for(int i=0;i<names.length;i++){
//            names[i] = courses.get(i).getName();
//        }
//        if(names.size()<2) {
            return String.join(";", names);
//        }else{
//            return names.get(0)+";"+names.get(1)+"...";
//        }
    }
}
