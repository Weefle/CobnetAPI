package fr.cobnet.core.opti;

import com.google.common.collect.Maps;
import fr.cobnet.core.opti.modules.ArrowRemoveModule;
import fr.cobnet.core.opti.modules.DropSuppresserModule;
import org.apache.commons.lang.Validate;

import java.util.Map;

public class ModuleHandler {

    private static final String PREFIX = "[OPTI MODULES] ";

    private static ModuleHandler instance;

    public static ModuleHandler getInstance() {
        return instance == null ? instance = new ModuleHandler() : instance;
    }

    private Map<String, OptimisationModule> modules;

    private ModuleHandler() {
        modules = Maps.newHashMap();

        registerModule(new ArrowRemoveModule("arrow_remove"));
        registerModule(new DropSuppresserModule("drop_suppr"));
    }

    public void registerModule(OptimisationModule module) {
        if (modules.containsKey(module.getName())) {
            System.out.println(PREFIX + "Le module \"" + module.getName() + "\" a déjà été activé !");
            return;
        }

        modules.put(module.getName(), module);
    }

    public OptimisationModule getModule(String name) {
        return modules.get(name);
    }

    public void enableModule(OptimisationModule module) {
        Validate.notNull(module, PREFIX + "Le module d'optimisation ne peut-être null !");

        if (module.isEnabled()) {
            System.out.println(PREFIX + "Le module \"" + module.getName() + "\" est déjà activé.");
            return;
        }

        module.enable();
        System.out.println(PREFIX + "Le module \"" + module.getName() + "\" vient d'être activé.");
    }

    public void disableModule(String moduleName) {
        Validate.notNull(moduleName);
        Validate.isTrue(moduleName.length() > 0);

        if (!modules.containsKey(moduleName)) {
            System.out.println(PREFIX + "Le module \"" + moduleName + "\" n'existe pas, il ne peut donc pas être désactivé.");
            return;
        }

        OptimisationModule module = modules.get(moduleName);
        if (module.isEnabled()) {
            module.disable();
            System.out.println(PREFIX + "Le module \"" + moduleName + "\" vient d'être désactivé.");
        } else {
            System.out.println(PREFIX + "Le module \"" + moduleName + "\" n'est pas activé, il ne peut donc pas être désactivé.");
        }
    }

}
