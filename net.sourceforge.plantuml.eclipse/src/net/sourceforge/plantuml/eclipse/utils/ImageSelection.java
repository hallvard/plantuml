package net.sourceforge.plantuml.eclipse.utils;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

// Inner class is used to hold an image while on the clipboard.
public class ImageSelection implements Transferable {
    // the Image object which will be housed by the ImageSelection
    private Image image;

    public ImageSelection(Image image) {
        this.image = image;
    }

    // Returns the supported flavors of our implementation
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] { DataFlavor.imageFlavor };
    }

    // Returns true if flavor is supported
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return DataFlavor.imageFlavor.equals(flavor);
    }

    // Returns Image object housed by Transferable object
    public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException, IOException {
        if (!DataFlavor.imageFlavor.equals(flavor)) {
            throw new UnsupportedFlavorException(flavor);
        }
        // else return the payload
        return image;
    }
}
