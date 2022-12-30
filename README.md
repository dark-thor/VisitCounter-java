# Visit Counter

## Description

One of the input files provides account names and associated visits limit for that account.
Another input file is the log of visits for the account name with dates of access.

Return the account names that have exceeded the limit.

### Getting Started

Requires gradle 7, JDK 1.8

### Build

`./gradlew build`

### Running the program

Execute with command <br>
`java -jar build/libs/VisitCounter-1.0-SNAPSHOT.jar -L /path/to/limits.json -V /path/to/visits.csv`

Arguments description
* -L : (required) Path to JSON file containing account name and limits.
* -V : (required) Path to CSV file containing name and dates of the account visits.

### Output

The desired solution emit the valid lines to standard output.

### Design of the solution

* Limits file is provided as JSON map whereas Visits file is provided as CSV file. JSON map ensures no duplicates. Visits could also be JSON file if logging data structure is complex.
* Read each file into Map and List respectively.
* Write a grouping by name to count visits for each account.
* Iterate through limits and check corresponding visits count. If threshold exceeded, add account name to result list.
* Return the result.
