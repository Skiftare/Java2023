package edu.project4;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.w3c.dom.css.Rect;

public class FractalRenderer {
    private final FractalImage image;
    private final Rect world;
    private final List<Transformation> variations;
    private final int samples;
    private final short iterPerSample;
    private final long seed;

    public FractalRenderer(FractalImage image, Rect world, List<Transformation> variations, int samples, short iterPerSample, long seed) {
        this.image = image;
        this.world = world;
        this.variations = variations;
        this.samples = samples;
        this.iterPerSample = iterPerSample;
        this.seed = seed;
    }

    public FractalImage render() {
        Random random = new Random(seed);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        AtomicInteger pixelCount = new AtomicInteger(0);

        for (int num = 0; num < samples; ++num) {
            executorService.submit(() -> {
                for (short step = 0; step < iterPerSample; ++step) {
                    Point pw = randomPoint(random, world);

                    for (Transformation variation : variations) {
                        pw = variation.apply(pw);

                        double theta2 = 0.0;
                        for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
                            var pwr = rotate(pw, theta2);
                            if (!world.contains(pwr)) continue;

                            Pixel pixel = mapRange(world, pwr, image);
                            if (pixel == null) continue;

                            synchronized (pixel) {
                                // Подмешиваем цвет и увеличиваем hit count
                                pixel = new Pixel(pixel.getR(), pixel.getG(), pixel.getB(), pixel.getHitCount() + 1);
                                image.setPixel((int) Math.floor(pwr.getX()), (int) Math.floor(pwr.getY()), pixel);
                            }
                        }
                    }
                }
                int count = pixelCount.incrementAndGet();
                if (count % 1000 == 0) {
                    System.out.println("Processed " + count + " pixels");
                }
            });
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // Ждем завершения всех потоков
        }

        return image;
    }

    private Point randomPoint(Random random, Rect rect) {
        double x = rect.x + random.nextDouble() * rect.width;
        double y = rect.y + random.nextDouble() * rect.height;
        return new Point(x, y);
    }

    private Point rotate(Point point, double angle) {
        double x = point.x * Math.cos(angle) - point.y * Math.sin(angle);
        double y = point.x * Math.sin(angle) + point.y * Math.cos(angle);
        return new Point(x, y);
    }

    private Pixel mapRange(Rect world, Point point, FractalImage image) {
        int x = (int) ((point.x - world.x) / world.width * image.width);
        int y = (int) ((point.y - world.y) / world.height * image.height);

        if (image.contains(x, y)) {
            return image.pixel(x, y);
        } else {
            return null;
        }
    }
}

