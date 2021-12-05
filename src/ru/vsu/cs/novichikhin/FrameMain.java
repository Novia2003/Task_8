package ru.vsu.cs.novichikhin;

import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Objects;

public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JTextField textFieldMonth;
    private JTextField textFieldYear;
    private JButton buttonLoadInputFromFile;
    private JButton buttonSaveInputInfoFile;
    private JButton buttonPrintCalendar;
    private JTable tableOutput;
    private JButton buttonSaveOutputIntoFile;
    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;

    public FrameMain() {
        this.setTitle("Calendar");
        this.setContentPane(panelMain);
        this.setBounds(450, 200, 300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableOutput, 80, false, true,
                false, false);
        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("C:\\Users\\ВЯЧЕСЛАВ\\ВГУ\\ВВП\\Eight\\Eight"));
        fileChooserSave.setCurrentDirectory(new File("C:\\Users\\ВЯЧЕСЛАВ\\ВГУ\\ВВП\\Eight\\Eight"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        buttonLoadInputFromFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[] arr = ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                    textFieldYear.setText("" + Objects.requireNonNull(arr)[1]);
                    textFieldMonth.setText("" + Objects.requireNonNull(arr)[0]);
                }
            } catch (Exception e) {
                SwingUtils.showInfoMessageBox("The data in the input file is incorrect " +
                        "(you must specify the month and year numbers)", "Error");
            }
        });

        buttonSaveInputInfoFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[] monthAndYear = new int[]{Integer.parseInt(textFieldMonth.getText()),
                            Integer.parseInt(textFieldYear.getText())};
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    ArrayUtils.writeArrayToFile(file, monthAndYear);
                }
            } catch (Exception e) {
                SwingUtils.showInfoMessageBox("Unable to save file", "Error");
            }

        });

        buttonPrintCalendar.addActionListener(actionEvent -> {
            try {
                int month = Integer.parseInt(textFieldMonth.getText());
                int year = Integer.parseInt(textFieldYear.getText());
                Calendar calendar = new Calendar(month, year);
                int[][] array = calendar.getCalendar();
                JTableUtils.writeArrayToJTable(tableOutput, array);
            } catch (Exception e) {
                SwingUtils.showInfoMessageBox("The month number must be positive and no more than 12." +
                        "The year number must be positive", "Error");
            }
        });

        buttonSaveOutputIntoFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[][] calendar = JTableUtils.readIntMatrixFromJTable(tableOutput);
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    ArrayUtils.writeArrayToFile(file, calendar);
                }
            } catch (Exception e) {
                SwingUtils.showInfoMessageBox("Unable to save file", "Error");
            }
        });
    }
}
