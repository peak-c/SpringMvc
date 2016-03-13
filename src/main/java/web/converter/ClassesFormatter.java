package web.converter;

import domain.Classes;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by peakone on 2015/9/30.
 */
public class ClassesFormatter implements Formatter<Classes> {
    @Override
    public Classes parse(String text, Locale locale) throws ParseException {
        Classes classes = new Classes();
        classes.setName(text);
        return classes;
    }

    @Override
    public String print(Classes classes, Locale locale) {
        return classes.getName();
    }
}
