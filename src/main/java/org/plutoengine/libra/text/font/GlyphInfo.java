package org.plutoengine.libra.text.font;

import org.joml.primitives.Rectanglef;

public class GlyphInfo<T extends LiFont<? super T, ? super M>.GlyphAtlas, M extends GlyphMetrics>
{
    protected T atlas;
    protected int page;
    protected Rectanglef rect;

    public GlyphInfo(T atlas, int page, Rectanglef rect)
    {
        this.atlas = atlas;
        this.page = page;
        this.rect = rect;
    }

    public T getAtlas()
    {
        return this.atlas;
    }

    public int getPage()
    {
        return this.page;
    }

    public Rectanglef getRect()
    {
        return this.rect;
    }
}
