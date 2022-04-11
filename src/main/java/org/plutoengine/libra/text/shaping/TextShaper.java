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

    public LiTextInfo shape(F font, String text)
    {
        return this.strategy.shape(this.featureSet, font, text);
    }
}
