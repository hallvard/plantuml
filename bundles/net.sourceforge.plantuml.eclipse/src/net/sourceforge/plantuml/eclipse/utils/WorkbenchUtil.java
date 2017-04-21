package net.sourceforge.plantuml.eclipse.utils;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * Class to manage some basic functionalities on the Workbench (message box, get
 * system objects, ...)
 * 
 * @author durif_c
 * 
 */
public class WorkbenchUtil {

    /**
     * get the current active window
     * 
     * @return {@link IWorkbenchWindow} : current active window
     * @author durif_c
     */
    public static IWorkbenchWindow getCurrentActiveWindows() {
        return getWorkbench().getActiveWorkbenchWindow();
    }

    /**
     * get the workbench instance
     * 
     * @return {@link IWorkbench} : the workbench
     * @author durif_c
     */
    public static IWorkbench getWorkbench() {
        return PlatformUI.getWorkbench();
    }

    /**
     * get the shell of the current active window
     * 
     * @return {@link Shell} : the shell
     * @author durif_c
     */
    public static Shell getShell() {
        return getCurrentActiveWindows().getShell();
    }

    /**
     * open an error box.
     * 
     * @param message :
     *            {@link String}
     * @author durif_c
     */
    public static void errorBox(String message) {
        errorBox("ERROR", message, null);
    }

    /**
     * open an error box.
     * 
     * @param title :
     *            {@link String}
     * @param message :
     *            {@link String}
     * @param e :
     *            {@link Throwable}
     * @author durif_c
     */
    public static void errorBox(String title, String message, Throwable e) {
        MessageDialog.openError(getShell(), title, message + "\n"
                + (e != null ? e.getMessage() : ""));

    }

    /**
     * open an error box.
     * 
     * @param message :
     *            {@link String}
     * @param e :
     *            {@link Throwable}
     * @author durif_c
     */
    public static void errorBox(String message, Throwable e) {
        errorBox("ERROR", message, e);
    }
}
