/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import lib.ImageCompare;

/**
 *
 * @author Marcos
 */
public class app {

    /* create a runable demo thing. */
    public static void main(String[] args) {
        // Create a compare object specifying the 2 images for comparison.
        ImageCompare ic = new ImageCompare("c:\\test1.jpg", "c:\\test2.jpg");
		// Set the comparison parameters. 
        //   (num vertical regions, num horizontal regions, sensitivity, stabilizer)
        ic.setParameters(8, 6, 5, 10);
        // Display some indication of the differences in the image.
        ic.setDebugMode(2);
        // Compare.
        ic.compare();
        // Display if these images are considered a match according to our parameters.
        System.out.println("Match: " + ic.match());
        // If its not a match then write a file to show changed regions.
        if (!ic.match()) {
            ic.saveJPG(ic.getChangeIndicator(), "c:\\changes.jpg");
        }
    }

}
