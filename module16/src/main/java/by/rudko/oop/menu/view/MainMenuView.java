package by.rudko.oop.menu.view;

/**
 * Created by rudkodm on 9/7/15.
 */
public class MainMenuView extends ConsoleView {
    @Override
    protected String getStringRepresentation() {
        StringBuilder builder = new StringBuilder();
        builder.append("1 - create toy duck;\r\n");
        builder.append("2 - create ducky duck;\r\n");
        builder.append("q - quit;\r\n");
        return builder.toString();
    }
}
