package org.sky;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.cli.*;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * @author thor
 */
public class VisitCounterMain {
    public static void main(String[] args) throws Exception {
        CommandLineParser parser = new BasicParser();
        Options options = new Options();

        options.addOption("L", true, "path to limits json file.");
        options.addOption("V", true, "path to visits csv file");

        CommandLine commandLine = parser.parse(options, args);
        String limitsFilePath = commandLine.getOptionValue("L");
        if( limitsFilePath == null) {
            throw new ParseException("Mandatory arguments: -L path/to/limits/file");
        }
        String visitsFilePath = commandLine.getOptionValue("V");
        if( visitsFilePath == null) {
            throw new ParseException("Mandatory arguments: -V path/to/visits/file");
        }

        String accountLimitsData = new String(Files.readAllBytes(Paths.get(limitsFilePath)));
        Map<String, Integer> accountLimits = AccountLimits.parse(accountLimitsData);

        FileReader filereader = new FileReader(visitsFilePath);
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withSkipLines(1)
                .build();
        List<String[]> visitsData = csvReader.readAll();
        List<AccountVisit> accountVisits = AccountVisit.parse(visitsData);
        System.out.println(AccountLimits.getExceededAccounts(accountLimits, accountVisits));
    }
}