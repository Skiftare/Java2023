package edu.project3;

import java.util.ArrayList;
import java.util.List;

public class Report {

        private List<Table> tables;

        public Report() {
            tables = new ArrayList<>();
        }

        public void addTable(Table newIncomeTable){
            tables.add(newIncomeTable);
        }

        public List<Table> getTablesInReport() {
            return tables;
        }
}
