package edu.project3.tables;

import java.util.List;
import static edu.project3.utility.UtilityTableClass.CELL_SEPARATOR;
import static edu.project3.utility.UtilityTableClass.ENDL_CHAR;
import static edu.project3.utility.UtilityTableClass.TOGGLE_TABLE_MODE_FOR_ADOC;
import static edu.project3.utility.UtilityTableClass.WORDS_SEPARATOR;

class AdocTablePrinter extends TablePrinter {
    @Override
    protected void printTableHeader() {
        sb.append(nameOfTable).append(ENDL_CHAR);
        sb.append(TOGGLE_TABLE_MODE_FOR_ADOC).append(ENDL_CHAR);
        for (String header : headers) {
            sb.append(CELL_SEPARATOR).append(WORDS_SEPARATOR).append(header).append(WORDS_SEPARATOR);
        }
        sb.append(ENDL_CHAR).append(ENDL_CHAR);
    }

    @Override
    protected void printTableRow(List<String> row) {
        for (String cell : row) {
            sb.append(CELL_SEPARATOR).append(cell).append(ENDL_CHAR);
        }
        sb.append(ENDL_CHAR);
    }

}
