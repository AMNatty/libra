package org.plutoengine.libra.text.font;

import java.util.HashMap;
import java.util.Map;

public abstract class LiFont<G extends LiFont<G, M>.GlyphAtlas, M extends GlyphMetrics>
{
    private final String name;
    private final Map<Integer, M> metrics;
    protected G atlas;
    LiFontFamily<?, G, M> family;

    protected LiFont(String name)
    {
        this.name = name;
        this.metrics = new HashMap<>();
    }

    public LiFontFamily<?, G, M> getFamily()
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
        protected Map<Integer, GlyphInfo<G, M>> glyphInfoMap;

        protected GlyphAtlas()
        {
            this.glyphInfoMap = new HashMap<>();
        }

        public GlyphInfo<G, M> getGlyph(int codepoint)
        {
            return this.glyphInfoMap.get(codepoint);
        }
    }
}
