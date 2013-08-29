
package altenergy.world;

import net.minecraft.util.ChunkCoordinates;

public class PortalPosition extends ChunkCoordinates {

	public long lastUpdateTime;

	final TutorialTeleporter field_85088_e;

	public PortalPosition(TutorialTeleporter tutorialTeleporter, int par2, int par3, int par4, long lastUpdateTime) {

		super(par2, par3, par4);
		field_85088_e = tutorialTeleporter;
		this.lastUpdateTime = lastUpdateTime;
	}
}
