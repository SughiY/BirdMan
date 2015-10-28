package controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by user on 15/10/28.
 */
public class UserSettingsController implements AbstractController {
    private Component mComponent;
    private AbstractController parentController;
    private JPanel  mUserSettingsPanel = new JPanel();
    private JButton mStartButton       = new JButton("Start");

    public UserSettingsController() {
        mComponent = mUserSettingsPanel;
        initUI();
    }

    private void initUI() {
        mUserSettingsPanel.add(mStartButton);
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
}
