
# Eagler Context Redacted Diff
# Copyright (c) 2023 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  2 : 4  @  2

+ import java.util.HashMap;
+ 

> DELETE  3  @  3 : 4

> INSERT  48 : 51  @  48

+ import net.zxmushroom63.plugins.BaseData;
+ import net.zxmushroom63.plugins.PluginAPI;
+ import net.zxmushroom63.plugins.PluginData;

> DELETE  3  @  3 : 4

> CHANGE  12 : 13  @  12 : 13

~ 	private int sprintToggleTimer;

> INSERT  9 : 10  @  9

+ 	private StatFileWriter statWriter;

> CHANGE  1 : 2  @  1 : 2

~ 	public EntityPlayerSP(Minecraft mcIn, World worldIn, NetHandlerPlayClient netHandler, StatFileWriter statWriter) {

> DELETE  2  @  2 : 3

> INSERT  2 : 3  @  2

+ 		this.statWriter = statWriter;

> INSERT  2 : 126  @  2

+ 	@Override
+ 	public PluginData makePluginData() {
+ 		PluginData data = super.makePluginData();
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadPluginData(data);
+ 		});
+ 		data.set("lastReportedPosX", lastReportedPosX);
+ 		data.set("lastReportedPosY", lastReportedPosY);
+ 		data.set("lastReportedPosZ", lastReportedPosZ);
+ 		data.set("lastReportedYaw", lastReportedYaw);
+ 		data.set("lastReportedPitch", lastReportedPitch);
+ 		data.set("serverSneakState", serverSneakState);
+ 		data.set("serverSprintState", serverSprintState);
+ 		data.set("positionUpdateTicks", positionUpdateTicks);
+ 		data.set("hasValidHealth", hasValidHealth);
+ 		data.set("clientBrand", clientBrand);
+ 		data.set("sprintToggleTimer", sprintToggleTimer);
+ 		data.set("sprintingTicksLeft", sprintingTicksLeft);
+ 
+ 		data.set("renderArmYaw", renderArmYaw);
+ 		data.set("renderArmPitch", renderArmPitch);
+ 		data.set("prevRenderArmYaw", prevRenderArmYaw);
+ 		data.set("prevRenderArmPitch", prevRenderArmPitch);
+ 		data.set("horseJumpPower", horseJumpPower);
+ 		data.set("horseJumpPowerCounter", horseJumpPowerCounter);
+ 
+ 		data.setCallbackVoidWithDataArg("mountEntity", (BaseData params) -> {
+ 			mountEntity(mc.theWorld.getEntityByUUID(params.getString("entityUUID")));
+ 		});
+ 		data.setCallbackObjectWithDataArg("dropOneItem", (BaseData params) -> {
+ 			EntityItem itemEntity = dropOneItem(params.getBoolean("dropAll"));
+ 			if (itemEntity != null) {
+ 				return itemEntity.makePluginData();
+ 			} else {
+ 				return new PluginData();
+ 			}
+ 		});
+ 		data.setCallbackVoidWithDataArg("sendChatMessage", (BaseData params) -> {
+ 			sendChatMessage(params.getString("message"));
+ 		});
+ 		data.setCallbackVoid("respawnPlayer", () -> {
+ 			respawnPlayer();
+ 		});
+ 		data.setCallbackVoid("closeScreen", () -> {
+ 			closeScreen();
+ 		});
+ 		data.setCallbackVoid("closeScreenAndDropStack", () -> {
+ 			closeScreenAndDropStack();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setPlayerSPHealth", (BaseData params) -> {
+ 			setPlayerSPHealth(params.getFloat("health"));
+ 		});
+ 		data.setCallbackVoid("sendPlayerAbilities", () -> {
+ 			sendPlayerAbilities();
+ 		});
+ 		data.setCallbackBoolean("isUser", () -> {
+ 			return isUser();
+ 		});
+ 		data.setCallbackVoid("sendHorseInventory", () -> {
+ 			sendHorseInventory();
+ 		});
+ 		data.setCallbackVoid("sendHorseJump", () -> {
+ 			sendHorseJump();
+ 		});
+ 		data.setCallbackVoidWithDataArg("setClientBrand", (BaseData params) -> {
+ 			setClientBrand(params.getString("brand"));
+ 		});
+ 		data.setCallbackString("getClientBrand", () -> {
+ 			return getClientBrand();
+ 		});
+ 		data.setCallbackBooleanWithDataArg("pushOutOfBlocks", (BaseData params) -> {
+ 			return pushOutOfBlocks(params.getDouble("x"), params.getDouble("y"), params.getDouble("z"));
+ 		});
+ 		data.setCallbackBooleanWithDataArg("isOpenBlockSpace", (BaseData bp) -> {
+ 			return isOpenBlockSpace(BlockPos.fromPluginData(bp));
+ 		});
+ 		data.setCallbackVoidWithDataArg("setXPStats", (BaseData params) -> {
+ 			setXPStats(params.getFloat("currentXP"), params.getInt("maxXP"), params.getInt("level"));
+ 		});
+ 		data.setCallbackVoidWithDataArg("playSound", (BaseData params) -> {
+ 			playSound(params.getString("name"), params.getFloat("volume"), params.getFloat("pitch"));
+ 		});
+ 		data.setCallbackBoolean("isServerWorld", () -> {
+ 			return isServerWorld();
+ 		});
+ 		data.setCallbackBoolean("isRidingHorse", () -> {
+ 			return isRidingHorse();
+ 		});
+ 		data.setCallbackFloat("getHorseJumpPower", () -> {
+ 			return getHorseJumpPower();
+ 		});
+ 		data.setCallbackBoolean("isCurrentViewEntity", () -> {
+ 			return isCurrentViewEntity();
+ 		});
+ 		data.setCallbackBoolean("isSpectator", () -> {
+ 			return isSpectator();
+ 		});
+ 		return data;
+ 	}
+ 
+ 	@Override
+ 	public void loadPluginData(BaseData data) {
+ 		super.loadPluginData(data);
+ 		lastReportedPosX = data.getDouble("lastReportedPosX");
+ 		lastReportedPosY = data.getDouble("lastReportedPosY");
+ 		lastReportedPosZ = data.getDouble("lastReportedPosZ");
+ 		lastReportedYaw = data.getFloat("lastReportedYaw");
+ 		lastReportedPitch = data.getFloat("lastReportedPitch");
+ 		serverSneakState = data.getBoolean("serverSneakState");
+ 		serverSprintState = data.getBoolean("serverSprintState");
+ 		positionUpdateTicks = data.getInt("positionUpdateTicks");
+ 		hasValidHealth = data.getBoolean("hasValidHealth");
+ 		clientBrand = data.getString("clientBrand");
+ 		sprintToggleTimer = data.getInt("sprintToggleTimer");
+ 		sprintingTicksLeft = data.getInt("sprintingTicksLeft");
+ 
+ 		renderArmYaw = data.getFloat("renderArmYaw");
+ 		renderArmPitch = data.getFloat("renderArmPitch");
+ 		prevRenderArmYaw = data.getFloat("prevRenderArmYaw");
+ 		prevRenderArmPitch = data.getFloat("prevRenderArmPitch");
+ 		horseJumpPower = data.getFloat("horseJumpPower");
+ 		horseJumpPowerCounter = data.getInt("horseJumpPowerCounter");
+ 	}
+ 

> DELETE  12  @  12 : 13

> INSERT  4 : 5  @  4

+ 			mc.pluginApi.onUpdate();

> INSERT  8 : 9  @  8

+ 				PluginAPI.callEvent("postmotionupdate", new PluginData());

> DELETE  1  @  1 : 2

> INSERT  4 : 9  @  4

+ 		PluginData event = new PluginData();
+ 		event.set("yaw", this.rotationYaw);
+ 		event.set("pitch", this.rotationPitch);
+ 		event.set("onground", this.onGround);
+ 		PluginAPI.callEvent("premotionupdate", event);

> INSERT  83 : 91  @  83

+ 		PluginData event = new PluginData();
+ 		event.set("message", message);
+ 		event.set("preventDefault", false);
+ 		BaseData newEvent = mc.pluginApi.callEvent("sendchatmessage", event);
+ 		if (newEvent.has("preventDefault") && newEvent.getBoolean("preventDefault")) {
+ 			return;
+ 		}
+ 		message = newEvent.has("message") ? newEvent.getString("message") : message;

> DELETE  203  @  203 : 204

> CHANGE  72 : 73  @  72 : 73

~ 	public boolean isCurrentViewEntity() {

> EOF
