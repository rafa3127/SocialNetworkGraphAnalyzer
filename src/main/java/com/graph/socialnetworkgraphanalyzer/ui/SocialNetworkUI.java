/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.graph.socialnetworkgraphanalyzer.ui;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Graph;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.LinkedList;
import com.graph.socialnetworkgraphanalyzer.io.GraphFileManager;
import com.graph.socialnetworkgraphanalyzer.algorithm.Kosaraju;

/**
 *
 * @author rafaelc3127
 */
public class SocialNetworkUI extends javax.swing.JFrame implements GraphUpdateListener {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SocialNetworkUI.class.getName());
    
    private Graph<String> currentGraph;
    private String currentFilePath;
    private boolean hasUnsavedChanges;
    private InfoPanel infoPanel;
    private ControlsPanel controlsPanel;
    private VisualizationPanel visualizationPanel;

    /**
     * Creates new form SocialNetworkUI
     */
    public SocialNetworkUI() {
        initComponents();
        
        // Initialize graph state
        currentGraph = new Graph<>();
        currentFilePath = null;
        hasUnsavedChanges = false;
        
        // Configure main panel layout
        mainPanel.setLayout(new java.awt.BorderLayout());
        
        // Create and add visualization panel
        visualizationPanel = new VisualizationPanel();
        mainPanel.add(visualizationPanel, java.awt.BorderLayout.CENTER);
        
        // Create and add info panel
        infoPanel = new InfoPanel();
        infoPanel.setPreferredSize(new java.awt.Dimension(280, 0));
        mainPanel.add(infoPanel, java.awt.BorderLayout.EAST);
        
        // Create and add controls panel
        controlsPanel = new ControlsPanel();
        controlsPanel.setPreferredSize(new java.awt.Dimension(300, 0));
        mainPanel.add(controlsPanel, java.awt.BorderLayout.WEST);
        controlsPanel.setGraphAndListener(currentGraph, this);
        controlsPanel.updateFilePath(currentFilePath);
        
        // Configure window
        this.setSize(1200, 700); 
        this.setLocationRelativeTo(null);
    }
    
    // ========== GraphUpdateListener Implementation ========== //
    
    @Override
    public void onGraphUpdated() {
        hasUnsavedChanges = true;
        
        // update panels
        updatePanelsInfo();
    }
    
    @Override
    public void onNodeAdded(String username) {
        hasUnsavedChanges = true;
        infoPanel.updateGraphInfo(currentGraph);
        controlsPanel.setGraphAndListener(currentGraph, this);
        visualizationPanel.addNode(username);
        visualizationPanel.resetComponentColors();
    }
    
    @Override
    public void onNodeRemoved(String username) {
        hasUnsavedChanges = true;
        infoPanel.updateGraphInfo(currentGraph);
        controlsPanel.setGraphAndListener(currentGraph, this);
        visualizationPanel.removeNode(username);
        visualizationPanel.resetComponentColors();
    }
    
    @Override
    public void onEdgeAdded(String from, String to) {
        hasUnsavedChanges = true;
        infoPanel.updateGraphInfo(currentGraph);
        visualizationPanel.addEdge(from, to);
        visualizationPanel.resetComponentColors();
    }
    
    @Override
    public void onEdgeRemoved(String from, String to) {
        hasUnsavedChanges = true;
        infoPanel.updateGraphInfo(currentGraph);
        visualizationPanel.removeEdge(from, to);
        visualizationPanel.resetComponentColors();
    }
    
    @Override
    public void onLoadFileRequested() {
        loadFile();
    }
    
    @Override
    public void onSaveFileRequested() {
        saveFile();
    }
    
    // ======== END - GraphUpdateListener Implementation ======== //
    public void updatePanelsInfo() {
        infoPanel.updateGraphInfo(currentGraph);
        controlsPanel.setGraphAndListener(currentGraph, this);
        controlsPanel.updateFilePath(currentFilePath);
        visualizationPanel.rebuildGraph(currentGraph);
    }
    
    private void loadFile() {
        
        // Check for unsaved changes
        if (hasUnsavedChanges) {
            int option = JOptionPane.showConfirmDialog(this,
                "Hay cambios sin guardar en el grafo actual. Abrir un nuevo grafo eliminará los datos del grafo actual. ¿Deseas continuar sin guardar? Se perderán los cambios actuales.",
                "Cambios sin guardar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (option != JOptionPane.YES_OPTION) {
                return;
            }
        }
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text Files", "txt"));
        
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                java.io.File selectedFile = fileChooser.getSelectedFile();
                currentFilePath = selectedFile.getAbsolutePath();
                currentGraph = GraphFileManager.loadGraphFromFile(currentFilePath);
                hasUnsavedChanges = false;
                
                // Update InfoPanel
                updatePanelsInfo();
                
                JOptionPane.showMessageDialog(this, 
                    "Archivo cargado exitosamente: " + currentGraph.getNodeCount() + " usuarios, " + currentGraph.getEdgeCount() + " relaciones", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error al cargar archivo: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void saveFile() {
        // If no file path, ask to user
        if (currentFilePath == null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text Files", "txt"));
            
            int result = fileChooser.showSaveDialog(this);
            
            if (result == JFileChooser.APPROVE_OPTION) {
                java.io.File selectedFile = fileChooser.getSelectedFile();
                currentFilePath = selectedFile.getAbsolutePath();
                
                // Add .txt extension if not present
                if (!currentFilePath.endsWith(".txt")) {
                    currentFilePath += ".txt";
                }
            } else {
                return;
            }
        }
        
        // Save to currentFilePath
        try {
            GraphFileManager.saveGraphToFile(currentGraph, currentFilePath);
            hasUnsavedChanges = false;
            
            JOptionPane.showMessageDialog(this, 
                "Archivo guardado exitosamente en:\n" + currentFilePath, 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al guardar archivo: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        controlsPanel.updateFilePath(currentFilePath);
    }

    private void exitApplication() {
        // Check for unsaved changes
        if (hasUnsavedChanges) {
            int option = JOptionPane.showConfirmDialog(this,
                "Hay cambios sin guardar. ¿Deseas salir sin guardar?",
                "Cambios sin guardar",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            } else if (option == JOptionPane.NO_OPTION) {
                saveFile();
                if (!hasUnsavedChanges) {
                    System.exit(0);
                }
            }
      
        } else {
            System.exit(0);
        }
    }
    
    private void newGraph() {
        // Check for unsaved changes (REUTILIZA la misma validación)
        if (hasUnsavedChanges) {
            int option = JOptionPane.showConfirmDialog(this,
                "Hay cambios sin guardar en el grafo actual. Abrir un nuevo grafo eliminará los datos del grafo actual. ¿Deseas continuar sin guardar? Se perderán los cambios actuales.",
                "Cambios sin guardar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (option != JOptionPane.YES_OPTION) {
                return;
            }
        }
        
        // Create new empty graph
        currentGraph = new Graph<>();
        currentFilePath = null;
        hasUnsavedChanges = false;
        
        updatePanelsInfo();
       
    }
    
    private void findComponentsWithKosaraju () {
        // Check if graph has nodes
        if (currentGraph.getNodeCount() == 0) {
            JOptionPane.showMessageDialog(this,
                "El grafo está vacío. Agrega usuarios y relaciones antes de analizar.",
                "Grafo vacío",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Execute Kosaraju algorithm
        LinkedList<LinkedList<String>> components = Kosaraju.findSCC(currentGraph);
        
        // Update InfoPanel with components
        infoPanel.updateComponents(components);
        
        // Update visualization with colors
        visualizationPanel.updateComponentColors(components);
        
        // Show success message
        JOptionPane.showMessageDialog(this,
            "Análisis completado: se encontraron " + components.getSize() + " componente(s) fuertemente conectado(s)",
            "Análisis exitoso",
            JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        loadFileMenuItem = new javax.swing.JMenuItem();
        saveFileMenuItem = new javax.swing.JMenuItem();
        newGraphFileMenuItem = new javax.swing.JMenuItem();
        exitFileMenuItem = new javax.swing.JMenuItem();
        analysisMenu = new javax.swing.JMenu();
        kosarajuAnalysisMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Social Network Graph Analyzer");
        setSize(new java.awt.Dimension(1200, 700));

        mainPanel.setSize(new java.awt.Dimension(5, 5));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 801, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 377, Short.MAX_VALUE)
        );

        fileMenu.setText("Archivo");

        loadFileMenuItem.setText("Cargar");
        loadFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(loadFileMenuItem);

        saveFileMenuItem.setText("Guardar");
        saveFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveFileMenuItem);

        newGraphFileMenuItem.setText("Iniciar nuevo grafo");
        newGraphFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGraphFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newGraphFileMenuItem);

        exitFileMenuItem.setText("Cerrar");
        exitFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitFileMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitFileMenuItem);

        menuBar.add(fileMenu);

        analysisMenu.setText("Análisis");

        kosarajuAnalysisMenuItem.setText("Encontrar SCC (Kosaraju)");
        kosarajuAnalysisMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kosarajuAnalysisMenuItemActionPerformed(evt);
            }
        });
        analysisMenu.add(kosarajuAnalysisMenuItem);

        menuBar.add(analysisMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFileMenuItemActionPerformed
        loadFile();
    }//GEN-LAST:event_loadFileMenuItemActionPerformed

    private void saveFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFileMenuItemActionPerformed
        saveFile();
    }//GEN-LAST:event_saveFileMenuItemActionPerformed

    private void exitFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitFileMenuItemActionPerformed
        exitApplication();
    }//GEN-LAST:event_exitFileMenuItemActionPerformed

    private void newGraphFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGraphFileMenuItemActionPerformed
        newGraph();
    }//GEN-LAST:event_newGraphFileMenuItemActionPerformed

    private void kosarajuAnalysisMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kosarajuAnalysisMenuItemActionPerformed
        findComponentsWithKosaraju();
    }//GEN-LAST:event_kosarajuAnalysisMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new SocialNetworkUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu analysisMenu;
    private javax.swing.JMenuItem exitFileMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem kosarajuAnalysisMenuItem;
    private javax.swing.JMenuItem loadFileMenuItem;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newGraphFileMenuItem;
    private javax.swing.JMenuItem saveFileMenuItem;
    // End of variables declaration//GEN-END:variables
}
