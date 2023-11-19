package edu.project3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Table {
    private String nameOfTable = "";
    private final List<String> headers = new ArrayList<>();
    private final List<List<String>> data = new ArrayList<>();
    private static final String TOGGLE_TABLE_MODE_FOR_ADOC = "|===";
    private static final Character ENDL_CHAR = '\n';
    private static final Character CELL_SEPARATOR = '|';
    private static final String TABLE_NAME_DETERMINANT_FOR_MARKDOWN = "# ";
    private static final String HEADER_AND_BODY_SEPARATOR_FOR_MARKDOWN = "|---";

    public Table(String... headers) {
        this.headers.addAll(Arrays.asList(headers));
    }

    public void nameTable(String name) {
        this.nameOfTable = name;
    }

    public void addRow(String... row) {
        data.add(Arrays.asList(row));
    }

    public String printAsString(String format) {
        if (Objects.equals(format, ".adoc")) {
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
        } else {
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
    }


}
