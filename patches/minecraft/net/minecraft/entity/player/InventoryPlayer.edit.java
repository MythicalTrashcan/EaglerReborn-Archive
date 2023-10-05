
# Eagler Context Redacted Diff
# Copyright (c) 2023 lax1dude. All rights reserved.

# Version: 1.0
# Author: lax1dude

> INSERT  3 : 4  @  3

+ 

> DELETE  3  @  3 : 4

> INSERT  11 : 13  @  11

+ import net.zxmushroom63.plugins.BaseData;
+ import net.zxmushroom63.plugins.PluginData;

> INSERT  13 : 58  @  13

+ 	public void loadPluginData(BaseData data) {
+ 		BaseData[] parItemStacks = data.getBaseDataArr("mainInventory");
+ 		for (int i = 0; i < parItemStacks.length && i < mainInventory.length; i++) {
+ 			if (mainInventory[i] != null) {
+ 				mainInventory[i].loadPluginData(parItemStacks[i]);
+ 			}
+ 		}
+ 
+ 		BaseData[] parArmorStacks = data.getBaseDataArr("armorInventory");
+ 		for (int i = 0; i < parArmorStacks.length && i < armorInventory.length; i++) {
+ 			if (armorInventory[i] != null) {
+ 				armorInventory[i].loadPluginData(parArmorStacks[i]);
+ 			}
+ 		}
+ 		currentItem = data.getInt("currentItem");
+ 		inventoryChanged = data.getBoolean("inventoryChanged");
+ 	}
+ 
+ 	public PluginData makePluginData() {
+ 		PluginData data = new PluginData();
+ 
+ 		PluginData[] parBaseDatas = new PluginData[mainInventory.length];
+ 		for (int i = 0; i < mainInventory.length; i++) {
+ 			if (mainInventory[i] != null) {
+ 				parBaseDatas[i] = mainInventory[i].makePluginData();
+ 			}
+ 		}
+ 		data.set("mainInventory", parBaseDatas);
+ 
+ 		PluginData[] parBaseDatasArmor = new PluginData[armorInventory.length];
+ 		for (int i = 0; i < armorInventory.length; i++) {
+ 			if (armorInventory[i] != null) {
+ 				parBaseDatasArmor[i] = armorInventory[i].makePluginData();
+ 			}
+ 		}
+ 		data.set("armorInventory", parBaseDatasArmor);
+ 
+ 		data.set("currentItem", currentItem);
+ 		data.set("inventoryChanged", inventoryChanged);
+ 		data.setCallbackVoid("reload", () -> {
+ 			loadPluginData(data);
+ 		});
+ 		return data;
+ 	}
+ 

> EOF
