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
