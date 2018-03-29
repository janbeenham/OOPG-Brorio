package nl.han.ica.brorio.gfx;

import com.sun.prism.image.ViewPort;
import nl.han.ica.OOPDProcessingEngineHAN.View.EdgeFollowingViewport;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.brorio.Launcher;

public class Display{
    private Launcher launcher;
    private View view;
    private ViewPort viewPort;
    private Animations animatedBackground;

    public Display(Launcher launcher){

        this.launcher=launcher;
        animatedBackground = new Animations(launcher, "src/main/java/nl/han/ica/brorio/resources/textures/background/backgroundSprite.png", 12);

    }
    public void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);
        view.setBackground(animatedBackground.animation(),screenWidth,screenHeight);
        launcher.setView(view);
        launcher.size(screenWidth, screenHeight);
    }

    public void createViewWithViewport(int worldWidth, int worldHeight, int screenWidth, int screenHeight, float zoomFactor) {
        EdgeFollowingViewport viewPort = new EdgeFollowingViewport(launcher.getPlayer(), (int) Math.ceil(screenWidth / zoomFactor), (int) Math.ceil(screenHeight / zoomFactor), 0, 0);
        viewPort.setTolerance(50, 50, 50, 50);
        View view = new View(viewPort, worldWidth, worldHeight);
        launcher.setView(view);
        launcher.size(screenWidth, screenHeight);
        view.setBackground(animatedBackground.animation(), screenWidth, screenHeight);

    }

    public View getView() {
        return view;
    }

}
