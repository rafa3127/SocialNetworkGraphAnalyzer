/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.graph.socialnetworkgraphanalyzer.ui;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Graph;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.LinkedList;
import com.graph.socialnetworkgraphanalyzer.basicdatastructures.Node;
import javax.swing.JOptionPane;

/**
 *
 * @author rafaelc3127
 */
public class ControlsPanel extends javax.swing.JPanel {
    
    private Graph<String> graph;
    private GraphUpdateListener updateListener;
    private javax.swing.JFrame parentFrame;

    /**
     * Creates new form ControlsPanel
     */
    public ControlsPanel() {
        this.graph = graph;
        initComponents();
        
        // --------------- INITIALIZE LISTS VALUES --------------- //
        fromUserComboBox.removeAllItems();
        toUserComboBox.removeAllItems();
        
        // --------------- CONFIGURE SUB-PANELS LAYOUT --------------- //
        configureUsersPanel();
        configureRelationsPanel();
        configureFilePanel();
        
        // --------------- ADD SPACE BETWEEN ELEMENTS --------------- //
        // Remove all components first
        this.removeAll();
        
        // Set preferred size for sub-panels to expand
        usersPanel.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, usersPanel.getPreferredSize().height));
        relationsPanel.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, relationsPanel.getPreferredSize().height));
        filePanel.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, filePanel.getPreferredSize().height));
        
        // Set new layout
        this.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gbc.fill = java.awt.GridBagConstraints.BOTH; // Changed to BOTH
        gbc.weightx = 1.0;
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        
        // Add title
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL; // Title only horizontal
        this.add(titleLabel, gbc);
        
        // Add users panel
        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.fill = java.awt.GridBagConstraints.BOTH; // Both directions
        this.add(usersPanel, gbc);
        
        // Add relations panel
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.fill = java.awt.GridBagConstraints.BOTH; // Both directions
        this.add(relationsPanel, gbc);
        
        // Add file panel
        gbc.gridy = 3;
        gbc.weighty = 0;
        gbc.fill = java.awt.GridBagConstraints.BOTH; // Both directions
        this.add(filePanel, gbc);
        
        // Add empty space at bottom to push everything up
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        this.add(new javax.swing.JPanel(), gbc);
    }
    
    // ----------------- LAYOUT CONFIG FUNCTIONS -----------------  //
    
    private void configureUsersPanel() {
        usersPanel.removeAll();
        usersPanel.setLayout(new java.awt.GridBagLayout());
        
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new java.awt.Insets(5, 10, 5, 10);
        
        // User label
        gbc.gridy = 0;
        usersPanel.add(userLabel, gbc);
        
        // User text field
        gbc.gridy = 1;
        usersPanel.add(userText, gbc);
        
        // Add button
        gbc.gridy = 2;
        gbc.insets = new java.awt.Insets(10, 10, 5, 10);
        usersPanel.add(addUserButton, gbc);
        
        // Remove button
        gbc.gridy = 3;
        gbc.insets = new java.awt.Insets(5, 10, 10, 10);
        usersPanel.add(removeUserButton, gbc);
    }

    private void configureRelationsPanel() {
        relationsPanel.removeAll();
        relationsPanel.setLayout(new java.awt.GridBagLayout());
        
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new java.awt.Insets(5, 10, 5, 10);
        
        // From label
        gbc.gridy = 0;
        relationsPanel.add(fromUserLabel, gbc);
        
        // From combobox
        gbc.gridy = 1;
        relationsPanel.add(fromUserComboBox, gbc);
        
        // To label
        gbc.gridy = 2;
        gbc.insets = new java.awt.Insets(10, 10, 5, 10);
        relationsPanel.add(toUserLabel, gbc);
        
        // To combobox
        gbc.gridy = 3;
        gbc.insets = new java.awt.Insets(5, 10, 5, 10);
        relationsPanel.add(toUserComboBox, gbc);
        
        // Add button
        gbc.gridy = 4;
        gbc.insets = new java.awt.Insets(10, 10, 5, 10);
        relationsPanel.add(addRelationButton, gbc);
        
        // Remove button
        gbc.gridy = 5;
        gbc.insets = new java.awt.Insets(5, 10, 10, 10);
        relationsPanel.add(removeRelationButton, gbc);
    }
    
    private void configureFilePanel() {
        filePanel.removeAll();
        filePanel.setLayout(new java.awt.GridBagLayout());
        
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new java.awt.Insets(5, 10, 5, 10);
        
        // File path label
        gbc.gridy = 0;
        filePanel.add(filePathLabel, gbc);
        
        // Load button
        gbc.gridy = 1;
        gbc.insets = new java.awt.Insets(10, 10, 5, 10);
        filePanel.add(loadFileButton, gbc);
        
        // Save button
        gbc.gridy = 2;
        gbc.insets = new java.awt.Insets(5, 10, 10, 10);
        filePanel.add(saveFileButton, gbc);
    }
    
    // ----------------- END - LAYOUT CONFIG FUNCTIONS -----------------  //
    
    // ----------------- EXTERNAL STATE FROM PARENT HANDLERS -----------------  //
    
    public void setGraphAndListener(Graph<String> graph, GraphUpdateListener listener) {
        this.graph = graph;
        this.updateListener = listener;
        updateComboBoxes();
    }
    
    private void updateComboBoxes() {
        fromUserComboBox.removeAllItems();
        toUserComboBox.removeAllItems();
        
        // Add placeholder item
        fromUserComboBox.addItem("-- Seleccionar usuario --");
        toUserComboBox.addItem("-- Seleccionar usuario --");
        
        // add options from graph nodes
        LinkedList<String> nodes = graph.getNodes();
        Node<String> current = nodes.getHead();
        while (current != null) {
            String user = current.getData();
            fromUserComboBox.addItem(user);
            toUserComboBox.addItem(user);
            current = current.getNext();
        }
    }
    
    public void updateFilePath(String filePath) {
        if (filePath == null) {
            filePathLabel.setText("Archivo: No asignado");
        } else {
             String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            filePathLabel.setText("Archivo: " + fileName);
        }
    }
    // -------------- END - EXTERNAL STATE FROM PARENT HANDLERS --------------  //
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        titleLabel = new javax.swing.JLabel();
        usersPanel = new javax.swing.JPanel();
        userLabel = new javax.swing.JLabel();
        userText = new javax.swing.JTextField();
        addUserButton = new javax.swing.JButton();
        removeUserButton = new javax.swing.JButton();
        relationsPanel = new javax.swing.JPanel();
        fromUserLabel = new javax.swing.JLabel();
        fromUserComboBox = new javax.swing.JComboBox<>();
        toUserLabel = new javax.swing.JLabel();
        toUserComboBox = new javax.swing.JComboBox<>();
        addRelationButton = new javax.swing.JButton();
        removeRelationButton = new javax.swing.JButton();
        filePanel = new javax.swing.JPanel();
        filePathLabel = new javax.swing.JLabel();
        loadFileButton = new javax.swing.JButton();
        saveFileButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridBagLayout());

        titleLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(59, 169, 156));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Controles");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 282;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 14, 0, 0);
        add(titleLabel, gridBagConstraints);

        usersPanel.setBackground(new java.awt.Color(255, 255, 255));
        usersPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestionar Usuarios"));

        userLabel.setText("Usuario");

        userText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTextActionPerformed(evt);
            }
        });

        addUserButton.setBackground(new java.awt.Color(59, 169, 156));
        addUserButton.setForeground(new java.awt.Color(255, 255, 255));
        addUserButton.setText("Agregar");
        addUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserButtonActionPerformed(evt);
            }
        });

        removeUserButton.setBackground(new java.awt.Color(255, 102, 102));
        removeUserButton.setForeground(new java.awt.Color(255, 255, 255));
        removeUserButton.setText("Eliminar");
        removeUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeUserButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout usersPanelLayout = new javax.swing.GroupLayout(usersPanel);
        usersPanel.setLayout(usersPanelLayout);
        usersPanelLayout.setHorizontalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userText)
                    .addComponent(addUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        usersPanelLayout.setVerticalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addUserButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(removeUserButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 22;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 20, 0, 10);
        add(usersPanel, gridBagConstraints);

        relationsPanel.setBackground(new java.awt.Color(255, 255, 255));
        relationsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestionar Relaciones"));

        fromUserLabel.setText("Desde");

        fromUserComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        toUserLabel.setText("Hacia");

        toUserComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        addRelationButton.setBackground(new java.awt.Color(59, 169, 156));
        addRelationButton.setForeground(new java.awt.Color(255, 255, 255));
        addRelationButton.setText("Agregar");
        addRelationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRelationButtonActionPerformed(evt);
            }
        });

        removeRelationButton.setBackground(new java.awt.Color(255, 102, 102));
        removeRelationButton.setForeground(new java.awt.Color(255, 255, 255));
        removeRelationButton.setText("Eliminar");
        removeRelationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeRelationButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout relationsPanelLayout = new javax.swing.GroupLayout(relationsPanel);
        relationsPanel.setLayout(relationsPanelLayout);
        relationsPanelLayout.setHorizontalGroup(
            relationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(relationsPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(relationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(toUserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(relationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(fromUserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addRelationButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(removeRelationButton, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                        .addComponent(fromUserComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(toUserComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        relationsPanelLayout.setVerticalGroup(
            relationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(relationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fromUserLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fromUserComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(toUserLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(toUserComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addRelationButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(removeRelationButton)
                .addGap(15, 15, 15))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 22;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 20, 290, 10);
        add(relationsPanel, gridBagConstraints);

        filePanel.setBackground(new java.awt.Color(255, 255, 255));
        filePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestionar Archivo"));

        filePathLabel.setText("Archivo:");

        loadFileButton.setBackground(new java.awt.Color(59, 169, 156));
        loadFileButton.setForeground(new java.awt.Color(255, 255, 255));
        loadFileButton.setText("Cargar archivo");
        loadFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFileButtonActionPerformed(evt);
            }
        });

        saveFileButton.setForeground(new java.awt.Color(59, 169, 156));
        saveFileButton.setText("Guardar grafo");
        saveFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout filePanelLayout = new javax.swing.GroupLayout(filePanel);
        filePanel.setLayout(filePanelLayout);
        filePanelLayout.setHorizontalGroup(
            filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(filePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(filePathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(loadFileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveFileButton, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        filePanelLayout.setVerticalGroup(
            filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(filePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(filePanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(filePathLabel)
                    .addGap(41, 41, 41)
                    .addComponent(loadFileButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(saveFileButton)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        add(filePanel, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents

    private void userTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userTextActionPerformed

    private void addUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserButtonActionPerformed
        String username = userText.getText().trim();
    
        // Validate empty input
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, 
                "Por favor ingresa un nombre de usuario", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validate format (must start with @)
        if (!username.startsWith("@")) {
            JOptionPane.showMessageDialog(parentFrame, 
                "El usuario debe comenzar con @", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            graph.addNode(username);
            
            // Clear the text field
            userText.setText("");
            
            // Notify parent that graph was updated
            if (updateListener != null) {
                updateListener.onNodeAdded(username);
            }
            
            // Show success message
            JOptionPane.showMessageDialog(parentFrame, 
                "Usuario " + username + " agregado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (IllegalArgumentException e) {
            // Handle case where node already exists
            JOptionPane.showMessageDialog(parentFrame, 
                "El usuario " + username + " ya existe en el grafo", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addUserButtonActionPerformed

    private void removeUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeUserButtonActionPerformed
        String username = userText.getText().trim();
        // Validate empty input
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, 
                "Por favor ingresa un nombre de usuario", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            graph.removeNode(username);
            
            // Clear the text field
            userText.setText("");
            
            // Notify parent that graph was updated
            if (updateListener != null) {
                updateListener.onNodeRemoved(username);
            }
            
            // Show success message
            JOptionPane.showMessageDialog(parentFrame, 
                "Usuario " + username + " eliminado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (IllegalArgumentException e) {
            // Handle case where node does not exist
            JOptionPane.showMessageDialog(parentFrame, 
                "El usuario " + username + " no existe en el grafo", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_removeUserButtonActionPerformed

    private void addRelationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRelationButtonActionPerformed
        String fromUser = (String) fromUserComboBox.getSelectedItem();
        String toUser = (String) toUserComboBox.getSelectedItem();
        
        // Validate selection (check if placeholder is selected)
        if (fromUser == null || toUser == null || fromUser.startsWith("--") || toUser.startsWith("--")) {
            JOptionPane.showMessageDialog(parentFrame, 
                "Por favor selecciona usuarios válidos", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (fromUser == toUser) {
            JOptionPane.showMessageDialog(parentFrame, 
                "Un usuario no se puede seguir a sí mismo", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Try to add the edge
        try {
            graph.addEdge(fromUser, toUser);
            
            // Notify parent that graph was updated
            if (updateListener != null) {
                updateListener.onEdgeAdded(fromUser, toUser);
            }
            
            // Show success message
            JOptionPane.showMessageDialog(parentFrame, 
                "Relación " + fromUser + " → " + toUser + " agregada exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (IllegalArgumentException e) {
            // Handle case where nodes don't exist
            JOptionPane.showMessageDialog(parentFrame, 
                "Error: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addRelationButtonActionPerformed

    private void removeRelationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeRelationButtonActionPerformed
        String fromUser = (String) fromUserComboBox.getSelectedItem();
        String toUser = (String) toUserComboBox.getSelectedItem();
        
        // Validate selection (check if placeholder is selected)
        if (fromUser == null || toUser == null || 
            fromUser.startsWith("--") || toUser.startsWith("--")) {
            JOptionPane.showMessageDialog(parentFrame, 
                "Por favor selecciona usuarios válidos", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            graph.removeEdge(fromUser, toUser);
            
            // Notify parent that graph was updated
            if (updateListener != null) {
                updateListener.onEdgeRemoved(fromUser,toUser);
            }
            
            // Show success message
            JOptionPane.showMessageDialog(parentFrame, 
                "Relación " + fromUser + " → " + toUser + " eliminada exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (IllegalArgumentException e) {
            // Handle case where edge doesn't exist or nodes don't exist 
            JOptionPane.showMessageDialog(parentFrame, 
                "Error: Esta relación no existe" , 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_removeRelationButtonActionPerformed

    private void loadFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFileButtonActionPerformed
        if (updateListener != null) {
            updateListener.onLoadFileRequested();
        }
    }//GEN-LAST:event_loadFileButtonActionPerformed

    private void saveFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFileButtonActionPerformed
        if (updateListener != null) {
            updateListener.onSaveFileRequested();
        }
    }//GEN-LAST:event_saveFileButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRelationButton;
    private javax.swing.JButton addUserButton;
    private javax.swing.JPanel filePanel;
    private javax.swing.JLabel filePathLabel;
    private javax.swing.JComboBox<String> fromUserComboBox;
    private javax.swing.JLabel fromUserLabel;
    private javax.swing.JButton loadFileButton;
    private javax.swing.JPanel relationsPanel;
    private javax.swing.JButton removeRelationButton;
    private javax.swing.JButton removeUserButton;
    private javax.swing.JButton saveFileButton;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JComboBox<String> toUserComboBox;
    private javax.swing.JLabel toUserLabel;
    private javax.swing.JLabel userLabel;
    private javax.swing.JTextField userText;
    private javax.swing.JPanel usersPanel;
    // End of variables declaration//GEN-END:variables
}
