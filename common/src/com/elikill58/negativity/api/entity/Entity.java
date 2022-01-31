package com.elikill58.negativity.api.entity;

import java.util.List;

import com.elikill58.negativity.api.block.Block;
import com.elikill58.negativity.api.commands.CommandSender;
import com.elikill58.negativity.api.location.Location;
import com.elikill58.negativity.api.location.Vector;

public interface Entity extends CommandSender {

	/**
	 * Check if the given entity is currently on ground
	 * 
	 * @return true if is on ground
	 */
	boolean isOnGround();

	/**
	 * Check if the actual entity is OP or not
	 * 
	 * @return true if entity OP
	 */
	boolean isOp();
	
	List<Block> getTargetBlock(int maxDistance);
	
	/**
	 * Get the entity location
	 * 
	 * @return entity location
	 */
	Location getLocation();
	
	double getEyeHeight();
	
	Location getEyeLocation();
	
	/**
	 * Get the rotation (also called "direction") of the entity
	 * 
	 * @return vector of entity's direction
	 */
	Vector getRotation();
	
	/**
	 * Get the type of the entity
	 * 
	 * @return the entity type
	 */
	EntityType getType();
	
	/**
	 * Get the entity ID. Can not work with Sponge and some other platforms
	 * 
	 * @return the entity ID
	 */
	int getEntityId();
	
	BoundingBox getBoundingBox();
	
	/**
	 * Get current entity velocity
	 * 
	 * @return the entity velocity
	 */
	Vector getVelocity();
	
	void applyTheoricVelocity();
	
	/**
	 * Get the velocity that the entity SHOULD be take
	 *
	 * @return the theoric (and platform) velocity
	 */
	Vector getTheoricVelocity();
	
	/**
	 * Edit the entity velocity
	 * 
	 * @param vel the new velocity
	 */
	void setVelocity(Vector vel);
}
