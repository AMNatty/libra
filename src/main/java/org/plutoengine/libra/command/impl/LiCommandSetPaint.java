package org.plutoengine.libra.command.impl;

import org.plutoengine.libra.command.EnumGUIPipelineCommand;
import org.plutoengine.libra.paint.LiPaint;

public final class LiCommandSetPaint extends LiCommand
{
    private final LiPaint paint;

    public LiCommandSetPaint(LiPaint paint)
    {
        super(EnumGUIPipelineCommand.SET_PAINT);

        this.paint = paint;
    }

    public LiPaint getPaint()
    {
        return this.paint;
    }
}
