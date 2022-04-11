package org.plutoengine.libra.text.shaping;

import org.plutoengine.libra.text.LiTextInfo;
import org.plutoengine.libra.text.font.GlyphMetrics;
import org.plutoengine.libra.text.font.LiFont;

import java.util.EnumSet;

public interface IShapingStrategy<M extends GlyphMetrics, T extends LiFont<T, M>.GlyphAtlas, F extends LiFont<T, M>>
{
    LiTextInfo shape(EnumSet<TextShaper.EnumFeature> features, F font, String text);
}
