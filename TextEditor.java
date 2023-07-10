package textEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor extends JFrame {

    private JTextArea textArea;

    public TextEditor() {
        setTitle("Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem newWindowItem = new JMenuItem("New Window");
        newWindowItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TextEditor().setVisible(true);
            }
        });
        fileMenu.add(newWindowItem);

        JMenuItem openFileItem = new JMenuItem("Open File");
        openFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(TextEditor.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        StringBuilder content = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        reader.close();
                        textArea.setText(content.toString());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        fileMenu.add(openFileItem);

        JMenuItem saveFileItem = new JMenuItem("Save File");
        saveFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(TextEditor.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        writer.write(textArea.getText());
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        fileMenu.add(saveFileItem);

        JMenuItem closeItem = new JMenuItem("Close");
        closeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        fileMenu.add(closeItem);

        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        JMenuItem cutItem = new JMenuItem("Cut");
        cutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.cut();
            }
        });
        editMenu.add(cutItem);

        JMenuItem copyItem = new JMenuItem("Copy");
        copyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.copy();
            }
        });
        editMenu.add(copyItem);

        JMenuItem pasteItem = new JMenuItem("Paste");
        pasteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.paste();
            }
        });
        editMenu.add(pasteItem);

        JMenuItem selectAllItem = new JMenuItem("Select All");
        selectAllItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.selectAll();
            }
        });
        editMenu.add(selectAllItem);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TextEditor().setVisible(true);
            }
        });
    }
}
