package eu.thehypesply.inventorytracker.service;
import eu.thehypesply.inventorytracker.exception.SneakerNotFound;
import eu.thehypesply.inventorytracker.model.Sneaker;
import eu.thehypesply.inventorytracker.repository.SneakerRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SneakerServiceImpl implements SneakerService{

    @Autowired
    private SneakerRepository sneakerRepository;


    public List<Sneaker> getAllSneakers(){return sneakerRepository.findAll();}

    public Optional<Sneaker> getSneaker(String pid){return sneakerRepository.findById(pid);}

    public String createSneaker(Sneaker sneaker) throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "C://Users//renzo//OneDrive//Documenten//programming//chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36";
        options.addArguments("--headless");
        options.addArguments("user-agent="+userAgent);
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--allow-cross-origin-auth-prompt");
        String url = "https://stockx.com";
        WebDriver driver = new ChromeDriver(options);

        Sneaker newSneaker = new Sneaker();
        newSneaker.setPid(sneaker.getPid());
        newSneaker.setSize(sneaker.getSize());
        newSneaker.setPriceBought(sneaker.getPriceBought());
        //load yahoo + accept cookies
        driver.get(url);
        Thread.sleep(1200);
        driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
        Thread.sleep(1450);
        //search sneaker with pid
        driver.findElement(By.name("q")).sendKeys(sneaker.getPid(), Keys.ENTER);
        Thread.sleep(1100);
        //load sneaker page
        driver.findElement(By.xpath("//*[@id='main-content']/div[2]/div[2]/div")).click();
        Thread.sleep(1400);
        //get sneakername
        String title = driver.getTitle();
        String name = title.substring(0,title.indexOf("-"));
        //get image url
        WebElement img = driver.findElement(By.xpath("//img[@type='image/jpeg']"));
        String imUrl = img.getAttribute("srcset");
        int lastIndex = imUrl.indexOf(".jpg")+4;
        URL imageUrl = new URL(imUrl.substring(0,lastIndex));
        //get image
        InputStream in = new BufferedInputStream(imageUrl.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n;
        while (-1!=(n=in.read(buf)))
        {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] image = out.toByteArray();
        //set image
        newSneaker.setPhoto(image);
        //set name
        newSneaker.setSneakerName(name);
        driver.close();
        sneakerRepository.save(newSneaker);
        return newSneaker.getSneakerName();

    }

    public void deleteSneaker(String pid) {
        if (!sneakerRepository.existsById(pid)) {throw new SneakerNotFound();}
        sneakerRepository.deleteById(pid);
    }

    @Override
    public void updateSneaker(String pid, Map<String, Object> fields) {
        if (!sneakerRepository.existsById(pid)) {throw new SneakerNotFound();}
        Sneaker sneaker = sneakerRepository.findById(pid).get();
        for (String field : fields.keySet()){
            switch (field){
                case "size":
                    sneaker.setSize((long) fields.get(field));
                    break;
                case "priceBought":
                    sneaker.setPriceBought((long) fields.get(field));
                    break;
                case "salePrice":
                    sneaker.setSalePrice((long) fields.get(field));
                    break;
            }
        }
        sneakerRepository.save(sneaker);
    }


}
