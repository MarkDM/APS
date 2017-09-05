package app;

import java.awt.Image;
import java.io.File;
import java.security.Timestamp;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import lib.ImageCompare;

/**
 *
 * @author Marcos
 */
public class App {

    /* create a runable demo thing. */
    public String compare(String img1, String img2) {
        // Create a compare object specifying the 2 images for comparison.
        ImageCompare ic = new ImageCompare(img1, img2);
        // Set the comparison parameters. 
        //   (num vertical regions, num horizontal regions, sensitivity, stabilizer)
        ic.setParameters(8, 6, 3, 10);
        // Display some indication of the differences in the image.
        ic.setDebugMode(2);
        // Compare.
        ic.compare();
        // Display if these images are considered a match according to our parameters.
        System.out.println("Match: " + ic.match());
        // If its not a match then write a file to show changed regions.
        if (!ic.match()) {

            String path = new File("").getAbsolutePath() + "\\resultados";

            if (!new File("").exists()) {
                new File(path).mkdir();
            }

            path += "\\img" + System.currentTimeMillis() + ".jpg";

            ic.saveJPG(ic.getChangeIndicator(), path);

            return path;
        }
        return "";
    }

    public String definirImgLabel(JLabel lbl, JFrame owner) {
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(owner);
        File img = jfc.getSelectedFile();

        try {
            if (img != null) {
                Image bi = ImageIO.read(img);
                lbl.setIcon(new ImageIcon(bi.getScaledInstance(261, 215, 50)));
                System.out.println(img.getAbsolutePath());
                return img.getAbsolutePath();
            }

        } catch (Exception e) {
        }

        return "";
    }

}
