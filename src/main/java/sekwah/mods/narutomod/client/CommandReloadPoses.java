package sekwah.mods.narutomod.client;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.animation.NarutoAnimator;
import sekwah.mods.narutomod.animation.Pose;

import java.io.InputStream;
import java.util.List;

public class CommandReloadPoses extends CommandBase {

    @Override
    public String getCommandName() {
        return "reload_poses";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/reload_poses";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] p_71515_2_) {
        InputStream fileStreamJson = NarutoMod.class.getResourceAsStream("/assets/narutomod/mod/poseData.json");
        NarutoAnimator.playerPoses = new Pose[]{new Pose("default")};
        NarutoAnimator.playerPoses = NarutoMod.entityAnimator.addPoses(fileStreamJson, NarutoAnimator.playerPoses);
        sender.addChatMessage(new ChatComponentText("Poses reloaded"));
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return true;
    }
}
