package com.macilias.mediasearch.client.view;

import javax.swing.*;
import java.awt.*;

/**
 * Some Description
 *
 * @author macilias@gmail.com
 */
public class DefaultOptionPane implements OptionPane {

    public int showConfirmDialog(final Component parentComponent, final Object message, final String title, final int optionType) {
        return JOptionPane.showConfirmDialog(parentComponent, message, title, optionType);
    }


    public int showConfirmDialog(final Component parentComponent, final Object message, final String title, final int optionType, final int messageType, final Icon icon) {
        return JOptionPane.showConfirmDialog(parentComponent, message, title, optionType, messageType, icon);
    }


    public void showMessageDialog(final Component parentComponent, final Object message, final String title, final int messageType) {
        JOptionPane.showMessageDialog(parentComponent, message, title, messageType);
    }

}
