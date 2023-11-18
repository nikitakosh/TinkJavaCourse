package edu.project3;

import edu.project3.exceptions.EmptyArgumentsException;
import edu.project3.exceptions.NotFoundPathException;
import edu.project3.handlers.InputHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class InputHandlerTest {

    @Test
    @DisplayName("handle args")
    public void handleArgs() {
        InputHandler inputHandler = new InputHandler();
        Assertions.assertEquals(
                inputHandler.handleArgs(new String[]{"--path", "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs", "--format adoc"}),
                "--path https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs --format adoc"
        );
        Assertions.assertThrows(EmptyArgumentsException.class, () -> inputHandler.handleArgs(new String[]{}));
        Assertions.assertThrows(NotFoundPathException.class, () -> inputHandler.handleArgs(new String[]{"--format adoc"}));
    }

    @Test
    @DisplayName("get request params")
    public void getRequestParams() {
        InputHandler inputHandler = new InputHandler();
        Assertions.assertEquals(
                inputHandler.getRequestParams("--path logs/2023* --from 2023-08-31 --format markdown"),
                Map.of(
                        "path", "logs/2023*",
                        "from", "2023-08-31",
                        "to", "",
                        "format", "markdown"
                )
        );
        Assertions.assertEquals(
                inputHandler.getRequestParams("--path https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs --format adoc"),
                Map.of(
                        "path", "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
                        "from", "",
                        "to", "",
                        "format", "adoc"
                )
        );
        Assertions.assertEquals(
                inputHandler.getRequestParams("--path logs/**/2023-08-31.txt"),
                Map.of(
                        "path", "logs/**/2023-08-31.txt",
                        "from", "",
                        "to", "",
                        "format", ""
                )
        );
    }
}
