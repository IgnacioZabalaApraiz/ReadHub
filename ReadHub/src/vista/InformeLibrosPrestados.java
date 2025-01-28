package vista;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;

public class InformeLibrosPrestados extends JFrame {
    private JEditorPane editorPane;

    public InformeLibrosPrestados() {
        setTitle("Visor de Informe BIRT");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        editorPane = new JEditorPane();
        editorPane.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(editorPane);
        add(scrollPane, BorderLayout.CENTER);

        generateAndLoadReport();
        setVisible(true);
    }

    private void generateAndLoadReport() {
        IReportEngine engine = null;
        try {
            EngineConfig config = new EngineConfig();
            Platform.startup(config);
            IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(
                IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY
            );
            engine = factory.createReportEngine(config);

            String reportPath = "C:\\Users\\aritz\\eclipseinformes\\EnlaceInforme\\informes\\ejercicio14.rptdesign";
            IReportRunnable report = engine.openReportDesign(reportPath);
            IRunAndRenderTask task = engine.createRunAndRenderTask(report);

            String outputFilePath = "C:\\Users\\aritz\\eclipseinformes\\EnlaceInforme\\informes\\ejercicio14.html";
            HTMLRenderOption options = new HTMLRenderOption();
            options.setOutputFileName(outputFilePath);
            options.setOutputFormat("html");

            task.setRenderOption(options);
            task.run();
            task.close();

            File htmlFile = new File(outputFilePath);
            editorPane.setPage(htmlFile.toURI().toURL());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (engine != null) {
                engine.destroy();
            }
            Platform.shutdown();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InformeLibrosPrestados());
    }
}