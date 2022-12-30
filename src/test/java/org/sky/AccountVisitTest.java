package org.sky;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author thor
 */
public class AccountVisitTest {
    @Test
    public void testParse() throws Exception {
        List <String[]> visits = new ArrayList<>();
        visits.add(new String[]{"Thor", "2012-11-23"});
        visits.add(new String[]{"Widow", "2022-04-20"});
        List<AccountVisit> accountVisits = AccountVisit.parse(visits);
        Assertions.assertEquals(2, accountVisits.size());
        Assertions.assertEquals("Thor", accountVisits.get(0).getAccountName());
        Assertions.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2012-11-23"), accountVisits.get(0).getVisitDate());
        Assertions.assertEquals("Widow", accountVisits.get(1).getAccountName());
        Assertions.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2022-04-20"), accountVisits.get(1).getVisitDate());
    }
}
