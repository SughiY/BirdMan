package action;

import main.Constants;
import view.ImageComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

/**
 * Created by user on 15/10/28.
 */

public class AnimatedImage extends ImageComponent implements AnimationComponent{

    private Timer mTimer = new Timer(Constants.DEFAULT_DELAY, this);
    private JPanel mContainer;
    private Change mCallback;

    public AnimatedImage(String path) {
        super(path);
    }

    //implement interface
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (mCallback != null) mCallback.perform(this);
        if (mContainer != null) mContainer.repaint();
    }

    @Override
    public void startAnimation() {
        mTimer.start();
    }

    @Override
    public void addToContainer(JPanel container) {
        container.add(this);
        mContainer = container;
    }

    // getter setter
    public JPanel getContainer() {
        return mContainer;
    }

    public void setContainer(JPanel container) {
        this.mContainer = container;
    }

    public void registerCallback(Change callback) {
        mCallback = callback;
    }

    public Timer getTimer() {
        return  mTimer;
    }
}
