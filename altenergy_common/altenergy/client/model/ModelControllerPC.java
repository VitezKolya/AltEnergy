
package altenergy.client.model;

/**
 * ModelBusway
 * 
 * Model for the Busways
 * 
 * @author Vitez Kolya
 * 
 */
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import altenergy.lib.Models;

public class ModelControllerPC {

	private IModelCustom model;

	public ModelControllerPC() {

		model = AdvancedModelLoader.loadModel(Models.CONTROLLER_PC);
	}

	public void render() {

		model.renderAll();
	}

	public void renderPart(String partName) {

		model.renderPart(partName);
	}
}
