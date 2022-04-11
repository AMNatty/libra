package org.plutoengine.libra.command;

import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3fc;
import org.plutoengine.libra.command.impl.LiCommand;
import org.plutoengine.libra.command.impl.LiCommandSetTransform;
import org.plutoengine.libra.command.impl.LiCommandSwitchShader;
import org.plutoengine.libra.command.impl.LiCommandSwitchTexture;

import java.util.*;
import java.util.stream.Stream;

public class LiCommandBuffer implements Iterable<LiCommand>
{
    private final Deque<LiCommand> commands;

    private Object texture;
    private Object shader;
    private Matrix3fc transform;

    public LiCommandBuffer()
    {
        this.commands = new LinkedList<>();
    }

    public LiCommandBuffer pushAll(LiCommandBuffer commandBuffer)
    {
        commandBuffer.forEach(this::push);

        return this;
    }

    public LiCommandBuffer push(LiCommand command)
    {
        switch (command.getType())
        {
            case SET_TRANSFORM -> {
                removeAllUntil(EnumSet.of(EnumGUIPipelineCommand.SET_TRANSFORM), EnumSet.of(EnumGUIPipelineCommand.DRAW_MESH));

                var trans = ((LiCommandSetTransform) command).getTransform();

                if (trans.equals(this.transform))
                    break;

                this.commands.add(command);
                this.transform = trans;
            }

            case SWITCH_SHADER -> {
                // This does have side effects, however in practice it is unwanted to switch shaders like that
                removeAllUntil(EnumSet.of(EnumGUIPipelineCommand.SWITCH_SHADER, EnumGUIPipelineCommand.SET_TRANSFORM), EnumSet.of(EnumGUIPipelineCommand.DRAW_MESH));

                var shd = ((LiCommandSwitchShader<?>) command).getShader();

                if (shd.equals(this.shader))
                    break;

                this.commands.add(command);
                this.shader = shd;

                if (this.transform != null)
                    this.commands.add(new LiCommandSetTransform(this.transform));
            }

            case SWITCH_TEXTURE -> {
                removeAllUntil(EnumSet.of(EnumGUIPipelineCommand.SWITCH_TEXTURE), EnumSet.of(EnumGUIPipelineCommand.DRAW_MESH));
                var tex = ((LiCommandSwitchTexture<?>) command).getTexture();

                if (tex.equals(this.texture))
                    break;

                this.commands.add(command);
                this.texture = tex;
            }

            case DRAW_MESH -> mergeInto(command);
        }

        return this;
    }

    private void removeAllUntil(EnumSet<EnumGUIPipelineCommand> remove, EnumSet<EnumGUIPipelineCommand> until)
    {
        var it = this.commands.descendingIterator();
        while (it.hasNext())
        {
            var val = it.next();
            var type = val.getType();

            if (until.contains(type))
                return;

            if (remove.contains(type))
                it.remove();
        }
    }

    private void mergeInto(LiCommand command)
    {
        LiCommand prev;

        while ((prev = this.commands.peekLast()) != null)
        {
            if (prev.getType() != command.getType() || !prev.supportsMerge(command))
                break;

            command = prev.merge(command);

            this.commands.removeLast();
        }

        this.commands.add(command);
    }

    public Stream<LiCommand> stream()
    {
        return this.commands.stream();
    }

    @NotNull
    @Override
    public Iterator<LiCommand> iterator()
    {
        return this.commands.iterator();
    }
}
