package com.zolli.rodolffoutilsreloaded.utils;

import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.material.MaterialData;

import com.zolli.rodolffoutilsreloaded.rodolffoUtilsReloaded;

public class recipes {
	
	public void createRecipes(rodolffoUtilsReloaded plugin) {
		
		ShapelessRecipe sandRecipe = new ShapelessRecipe(new ItemStack(12, 4));
		sandRecipe.addIngredient(Material.SANDSTONE);
		plugin.getServer().addRecipe(sandRecipe);
		
		ShapedRecipe mossyBrickRecipe = new ShapedRecipe(new ItemStack(98, 1, (short) 1));
		mossyBrickRecipe.shape(new String[] {"vvv", "vsv", "vvv"});
		mossyBrickRecipe.setIngredient('v', new MaterialData(106));
		mossyBrickRecipe.setIngredient('s', new MaterialData(98));
		plugin.getServer().addRecipe(mossyBrickRecipe);
		
		ShapedRecipe crackedBrickRecipe = new ShapedRecipe(new ItemStack(98, 1, (short) 2));
		crackedBrickRecipe.shape(new String[] {"ccc", "csc", "ccc"});
		crackedBrickRecipe.setIngredient('c', new MaterialData(4));
		crackedBrickRecipe.setIngredient('s', new MaterialData(98));
		plugin.getServer().addRecipe(crackedBrickRecipe);
		
		ShapedRecipe mossyCobbleRecipe = new ShapedRecipe(new ItemStack(48, 1));
		mossyCobbleRecipe.shape(new String[] {"vvv", "vcv", "vvv"});
		mossyCobbleRecipe.setIngredient('v', new MaterialData(106));
		mossyCobbleRecipe.setIngredient('c', new MaterialData(4));
		plugin.getServer().addRecipe(mossyCobbleRecipe);
		
		ShapedRecipe cobWebRecipe = new ShapedRecipe(new ItemStack(30, 1));
		cobWebRecipe.shape(new String[] {"sss", "sgs", "sss"});
		cobWebRecipe.setIngredient('s', new MaterialData(287));
		cobWebRecipe.setIngredient('g', new MaterialData(370));
		plugin.getServer().addRecipe(cobWebRecipe);
		
		ShapedRecipe stepBlockRecipe = new ShapedRecipe(new ItemStack(44, 2, (short) 7));
		stepBlockRecipe.shape(new String[] {"h", "s", "h"});
		stepBlockRecipe.setIngredient('h', new MaterialData(44));
		stepBlockRecipe.setIngredient('s', new MaterialData(1));
		plugin.getServer().addRecipe(stepBlockRecipe);
		
		stepBlockRecipe = new ShapedRecipe(new ItemStack(44, 2, (short) 7));
		stepBlockRecipe.shape(new String[] {" h", " s", " h"});
		stepBlockRecipe.setIngredient('h', new MaterialData(44));
		stepBlockRecipe.setIngredient('s', new MaterialData(1));
		plugin.getServer().addRecipe(stepBlockRecipe);
		
		stepBlockRecipe = new ShapedRecipe(new ItemStack(44, 2, (short) 7));
		stepBlockRecipe.shape(new String[] {"  h", "  s", "  h"});
		stepBlockRecipe.setIngredient('h', new MaterialData(44));
		stepBlockRecipe.setIngredient('s', new MaterialData(1));
		plugin.getServer().addRecipe(stepBlockRecipe);
		
		ShapedRecipe grassRecipe = new ShapedRecipe(new ItemStack(2, 1));
		grassRecipe.shape(new String[] {"ttt", "ddd", "ddd"});
		grassRecipe.setIngredient('t', new MaterialData(31, (byte) 1));
		grassRecipe.setIngredient('d', new MaterialData(3));
		plugin.getServer().addRecipe(grassRecipe);
		
		ShapedRecipe cocoaRecipe = new ShapedRecipe(new ItemStack(351, 1, (short) 3));
		cocoaRecipe.shape(new String[] {"m", "s", "b"});
		cocoaRecipe.setIngredient('m', new MaterialData(39));
		cocoaRecipe.setIngredient('s', new MaterialData(353));
		cocoaRecipe.setIngredient('b', new MaterialData(281));
		plugin.getServer().addRecipe(cocoaRecipe);
		
		cocoaRecipe = new ShapedRecipe(new ItemStack(351, 1, (short) 3));
		cocoaRecipe.shape(new String[] {" m", " s", " b"});
		cocoaRecipe.setIngredient('m', new MaterialData(39));
		cocoaRecipe.setIngredient('s', new MaterialData(353));
		cocoaRecipe.setIngredient('b', new MaterialData(281));
		plugin.getServer().addRecipe(cocoaRecipe);
		
		cocoaRecipe = new ShapedRecipe(new ItemStack(351, 1, (short) 3));
		cocoaRecipe.shape(new String[] {"  m", "  s", " b"});
		cocoaRecipe.setIngredient('m', new MaterialData(39));
		cocoaRecipe.setIngredient('s', new MaterialData(353));
		cocoaRecipe.setIngredient('b', new MaterialData(281));
		plugin.getServer().addRecipe(cocoaRecipe);
		
		ShapedRecipe lilyPadRecipe = new ShapedRecipe(new ItemStack(111, 1));
		lilyPadRecipe.shape(new String[] {"l l", "lll", "lll"});
		lilyPadRecipe.setIngredient('l', new MaterialData(18));
		plugin.getServer().addRecipe(lilyPadRecipe);
		
		lilyPadRecipe = new ShapedRecipe(new ItemStack(111, 1));
		lilyPadRecipe.shape(new String[] {"l l", "lll", "lll"});
		lilyPadRecipe.setIngredient('l', new MaterialData(18, (byte) 1));
		plugin.getServer().addRecipe(lilyPadRecipe);
		
		lilyPadRecipe = new ShapedRecipe(new ItemStack(111, 1));
		lilyPadRecipe.shape(new String[] {"l l", "lll", "lll"});
		lilyPadRecipe.setIngredient('l', new MaterialData(18, (byte) 2));
		plugin.getServer().addRecipe(lilyPadRecipe);
		
		lilyPadRecipe = new ShapedRecipe(new ItemStack(111, 1));
		lilyPadRecipe.shape(new String[] {"l l", "lll", "lll"});
		lilyPadRecipe.setIngredient('l', new MaterialData(18, (byte) 3));
		plugin.getServer().addRecipe(lilyPadRecipe);
		
		ShapedRecipe iceRecipe = new ShapedRecipe(new ItemStack(79, 1));
		iceRecipe.shape(new String[] {"ss ", "ss ", "   "});
		iceRecipe.setIngredient('s', new MaterialData(80));
		plugin.getServer().addRecipe(iceRecipe);
		
		iceRecipe = new ShapedRecipe(new ItemStack(79, 1));
		iceRecipe.shape(new String[] {" ss", " ss", "   "});
		iceRecipe.setIngredient('s', new MaterialData(80));
		plugin.getServer().addRecipe(iceRecipe);
		
		iceRecipe = new ShapedRecipe(new ItemStack(79, 1));
		iceRecipe.shape(new String[] {"   ", "ss ", "ss "});
		iceRecipe.setIngredient('s', new MaterialData(80));
		plugin.getServer().addRecipe(iceRecipe);
		
		iceRecipe = new ShapedRecipe(new ItemStack(79, 1));
		iceRecipe.shape(new String[] {"   ", " ss", " ss"});
		iceRecipe.setIngredient('s', new MaterialData(80));
		plugin.getServer().addRecipe(iceRecipe);
		
		ShapedRecipe myceliumRecipe = new ShapedRecipe(new ItemStack(110, 1));
		myceliumRecipe.shape(new String[] {"brb", "ggg", "ddd"});
		myceliumRecipe.setIngredient('b', new MaterialData(39));
		myceliumRecipe.setIngredient('r', new MaterialData(40));
		myceliumRecipe.setIngredient('g', new MaterialData(2));
		myceliumRecipe.setIngredient('d', new MaterialData(3));
		plugin.getServer().addRecipe(myceliumRecipe);
		
		ShapedRecipe circleStoneRecipe = new ShapedRecipe(new ItemStack(98, 8, (short) 3));
		circleStoneRecipe.shape(new String[] {"bbb", "bdb", "bbb"});
		circleStoneRecipe.setIngredient('b', new MaterialData(98, (byte) 0));
		circleStoneRecipe.setIngredient('d', new MaterialData(264));
		plugin.getServer().addRecipe(circleStoneRecipe);
		
		if(plugin.config.getString("enableXPBottles").equalsIgnoreCase("true")) {
			ShapedRecipe xpBottleRecipe = new ShapedRecipe(new ItemStack(384, 1));
			xpBottleRecipe.shape(new String[] {" p ", "gbg", "lsl"});
			xpBottleRecipe.setIngredient('p', new MaterialData(351, (byte) 5));
			xpBottleRecipe.setIngredient('g', new MaterialData(266));
			xpBottleRecipe.setIngredient('b', new MaterialData(374));
			xpBottleRecipe.setIngredient('l', new MaterialData(348));
			xpBottleRecipe.setIngredient('s', new MaterialData(353));
			plugin.getServer().addRecipe(xpBottleRecipe);
		} else {
			plugin.log.info(plugin.logPrefix + "XP Bottle crafting is currently disabled!");
		}
		
		FurnaceRecipe netherBrickRecipe = new FurnaceRecipe(new ItemStack(112, 1), Material.NETHERRACK);
		plugin.getServer().addRecipe(netherBrickRecipe);
		
	}
	
}
