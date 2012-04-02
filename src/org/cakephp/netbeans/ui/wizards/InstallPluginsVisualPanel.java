/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cakephp.netbeans.ui.wizards;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.cakephp.netbeans.options.CakePhpOptions;
import org.cakephp.netbeans.options.CakePhpPlugin;
import org.openide.util.NbBundle;

public final class InstallPluginsVisualPanel extends JPanel {
        private static final long serialVersionUID = 4577339233353249978L;
        private InstallPluginTableModel model = new InstallPluginTableModel();

        /**
         * Creates new form InstallPluginsVisualPanel
         */
        public InstallPluginsVisualPanel() {
                model.setPlugins(CakePhpOptions.getInstance().getPlugins());
                initComponents();
                
                pluginTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
                pluginTable.setSize(new Dimension(600, 600));
                TableColumnModel columnModel = pluginTable.getColumnModel();
                TableColumn column = columnModel.getColumn(InstallPluginTableModel.INSTALL);
                column.setMinWidth(45);
                column.setPreferredWidth(45);
                column = columnModel.getColumn(InstallPluginTableModel.NAME);
                column.setMinWidth(100);
                column.setPreferredWidth(100);
                column = columnModel.getColumn(InstallPluginTableModel.URL);
                column.setMinWidth(450);
                column.setPreferredWidth(450);
        }

        @Override
        public String getName() {
                return "Install plugins...";
        }

        public String getInstallPathTextField(){
                return installPathTextField.getText();
        }
        
        public List<CakePhpPlugin> getCakePhpPluginList(){
                return model.getPlugins();
        }
        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        pluginTable = new javax.swing.JTable();
        installPathTextField = new javax.swing.JTextField();
        installPathLabel = new javax.swing.JLabel();

        pluginTable.setModel(model);
        pluginTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(pluginTable);

        installPathTextField.setText(org.openide.util.NbBundle.getMessage(InstallPluginsVisualPanel.class, "InstallPluginsVisualPanel.installPathTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(installPathLabel, org.openide.util.NbBundle.getMessage(InstallPluginsVisualPanel.class, "InstallPluginsVisualPanel.installPathLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(installPathLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(installPathTextField)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(installPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(installPathLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel installPathLabel;
    private javax.swing.JTextField installPathTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable pluginTable;
    // End of variables declaration//GEN-END:variables

        private class InstallPluginTableModel extends AbstractTableModel{
                private static final int INSTALL = 0;
                private static final int NAME    = 1;
                private static final int URL     = 2;
                private static final long serialVersionUID = 1795493071005593192L;
                private List<CakePhpPlugin> plugins;
                private String[] column;
                
                public InstallPluginTableModel(){
                        column = new String[]{
                                NbBundle.getMessage(InstallPluginsVisualPanel.class, "InstallPluginsVisualPanel.pluginTable.columnModel.title0"),
                                NbBundle.getMessage(InstallPluginsVisualPanel.class, "InstallPluginsVisualPanel.pluginTable.columnModel.title1"),
                                NbBundle.getMessage(InstallPluginsVisualPanel.class, "InstallPluginsVisualPanel.pluginTable.columnModel.title2")
                        };
                        plugins = new ArrayList<CakePhpPlugin>();
                }

                public int getRowCount() {
                        return plugins.size();
                }

                public int getColumnCount() {
                        return column.length;
                }
                
                public Object getValueAt(int rowIndex, int columnIndex) {
                        CakePhpPlugin plugin = plugins.get(rowIndex);
                        if(columnIndex == INSTALL){
                                return plugin.isInstall();
                        }else if(columnIndex == NAME){
                                return plugin.getName();
                        }else if(columnIndex == URL){
                                return plugin.getUrl();
                        }
                        return null;
                }

                @Override
                public  String getColumnName(int columnIndex){
                        return column[columnIndex];
                }
                
                @Override
                public Class<?> getColumnClass(int columnIndex){
                       switch(columnIndex) {
                                case INSTALL:
                                        return Boolean.class;
                                case NAME:
                                case URL:
                                        return String.class;
                                default:
                                        return Object.class;
                        }
                }
                
                @Override
                public boolean isCellEditable(int row, int col){
                        if(col == INSTALL){
                                return true;
                        }
                        return false;
                }
                
                @Override
                public void setValueAt(Object value, int row, int col ) {
                        if(col == INSTALL){
                                plugins.get(row).setInstall(!plugins.get(row).isInstall());
                                fireTableCellUpdated(row, col);
                        }
                }
                
                public void setPlugins(List<CakePhpPlugin> plugins){
                        this.plugins = plugins;
                }
                
                public List<CakePhpPlugin> getPlugins(){
                        return plugins;
                }
        }
}