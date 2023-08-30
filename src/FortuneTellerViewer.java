import java.awt.*;

public class FortuneTellerViewer {

    public static void main(String[] args) {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenW = screenSize.width;
        int screenH = screenSize.height;

        FortuneTellerFrame fortuneTeller = new FortuneTellerFrame(screenW, screenH);
        fortuneTeller.setVisible(true);
    }
}
