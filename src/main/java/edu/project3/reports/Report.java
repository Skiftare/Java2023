package edu.project3.reports;

import edu.project3.tables.Table;
import java.util.ArrayList;
import java.util.List;

public class Report {

    private final List<Table> tables;

    public Report() {
        tables = new ArrayList<>();
    }

    void addTable(Table newIncomeTable) {
        tables.add(newIncomeTable);
    }

    List<Table> getTablesInReport() {
        return tables;
    }
}
