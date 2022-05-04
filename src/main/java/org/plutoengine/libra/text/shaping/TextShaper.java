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

import org.plutoengine.libra.text.LiTextInfo;
import org.plutoengine.libra.text.font.GlyphMetrics;
import org.plutoengine.libra.text.font.LiFont;

import java.util.EnumSet;


public class TextShaper<M extends GlyphMetrics, T extends LiFont<T, M>.GlyphAtlas, F extends LiFont<T, M>>
{
    // TODO: Use FreeType when project Panama is done

    // TODO: Text direction

    public enum EnumFeature
    {
        KERNING

        // TODO: Ligatures
    }

    private final EnumSet<EnumFeature> featureSet;
    private final IShapingStrategy<M, T, F> strategy;

    public TextShaper(IShapingStrategy<M, T, F> strategy)
    {
        this.featureSet = EnumSet.noneOf(EnumFeature.class);
        this.strategy = strategy;
    }

    public TextShaper<M, T, F> withFeature(EnumFeature feature)
    {
        this.featureSet.add(feature);

        return this;
    }

    public LiTextInfo shape(F font, String text, TextStyleOptions style)
    {
        return this.strategy.shape(this.featureSet, font, text, style);
    }
}
