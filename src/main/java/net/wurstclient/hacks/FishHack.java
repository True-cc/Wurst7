/*
 * Copyright (C) 2014 - 2019 | Wurst-Imperium | All rights reserved.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient.hacks;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.wurstclient.Category;
import net.wurstclient.SearchTags;
import net.wurstclient.events.UpdateListener;
import net.wurstclient.hack.Hack;

@SearchTags({"AutoSwim", "auto swim"})
public final class FishHack extends Hack implements UpdateListener
{
	public FishHack()
	{
		super("Fish",
			"Disables underwater gravity\n" + "so you can swim like a fish.");
		setCategory(Category.MOVEMENT);
	}
	
	@Override
	public void onEnable()
	{
		WURST.getEventManager().add(UpdateListener.class, this);
		WURST.getHax().dolphinHack.setEnabled(false);
	}
	
	@Override
	public void onDisable()
	{
		WURST.getEventManager().remove(UpdateListener.class, this);
	}
	
	@Override
	public void onUpdate()
	{
		ClientPlayerEntity player = MC.player;
		if(!player.isTouchingWater() || player.isSneaking())
			return;
		
		Vec3d velocity = player.getVelocity();
		player.setVelocity(velocity.x, velocity.y + 0.005, velocity.z);
	}
}
