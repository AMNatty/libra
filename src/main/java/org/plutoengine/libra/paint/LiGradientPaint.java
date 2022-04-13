package org.plutoengine.libra.paint;

import org.joml.Vector2fc;
import org.plutoengine.util.color.Color;

public final class LiGradientPaint extends LiPaint
{
    public enum Type
    {
        LINEAR,
        LINEAR_MIRROR,
        RADIAL,
        CONICAL,
        DIAMOND
    }

    private final Type gradientType;

    private final Vector2fc start;
    private final Vector2fc end;

    private final Stop[] stops;

    LiGradientPaint(Type gradientType, Vector2fc start, Vector2fc end, Stop... stops)
    {
        super(PaintType.GRADIENT);

        this.gradientType = gradientType;

        this.start = start;
        this.end = end;

        if (stops.length == 0 || stops.length > 16)
            throw new UnsupportedOperationException("Only 1-16 gradient stops are supported.");

        this.stops = stops;
    }

    public record Stop(float position, Color color)
    {

    }

    public Type getGradientType()
    {
        return this.gradientType;
    }

    public Vector2fc getStart()
    {
        return this.start;
    }

    public Vector2fc getEnd()
    {
        return this.end;
    }

    public Stop[] getStops()
    {
        return this.stops;
    }
}
