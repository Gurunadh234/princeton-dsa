package com.dsa.problems.percolation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class PercolationVisualizer extends JFrame {
    private final int gridSize;
    private final JPanel gridPanel;
    private final JButton startButton;
    private final JLabel statusLabel;
    private final Percolation percolation;
    private final SecureRandom random;
    private final JPanel[][] gridCells;

    public PercolationVisualizer(int n) {
        this.gridSize = n;
        this.percolation = new Percolation(n);
        this.random = new SecureRandom();
        this.gridCells = new JPanel[n][n];

        // Frame settings
        setTitle("Percolation Simulation");
        setSize(600, 700);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Grid panel
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(n, n));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize grid
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                JPanel cell = new JPanel();
                cell.setBackground(Color.BLACK);
                cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                gridPanel.add(cell);
                gridCells[row][col] = cell;
            }
        }

        // Start button
        startButton = new JButton("Start Simulation");
        startButton.addActionListener(new StartSimulation());

        // Status label
        statusLabel = new JLabel("Status: Not started", SwingConstants.CENTER);

        // Adding components to frame
        add(gridPanel, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);
    }

    // Simulation action
    private class StartSimulation implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startButton.setEnabled(false); // Disable button while running
            new Thread(() -> {
                simulatePercolation();
                startButton.setEnabled(true);
            }).start();
        }
    }

    private void simulatePercolation() {
        while (!percolation.percolates()) {
            int row = random.nextInt(1, gridSize + 1);
            int col = random.nextInt(1, gridSize + 1);

            if (!percolation.isOpen(row, col)) {
                percolation.open(row, col);
                SwingUtilities.invokeLater(() -> {
                    for (int r = 1; r <= gridSize; r++) {
                        for (int c = 1; c <= gridSize; c++) {
                            updateGrid(r, c);
                        }
                    }
                });

            }

            try {
                Thread.sleep(100); // Delay for visualization effect
            } catch (InterruptedException ignored) { }
        }

        SwingUtilities.invokeLater(() -> statusLabel.setText("Status: System Percolates!"));
    }

    private void updateGrid(int row, int col) {
        int indexRow = row - 1;
        int indexCol = col - 1;

        if (!percolation.isOpen(row, col)) {
            gridCells[indexRow][indexCol].setBackground(Color.BLACK); // Keep blocked sites black
            return;
        }

        if (percolation.isFull(row, col)) {
            gridCells[indexRow][indexCol].setBackground(Color.BLUE); // Full site
//            System.out.println("Full site at (" + row + ", " + col + ") -> BLUE");
        } else {
            gridCells[indexRow][indexCol].setBackground(Color.WHITE); // Open site
//            System.out.println("Open site at (" + row + ", " + col + ") -> WHITE");
        }

        gridCells[indexRow][indexCol].revalidate();
        gridCells[indexRow][indexCol].repaint();
    }


    public static void main(String[] args) {
        int gridSize = 20; // Change this for a different grid size
        SwingUtilities.invokeLater(() -> {
            PercolationVisualizer visualizer = new PercolationVisualizer(gridSize);
            visualizer.setVisible(true);
        });
    }
}
