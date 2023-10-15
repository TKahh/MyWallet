package tdtu.fit.tvka.mywallet;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.sql.Array;
import java.util.Arrays;

public class XAxisVFormatter extends ValueFormatter {

    @Override
    public String getAxisLabel(float value, AxisBase axis ){
        int intValue = (int) value;
        int adjustedValue = Math.max(1, Math.min(intValue, 31));
        return String.valueOf(adjustedValue);
    }
}
