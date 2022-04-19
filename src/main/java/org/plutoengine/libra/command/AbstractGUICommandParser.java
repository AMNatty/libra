package org.plutoengine.libra.command;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGUICommandParser
{
    protected final List<LiCommandBuffer> buffers;

    protected AbstractGUICommandParser()
    {
        this.buffers = new ArrayList<>();
    }

    public final void add(LiCommandBuffer buffer)
    {
        this.buffers.add(buffer);
    }

    public final IGUIRenderer parse()
    {
        return this.parse(GUICommandMerger.merge(this.buffers));
    }

    protected abstract IGUIRenderer parse(LiCommandBuffer mergedBuffer);
}
