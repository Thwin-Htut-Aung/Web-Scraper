package com.tha.webscraper;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class WebScraper extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JLabel label;
    private JTextField urlInput;
    private JPanel scrapedContent;
    private JButton scrapeButton;

    public WebScraper() {
        super("Web Scraper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocation(0,0);
        setLayout(null);



        // Create input field for URL
        label = new JLabel("Enter web URL for scraping:");
        label.setBounds(0,0,300,50);
        add(label);
        urlInput = new JTextField(6);
        urlInput.setBounds(300,0,700,50);
        add(urlInput);

        // Create button to initiate web scraping
        scrapeButton = new JButton("Scrape");
        scrapeButton.addActionListener(this);
        scrapeButton.setBounds(400,100,150,50);
        scrapeButton.setBackground(new Color(113,120,215));
        add(scrapeButton);

        label = new JLabel("The scraped web content will appear in the following result panel.");
        label.setBounds(300,150,500,50);
        add(label);
        // Create text area to display results
        scrapedContent = new JPanel();
        JScrollPane scrollPane = new JScrollPane(scrapedContent);
        BoxLayout layout = new BoxLayout(scrapedContent, BoxLayout.Y_AXIS);
        scrapedContent.setLayout(layout);
        scrapedContent.setSize(950,600);
        scrollPane.setBounds(150,200,700,450);

        add(scrollPane);

        setVisible(true);
        revalidate();
        repaint();
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        scrapedContent.removeAll();
        if (e.getSource() == scrapeButton) {
            String url = urlInput.getText();

            try {
                // Use Jsoup to connect to URL and parse HTML
                Document doc = Jsoup.connect(url).get();

                // Select all paragraphs in the HTML
                Elements els = doc.select("p,th,td,h1,h2,h3,span,a");

                // Display the text of each paragraph in the result area

                for (Element el : els) {
                    JTextArea text = new JTextArea();
                    scrapedContent.add(text);

                    text.setText(el.text());
                    if (el.tagName().equals("h1")) {
                        Font font = new Font("Arial",Font.BOLD,20);
                        text.setForeground(Color.RED);
                        text.setFont(font);
                    }
                    else if(el.tagName().equals("h2")){
                        Font font = new Font("Arial",Font.BOLD,16);
                        text.setForeground(Color.RED);
                        text.setFont(font);
                    }
                    else if(el.tagName().equals("h3")){
                        Font font = new Font("Arial",Font.BOLD,12);
                        text.setForeground(Color.RED);
                        text.setFont(font);
                    }


                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        WebScraper app = new WebScraper();
    }
}