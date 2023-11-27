package edu.project3;

import edu.project3.utility.UtilityClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import static edu.project3.utility.UtilityTableClass.TOGGLE_TABLE_MODE_FOR_ADOC;
import static edu.project3.utility.UtilityTableClass.CELL_SEPARATOR;
import static edu.project3.utility.UtilityTableClass.ENDL_CHAR;
import static edu.project3.utility.UtilityTableClass.HEADER_AND_BODY_SEPARATOR_FOR_MARKDOWN;
import static edu.project3.utility.UtilityTableClass.TABLE_NAME_DETERMINANT_FOR_MARKDOWN;

public class Table {
    private String nameOfTable = "";
    private final List<String> headers = new ArrayList<>();
    private final List<List<String>> data = new ArrayList<>();


    public Table(String... headers) {
        this.headers.addAll(Arrays.asList(headers));
    }

    public void nameTable(String name) {
        this.nameOfTable = name;
    }

    public void addRow(String... row) {
        data.add(Arrays.asList(row));
    }

    private String printTableAsAdoc(){
        StringBuilder sb = new StringBuilder();
        sb.append(nameOfTable).append(ENDL_CHAR);
        sb.append(TOGGLE_TABLE_MODE_FOR_ADOC).append(ENDL_CHAR);
        for (String header : headers) {
            sb.append("| ").append(header).append(" ");
        }
        sb.append(ENDL_CHAR);
        sb.append(ENDL_CHAR);

        for (List<String> datum : data) {
            for (String s : datum) {
                sb.append(CELL_SEPARATOR).append(s).append(ENDL_CHAR);
            }
            sb.append(ENDL_CHAR);
        }
        sb.append(TOGGLE_TABLE_MODE_FOR_ADOC).append(ENDL_CHAR);
        return sb.toString();
    }

    private String printTableAsMarkdown(){
        StringBuilder markdown = new StringBuilder();
        markdown.append(TABLE_NAME_DETERMINANT_FOR_MARKDOWN).append(nameOfTable).append(ENDL_CHAR);
        markdown.append(CELL_SEPARATOR);
        for (String header : headers) {
            markdown.append(header).append(CELL_SEPARATOR);
        }
        markdown.append(ENDL_CHAR);
        markdown.append(HEADER_AND_BODY_SEPARATOR_FOR_MARKDOWN.repeat(headers.size()));
        markdown.append(CELL_SEPARATOR).append(ENDL_CHAR);
        for (List<String> row : data) {
            markdown.append(CELL_SEPARATOR);
            for (String cell : row) {
                markdown.append(cell).append(CELL_SEPARATOR);
            }
            markdown.append(ENDL_CHAR);
        }
        markdown.append(ENDL_CHAR);
        return markdown.toString();
    }

    public String printAsString() {
        String format = UtilityClass.getFileFormat();
        if (Objects.equals(format, ".adoc")) {
            return printTableAsAdoc();
        } else {
            return printTableAsMarkdown();
        }
    }


}
