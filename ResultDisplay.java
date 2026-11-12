import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ResultDisplay {

    public static void launchUI() {

        // Frame
        JFrame frame = new JFrame("Brain Segmentation Tool");
        frame.setSize(1100, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(245, 250, 255)); // light blue

        // ===== TOP PANEL =====
        JButton upload = new JButton("Upload Image");
        upload.setFont(new Font("Arial", Font.BOLD, 14));
        upload.setBackground(new Color(100, 149, 237)); // soft blue
        upload.setForeground(Color.WHITE);
        upload.setFocusPainted(false);

        JPanel top = new JPanel();
        top.setBackground(new Color(220, 235, 250));
        top.add(upload);

        frame.add(top, BorderLayout.NORTH);

        // ===== IMAGE GRID =====
        JPanel grid = new JPanel(new GridLayout(2,2,10,10));
        grid.setBackground(new Color(245, 250, 255));
        grid.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel l1 = new JLabel();
        JLabel l2 = new JLabel();
        JLabel l3 = new JLabel();
        JLabel l4 = new JLabel();

        grid.add(wrap("Original", l1, new Color(255, 240, 245)));   // pink
        grid.add(wrap("Preprocessed", l2, new Color(240, 255, 255))); // cyan
        grid.add(wrap("Segmented", l3, new Color(245, 255, 240)));  // green
        grid.add(wrap("ROI", l4, new Color(255, 250, 205)));        // yellow

        frame.add(grid, BorderLayout.CENTER);

        // ===== BOTTOM PANEL =====
        JLabel result = new JLabel("Upload an image", JLabel.CENTER);
        result.setFont(new Font("Arial", Font.BOLD, 16));

        JProgressBar bar = new JProgressBar(0,100);
        bar.setStringPainted(true);
        bar.setForeground(new Color(60, 179, 113)); // green

        JPanel bottom = new JPanel(new GridLayout(2,1,5,5));
        bottom.setBackground(new Color(220, 235, 250));
        bottom.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        bottom.add(result);
        bottom.add(bar);

        frame.add(bottom, BorderLayout.SOUTH);

        // ===== BUTTON ACTION =====
        upload.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter(
                    "Images", "jpg", "jpeg", "png", "bmp"));

            int res = chooser.showOpenDialog(null);

            if (res == JFileChooser.APPROVE_OPTION) {

                File file = chooser.getSelectedFile();

                BufferedImage img = ImageLoader.loadImage(file.getAbsolutePath());

                if (img == null) {
                    JOptionPane.showMessageDialog(frame, "Invalid Image!");
                    return;
                }

                BufferedImage pre = Preprocessing.process(img);
                BufferedImage seg = Segmentation.segment(pre);
                BufferedImage roi = ROIExtractor.extract(seg);

                double act = FeatureExtractor.getActivity(roi);
                String label = FeatureExtractor.getActivityLabel(act);
                int percent = FeatureExtractor.getPercent(act);

                l1.setIcon(new ImageIcon(img));
                l2.setIcon(new ImageIcon(pre));
                l3.setIcon(new ImageIcon(seg));
                l4.setIcon(new ImageIcon(roi));

                result.setText("Activity Level: " + label + " (" + percent + "%)");
                bar.setValue(percent);
            }
        });

        frame.setVisible(true);
    }

    private static JPanel wrap(String title, JLabel img, Color color) {

        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(color);
        p.setBorder(BorderFactory.createTitledBorder(title));

        img.setHorizontalAlignment(JLabel.CENTER);
        p.add(img);

        return p;
    }
}