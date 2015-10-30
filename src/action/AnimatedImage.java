package action;

import com.sun.tools.internal.jxc.ap.Const;
import main.Constants;
import view.ImageComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 15/10/28.
 */

public class AnimatedImage extends ImageComponent implements AnimationComponent {

    private static ScheduledThreadPoolExecutor pool   = new ScheduledThreadPoolExecutor(6);
    private        Timer                       mTimer = new Timer(Constants.DEFAULT_DELAY, this);
    private JPanel mContainer;
    private Change mCallback;

    public AnimatedImage(String path) {
        super(path);
    }

    //implement interface
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        final AnimatedImage that = this;
                synchronized (that) {
                    if (mCallback != null) {
                        mCallback.perform(that);
                    }
                }
    }

    @Override
    public void startAnimation(int delay) {
        if (!mTimer.isRunning()) {
            mTimer.setDelay(delay);
            mTimer.start();
        }
    }

    public void startAnimation() {
        startAnimation(Constants.DEFAULT_DELAY);
    }

    @Override
    public void stopAnimation() {
        mTimer.stop();
    }

    @Override
    public void reStartAnimation() {
        if (mTimer.isRunning()) {
            mTimer.restart();
        }
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

    public void setAnimation(Change callback) {
        mCallback = callback;
    }

    public Timer getTimer() {
        return mTimer;
    }
}
