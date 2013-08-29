
package altenergy.client.model;

/**
 * ModelBusway
 * 
 * Model for the Busways
 * 
 * @author Vitez Kolya
 * 
 */
import java.util.Random;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import altenergy.lib.Models;

public class ModelEnergyBusway {

	public boolean connections[] = {
			false, false, false, false, false, false
	};

	private IModelCustom modelBusway;

	public ModelEnergyBusway() {

		modelBusway = AdvancedModelLoader.loadModel(Models.BUSWAY);
	}

	private int random(int maxNum) {

		Random randNum = new Random();
		return randNum.nextInt(maxNum);
	}

	public void setConnections(boolean[] connections) {

		this.connections = connections;
	}

	public void render() {

		modelBusway.renderAll();
	}

	public void renderSides(int numSides) {

		switch (numSides) {
			case 0:
				renderPart("BuswayNone");
				break;
			case 1:
				if (connections[0]) {
					renderPart("BuswaySingleVertEndA");
				} else if (connections[1]) {
					renderPart("BuswaySingleVertEndB");
				} else if (connections[2]) {
					renderPart("BuswaySingleHorzEndA");
				} else if (connections[3]) {
					renderPart("BuswaySingleHorzEndB");
				} else if (connections[4]) {
					renderPart("BuswaySingleHorzEndC");
				} else if (connections[5]) {
					renderPart("BuswaySingleHorzEndD");
				}
				break;
			case 2:
				if (connections[0] && connections[1]) {
					renderPart("BuswayStrightConnectionC");
				} else if (connections[0] && connections[2]) {
					renderPart("BuswayConnectionF");
					renderPart("BuswaySingleHorzEndC");
				} else if (connections[0] && connections[3]) {
					renderPart("BuswayConnectionF");
					renderPart("BuswaySingleHorzEndA");
				} else if (connections[0] && connections[4]) {
					renderPart("BuswayConnectionF");
					renderPart("BuswaySingleHorzEndB");
				} else if (connections[0] && connections[5]) {
					renderPart("BuswayConnectionF");
					renderPart("BuswaySingleHorzEndD");
				} else if (connections[1] && connections[2]) {
					renderPart("BuswayConnectionE");
					renderPart("BuswaySingleHorzEndC");
				} else if (connections[1] && connections[3]) {
					renderPart("BuswayConnectionE");
					renderPart("BuswaySingleHorzEndA");
				} else if (connections[1] && connections[4]) {
					renderPart("BuswayConnectionE");
					renderPart("BuswaySingleHorzEndB");
				} else if (connections[1] && connections[5]) {
					renderPart("BuswayConnectionE");
					renderPart("BuswaySingleHorzEndD");
				} else if (connections[2] && connections[3]) {
					renderPart("BuswayStrightConnectionA");
				} else if (connections[2] && connections[4]) {
					if (random(1) == 0) {
						renderPart("BuswayConnectionA");
						renderPart("BuswayJunctionConnectionEndC");
					} else {
						renderPart("BuswayConnectionC");
						renderPart("BuswayJunctionConnectionEndDA");
					}
				} else if (connections[2] && connections[5]) {
					if (random(1) == 0) {
						renderPart("BuswayConnectionD");
						renderPart("BuswayJunctionConnectionEndA");
					} else {
						renderPart("BuswayConnectionA");
						renderPart("BuswayJunctionConnectionEndD");
					}
				} else if (connections[3] && connections[4]) {
					renderPart("BuswayConnectionB");
					renderPart("BuswayJunctionConnectionEndD");
				} else if (connections[3] && connections[5]) {
					renderPart("BuswayConnectionD");
					renderPart("BuswayJunctionConnectionEndB");
				} else if (connections[4] && connections[5]) {
					renderPart("BuswayStrightConnectionB");
				}
				break;
			case 3:
				if (connections[0] && connections[1] && connections[2] && !connections[3] && !connections[4]
						&& !connections[5]) {
					renderPart("BuswayJunctionConnectionEndA");
					renderPart("BuswayStrightConnectionC");
					//
				} else if (connections[0] && connections[1] && !connections[2] && connections[3] && !connections[4]
						&& !connections[5]) {
					renderPart("BuswayJunctionConnectionEndB");
					renderPart("BuswayStrightConnectionC");
				} else if (connections[0] && connections[1] && !connections[2] && !connections[3] && connections[4]
						&& !connections[5]) {
					renderPart("BuswayJunctionConnectionEndC");
					renderPart("BuswayStrightConnectionC");
				} else if (connections[0] && connections[1] && !connections[2] && !connections[3] && !connections[4]
						&& connections[5]) {
					renderPart("BuswayJunctionConnectionEndD");
					renderPart("BuswayStrightConnectionC");
				} else if (connections[0] && connections[2] && connections[3]) {
					renderPart("BuswayConnectionF");
					renderPart("BuswayStrightConnectionA");
				} else if (connections[0] && connections[2] && connections[4]) {

					if (random(1) == 1) {
						renderPart("BuswayConnectionF");
						renderPart("BuswayJunctionConnectionEndA");
						renderPart("BuswayConnectionC");
					} else {
						renderPart("BuswayConnectionF");
						renderPart("BuswayConnectionA");
						renderPart("BuswayJunctionConnectionEndC");
					}
				} else if (connections[0] && connections[2] && connections[5]) {

					if (random(1) == 1) {
						renderPart("BuswayConnectionF");
						renderPart("BuswayJunctionConnectionEndA");
						renderPart("BuswayConnectionC");
					} else {
						renderPart("BuswayConnectionF");
						renderPart("BuswayConnectionA");
						renderPart("BuswayJunctionConnectionEndC");
					}
				} else if (connections[0] && connections[3] && connections[4]) {

					if (random(1) == 1) {
						renderPart("BuswayConnectionF");
						renderPart("BuswayJunctionConnectionEndB");
						renderPart("BuswayConnectionC");
					} else {
						renderPart("BuswayConnectionF");
						renderPart("BuswayConnectionB");
						renderPart("BuswayJunctionConnectionEndC");
					}
				} else if (connections[0] && connections[3] && connections[5]) {
					if (random(1) == 1) {
						renderPart("BuswayConnectionF");
						renderPart("BuswayJunctionConnectionEndB");
						renderPart("BuswayConnectionC");
					} else {
						renderPart("BuswayConnectionF");
						renderPart("BuswayConnectionB");
						renderPart("BuswayJunctionConnectionEndC");
					}
				} else if (connections[0] && connections[4] && connections[5]) {

					if (random(1) == 1) {
						renderPart("BuswayConnectionF");
						renderPart("BuswayJunctionConnectionEndC");
						renderPart("BuswayConnectionD");
					} else {
						renderPart("BuswayConnectionF");
						renderPart("BuswayConnectionC");
						renderPart("BuswayJunctionConnectionEndD");
					}
				} else if (connections[1] && connections[2] && connections[3]) {
					if (random(1) == 1) {
						renderPart("BuswayConnectionE");
						renderPart("BuswayJunctionConnectionEndA");
						renderPart("BuswayConnectionB");
					} else {
						renderPart("BuswayConnectionE");
						renderPart("BuswayConnectionA");
						renderPart("BuswayJunctionConnectionEndB");
					}
				} else if (connections[1] && connections[2] && connections[4]) {
					if (random(1) == 1) {
						renderPart("BuswayConnectionE");
						renderPart("BuswayJunctionConnectionEndA");
						renderPart("BuswayConnectionC");
					} else {
						renderPart("BuswayConnectionE");
						renderPart("BuswayConnectionA");
						renderPart("BuswayJunctionConnectionEndC");
					}
				} else if (connections[1] && connections[2] && connections[5]) {
					if (random(1) == 1) {
						renderPart("BuswayConnectionE");
						renderPart("BuswayJunctionConnectionEndA");
						renderPart("BuswayConnectionD");
					} else {
						renderPart("BuswayConnectionE");
						renderPart("BuswayConnectionA");
						renderPart("BuswayJunctionConnectionEndD");
					}
				} else if (connections[1] && connections[3] && connections[4]) {
					if (random(1) == 1) {
						renderPart("BuswayConnectionE");
						renderPart("BuswayJunctionConnectionEndB");
						renderPart("BuswayConnectionC");
					} else {
						renderPart("BuswayConnectionE");
						renderPart("BuswayConnectionB");
						renderPart("BuswayJunctionConnectionEndC");
					}
				} else if (connections[1] && connections[3] && connections[5]) {
					if (random(1) == 1) {
						renderPart("BuswayConnectionE");
						renderPart("BuswayJunctionConnectionEndB");
						renderPart("BuswayConnectionD");
					} else {
						renderPart("BuswayConnectionE");
						renderPart("BuswayConnectionB");
						renderPart("BuswayJunctionConnectionEndD");
					}
				} else if (connections[1] && connections[4] && connections[5]) {
					if (random(1) == 1) {
						renderPart("BuswayConnectionE");
						renderPart("BuswayJunctionConnectionEndC");
						renderPart("BuswayConnectionD");
					} else {
						renderPart("BuswayConnectionE");
						renderPart("BuswayConnectionC");
						renderPart("BuswayJunctionConnectionEndD");
					}
				} else if (connections[2] && connections[3] && connections[4]) {
					if (random(1) == 0) {
						renderPart("BuswayStrightConnectionA");
						renderPart("BuswayConnectionC");
					} else {
						renderPart("BuswayConnectionA");
						renderPart("BuswayConnectionB");
						renderPart("BuswayJunctionConnectionEndC");
					}
				} else if (connections[2] && connections[4] && connections[5]) {
					if (random(1) == 0) {
						renderPart("BuswayJunctionConnectionEndA");
						renderPart("BuswayConnectionA");
						renderPart("BuswayConnectionD");
					} else {
						renderPart("BuswayConnectionA");
						renderPart("BuswayStrightConnectionB");
					}
				} else if (connections[3] && connections[4] && connections[5]) {
					if (random(1) == 0) {
						renderPart("BuswayJunctionConnectionEndB");
						renderPart("BuswayConnectionA");
						renderPart("BuswayConnectionD");
					} else {
						renderPart("BuswayConnectionA");
						renderPart("BuswayStrightConnectionB");
					}
				}
				break;
			case 4:
				if (connections[0] && connections[1] && connections[2] && connections[3]) {
					renderPart("BuswayStrightConnectionC");
					renderPart("BuswayStrightConnectionA");
				} else if (connections[0] && connections[1] && connections[2] && connections[4]) {
					if (random(1) == 0) {
						renderPart("BuswayStrightConnectionC");
						renderPart("BuswayJunctionConnectionEndA");
						renderPart("BuswayConnectionC");
					} else {
						renderPart("BuswayStrightConnectionC");
						renderPart("BuswayConnectionA");
						renderPart("BuswayJunctionConnectionEndC");
					}
				} else if (connections[0] && connections[1] && connections[2] && connections[5]) {
					if (random(1) == 0) {
						renderPart("BuswayStrightConnectionC");
						renderPart("BuswayJunctionConnectionEndA");
						renderPart("BuswayConnectionD");
					} else {
						renderPart("BuswayStrightConnectionC");
						renderPart("BuswayConnectionA");
						renderPart("BuswayJunctionConnectionEndD");
					}
				} else if (connections[0] && connections[1] && connections[3] && connections[4]) {
					if (random(1) == 0) {
						renderPart("BuswayStrightConnectionC");
						renderPart("BuswayJunctionConnectionEndB");
						renderPart("BuswayConnectionC");
					} else {
						renderPart("BuswayStrightConnectionC");
						renderPart("BuswayConnectionB");
						renderPart("BuswayJunctionConnectionEndC");
					}
				} else if (connections[0] && connections[1] && connections[3] && connections[5]) {
					if (random(1) == 0) {
						renderPart("BuswayStrightConnectionC");
						renderPart("BuswayJunctionConnectionEndB");
						renderPart("BuswayConnectionD");
					} else {
						renderPart("BuswayStrightConnectionC");
						renderPart("BuswayConnectionB");
						renderPart("BuswayJunctionConnectionEndD");
					}
				} else if (connections[0] && connections[1] && connections[4] && connections[5]) {
					if (random(1) == 0) {
						renderPart("BuswayStrightConnectionC");
						renderPart("BuswayJunctionConnectionEndC");
						renderPart("BuswayConnectionD");
					} else {
						renderPart("BuswayStrightConnectionC");
						renderPart("BuswayConnectionC");
						renderPart("BuswayJunctionConnectionEndD");
					}
				} else if (connections[0] && connections[2] && connections[3] && connections[4]) {
					if (random(1) == 0) {
						renderPart("BuswayConnectionF");
						renderPart("BuswayStrightConnectionA");
						renderPart("BuswayConnectionC");
					} else {
						renderPart("BuswayConnectionF");
						renderPart("BuswayStrightConnectionA");
						renderPart("BuswayJunctionConnectionEndC");
					}
				} else if (connections[0] && connections[2] && connections[3] && connections[5]) {
					if (random(1) == 0) {
						renderPart("BuswayConnectionF");
						renderPart("BuswayStrightConnectionA");
						renderPart("BuswayConnectionD");
					} else {
						renderPart("BuswayConnectionF");
						renderPart("BuswayStrightConnectionA");
						renderPart("BuswayJunctionConnectionEndD");
					}
				} else if (connections[0] && connections[2] && connections[4] && connections[5]) {
					if (random(1) == 0) {
						renderPart("BuswayConnectionF");
						renderPart("BuswayConnectionA");
						renderPart("BuswayStrightConnectionB");
					} else {
						renderPart("BuswayConnectionF");
						renderPart("BuswayJunctionConnectionEndA");
						renderPart("BuswayStrightConnectionB");
					}
				} else if (connections[0] && connections[3] && connections[4] && connections[5]) {
					if (random(1) == 0) {
						renderPart("BuswayConnectionF");
						renderPart("BuswayConnectionB");
						renderPart("BuswayStrightConnectionB");
					} else {
						renderPart("BuswayConnectionF");
						renderPart("BuswayJunctionConnectionEndB");
						renderPart("BuswayStrightConnectionB");
					}
				} else if (connections[1] && connections[2] && connections[3] && connections[4]) {
					if (random(1) == 0) {
						renderPart("BuswayConnectionE");
						renderPart("BuswayStrightConnectionA");
						renderPart("BuswayConnectionC");
					} else {
						renderPart("BuswayConnectionE");
						renderPart("BuswayStrightConnectionA");
						renderPart("BuswayJunctionConnectionEndC");
					}
				} else if (connections[1] && connections[2] && connections[3] && connections[5]) {
					if (random(1) == 0) {
						renderPart("BuswayConnectionE");
						renderPart("BuswayStrightConnectionA");
						renderPart("BuswayConnectionD");
					} else {
						renderPart("BuswayConnectionE");
						renderPart("BuswayStrightConnectionA");
						renderPart("BuswayJunctionConnectionEndD");
					}
				} else if (connections[1] && connections[2] && connections[4] && connections[5]) {
					if (random(1) == 0) {
						renderPart("BuswayConnectionE");
						renderPart("BuswayJunctionConnectionEndA");
						renderPart("BuswayStrightConnectionB");
					} else {
						renderPart("BuswayConnectionE");
						renderPart("BuswayConnectionA");
						renderPart("BuswayStrightConnectionB");
					}
				} else if (connections[1] && connections[3] && connections[4] && connections[5]) {
					if (random(1) == 0) {
						renderPart("BuswayConnectionE");
						renderPart("BuswayJunctionConnectionEndB");
						renderPart("BuswayStrightConnectionB");
					} else {
						renderPart("BuswayConnectionE");
						renderPart("BuswayConnectionB");
						renderPart("BuswayStrightConnectionB");
					}
				} else if (connections[2] && connections[3] && connections[4] && connections[5]) {
					renderPart("BuswayStrightConnectionA");
					renderPart("BuswayStrightConnectionB");
				}
				break;
			case 5:
				if (connections[0] && connections[1] && connections[2] && connections[3] && connections[4]) {
					renderPart("BuswayStrightConnectionC");
					renderPart("BuswayStrightConnectionA");
					renderPart("BuswayConnectionC");
				} else if (connections[0] && connections[1] && connections[2] && connections[3] && connections[5]) {
					renderPart("BuswayStrightConnectionC");
					renderPart("BuswayStrightConnectionA");
					renderPart("BuswayConnectionD");
				} else if (connections[0] && connections[1] && connections[2] && connections[4] && connections[5]) {
					renderPart("BuswayStrightConnectionC");
					renderPart("BuswayStrightConnectionB");
					renderPart("BuswayConnectionA");
				} else if (connections[0] && connections[1] && connections[3] && connections[4] && connections[5]) {
					renderPart("BuswayStrightConnectionC");
					renderPart("BuswayStrightConnectionB");
					renderPart("BuswayConnectionB");
				} else if (connections[0] && connections[2] && connections[3] && connections[4] && connections[5]) {
					renderPart("BuswayStrightConnectionA");
					renderPart("BuswayStrightConnectionB");
					renderPart("BuswayConnectionF");
				} else if (connections[1] && connections[2] && connections[3] && connections[4] && connections[5]) {
					renderPart("BuswayStrightConnectionC");
					renderPart("BuswayStrightConnectionB");
					renderPart("BuswayConnectionE");
				}
				break;
			case 6:
				renderPart("BuswayStrightConnectionA");
				renderPart("BuswayStrightConnectionB");
				renderPart("BuswayStrightConnectionC");
				break;
		}
	}

	public void renderPart(String partName) {

		modelBusway.renderPart(partName);
	}
}
