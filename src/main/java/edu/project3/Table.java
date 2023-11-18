package edu.project3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Table {
    private String nameOfTable = "";
    private final List<String> headers = new ArrayList<>();
    private final List<List<String>> data = new ArrayList<>();
    private final String toggleTableModeForAdoc = "|===";
    private final Character endlChar = '\n';
    private final Character cellSeparator = '|';
    private final String tableNameDeterminantForMarkdown = "# ";
    private final String headerAndBodySeparatorForMarkdown = "|---";

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
            sb.append(nameOfTable).append(endlChar);
            sb.append(toggleTableModeForAdoc).append(endlChar);
            for (String header : headers) {
                sb.append("| ").append(header).append(" ");
            }
            sb.append(endlChar);
            sb.append(endlChar);

            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < data.get(i).size(); j++) {
                    sb.append(cellSeparator).append(data.get(i).get(j)).append(endlChar);
                }
                sb.append(endlChar);
            }
            sb.append(toggleTableModeForAdoc).append(endlChar);
            return sb.toString();
        } else {
            StringBuilder markdown = new StringBuilder();
            markdown.append(tableNameDeterminantForMarkdown).append(nameOfTable).append(endlChar);
            markdown.append(cellSeparator);
            for (String header : headers) {
                markdown.append(header).append(cellSeparator);
            }
            markdown.append(endlChar);
            markdown.append(headerAndBodySeparatorForMarkdown.repeat(headers.size()));
            markdown.append(cellSeparator).append(endlChar);
            for (List<String> row : data) {
                markdown.append(cellSeparator);
                for (String cell : row) {
                    markdown.append(cell).append(cellSeparator);
                }
                markdown.append(endlChar);
            }
            markdown.append(endlChar);
            return markdown.toString();
        }
    }


}
