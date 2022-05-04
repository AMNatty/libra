/*
 * MIT License
 *
 * Copyright (c) 2022 493msi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
