package com.imz.imz_example_mod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Commands extends CommandBase {
    @Override
    public String getName() {
        return "taggui";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.taggui.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 0)
        {
            throw new WrongUsageException("commands.taggui.usage");
        }
        else
        {
            EntityPlayer entityPlayer = CommandBase.getCommandSenderAsPlayer(sender);
            World worldIn = sender.getEntityWorld();
            BlockPos pos = sender.getPosition();
            entityPlayer.openGui(ExampleMod.instance, GUIHandler.PlayerTagView , worldIn , pos.getX() , pos.getY() , pos.getZ());
        }
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }
}
