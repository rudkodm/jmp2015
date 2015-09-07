package by.rudko.oop.menu.view;

/**
 * Created by rudkodm on 9/7/15.
 */
public class DuckControlMenuView extends ConsoleView{
    @Override
    protected String getStringRepresentation() {
        StringBuilder builder = new StringBuilder();
        builder.append("w - up;\r\n");
        builder.append("s - down;\r\n");
        builder.append("a - left;\r\n");
        builder.append("d - right;\r\n");
        builder.append("q - back;\r\n");
        return builder.toString();
    }
}
