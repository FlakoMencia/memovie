package com.api.rent.memovie.utilities;

/**
 *
 * @author MARIO MENCIA
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtilities {

    public static Date obtenerFechaFinal(Date start, Integer dayToAdd) {
        if (dayToAdd != null || dayToAdd == 0) {
            return start;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.DAY_OF_YEAR, dayToAdd);
        return calendar.getTime();
    }

    public static Double calcularPenalidad(Date now, Date finalDate, Double amountPenaltyPerday) {
        int dias = (int) (now.getTime() - finalDate.getTime());
        return (dias * amountPenaltyPerday);
    }
}
