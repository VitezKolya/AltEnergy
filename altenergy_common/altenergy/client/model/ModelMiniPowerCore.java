
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

public class ModelMiniPowerCore {

	private IModelCustom model;

	public ModelMiniPowerCore() {

		model = AdvancedModelLoader.loadModel(Models.MINI_POWERCORE);
	}

	public void render() {

		model.renderAll();
	}

	public void renderPart(String partName) {

		model.renderPart(partName);
	}
}
