/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2013 Sun Microsystems, Inc.
 */
package org.cakephp.netbeans.editor.codecompletion;

import org.cakephp.netbeans.editor.CakePhpEditorExtender;
import org.cakephp.netbeans.module.CakePhpModule;
import org.cakephp.netbeans.util.CakePhpUtils;
import org.netbeans.modules.php.api.editor.PhpClass;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.openide.filesystems.FileObject;

public class CakePhp3EditorExtender extends CakePhpEditorExtender {

    public CakePhp3EditorExtender(PhpModule phpModule) {
        super(phpModule);
    }

    @Override
    public PhpClass getViewPhpClass() {
        String className = CakePhpModule.FILE_TYPE.VIEW.toString();
        String fullyQualifiedName = "\\Cake\\View\\" + className; // NOI18N
        return new PhpClass(className, fullyQualifiedName);
    }

    @Override
    public PhpClass getControllerPhpClass() {
        String className = CakePhpModule.FILE_TYPE.CONTROLLER.toString();
        String fullyQualifiedName = "\\Cake\\Controller\\" + className; // NOI18N
        return new PhpClass(className, fullyQualifiedName);
    }

    @Override
    public PhpClass getComponentPhpClass() {
        String className = CakePhpModule.FILE_TYPE.COMPONENT.toString();
        String fullyQualifiedName = "\\Cake\\Controller\\Component\\" + className; // NOI18N
        return new PhpClass(className, fullyQualifiedName);
    }

    @Override
    public PhpClass getHelperPhpClass() {
        String className = "AppHelper"; // NOI18N
        CakePhpModule cakeModule = CakePhpModule.forPhpModule(PhpModule.inferPhpModule());
        String appName = cakeModule == null ? "" : cakeModule.getAppName();
        String fullyQualifiedName = "\\" + appName + "\\" + className; // NOI18N
        return new PhpClass(className, fullyQualifiedName);
    }

    @Override
    public void addDefaultHelpers(PhpClass phpClass, FileObject fo) {
        if (isView()) {
            return;
        }
        super.addDefaultHelpers(phpClass, fo);
    }

    @Override
    public void addDefaultComponents(PhpClass phpClass, FileObject fo) {
        if (isController()) {
            return;
        }
        super.addDefaultComponents(phpClass, fo);
    }

    @Override
    public String getFullyQualifiedClassName(FileObject target) {
        return CakePhpUtils.getFullyQualifiedClassName(target);
    }
}
