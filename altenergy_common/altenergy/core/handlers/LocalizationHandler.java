
package altenergy.core.handlers;

import altenergy.core.util.LocalizationUtil;
import altenergy.lib.Localizations;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LocalizationHandler {
	public static void loadLanguages() {

		for (String localizationFile : Localizations.localFiles) {
			LanguageRegistry.instance().loadLocalization(localizationFile,
					LocalizationUtil.getLocaleFromFileName(localizationFile),
					LocalizationUtil.isXMLLanguageFile(localizationFile));
		}
	}
}
