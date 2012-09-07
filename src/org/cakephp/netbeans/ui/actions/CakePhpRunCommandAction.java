package org.cakephp.netbeans.ui.actions;

import org.cakephp.netbeans.CakePhpFrameworkProvider;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.spi.framework.actions.RunCommandAction;
import org.openide.util.NbBundle;

/**
 *
 * @author junichi11
 */
public final class CakePhpRunCommandAction extends RunCommandAction {

    private static final CakePhpRunCommandAction INSTANCE = new CakePhpRunCommandAction();
    private static final long serialVersionUID = 3814938231105448415L;

    private CakePhpRunCommandAction() {
    }

    public static CakePhpRunCommandAction getInstance() {
        return INSTANCE;
    }

    @Override
    public void actionPerformed(PhpModule phpModule) {
        if (!CakePhpFrameworkProvider.getInstance().isInPhpModule(phpModule)) {
            return;
        }
        CakePhpFrameworkProvider.getInstance().getFrameworkCommandSupport(phpModule).openPanel();
    }

    @Override
    protected String getFullName() {
        return NbBundle.getMessage(CakePhpRunCommandAction.class, "LBL_CakePhpAction", getPureName());
    }
}
