package edu.project4.components;

import edu.project4.transformation.nonlinear.NonLinearCompose;

public record ImageProperties(
    String fileName,
    String outputFolder,
    String fileExtension,
    int width,
    int height,
    int symmetry,
    NonLinearCompose vars,
    int countOfPoints,
    int cores
) {

}
