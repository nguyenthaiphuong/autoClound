/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoclound;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author ntphuong
 */
public class AutoClound extends JApplet {
    
    private static final int JFXPANEL_WIDTH_INT = 200;
    private static final int JFXPANEL_HEIGHT_INT = 150;
    private static JFXPanel fxContainer;
    WebDriver driver  = null;
    WebDriver outlook = null;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }
                
                JFrame frame = new JFrame("JavaFX 2 in Swing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                JApplet applet = new AutoClound();
                applet.init();
                
                frame.setContentPane(applet.getContentPane());
                
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                
                applet.start();
            }
        });
    }
    
    @Override
    public void init() {
        fxContainer = new JFXPanel();
        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT));
        add(fxContainer, BorderLayout.CENTER);
        // create JavaFX scene
        Platform.runLater(new Runnable() {
            
            @Override
            public void run() {
                createScene();
            }
        });
    }
    
    private void createScene() {
      System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        Button btn = new Button();
        btn.setText("Start");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                 driver = new ChromeDriver();
                 for(int i = 0; i< 1; i++ ) {
                     runIclound(driver);
                   }
                System.out.println("Hello World!");
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        fxContainer.setScene(new Scene(root));
    }
    
     public static void runIclound(WebDriver driver) {
     // project.Start(chrome, "ntphuong");
     try {
        driver.get("https://appleid.apple.com/account#!&page=create");
        waitForLoad(driver);
        Thread.sleep(1000);
        String name = driver.getCurrentUrl();
        WebElement firstName = driver.findElement(By.xpath("//input[@placeholder='first name']"));
        firstName.sendKeys(random());
        WebElement lastName = driver.findElement(By.xpath("//input[@placeholder='last name']"));
        lastName.sendKeys(random());
        Select country = new Select(driver.findElement(By.xpath("//select[@id='countryOptions']")));
        country.selectByValue(country());
        WebElement birthDay = driver.findElement(By.xpath("//input[@placeholder='birthday']"));
        birthDay.sendKeys(birthday());
        WebElement email = driver.findElement(By.xpath("//input[@placeholder='name@example.com']"));
        email.sendKeys("thaiphuong11dt123@gmail.com");
        WebElement password = driver.findElement(By.xpath("//input[@placeholder='password']"));
        password.sendKeys("Zxcv123123");
        WebElement passwordConfirm = driver.findElement(By.xpath("//input[@placeholder='confirm password']"));
        passwordConfirm.sendKeys("Zxcv123123");
        ArrayList<WebElement> listQuestion =  (ArrayList<WebElement>) driver.findElements(By.className("content-dropdown"));
        ArrayList<WebElement> listAnwser = (ArrayList<WebElement>) driver.findElements(By.xpath("//input[@placeholder='answer']"));
        Select question1 =new Select(listQuestion.get(0));
        question1.selectByValue("131");
        listAnwser.get(0).sendKeys("Dennis");
        Select question2 =new Select(listQuestion.get(2));
        question2.selectByValue("137");
        listAnwser.get(1).sendKeys("Worcester");
        Select question3 =new Select(listQuestion.get(4));
        question3.selectByValue("143");
        listAnwser.get(2).sendKeys("Simmons");
        if(driver.findElement(By.xpath("//input[@id='news']")).isEnabled()) {
            WebElement checkbox1 = driver.findElement(By.xpath("//input[@id='news']")); 
            checkbox1.isSelected();
        }
        if(driver.findElement(By.xpath("//input[@id='itunes']")).isEnabled()) {
            WebElement checkbox2 = driver.findElement(By.xpath("//input[@id='itunes']"));  
            checkbox2.isSelected();
        }
//        if(driver.findElement(By.xpath("//input[@id='appleNews']")).isEnabled()) {
//            WebElement checkbox3 = driver.findElement(By.xpath("//input[@id='appleNews']"));
//            checkbox3.isSelected();
//        } 
        WebElement captcha = driver.findElement(By.xpath("//input[@placeholder='Type the characters in the image']"));
        captcha.sendKeys("hello");
        WebElement submit = driver.findElement(By.className("overflow-text"));
        //String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,"t");
        //outlook.get("https://login.live.com/login.srf").sendKeys(selectLinkOpeninNewTab);
        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://login.live.com/login.srf");
        waitForLoad(driver);
        WebElement emailOutlook =  driver.findElement(By.className("ltr_override"));
        emailOutlook.sendKeys("testout1234@outlook.com");
        WebElement submitOutlook = driver.findElement(By.className("btn-primary"));
        submitOutlook.click(); 
        Thread.sleep(1000);
        WebElement passwordOul = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        passwordOul.sendKeys("Zxcv123123");
        WebElement buttonSubmit = driver.findElement(By.xpath("//input[@id='idSIButton9']"));
        buttonSubmit.click();
        driver.switchTo().window(tabs.get(0));
        driver.get("https://appleid.apple.com/account#!&page=create");
        //submit.click();
     } catch(Exception e) {
         System.out.println("cho lau qua");
     }
    }
    public static String random() {
        String[] array = { "Dennis", "Grace", "Bjarne", "James",
            "Vaillancourt", "Anderson", "Danielson", "Lawrence", 
            "William", "Carter", "Aguilera", "Kimberly", "Simmons",
            "Michael", "Eddings", "Naomi", "Worcester", "Delisle", "Augusta"};
        return array[getRandomNumberInRange(0, 18)];
    }
    
    public static String country() {
        String[] country = {"ASM", "AGO", "ARG", "AUS", "AUT", "KHM", "CAN", "CYM",
            "COL", "CZE", "FRA", "DEU", "GRD", "NZL", "TUR", "USA", "GBR", "UKR", "VEN"};
        return country[getRandomNumberInRange(0, 18)];
    }
    
    public static String birthday() {  
        return "0"+getRandomNumberInRange(1, 9) + "/" + "0"+getRandomNumberInRange(1, 9) + "/" + getRandomNumberInRange(1980, 1990);
    }
    
     public static void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }
     
     private static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
		  throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
