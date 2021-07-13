package eu.thehypesply.inventorytracker.service;

import eu.thehypesply.inventorytracker.dto.DataBoughtDto;
import eu.thehypesply.inventorytracker.dto.DataDto;
import eu.thehypesply.inventorytracker.dto.DataSoldDto;
import eu.thehypesply.inventorytracker.exception.SneakerNotFound;
import eu.thehypesply.inventorytracker.exception.UserNotFoundException;
import eu.thehypesply.inventorytracker.model.Image;
import eu.thehypesply.inventorytracker.model.Item;
import eu.thehypesply.inventorytracker.model.Sneaker;
import eu.thehypesply.inventorytracker.model.User;
import eu.thehypesply.inventorytracker.repository.SneakerRepository;
import eu.thehypesply.inventorytracker.repository.TotalRepository;
import eu.thehypesply.inventorytracker.repository.UserRepository;
import eu.thehypesply.inventorytracker.service.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SneakerServiceImpl implements SneakerService {

    @Autowired
    private SneakerRepository sneakerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private TotalRepository totalRepository;


    public List<Sneaker> getAllSneakers(User user) {
        return sneakerRepository.findAllByUser(user);
    }

    public Optional<Sneaker> getSneaker(long id) {
        return sneakerRepository.findById(id);
    }

//    public String createSneaker(Sneaker sneaker) throws InterruptedException, IOException {
//        System.setProperty("webdriver.chrome.driver", "C://Users//renzo//OneDrive//Documenten//programming//chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36";
//        //options.addArguments("--headless");
//        options.addArguments("user-agent="+userAgent);
//        options.addArguments("--disable-web-security");
//        options.addArguments("--allow-running-insecure-content");
//        options.addArguments("--allow-cross-origin-auth-prompt");
//        String url = "https://stockx.com";
//        WebDriver driver = new ChromeDriver(options);
//
//        Sneaker newSneaker = new Sneaker();
//        newSneaker.setId(sneaker.getId());
//        newSneaker.setPid(sneaker.getPid());
//        newSneaker.setSize(sneaker.getSize());
//        newSneaker.setPriceBought(sneaker.getPriceBought());
//        //load stockx and close popup
//        driver.get(url);
//        Thread.sleep(1200);
//        driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
//        Thread.sleep(1450);
//        //search sneaker with pid
//        driver.findElement(By.name("q")).sendKeys(sneaker.getPid(), Keys.ENTER);
//        Thread.sleep(1100);
//        //load sneaker page
//        try {
//            driver.findElement(By.xpath("//*[@id='main-content']/div[2]/div[2]/div")).click();
//            Thread.sleep(1400);
//            //get sneakername
//            String title = driver.getTitle();
//            String name = title.substring(0,title.indexOf("-"));
//            //get image url
//            WebElement img = driver.findElement(By.xpath("//img[@type='image/jpeg']"));
//            String imUrl = img.getAttribute("srcset");
//            int lastIndex = imUrl.indexOf(".jpg")+4;
//            URL imageUrl = new URL(imUrl.substring(0,lastIndex));
//            //get image
//            InputStream in = new BufferedInputStream(imageUrl.openStream());
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            byte[] buf = new byte[1024];
//            int n;
//            while (-1!=(n=in.read(buf)))
//            {
//                out.write(buf, 0, n);
//            }
//            out.close();
//            in.close();
//            byte[] image = out.toByteArray();
//            //set image
//            newSneaker.setPhoto(image);
//            //set name
//            newSneaker.setSneakerName(name);
//            driver.close();
//            sneakerRepository.save(newSneaker);
//        }catch (Exception e){
//            System.out.println("Can't find sneaker with stylecode " + sneaker.getPid() +" try to add the sneaker manually.");
//        }
//
//        return newSneaker.getSneakerName();
//
//    }

    @Override
    public String createSneakerManual(Sneaker sneaker, Authentication auth, Image photo, Image invoice) {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UserNotFoundException("User not found")
        );
        sneaker.setDateBought(LocalDate.now());
        sneaker.setUser(user);
        sneakerRepository.save(sneaker);
        return sneaker.getSneakerName();
    }

    public void deleteSneaker(long id) {
        if (!sneakerRepository.existsById(id)) {
            throw new SneakerNotFound();
        }
        sneakerRepository.deleteById(id);
    }

    @Override
    public void updateSneaker(long id, Map<String, Object> fields) {
        if (!sneakerRepository.existsById(id)) {
            throw new SneakerNotFound();
        }
        Sneaker sneaker = sneakerRepository.findById(id).get();
        for (String field : fields.keySet()) {
            switch (field) {
                case "sneakerName":
                    sneaker.setSneakerName((String) fields.get(field));
                    break;
                case "size":
                    sneaker.setSneakerSize((Integer) fields.get(field));
                    break;
                case "priceBought":
                    sneaker.setPriceBought((Integer) fields.get(field));
                    break;
                case "salePrice":
                    sneaker.setSalePrice((Integer) fields.get(field));
                    break;
            }
        }
        sneakerRepository.save(sneaker);
    }

    @Override
    public long getTotalBought() {
        List<Sneaker> sneakers = sneakerRepository.findAll();
        long total = 0;
        for (Sneaker sneaker : sneakers) {
            long value = sneaker.getPriceBought();
            total = total + value;
        }
        return total;
    }

    @Override
    public long getTotalSold() {
        List<Sneaker> sneakers = sneakerRepository.findAll();
        long total = 0;
        for (Sneaker sneaker : sneakers) {
            long value = sneaker.getSalePrice();
            total = total + value;
        }
        return total;
    }

    @Override
    public long getBalance() {
        return getTotalSold() - getTotalBought();
    }



    @Override
    public void sold(long id, int priceSold) {
        Sneaker sneaker = sneakerRepository.findById(id).get();
        sneaker.setDateSold(LocalDate.now());
        sneaker.setSalePrice(priceSold);
        sneakerRepository.save(sneaker);
    }

    @Override
    public List<DataDto> getSneakerData(Authentication auth) {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
        List<Sneaker> sneakers = sneakerRepository.findAllByUser(user);
        List<DataDto> itemData = new ArrayList<>();
        List<DataDto> sortedData = new ArrayList<>();
        for (Sneaker sneaker : sneakers) {
            if (sneaker.getDateBought().isAfter(LocalDate.now().minusDays(30))) {
                DataDto sneakerDto = new DataDto(sneaker.getDateBought(), sneaker.getPriceBought());
                itemData.add(sneakerDto);
            }
            if (sneaker.getDateSold() != null && sneaker.getDateSold().isAfter(LocalDate.now().minusDays(30))) {
                DataDto dataSoldDto = new DataDto();
                dataSoldDto.setDate(sneaker.getDateSold());
                dataSoldDto.setPriceSold(sneaker.getSalePrice());
                itemData.add(dataSoldDto);
            }
        }
        for (int i = 0; i < 30; i++ ){
            DataDto dto = new DataDto(LocalDate.now().minusDays(i), 0, 0);
            sortedData.add(dto);
        }
        for (DataDto sorteditem : sortedData){
            for (DataDto item: itemData) {
                if (item.getDate().equals(sorteditem.getDate())){
                    sorteditem.setPriceSold(sorteditem.getPriceSold()+ item.getPriceSold());
                    sorteditem.setPriceBought(sorteditem.getPriceBought() + item.getPriceBought());
                }
            }
        }

        return sortedData;
    }
}
