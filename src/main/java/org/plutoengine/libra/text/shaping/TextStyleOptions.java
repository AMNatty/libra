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

package org.plutoengine.libra.text.shaping;

import org.joml.primitives.Rectanglef;
import org.plutoengine.libra.paint.LiPaint;
import org.plutoengine.libra.text.font.GlyphMetrics;
import org.plutoengine.libra.text.font.LiFont;
import org.plutoengine.libra.text.font.LiFontFamily;
import org.plutoengine.util.color.Color;

import java.util.Locale;

public class TextStyleOptions
{
    public static final String STYLE_BOLD = "bold";
    public static final String STYLE_BLACK = "black";
    public static final String STYLE_REGULAR = "regular";
    public static final String STYLE_BOLD_ITALIC = "bold italic";
    public static final String STYLE_ITALIC = "italic";
    public static final String STYLE_MEDIUM = "medium";

    public enum TextAlign
    {
        START,
        CENTER,
        END
    }

    public enum OverflowXStrategy
    {
        OVERFLOW,
        SCALE_TO_FIT
    }

    public enum OverflowYStrategy
    {
        OVERFLOW,
        SCALE_TO_FIT
    }

    private TextAlign horizontalAlign;
    private TextAlign verticalAlign;

    private Rectanglef fitBox;

    private @FontStyleValue String style;

    private OverflowXStrategy overflowX;
    private OverflowYStrategy overflowY;

    private LiPaint paint;

    private float size;

    public TextStyleOptions(float size)
    {
        this.size = size;
        this.style = STYLE_REGULAR;
        this.paint = LiPaint.solidColor(Color.WHITE);
        this.overflowX = OverflowXStrategy.OVERFLOW;
        this.overflowY = OverflowYStrategy.OVERFLOW;
        this.horizontalAlign = TextAlign.START;
        this.verticalAlign = TextAlign.END;
    }

    public TextStyleOptions setFitBox(Rectanglef fitBox)
    {
        this.fitBox = fitBox;

        return this;
    }

    public TextStyleOptions setOverflowX(OverflowXStrategy overflowX)
    {
        this.overflowX = overflowX;

        return this;
    }

    public TextStyleOptions setOverflowY(OverflowYStrategy overflowY)
    {
        this.overflowY = overflowY;

        return this;
    }

    public TextStyleOptions setSize(float size)
    {
        this.size = size;

        return this;
    }

    public TextStyleOptions setStyle(@FontStyleValue String style)
    {
        this.style = style;

        return this;
    }

    public Rectanglef getFitBox()
    {
        return this.fitBox;
    }

    public OverflowXStrategy getOverflowX()
    {
        return this.overflowX;
    }

    public OverflowYStrategy getOverflowY()
    {
        return this.overflowY;
    }

    public float getSize()
    {
        return this.size;
    }

    public @FontStyleValue String getStyle()
    {
        return this.style;
    }

    public TextStyleOptions setPaint(LiPaint paint)
    {
        this.paint = paint;

        return this;
    }

    public LiPaint getPaint()
    {
        return this.paint;
    }

    public TextAlign getHorizontalAlign()
    {
        return this.horizontalAlign;
    }

    public TextStyleOptions setHorizontalAlign(TextAlign horizontalAlign)
    {
        this.horizontalAlign = horizontalAlign;

        return this;
    }

    public TextAlign getVerticalAlign()
    {
        return this.verticalAlign;
    }

    public TextStyleOptions setVerticalAlign(TextAlign verticalAlign)
    {
        this.verticalAlign = verticalAlign;

        return this;
    }

    public <F extends LiFont<? super G, M>, G extends LiFont<? super G, M>.GlyphAtlas, M extends GlyphMetrics> F pickFont(LiFontFamily<F> family)
    {
        var font = family.getStyle(this.style);

        if (font != null)
            return font;

        var styles = family.getStyles();

        for (var e : styles.entrySet())
        {
            var key = e.getKey();

            var sv = key.toLowerCase(Locale.ROOT);
            var cv = this.style.toLowerCase(Locale.ROOT);

            if (sv.equals(cv))
                return e.getValue();
        }

        font = family.getStyle(STYLE_REGULAR);

        if (font != null)
            return font;

        return styles.values()
            .stream()
            .findAny()
            .orElse(null);
    }
}
