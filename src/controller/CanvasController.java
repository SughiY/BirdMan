package controller;

import action.AnimatedImage;
import action.AnimationComponent;
import action.AnimationDropHandler;
//import action.DragImage;
import action.PanelDragMoveHandler;
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
    private JPanel        mCanvasPanel = new JPanel();
    private DataFlavor dataFlover;

    private AnimatedImage ghost        = new AnimatedImage(Constants.IMAGE_PATH_GHOST);
   // private AnimatedImage ghost2        = new AnimatedImage(Constants.IMAGE_PATH_GHOST);
    private AnimatedImage brick        = new AnimatedImage(Constants.IMAGE_PATH_BRICK);
    private AnimationDropHandler adh = new AnimationDropHandler(mCanvasPanel, dataFlover);
    //private UserSettingsController usc = new UserSettingsController();
    private int bx, by;
    
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

//        animatedLabel.setAnimation(new AnimationComponent.Change() {
//            @Override
//            public void perform(JComponent c) {
//                JLabel l = (JLabel) c;
//                if (l != null) {
//                    String[] time = l.getText().split(" ");
//                    int time0 = Integer.parseInt(time[0]);
//                    ++time0;
//                    l.setText(Integer.toString(time0) + " s");
//                }
//            }
//        });
//        animatedLabel.setBounds(400,20, 40, 25);
//        animatedLabel.addToContainer(mCanvasPanel);

        new AnimationDropHandler(mCanvasPanel, AnimatedImage.dataFlavor);
        initGhost();
    }

    private void initGhost(){

        ghost.setLocation(new Point(240, 20));
        ghost.setSize(new Dimension(40, 40));
        ghost.addToContainer(mCanvasPanel);


        KeyBoardPress.registerAsObserver(Constants.KEY_EVENT_LEFT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int x = ghost.getX(), y = ghost.getY();
                //int r = Integer.valueOf(usc.windSpeed.getText()).intValue();
                ghost.setLocation(x - 10, y);
                System.out.println(adh.getBrickX());
            }
        });

        //press to right
        KeyBoardPress.registerAsObserver(Constants.KEY_EVENT_RIGHT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int x = ghost.getX(), y = ghost.getY();
                ghost.setLocation(x + 10, y);
                System.out.println(adh.getBrickY());
                //System.out.println(by);
                
            }
        });
        
        KeyBoardPress.registerAsObserver(Constants.KEY_EVENT_DOWN, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int x = ghost.getX(), y = ghost.getY();
                ghost.setLocation(x, y+5);
                System.out.println(adh.getBrickY());
                //System.out.println(by);
                
            }
        });
        

        ghost.setAnimation(new AnimationComponent.Change() {
            @Override
            public void perform(JComponent c) {
                int x = c.getX(), y = c.getY();
                if(y<560){
                	c.setLocation(x, y + 1);
                }
                else{
                	System.out.println("you win!");
                	JOptionPane.showMessageDialog(null, "Congratulations! You win!", "Win", JOptionPane.INFORMATION_MESSAGE);
                	//JOptionPane.showConfirmDialog(null, "choose one", "choose one", JOptionPane.YES_NO_OPTION);
                	ghost.stopAnimation();
                }
                //comprisonLocation(ghost, brick);
            }
        });

//        ghost2.setAnimation(new AnimationComponent.Change() {
//            @Override
//            public void perform(JComponent c) {
//                int x = c.getX(), y = c.getY();
//                c.setLocation(x, y + 1);
//            }
//        });
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

    public void brickLocationX(int brickX){
    	 bx= brickX;
    	
    }
    
    public void brickLocationY(int brickY){
    	by= brickY;
    	
    }
//    public void comprisonLocation (AnimatedImage gst, AnimatedImage brk){
//    	gst = ghost; 
//    	//brk = brick;
//    	
//    	int x = ghost.getX(), y = ghost.getY();
//    	//int a = brickLocationX(bx), b = adh.getBrickY();
//    	int w = brick.getWidth(), h = brick.getHeight();
//    	
//    	if(x<(a+w)&&x>a&&y>b&&y<(b+h)){
//    		ghost.stopAnimation();
//    		System.out.println("you lose");
//    		
//    	}
//    	
//    }
    
//    public boolean equals(Object obj) {
//        return (this == obj);
//    }
    

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        if (command.equals(Constants.BUTTON_EVENT_START)) {
            ghost.startAnimation(20);
            //ghost2.startAnimation(20);
        } else if (command.equals(Constants.BUTTON_EVENT_STOP)) {
            ghost.stopAnimation();
            //ghost2.stopAnimation();

        } else {

        }
    }
}
