package org.plutoengine.libra.paint;

import org.joml.Vector2f;
import org.joml.Vector2fc;
import org.plutoengine.util.color.Color;

public sealed abstract class LiPaint permits LiColorPaint, LiGradientPaint
{
    public enum PaintType
    {
        SOLID_COLOR,
        GRADIENT
    }

    private final PaintType type;

    protected LiPaint(PaintType type)
    {
        this.type = type;
    }

    public PaintType getType()
    {
        return this.type;
    }

    public static LiColorPaint solidColor(Color color)
    {
        return new LiColorPaint(color);
    }

    public static LiGradientPaint linearGradient(Vector2fc start, Vector2fc end, LiGradientPaint.Stop... stops)
    {
        return new LiGradientPaint(LiGradientPaint.Type.LINEAR, start, end, stops);
    }

    public static LiGradientPaint horizontaLinearGradient(LiGradientPaint.Stop... stops)
    {
        return new LiGradientPaint(LiGradientPaint.Type.LINEAR, new Vector2f(0f, 0.5f), new Vector2f(1f, 0.5f), stops);
    }

    public static LiGradientPaint verticalLinearGradient(LiGradientPaint.Stop... stops)
    {
        return new LiGradientPaint(LiGradientPaint.Type.LINEAR, new Vector2f(0.5f, 0f), new Vector2f(0.5f, 1.0f), stops);
    }

    public static LiGradientPaint radialGradient(Vector2fc center, float size, LiGradientPaint.Stop... stops)
    {
        return new LiGradientPaint(LiGradientPaint.Type.RADIAL, center, center.mul(size, new Vector2f()), stops);
    }
}
