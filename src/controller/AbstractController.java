package controller;

import java.awt.*;

/**
 * Created by user on 15/10/28.
 */
public interface AbstractController {
     AbstractController parentController = null;
     Component          mComponent       = null;
     Component getComponent();
     AbstractController getParentController();
     void addSubController(AbstractController controller);
}
