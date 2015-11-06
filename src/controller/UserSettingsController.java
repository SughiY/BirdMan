package controller;

import action.AnimatedImage;
import action.AnimatedLabel;
import action.AnimationComponent;
import action.ListDragCopyHandler;
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

    private Object[] blocks = new Object[]{
            new ImageIcon(getClass().getClassLoader().getResource("images/brick.png"))
    };

    private Component          mComponent;
    private AbstractController parentController;
    private JPanel        mUserSettingsPanel = new JPanel();
    private JButton       mStartButton       = new JButton("Start");
    private JList         dragList           = new JList(blocks);
    private ActionListener listener;

    public UserSettingsController() {
        mComponent = mUserSettingsPanel;
        initUI();
    }

    private void initUI() {
        KeyBoardPress.addKeyboardAction(Constants.KEY_EVENT_LEFT, mUserSettingsPanel, KeyEvent.VK_LEFT);

        mStartButton.addActionListener(this);
        mStartButton.setActionCommand(Constants.BUTTON_EVENT_START);

        //init dragList
        dragList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dragList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        dragList.setVisibleRowCount(1);
        dragList.setTransferHandler(new ListDragCopyHandler(dragList, AnimatedImage.dataFlavor));
        dragList.setDropMode(DropMode.USE_SELECTION);
        dragList.setDragEnabled(true);



        mUserSettingsPanel.add(mStartButton);
        mUserSettingsPanel.add(dragList);
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
