package controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by user on 15/10/28.
 */
public class CanvasController implements AbstractController {
    private Component mComponent;
    private AbstractController parentController;
    private JPanel mCanvasPanel = new JPanel();

    public CanvasController(){
        mComponent = mCanvasPanel;
        initUI();
    }

    private void initUI() {

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        GridBagLayout      layout             = new GridBagLayout();

        mCanvasPanel.setLayout(new BorderLayout());
        mCanvasPanel.setBorder(BorderFactory.createTitledBorder("Game Panel"));
        mCanvasPanel.setPreferredSize(new Dimension(560, 930));
        ;
    }

    @Override
    public void addSubController(AbstractController controller) {
        mCanvasPanel.add(controller.getComponent());
    }

    @Override
    public Component getComponent() {
        return mComponent;
    }

    @Override
    public AbstractController getParentController() {
        return parentController;
    }
}
