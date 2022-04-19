package org.plutoengine.libra.paint;

import org.plutoengine.util.color.Color;

public final class LiColorPaint extends LiPaint
{
    private final Color color;

    LiColorPaint(Color color)
    {
        super(PaintType.SOLID_COLOR);

        this.color = color;
    }

    public Color getColor()
    {
        return this.color;
    }
}
