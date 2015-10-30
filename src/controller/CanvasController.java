package controller;

import action.AnimatedImage;
import action.AnimationComponent;
import main.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by user on 15/10/28.
 */
public class CanvasController implements AbstractController, ActionListener {

    private Component          mComponent;
    private AbstractController parentController;
    private JPanel        mCanvasPanel = new JPanel();

    private AnimatedImage ghost        = new AnimatedImage(Constants.IMAGE_PATH_GHOST);
    private AnimatedImage ghost2        = new AnimatedImage(Constants.IMAGE_PATH_GHOST);

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

        ghost2.setLocation(new Point(240, 20));
        ghost2.setSize(new Dimension(40, 40));
        ghost2.addToContainer(mCanvasPanel);

        KeyBoardPress.registerAsObserver(Constants.KEY_EVENT_LEFT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int x = ghost.getX(), y = ghost.getY();
                ghost.setLocation(x - 1, y);
            }
        });

        ghost.setAnimation(new AnimationComponent.Change() {
            @Override
            public void perform(JComponent c) {
                int x = c.getX(), y = c.getY();
                c.setLocation(x, y + 1);
            }
        });

        ghost2.setAnimation(new AnimationComponent.Change() {
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
        String command = actionEvent.getActionCommand();
        if (command.equals(Constants.BUTTON_EVENT_START)) {
            ghost.startAnimation(20);
            ghost2.startAnimation(20);
        } else if (command.equals(Constants.BUTTON_EVENT_STOP)) {
            ghost.stopAnimation();
        } else {

        }
    }
}
