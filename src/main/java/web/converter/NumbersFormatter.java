package web.converter;

import domain.Numbers;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by peakone on 2015/9/30.
 */
public class NumbersFormatter implements Formatter<Numbers> {
    @Override
    public Numbers parse(String text, Locale locale) throws ParseException {
        Numbers numbers = new Numbers();
        numbers.setNum(text);
        return numbers;
    }

    @Override
    public String print(Numbers numbers, Locale locale) {
        return numbers.getNum();
    }
}
