import com.ubs.opsit.interviews.BerlinClock;
import com.ubs.opsit.interviews.TimeConverter;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class BerlinClockUnitTests {

    static TimeConverter berlinClock;

    @BeforeClass
    public static void initialize() {
        berlinClock = new BerlinClock();
    }

    @Test
    public void shouldConvertTimeThrowExceptionWhenInputIsNull() {
        try {
            berlinClock.convertTime(null);
            fail("Should throw exception but it didn't happen!");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Given time should not be null!");
        }
    }

    @Test
    public void shouldConvertTimeThrowExceptionWhenInputIsEmpty() {
        try {
            berlinClock.convertTime("");
            fail("Should throw exception but it didn't happen!");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Given time is empty!");
        }
    }

    @Test
    public void shouldConvertTimeThrowExceptionWhenInputDoesNotMatchThe24hTimePattern() {
        try {
            berlinClock.convertTime("99:99:99");
            fail("Should throw exception but it didn't happen!");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Given time is in a wrong format. Should look like XX:XX:XX" +
                    " and match 24h clock rules.");
        }
    }

    @Test
    public void shouldConvertTimeReturnCorrectValuesForCorrectInputTime() {
        assertThat(berlinClock.convertTime("12:34:56")).isEqualTo("Y" + System.lineSeparator() +
                "RROO" + System.lineSeparator() +
                "RROO" + System.lineSeparator() +
                "YYRYYROOOOO" + System.lineSeparator() +
                "YYYY");
    }

    @Test
    public void shouldConvertTimeHandle_24_00_00() {
        assertThat(berlinClock.convertTime("24:00:00")).isEqualTo("Y" + System.lineSeparator() +
                "RRRR" + System.lineSeparator() +
                "RRRR" + System.lineSeparator() +
                "OOOOOOOOOOO" + System.lineSeparator() +
                "OOOO");
    }

}
