package org.sky;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author thor
 */
public class AccountVisit {
    private String accountName;
    private Date visitDate;
    public AccountVisit(String name, Date date) {
        accountName = name;
        visitDate = date;
    }

    public String getAccountName() { return  accountName; }
    public Date getVisitDate() { return visitDate; }
    public static List<AccountVisit> parse(List<String[]> visits) throws ParseException {
        List<AccountVisit> accountVisits = new ArrayList<>();
        for (String[] visit: visits) {
            String name = visit[0];
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date visitDate = format.parse(visit[1]);
            accountVisits.add(new AccountVisit(name, visitDate));
        }
        return accountVisits;
    }
}
