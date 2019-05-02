package sekwah.mods.narutomod.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.UserListOpsEntry;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import sekwah.mods.narutomod.jutsu.Jutsu;
import sekwah.mods.narutomod.packets.PacketDispatcher;
import sekwah.mods.narutomod.packets.clientbound.ClientJutsuCommandPacket;

import java.util.Collections;
import java.util.List;

import static net.minecraft.util.EnumChatFormatting.RED;

public class CommandJutsu extends CommandBase {

    //TODO make configurable
    private static final int PERMISSION_LEVEL_LIST_JUTSUS = 0;
    private static final int PERMISSION_LEVEL_ACTIVATE_JUTSUS = 0;

    @Override
    public String getCommandName() {
        return "jutsu";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "commands.naruto.jutsu.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        EntityPlayerMP player = getCommandSenderAsPlayer(sender);
        int perms = getPermissionLevel(player);
        if(args.length == 0) throw new WrongUsageException(getCommandUsage(sender));
        if(args.length == 1 && "list".equals(args[0])) {
            if(perms >= PERMISSION_LEVEL_LIST_JUTSUS) {
                PacketDispatcher.sendPacketToPlayer(new ClientJutsuCommandPacket(0), player);
            }
            else {
                IChatComponent chatComponent = new ChatComponentTranslation("commands.generic.permission");
                chatComponent.getChatStyle().setColor(RED);
                sender.addChatMessage(chatComponent);
            }
            return;
        }

        if("activate".equals(args[0])) {
            if(perms < PERMISSION_LEVEL_ACTIVATE_JUTSUS) {
                IChatComponent chatComponent = new ChatComponentTranslation("commands.generic.permission");
                chatComponent.getChatStyle().setColor(RED);
                sender.addChatMessage(chatComponent);
            }
            if(args.length == 2) {
                if(Jutsu.getRegisteredJutsuCombinations().containsKey(args[1])) {
                    PacketDispatcher.sendPacketToPlayer(new ClientJutsuCommandPacket(Jutsu.getRegisteredJutsuCombinations().get(args[1])), player);
                }
                else {
                    sender.addChatMessage(new ChatComponentText("Invalid Jutsu!"));
                }
                return;
            }
            else {
                throw new WrongUsageException("commands.naruto.jutsu.activate.usage");
            }
        }
        throw new WrongUsageException(getCommandUsage(sender));
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
