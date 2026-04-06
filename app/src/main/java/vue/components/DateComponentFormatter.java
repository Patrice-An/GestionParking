package vue.components;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFormattedTextField.AbstractFormatter;

public class DateComponentFormatter extends AbstractFormatter {
    private static final long serialVersionUID = 1L;
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Object stringToValue(String text) throws ParseException {
        if (text == null || text.trim().isEmpty())
            return null;
        return df.parse(text);
    }

    @Override
    public String valueToString(Object value) {
        if (value == null)
            return "";
        if (value instanceof java.util.Date) {
            return df.format((java.util.Date) value);
        }
        if (value instanceof Calendar) {
            return df.format(((Calendar) value).getTime());
        }
        return value.toString();
    }
}
