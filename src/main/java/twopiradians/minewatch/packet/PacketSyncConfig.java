package twopiradians.minewatch.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import twopiradians.minewatch.common.Minewatch;
import twopiradians.minewatch.common.config.Config;

public class PacketSyncConfig implements IMessage {

	// SERVERSIDE
	private boolean preventFallDamage;
	private boolean allowGunWarnings;
	private boolean projectilesCauseKnockback;
	private int tokenDropRate;
	private int wildCardRate;
	private double damageScale;
	private int durabilityOptionsArmor;
	private int durabilityOptionsWeapons;
	private boolean healMobs;
	private double healthPackHealMultiplier;
	private double healthPackRespawnMultiplier;
	private double ammoMultiplier;
	private boolean tokenDropRequiresPlayer;
	private double abilityCooldownMultiplier;
	private double aimAssist;
	private boolean deleteItemsOnGround;
	private boolean lobbyCommand;
	private double healthScale;
	private double armor;
	private boolean stepAssist;
	private double ultimateChargeNormal;
	private double ultimateChargeDamage;
	private boolean lowerGravity;
	private boolean saturation;
	// team blocks
	private boolean customDeathScreen;
	private int respawnTime;
	private boolean allowHeroRespawn;
	private boolean allowMobRespawn;
	private boolean allowPlayerRespawn;
	private boolean mobRespawnRandomHero;
	private boolean heroSelectClearMWItems;
	// hero mobs
	private boolean heroMobsDespawn;
	private boolean mobRandomSkins;
	private int mobSpawn;
	private int mobSpawnFreq;
	private boolean mobTargetPlayers;
	private boolean mobTargetHostiles;
	private boolean mobTargetPassives;
	private boolean mobTargetHeroes;
	private int mobTokenDropRate;
	private int mobWildCardDropRate;
	private double mobEquipmentDropRate;
	private double mobAttackCooldown;
	private double mobInaccuracy;
	private double damageScaleHero;
	private double ultimateChargeNormalHero;
	private double ultimateChargeDamageHero;

	public PacketSyncConfig() {
		// read values from config now that it's about to be sent to server
		Config.syncConfig();
		Config.config.save();

		this.preventFallDamage = Config.preventFallDamage;
		this.allowGunWarnings = Config.allowGunWarnings;
		this.projectilesCauseKnockback = Config.projectilesCauseKnockback;
		this.tokenDropRate = Config.tokenDropRate;
		this.wildCardRate = Config.wildCardRate;
		this.damageScale = Config.damageScale;
		this.durabilityOptionsArmor = Config.durabilityOptionArmors;
		this.durabilityOptionsWeapons = Config.durabilityOptionWeapons;
		this.healMobs = Config.healMobs;
		this.healthPackHealMultiplier = Config.healthPackHealMultiplier;
		this.healthPackRespawnMultiplier = Config.healthPackRespawnMultiplier;
		this.ammoMultiplier = Config.ammoMultiplier;
		this.tokenDropRequiresPlayer = Config.tokenDropRequiresPlayer;
		this.abilityCooldownMultiplier = Config.abilityCooldownMultiplier;
		this.aimAssist = Config.aimAssist;
		this.deleteItemsOnGround = Config.deleteItemsOnGround;
		this.lobbyCommand = Config.lobbyCommand;
		this.healthScale = Config.healthScale;
		this.armor = Config.armor;
		this.stepAssist = Config.stepAssist;
		this.ultimateChargeNormal = Config.ultimateChargeNormal;
		this.ultimateChargeDamage = Config.ultimateChargeDamage;
		this.lowerGravity = Config.lowerGravity;
		this.saturation = Config.saturation;

		this.customDeathScreen = Config.customDeathScreen;
		this.respawnTime = Config.respawnTime;
		this.allowHeroRespawn = Config.allowHeroRespawn;
		this.allowMobRespawn = Config.allowMobRespawn;
		this.allowPlayerRespawn = Config.allowPlayerRespawn;
		this.mobRespawnRandomHero = Config.mobRespawnRandomHero;
		this.heroSelectClearMWItems = Config.heroSelectClearMWItems;

		this.heroMobsDespawn = Config.heroMobsDespawn;
		this.mobRandomSkins = Config.mobRandomSkins;
		this.mobSpawn = Config.mobSpawn;
		this.mobSpawnFreq = Config.mobSpawnFreq;
		this.mobTargetPlayers = Config.mobTargetPlayers;
		this.mobTargetHostiles = Config.mobTargetHostiles;
		this.mobTargetPassives = Config.mobTargetPassives;
		this.mobTargetHeroes = Config.mobTargetHeroes;
		this.mobTokenDropRate = Config.mobTokenDropRate;
		this.mobWildCardDropRate = Config.mobWildCardDropRate;
		this.mobEquipmentDropRate = Config.mobEquipmentDropRate;
		this.mobAttackCooldown = Config.mobAttackCooldown;
		this.mobInaccuracy = Config.mobInaccuracy;
		this.damageScaleHero = Config.damageScaleHero;
		this.ultimateChargeNormalHero = Config.ultimateChargeNormalHero;
		this.ultimateChargeDamageHero = Config.ultimateChargeDamageHero;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.preventFallDamage = buf.readBoolean();
		this.allowGunWarnings = buf.readBoolean();
		this.projectilesCauseKnockback = buf.readBoolean();
		this.tokenDropRate = buf.readInt();
		this.wildCardRate = buf.readInt();
		this.damageScale = buf.readDouble();
		this.durabilityOptionsArmor = buf.readInt();
		this.durabilityOptionsWeapons = buf.readInt();
		this.healMobs = buf.readBoolean();
		this.healthPackHealMultiplier = buf.readDouble();
		this.healthPackRespawnMultiplier = buf.readDouble();
		this.ammoMultiplier = buf.readDouble();
		this.tokenDropRequiresPlayer = buf.readBoolean();
		this.abilityCooldownMultiplier = buf.readDouble();
		this.aimAssist = buf.readDouble();
		this.deleteItemsOnGround = buf.readBoolean();
		this.lobbyCommand = buf.readBoolean();
		this.healthScale = buf.readDouble();
		this.armor = buf.readDouble();
		this.stepAssist = buf.readBoolean();
		this.ultimateChargeNormal = buf.readDouble();
		this.ultimateChargeDamage = buf.readDouble();
		this.lowerGravity = buf.readBoolean();
		this.saturation = buf.readBoolean();
		
		this.customDeathScreen = buf.readBoolean();
		this.respawnTime = buf.readInt();
		this.allowHeroRespawn = buf.readBoolean();
		this.allowMobRespawn = buf.readBoolean();
		this.allowPlayerRespawn = buf.readBoolean();
		this.mobRespawnRandomHero = buf.readBoolean();
		this.heroSelectClearMWItems = buf.readBoolean();

		this.heroMobsDespawn = buf.readBoolean();
		this.mobRandomSkins = buf.readBoolean();
		this.mobSpawn = buf.readInt();
		this.mobSpawnFreq = buf.readInt();
		this.mobTargetPlayers = buf.readBoolean();
		this.mobTargetHostiles = buf.readBoolean();
		this.mobTargetPassives = buf.readBoolean();
		this.mobTargetHeroes = buf.readBoolean();
		this.mobTokenDropRate = buf.readInt();
		this.mobWildCardDropRate = buf.readInt();
		this.mobEquipmentDropRate = buf.readDouble();
		this.mobAttackCooldown = buf.readDouble();
		this.mobInaccuracy = buf.readDouble();
		this.damageScaleHero = buf.readDouble();
		this.ultimateChargeNormalHero = buf.readDouble();
		this.ultimateChargeDamageHero = buf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(this.preventFallDamage);
		buf.writeBoolean(this.allowGunWarnings);
		buf.writeBoolean(this.projectilesCauseKnockback);
		buf.writeInt(this.tokenDropRate);
		buf.writeInt(this.wildCardRate);
		buf.writeDouble(this.damageScale);
		buf.writeInt(this.durabilityOptionsArmor);
		buf.writeInt(this.durabilityOptionsWeapons);
		buf.writeBoolean(this.healMobs);
		buf.writeDouble(this.healthPackHealMultiplier);
		buf.writeDouble(this.healthPackRespawnMultiplier);
		buf.writeDouble(this.ammoMultiplier);
		buf.writeBoolean(this.tokenDropRequiresPlayer);
		buf.writeDouble(this.abilityCooldownMultiplier);
		buf.writeDouble(this.aimAssist);
		buf.writeBoolean(this.deleteItemsOnGround);
		buf.writeBoolean(this.lobbyCommand);
		buf.writeDouble(this.healthScale);
		buf.writeDouble(this.armor);
		buf.writeBoolean(this.stepAssist);
		buf.writeDouble(this.ultimateChargeNormal);
		buf.writeDouble(this.ultimateChargeDamage);
		buf.writeBoolean(this.lowerGravity);
		buf.writeBoolean(this.saturation);

		buf.writeBoolean(this.customDeathScreen);
		buf.writeInt(this.respawnTime);
		buf.writeBoolean(this.allowHeroRespawn);
		buf.writeBoolean(this.allowMobRespawn);
		buf.writeBoolean(this.allowPlayerRespawn);
		buf.writeBoolean(this.mobRespawnRandomHero);
		buf.writeBoolean(this.heroSelectClearMWItems);

		buf.writeBoolean(this.heroMobsDespawn);
		buf.writeBoolean(this.mobRandomSkins);
		buf.writeInt(this.mobSpawn);
		buf.writeInt(this.mobSpawnFreq);
		buf.writeBoolean(this.mobTargetPlayers);
		buf.writeBoolean(this.mobTargetHostiles);
		buf.writeBoolean(this.mobTargetPassives);
		buf.writeBoolean(this.mobTargetHeroes);
		buf.writeInt(this.mobTokenDropRate);
		buf.writeInt(this.mobWildCardDropRate);
		buf.writeDouble(this.mobEquipmentDropRate);
		buf.writeDouble(this.mobAttackCooldown);
		buf.writeDouble(this.mobInaccuracy);
		buf.writeDouble(this.damageScaleHero);
		buf.writeDouble(this.ultimateChargeNormalHero);
		buf.writeDouble(this.ultimateChargeDamageHero);
	}

	public void run() {
		Config.preventFallDamage = this.preventFallDamage;
		Config.allowGunWarnings = this.allowGunWarnings;
		Config.projectilesCauseKnockback = this.projectilesCauseKnockback;
		Config.tokenDropRate = this.tokenDropRate;
		Config.wildCardRate = this.wildCardRate;
		Config.damageScale = this.damageScale;
		Config.durabilityOptionArmors = this.durabilityOptionsArmor;
		Config.durabilityOptionWeapons = this.durabilityOptionsWeapons;
		Config.healMobs = this.healMobs;
		Config.healthPackHealMultiplier = this.healthPackHealMultiplier;
		Config.healthPackRespawnMultiplier = this.healthPackRespawnMultiplier;
		Config.ammoMultiplier = this.ammoMultiplier;
		Config.tokenDropRequiresPlayer = this.tokenDropRequiresPlayer;
		Config.abilityCooldownMultiplier = this.abilityCooldownMultiplier;
		Config.aimAssist = this.aimAssist;
		Config.deleteItemsOnGround = this.deleteItemsOnGround;
		Config.lobbyCommand = this.lobbyCommand;
		Config.healthScale = this.healthScale;
		Config.armor = this.armor;
		Config.stepAssist = this.stepAssist;
		Config.ultimateChargeNormal = this.ultimateChargeNormal;
		Config.ultimateChargeDamage = this.ultimateChargeDamage;
		Config.lowerGravity = this.lowerGravity;
		Config.saturation = this.saturation;

		Config.customDeathScreen = this.customDeathScreen;
		Config.respawnTime = this.respawnTime;
		Config.allowHeroRespawn = this.allowHeroRespawn;
		Config.allowMobRespawn = this.allowMobRespawn;
		Config.allowPlayerRespawn = this.allowPlayerRespawn;
		Config.mobRespawnRandomHero = this.mobRespawnRandomHero;
		Config.heroSelectClearMWItems = this.heroSelectClearMWItems;

		Config.heroMobsDespawn = this.heroMobsDespawn;
		Config.mobRandomSkins = this.mobRandomSkins;
		Config.mobSpawn = this.mobSpawn;
		Config.mobSpawnFreq = this.mobSpawnFreq;
		Config.mobTargetPlayers = this.mobTargetPlayers;
		Config.mobTargetHostiles = this.mobTargetHostiles;
		Config.mobTargetPassives = this.mobTargetPassives;
		Config.mobTargetHeroes = this.mobTargetHeroes;
		Config.mobTokenDropRate = this.mobTokenDropRate;
		Config.mobWildCardDropRate = this.mobWildCardDropRate;
		Config.mobEquipmentDropRate = this.mobEquipmentDropRate;
		Config.mobAttackCooldown = this.mobAttackCooldown;
		Config.mobInaccuracy = this.mobInaccuracy;
		Config.damageScaleHero = this.damageScaleHero;
		Config.ultimateChargeNormalHero = this.ultimateChargeNormalHero;
		Config.ultimateChargeDamageHero = this.ultimateChargeDamageHero;

		Config.syncConfig(true, false);
		Config.config.save();
	}

	/**Sync config to client on login or config synced to server via tab button / command*/
	public static class HandlerClient implements IMessageHandler<PacketSyncConfig, IMessage> {
		@Override
		public IMessage onMessage(final PacketSyncConfig packet, final MessageContext ctx) {
			IThreadListener mainThread = Minecraft.getMinecraft();
			mainThread.addScheduledTask(() -> {
				packet.run();
				Minewatch.logger.info("Synced config from server.");
			});
			return null;
		}
	}

	/**Sync config to server with tab button / command*/
	public static class HandlerServer implements IMessageHandler<PacketSyncConfig, IMessage> {
		@Override
		public IMessage onMessage(final PacketSyncConfig packet, final MessageContext ctx) {
			IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
			mainThread.addScheduledTask(() -> {
				EntityPlayer player = ctx.getServerHandler().player;
				if (player != null) {
					if (player.getServer().getPlayerList().canSendCommands(player.getGameProfile())) {
						packet.run();
						Minewatch.network.sendToAll(new PacketSyncConfig()); // sync new config to all clients
						player.sendMessage(new TextComponentString(TextFormatting.GREEN+"Successfully synced config to server."));
					}
					else
						player.sendMessage(new TextComponentString(TextFormatting.RED+"You do not have permission to do that."));
				}
			});
			return null;
		}
	}
}
