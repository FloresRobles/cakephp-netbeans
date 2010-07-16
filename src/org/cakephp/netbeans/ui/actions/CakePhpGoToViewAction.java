/*
 * TODO: add license
 */

package org.cakephp.netbeans.ui.actions;

import org.cakephp.netbeans.util.CakePhpUtils;
import org.netbeans.modules.csl.api.UiUtils;
import org.netbeans.modules.php.api.editor.EditorSupport;
import org.netbeans.modules.php.api.editor.PhpBaseElement;
import org.netbeans.modules.php.spi.actions.GoToViewAction;
import org.openide.filesystems.FileObject;
import org.openide.util.Lookup;

public final class CakePhpGoToViewAction extends GoToViewAction {
    private static final long serialVersionUID = 9834759234756237L;

    private final FileObject controller;
    private final int offset;

    public CakePhpGoToViewAction(FileObject controller, int offset) {
        assert CakePhpUtils.isController(controller);
        this.controller = controller;
        this.offset = offset;
    }

    @Override
    public boolean goToView() {
        EditorSupport editorSupport = Lookup.getDefault().lookup(EditorSupport.class);
        PhpBaseElement phpElement = editorSupport.getElement(controller, offset);
        if (phpElement == null) {
            return false;
        }
        FileObject view = CakePhpUtils.getView(controller, phpElement);
        if (view != null) {
            UiUtils.open(view, DEFAULT_OFFSET);
            return true;
        }
        return false;
    }
}
