package controller;

import main.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by user on 15/10/28.
 */

public class UserSettingsController implements AbstractController, ActionListener {

    private Component          mComponent;
    private AbstractController parentController;
    private JPanel  mUserSettingsPanel = new JPanel();
    private JButton mStartButton       = new JButton(Constants.BUTTON_EVENT_START);
    private ActionListener listener;

    public UserSettingsController() {
        mComponent = mUserSettingsPanel;
        initUI();
    }

    private void initUI() {
        mUserSettingsPanel.add(mStartButton);
        KeyBoardPress.addKeyboardAction(Constants.KEY_EVENT_LEFT, mUserSettingsPanel, KeyEvent.VK_LEFT);
        mStartButton.addActionListener(this);
        mStartButton.setActionCommand(Constants.BUTTON_EVENT_START);
    }

    public void addActionListener(ActionListener listener) {
        this.listener = listener;
    }

    @Override
    public AbstractController getParentController() {
        return parentController;
    }

    @Override
    public void addSubController(AbstractController controller) {
        mUserSettingsPanel.add(controller.getComponent());
    }

    @Override
    public Component getComponent() {
        return mComponent;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        if (command.equals(Constants.BUTTON_EVENT_START) ||
            command.equals(Constants.BUTTON_EVENT_STOP)) {
            listener.actionPerformed(actionEvent);
        }
    }
}
