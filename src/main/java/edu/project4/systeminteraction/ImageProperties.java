package edu.project4.systeminteraction;

public record ImageProperties (
        String fileName,
        String outputFolder,
        String fileExtension,
        int width,
        int height,
        boolean optimize,
        int symmetry
    )
{

}
