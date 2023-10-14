package tdtu.fit.tvka.mywallet;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class XAxisVFormatter extends ValueFormatter {
    @Override
    public String getAxisLabel(float value, AxisBase axis ){
        return String.valueOf((int) value);
    }
}
