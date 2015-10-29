package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 15/10/28.
 */

public class UserSettingsController implements AbstractController, ActionListener {

    enum COMMAND{
        START("Start");
        private String command;
        COMMAND(String command){
            this.command = command;
        }
        public String getCommand() {
            return command;
        }
    }

    private Component          mComponent;
    private AbstractController parentController;
    private JPanel  mUserSettingsPanel = new JPanel();
    private JButton mStartButton       = new JButton(COMMAND.START.getCommand());
    private ActionListener listener;

    public UserSettingsController() {
        mComponent = mUserSettingsPanel;
        initUI();
    }

    private void initUI() {
        mUserSettingsPanel.add(mStartButton);
        mStartButton.addActionListener(this);
        mStartButton.setActionCommand(COMMAND.START.getCommand());
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
        if (actionEvent.getActionCommand().equals("Start")){
            listener.actionPerformed(actionEvent);
        }
    }
}
