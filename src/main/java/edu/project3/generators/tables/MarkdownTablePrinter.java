package edu.project3.generators.tables;

import java.util.List;
import static edu.project3.utility.UtilityTableClass.CELL_SEPARATOR;
import static edu.project3.utility.UtilityTableClass.ENDL_CHAR;
import static edu.project3.utility.UtilityTableClass.HEADER_AND_BODY_SEPARATOR_FOR_MARKDOWN;
import static edu.project3.utility.UtilityTableClass.TABLE_NAME_DETERMINANT_FOR_MARKDOWN;

public class MarkdownTablePrinter extends TablePrinter {
    @Override
    protected void printTableHeader() {
        sb.append(TABLE_NAME_DETERMINANT_FOR_MARKDOWN).append(nameOfTable).append(ENDL_CHAR);
        sb.append(CELL_SEPARATOR);
        for (String header : headers) {
            sb.append(header).append(CELL_SEPARATOR);
        }
        sb.append(ENDL_CHAR);
        sb.append(HEADER_AND_BODY_SEPARATOR_FOR_MARKDOWN.repeat(headers.size()));
        sb.append(CELL_SEPARATOR).append(ENDL_CHAR);
    }

    @Override
    protected void printTableRow(List<String> row) {
        sb.append(CELL_SEPARATOR);
        for (String cell : row) {
            sb.append(cell).append(CELL_SEPARATOR);
        }
        sb.append(ENDL_CHAR);
    }
}
