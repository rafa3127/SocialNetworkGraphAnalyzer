/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.graph.socialnetworkgraphanalyzer;
import com.formdev.flatlaf.FlatLightLaf;

import com.graph.socialnetworkgraphanalyzer.ui.SocialNetworkUI;

/**
 *
 * @author rafaelc3127
 */
public class SocialNetworkGraphAnalyzer {

    public static void main(String[] args) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new SocialNetworkUI().setVisible(true);
        }
    });
    }
}
