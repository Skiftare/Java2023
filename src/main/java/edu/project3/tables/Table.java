package edu.project3.tables;

import edu.project3.systeminteraction.FileAndPathManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Table {
    private final List<String> headers = new ArrayList<>();
    private final List<List<String>> data = new ArrayList<>();
    private String nameOfTable = "";

    public Table(String... headers) {
        this.headers.addAll(Arrays.asList(headers));
    }

    public void nameTable(String name) {
        this.nameOfTable = name;
    }

    public void addRow(String... row) {
        data.add(Arrays.asList(row));
    }

    private String printTableAsAdoc() {
        TablePrinter adocTablePrinter = new AdocTablePrinter();
        adocTablePrinter.printTable(nameOfTable, headers, data);
        return adocTablePrinter.printAsString();
    }

    private String printTableAsMarkdown() {
        TablePrinter markdownTablePrinter = new MarkdownTablePrinter();
        markdownTablePrinter.printTable(nameOfTable, headers, data);
        return markdownTablePrinter.printAsString();
    }

    public String printAsString() {
        String format = FileAndPathManager.getFileFormat();
        if (Objects.equals(format, ".adoc")) {
            return printTableAsAdoc();
        } else {
            return printTableAsMarkdown();
        }
    }

}
