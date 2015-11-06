package controller;

import action.*;
import main.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.Serializable;

/**
 * Created by user on 15/10/28.
 */
public class CanvasController implements AbstractController, ActionListener, Serializable{

    static final long serialVersionUID = 75443838583928L;

    private Component          mComponent;
    private AbstractController parentController;
    private JPanel        mCanvasPanel  = new JPanel();
    private AnimatedLabel animatedLabel = new AnimatedLabel("0 s");

    private AnimatedImage ghost  = new AnimatedImage(Constants.IMAGE_PATH_GHOST);
    private AnimatedImage ghost2 = new AnimatedImage(Constants.IMAGE_PATH_GHOST);

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

        animatedLabel.setAnimation(new AnimationComponent.Change() {
            @Override
            public void perform(JComponent c) {
                JLabel l = (JLabel) c;
                if (l != null) {
                    String[] time = l.getText().split(" ");
                    int time0 = Integer.parseInt(time[0]);
                    ++time0;
                    l.setText(Integer.toString(time0) + " s");
                }
            }
        });
        animatedLabel.setBounds(400,20, 40, 25);
        animatedLabel.addToContainer(mCanvasPanel);

        new AnimationDropHandler(mCanvasPanel, AnimatedImage.dataFlavor);
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
            animatedLabel.startAnimation(1000);
            ghost.startAnimation(20);
            ghost2.startAnimation(20);
        } else if (command.equals(Constants.BUTTON_EVENT_STOP)) {
            ghost.stopAnimation();
        } else {

        }
    }
}
