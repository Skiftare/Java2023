package edu.project4;


import edu.project4.components.Point;
    import edu.project4.transformation.afin.AfinTransformation;
    import org.junit.jupiter.api.Test;

    import java.awt.Color;

    import static org.assertj.core.api.Assertions.assertThat;

public class AfinTransformationTest {

    @Test
    public void apply_AfinTransformationAppliedToPoint_ReturnsTransformedPoint() {
        //given: afin transformation
        AfinTransformation afinTransformation = new AfinTransformation(
            2, 1, 3, 0.5, -1, 0, Color.RED);
        Point point = new Point(1, 2);

        //when: we apply transform to the point
        Point transformedPoint = afinTransformation.apply(point);

        //then: we get expected coords
        assertThat(transformedPoint.x()).isEqualTo(7);
        assertThat(transformedPoint.y()).isEqualTo(-1.5);
    }
}
