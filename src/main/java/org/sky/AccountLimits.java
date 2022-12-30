package org.sky;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author thor
 */
public enum AccountLimits {
    ACCOUNTS;

    public static Map<String, Integer> parse(String jsonString) {
        Gson gson = new Gson();
        Type accountType = new TypeToken<Map<String, Integer>>() {}.getType();
        Map<String, Integer> accounts = gson.fromJson(jsonString, accountType);
        return accounts;
    }

    public static List<String> getExceededAccounts(Map<String, Integer> limits, List<AccountVisit> visits) {
        List<String> result = new ArrayList<>();
        Map<String, List<AccountVisit>> visitsPerAccount = visits.stream().collect(Collectors.groupingBy(AccountVisit::getAccountName));
        limits.entrySet().stream().forEach(e -> {
            if (visitsPerAccount.get(e.getKey()).size() > e.getValue()) {
                result.add(e.getKey());
            }
        });
        return result;
    }
}
