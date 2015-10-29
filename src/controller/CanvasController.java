package controller;

import action.AnimatedImage;
import action.AnimationComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by user on 15/10/28.
 */
public class CanvasController implements AbstractController, ActionListener {

    private Component          mComponent;
    private AbstractController parentController;
    private JPanel        mCanvasPanel = new JPanel();

    private AnimatedImage ghost        = new AnimatedImage(getClass().getClassLoader().getResource("images/ghost.png"));

    public CanvasController() {
        mComponent = mCanvasPanel;
        initUI();
    }

    private void initUI() {

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        GridBagLayout      layout             = new GridBagLayout();

        mCanvasPanel.setLayout(null);
        mCanvasPanel.setBorder(BorderFactory.createTitledBorder("Game Panel"));
        mCanvasPanel.setPreferredSize(new Dimension(560, 930));

        initGhost();
    }

    private void initGhost(){

        ghost.setLocation(new Point(240, 20));
        ghost.setSize(new Dimension(40, 40));
        ghost.addToContainer(mCanvasPanel);

        ghost.registerCallback(new AnimationComponent.Change() {
            @Override
            public void perform(JComponent c) {
                int x = c.getX(), y = c.getY();
                c.setLocation(x, y + 1);
            }
        });

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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Start")){
            ghost.startAnimation();
        }
    }
}
