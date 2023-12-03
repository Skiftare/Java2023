package edu.project4.math;

import java.util.function.BiFunction;

enum NonlinearTransforms {
    Sinusoidal("Синусоидальное", (x, y) -> new Double[]{Math.sin(x), Math.sin(y)}, 0),
    Spherical("Сферическое", (x, y) -> new Double[]{x / (x * x + y * y), y / (x * x + y * y)}, 1),
    Polar("Полярное", (x, y) -> new Double[]{Math.atan2(y, x), Math.sqrt(x * x + y * y)}, 2),
    Heart("Сердце", (x, y) -> new Double[]{x * Math.pow(Math.sin(y), 2), y * Math.pow(Math.cos(x), 2)}, 3),
    Disk("Диск", (x, y) -> new Double[]{x / (x * x + y * y), y / (x * x + y * y)}, 4);

    private String name;
    private BiFunction<Double, Double, Double[]> transform;
    private final int id;



    NonlinearTransforms(String name, BiFunction<Double, Double, Double[]> transform, int id) {
        this.name = name;
        this.transform = transform;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Double[] applyTransform(Double x, Double y) {
        return transform.apply(x, y);
    }


}
