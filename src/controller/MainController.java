package controller;

import main.Constants;
import view.BirdManFrame;

import java.awt.*;

/**
 * Created by user on 15/10/28.
 */

public class MainController implements AbstractController {

    private Component          mComponent;
    private AbstractController mParentController;
    private       CanvasController       canvasController       = new CanvasController();
    private       UserSettingsController userSettingsController = new UserSettingsController();
    final private BirdManFrame           mFrame                 = new BirdManFrame();

    public MainController() {
        mComponent = mFrame;
        initUILayout();
    }

    private void initUILayout() {

        mFrame.setLayout(new GridLayout(0, 2));
        mFrame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        addSubController(userSettingsController);
        addSubController(canvasController);
    }

    @Override
    public AbstractController getParentController() {
        return mParentController;
    }

    @Override
    public void addSubController(AbstractController controller) {
        mFrame.add(controller.getComponent());
    }

    @Override
    public Component getComponent() {
        return mComponent;
    }
}
