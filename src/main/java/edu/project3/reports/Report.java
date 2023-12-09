package edu.project3.reports;

import edu.project3.tables.Table;
import java.util.ArrayList;
import java.util.List;

public class Report {

    private final List<Table> tables;

    public Report() {
        tables = new ArrayList<>();
    }

    public void addTable(Table newIncomeTable) {
        tables.add(newIncomeTable);
    }

    public List<Table> getTablesInReport() {
        return tables;
    }
}
