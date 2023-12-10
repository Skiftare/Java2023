package edu.project4;


import edu.project4.components.Point;
    import edu.project4.transformation.afin.AfinTransformation;
    import org.junit.jupiter.api.Test;

    import java.awt.Color;

    import static org.assertj.core.api.Assertions.assertThat;

public class AfinTransformationTest {

    @Test
    public void apply_AfinTransformationAppliedToPoint_ReturnsTransformedPoint() {
        AfinTransformation afinTransformation = new AfinTransformation(
            2, 1, 3, 0.5, -1, 0, Color.RED
        );
        Point point = new Point(1, 2);

        Point transformedPoint = afinTransformation.apply(point);

        assertThat(transformedPoint.x()).isEqualTo(7);
        assertThat(transformedPoint.y()).isEqualTo(-1.5);
    }
}
