package org.sky;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

/**
 * @author thor
 */
public class AccountLimitsTest {
    @Test
    public void testParse() throws IOException {
        String limits = "{ \"Thor\": 1, \"Widow\": 2, \"Ironman\": 10 }";
        Map<String, Integer> accounts = AccountLimits.parse(limits);
        Assertions.assertEquals(3, accounts.size());
        Assertions.assertEquals(1, accounts.get("Thor"));
        Assertions.assertEquals(2, accounts.get("Widow"));
        Assertions.assertEquals(10, accounts.get("Ironman"));
    }

    @Test
    public void testGetExceededAccounts() throws IOException {
        Map<String, Integer> limits = new HashMap<>();
        List<AccountVisit> visits = new ArrayList<>();
        limits.put("Thor", 2);
        limits.put("Widow", 0);
        visits.add(new AccountVisit("Thor", new Date()));
        visits.add(new AccountVisit("Widow", new Date()));
        List<String> result = AccountLimits.getExceededAccounts(limits, visits);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Widow", result.get(0));
    }
}
