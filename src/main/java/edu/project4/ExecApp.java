package edu.project4;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"UncommentedMain", "MultipleStringLiterals"})
public class ExecApp {

    private ExecApp() {}

    private final static Logger LOGGER = LogManager.getLogger();

    /**
     * <p>Пример использования:
     * <pre>
     * {@code
     * java app.jar --symmetry 10 --multi-thread no --height_image 1000 --width_image 1000
     * }
     * </pre>
     *
     * <p>Допустимые опции:
     * <ul>
     *   <li>-w, --width_image: Ширина изображения (по умолчанию: 3840).</li>
     *   <li>-h, --height_image: Высота изображения (по умолчанию: 2160).</li>
     *   <li>-x, --x_world: Координата X мира (по умолчанию: -3).</li>
     *   <li>-y, --y_world: Координата Y мира (по умолчанию: -2).</li>
     *   <li>-ww, --width_world: Ширина мира (по умолчанию: 6).</li>
     *   <li>-wh, --height_world: Высота мира (по умолчанию: 6).</li>
     *   <li>-s, --samples: Количество образцов (по умолчанию: 10).</li>
     *   <li>-ips, --iter_per_sample: Итераций на образец (по умолчанию: 1000000).</li>
     *   <li>-ca, --count_affine: Количество аффинных преобразований (по умолчанию: 5).</li>
     *   <li>-sym, --symmetry: Симметрия (по умолчанию: 1).</li>
     *   <li>-mt, --multi-thread: Многопоточность (true/false) (по умолчанию: true).</li>
     * </ul>
     */
    public static void main(String[] args) {
        Options options = getOptions();
        CommandLineParser parser = new BasicParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            int widthImage = Integer.parseInt(cmd.getOptionValue("w", "3840"));
            int heightImage = Integer.parseInt(cmd.getOptionValue("h", "2160"));
            double xWorld = Double.parseDouble(cmd.getOptionValue("x", "-3"));
            double yWorld = Double.parseDouble(cmd.getOptionValue("y", "-2"));
            double widthWorld = Double.parseDouble(cmd.getOptionValue("ww", "6"));
            double heightWorld = Double.parseDouble(cmd.getOptionValue("wh", "6"));
            int samples = Integer.parseInt(cmd.getOptionValue("s", "10"));
            int iterPerSample = Integer.parseInt(cmd.getOptionValue("ips", "1000000"));
            int countAffine = Integer.parseInt(cmd.getOptionValue("ca", "5"));
            int symmetry = Integer.parseInt(cmd.getOptionValue("sym", "1"));
            boolean multiThread = Boolean.parseBoolean(cmd.getOptionValue("mt", "true"));
            double gamma = Double.parseDouble(cmd.getOptionValue("sym", "2.2"));
            if (multiThread) {
                ExecMultiThreadRender execMultiThreadRender = new ExecMultiThreadRender(widthImage,
                    heightImage,
                    xWorld,
                    yWorld,
                    widthWorld,
                    heightWorld,
                    samples,
                    iterPerSample,
                    countAffine,
                    symmetry,
                    gamma
                );
                execMultiThreadRender.start();
            } else {
                ExecSingleThreadRender execSingleThreadRender = new ExecSingleThreadRender(widthImage,
                    heightImage,
                    xWorld,
                    yWorld,
                    widthWorld,
                    heightWorld,
                    samples,
                    iterPerSample,
                    countAffine,
                    symmetry,
                    gamma
                );
                execSingleThreadRender.start();
            }
        } catch (ParseException e) {
            LOGGER.error("Error parsing command line arguments: " + e.getMessage());
        }
    }

    @NotNull private static Options getOptions() {
        Options options = new Options();
        options.addOption("w", "width_image", true, "Width of the image");
        options.addOption("h", "height_image", true, "Height of the image");
        options.addOption("x", "x_world", true, "X coordinate of the world");
        options.addOption("y", "y_world", true, "Y coordinate of the world");
        options.addOption("ww", "width_world", true, "Width of the world");
        options.addOption("wh", "height_world", true, "Height of the world");
        options.addOption("s", "samples", true, "Number of samples");
        options.addOption("ips", "iter_per_sample", true, "Iterations per sample");
        options.addOption("ca", "count_affine", true, "Count of affine");
        options.addOption("sym", "symmetry", true, "Symmetry");
        options.addOption("mt", "multi-thread", true, "Multi-thread (true/false)");
        options.addOption("g", "gamma", true, "Gamma correction coef");
        return options;
    }
}
