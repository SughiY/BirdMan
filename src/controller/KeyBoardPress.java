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

    static private HashMap<String, ActionMap>      observersAction= new HashMap<String, ActionMap>();
//    static private HashMap<String, JComponent>     clients   = new HashMap<String, JComponent>();
//    static private HashMap<String, InputMap>       clientsInput   = new HashMap<String, InputMap>();

    /**
     * post an event when detecting correspondent key event
     * @param key event name correspondent actionListener
     * @param component the component in charge of detecting key event
     * @param keyEvent keyboard action needed to be detected
     */
    static public void postEventWhen(String key, JPanel component, final int keyEvent){
//        clients.put(key, component);
        InputMap  im = component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap am = component.getActionMap();
        im.put(KeyStroke.getKeyStroke(keyEvent, 0), key);
        observersAction.put(key, am);
    }

    /**
     * register a perfomAction for correspondent event name
     * @param key event name registered in postEventWhen()
     * @param action action needed to be executed
     */
    static public void registerAsObserver(String key, AbstractAction action){;
        ActionMap am = observersAction.get(key);
        if (am != null){
            am.put(key, action);
        }
    }

}
