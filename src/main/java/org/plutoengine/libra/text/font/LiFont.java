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

package org.plutoengine.libra.text.font;

import java.util.HashMap;
import java.util.Map;

public abstract class LiFont<G extends LiFont<? super G, ? super M>.GlyphAtlas, M extends GlyphMetrics> implements AutoCloseable
{
    private final String name;
    private final Map<Integer, M> metrics;
    protected G atlas;
    LiFontFamily<?> family;

    protected LiFont(String name)
    {
        this.name = name;
        this.metrics = new HashMap<>();
    }

    public LiFontFamily<?> getFamily()
    {
        return this.family;
    }

    public String getName()
    {
        return this.name;
    }

    public void addGlyph(int codepoint, M metrics, GlyphInfo<G, M> info)
    {
        this.metrics.put(codepoint, metrics);
        this.atlas.glyphInfoMap.put(codepoint, info);
    }

    public M getGlyphMetrics(int codepoint)
    {
        return this.metrics.get(codepoint);
    }

    public G getGlyphAtlas()
    {
        return this.atlas;
    }

    public abstract class GlyphAtlas
    {
        protected Map<Integer, GlyphInfo<? extends G, ? extends M>> glyphInfoMap;

        protected GlyphAtlas()
        {
            this.glyphInfoMap = new HashMap<>();
        }

        public GlyphInfo<? extends G, ? extends M> getGlyph(int codepoint)
        {
            return this.glyphInfoMap.get(codepoint);
        }
    }

    @Override
    public void close()
    {

    }
}
