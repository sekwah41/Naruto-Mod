package sekwah.mods.narutomod.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.UserListOpsEntry;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.jutsu.Jutsu;
import sekwah.mods.narutomod.packets.PacketDispatcher;
import sekwah.mods.narutomod.packets.clientbound.ClientJutsuCommandPacket;

import java.util.Collections;
import java.util.List;

import static net.minecraft.util.EnumChatFormatting.RED;

public class CommandReloadAnims extends CommandBase {

    //TODO make configurable
    private static final int PERMISSION_LEVEL_LIST_JUTSUS = 0;
    private static final int PERMISSION_LEVEL_ACTIVATE_JUTSUS = 0;

    @Override
    public String getCommandName() {
        return "reloadanims";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "commands.naruto.reload.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        sender.addChatMessage(new ChatComponentText("Reloading Anims."));
        NarutoMod.reloadAnims();
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        if(args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "activate", "list");
        }
        else if(args.length == 2 && "activate".equals(args[0])) {
            return getListOfStringsFromIterableMatchingLastWord(args, Jutsu.getRegisteredJutsuCombinations().keySet());
        }
        return Collections.emptyList();
    }

    private static int getPermissionLevel(EntityPlayerMP player) {
        UserListOpsEntry userlistopsentry = (UserListOpsEntry)player.mcServer.getConfigurationManager().getOppedPlayers().getEntry(player.getGameProfile());
        return userlistopsentry != null ? userlistopsentry.func_152644_a() : player.mcServer.getOpPermissionLevel();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
