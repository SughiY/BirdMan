package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by user on 15/10/29.
 */

public class KeyBoardPress {

    private JComponent component;
    private HashMap<String, ActionListener> observers = new HashMap<String, ActionListener>();
    private HashMap<String, ActionMap>      observersAction= new HashMap<String, ActionMap>();
    private HashMap<String, JComponent>     clients   = new HashMap<String, JComponent>();
    private HashMap<String, InputMap>       clientsInput   = new HashMap<String, InputMap>();


    public void addKeyboardAction(String key, JComponent component, final int keyEvent){
        clients.put(key, component);
        InputMap                        im        = component.getInputMap(JComponent.WHEN_FOCUSED);
        im.put(KeyStroke.getKeyStroke(keyEvent, 0), key);
        clientsInput.put(key, im);
    }

//    public void registerAsObserver(String key, ActionListener listener, AbstractAction action){
//        observers.put(key, listener);
//        ActionMap am =
//    }

    public KeyBoardPress(JComponent component) {
        this.component = component;

    }
}
