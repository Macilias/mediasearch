package com.macilias.mediasearch.client.view;

import javax.swing.*;
import java.awt.*;

/**
 * Some Description
 *
 * @author macilias@gmail.com
 */
public interface OptionPane {

    /**
     *  @see JOptionPane#showConfirmDialog(Component, Object, String, int);
     */
    int showConfirmDialog(Component parentComponent, Object message, String title, int optionType);


    /**
     *  @see JOptionPane#showConfirmDialog(Component, Object, String, int, int, Icon);
     */
    int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType, Icon icon);


    /**
     *  @see JOptionPane#showMessageDialog(Component, Object, String, int);
     */
    void showMessageDialog(Component parentComponent, Object message, String title, int messageType);

}
