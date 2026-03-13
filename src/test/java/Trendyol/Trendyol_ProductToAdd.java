package Trendyol;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.Assert;
import org.junit.jupiter.api.Test; // DOĞRU OLAN BU (api.Test)
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.junit.jupiter.api.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import io.qameta.allure.Step;
import io.qameta.allure.Description;
import java.time.Duration;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Trendyol_ProductToAdd {


    // Tarayıcıyı kontrol edecek olan WebDriver arayüzünden (Interface) bir değişken tanımlar.
    WebDriver driver;

    @BeforeEach
    @Description("Trendyol Testi")
    public void setUp() {

        // 1. Chrome için gerekli olan sürücü (driver) dosyalarını otomatik olarak indirir ve kurar.
        // Mevcut satırı bununla değiştir:
        // 37. satır civarındaki setUp metodunda:
        WebDriverManager.chromedriver().driverVersion("145.0.7632.117").setup();

        // 2. Yeni bir Chrome tarayıcı penceresi oluşturur (Tarayıcıyı fiziksel olarak açar).
        driver = new ChromeDriver();

        // Belirtilen web adresine (Trendyol) gider ve sayfanın yüklenmesini başlatır.
        driver.get("https://www.trendyol.com/");

        // 3. Açılan tarayıcı penceresini ekranı kaplayacak şekilde tam boyut yapar.
        driver.manage().window().maximize();

        // 4. Elemanların yüklenmesi için 10 saniye boyunca "akıllı bekleme" süresi tanımlar.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    // JUnit veya TestNG kütüphanesiyle çalıştırılacak olan ilk test metodunu tanımlar.
    // İçerisinde Thread.sleep gibi beklemeler kullanılacağı için "InterruptedException" hatasını dışarı fırlatır.
    public void test01() throws InterruptedException {

        // 1. Sayfada aniden çıkan pop-up (modal) penceresinin kapatma tuşunu "class" ismiyle bulur.
        WebElement popupClose = driver.findElement(By.className("modal-section-close"));

        // 2. Kapatma tuşuna tıklayarak ana sayfanın önündeki engeli kaldırır.
        popupClose.click();

        //*******************************************************************************************

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //*******************************************************************************************

        // 1. Sayfadaki "Kabul Et" (onetrust-accept-btn-handler) butonunu ID ile bulur.
        WebElement cookieClose = driver.findElement(By.id("onetrust-accept-btn-handler"));

        // 2. Butona tıklayarak çerez bilgilendirme bandını kapatır ve sayfanın önünü açar.
        cookieClose.click();

        //*******************************************************************************************

        // 1. Trendyol sayfasının beklenen (olması gereken) tam başlık metnini tanımlar.
        String expectedData = "En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da";

        // 2. Tarayıcıdan o an açık olan sayfanın gerçek başlığını (title) çeker.
        String actualData = driver.getTitle();

        // 3. Beklenen başlık ile gerçek başlığı karşılaştırır; uyuşmazsa testi "FAILED" yapar.
        Assert.assertEquals(expectedData, actualData);

        //*******************************************************************************************

        // 1. Pop-up üzerindeki "Erkek" yazısını içeren kutucuğu tam metin eşleşmesi (XPath) ile bulur.
        WebElement popUpMale = driver.findElement(By.xpath("//div[text()='Erkek']"));

        // 2. Seçeneğe tıklar ve ana sayfaya erkek kategorisi odaklı giriş yapar.
        popUpMale.click();

        //*******************************************************************************************

        // 1. Arama kutusunun üzerindeki "Ürün, kategori veya marka ara" yazılı alanı XPath ile bulur.
        WebElement searchBox = driver.findElement(By.xpath("//span[text()='Ürün, kategori veya marka ara']"));

        // 2. Bu alana tıklar; çünkü Trendyol'da yazı yazabilmek için önce bu span'ı aktif etmek gerekir.
        searchBox.click();

        //*******************************************************************************************

        // 1. Trendyol'un gerçek arama giriş alanını (input) 'data-testid' niteliğiyle bulur.
        WebElement searchText = driver.findElement(By.xpath("//input[@data-testid='suggestion']"));

        // 2. Kutuya "laptop" kelimesini yazar ve klavyeden "ENTER" tuşuna basarak aramayı ateşler.
        searchText.sendKeys("laptop" + Keys.ENTER);

        //*******************************************************************************************

        // 1. Arama sonucunda görmeyi beklediğimiz anahtar kelimeyi tanımlar.
        String expectedSearch = "Laptop";

        // 2. Sayfanın başındaki "Laptop" yazısını (h1 başlığı) 'data-testid' ile yakalar.
        WebElement actualSearch = driver.findElement(By.xpath("//h1[@data-testid='title']"));

        // 3. Beklenen kelime ile ekranda yazan başlığı karşılaştırır; tutmazsa testi "FAILED" yapar.
        Assert.assertEquals(expectedSearch, actualSearch.getText());

        //*******************************************************************************************

        // 1. Mevcut driver'ı JavaScript komutlarını çalıştırabilecek yapıya (Interface) dönüştürür.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // 2. Sayfayı dikey (Y ekseni) düzlemde 500 piksel aşağıya kaydırır.
        js.executeScript("window.scrollBy(0,500)");

        //*******************************************************************************************

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Kanka bak buraya aciklama ekledim, bakalim Jenkins gorecek mi bakalım?
        //suan görünüyo süperrrrrrrrrrrrrrr

        // Test otomatik calisacak mi?
        // Test otomatik calisacak mi 2 ?
        // Test otomatik calisacak mi 4 ?
        // Test otomatik calisacak mi 5 ?

        //*******************************************************************************************

        // 1. Sayfadaki tüm ürün kartlarını 'data-testid' kullanarak bir listeye doldurur.
        List<WebElement> products = driver.findElements(By.xpath("//a[@data-testid='product-card']"));

        // 2. Listenin içinden 5. sıradaki (index 3) laptopu seçer.
        WebElement fourProduct = products.get(3);

        // 3. Seçilen ürünün üzerine tıklar ve ürün detay sayfasını açar.
        fourProduct.click();

        //*******************************************************************************************

        // 1. Tarayıcıda açık olan tüm sekmelerin (Windows Handles) listesini bir diziye (Array) dönüştürür.
        // 2. [1] diyerek az önce açılan 2. sekmeyi seçer.
        // 3. Driver'ın odağını (Focus) mevcut sayfadan, o yeni açılan laptop detay sayfasına taşır.
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());

        //*******************************************************************************************

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //*******************************************************************************************

        // 1. Mouse ve klavye gibi gelişmiş hareketleri kontrol eden 'Actions' sınıfını başlatır.
        Actions action = new Actions(driver);

        // 2. Mouse imlecini sayfanın (0,0) noktasından 10 piksel sağa, 10 piksel aşağı kaydırır.
        // 3. O noktaya bir kez tıklar ve tüm bu komut zincirini 'perform()' ile icra eder.
        action.moveByOffset(10, 10).click().perform();

        //*******************************************************************************************

        // 1. Ürün detay sayfasındaki "Sepete Ekle" butonunu 'data-testid' ile nokta atışı bulur.
        WebElement addToProduct = driver.findElement(By.xpath("//button[@data-testid='add-to-cart-button']"));

        // 2. Butona tıklar ve seçilen laptopu alışveriş sepetine ekler.
        addToProduct.click();

        //*******************************************************************************************

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //*******************************************************************************************

        // 1. Sağ üst köşedeki sepet simgesini (ikonunu) class ismiyle bulur.
        WebElement basketClick = driver.findElement(By.className("basket-icon"));

        // 2. Sepet ikonuna tıklar ve kullanıcıyı "Sepetim" sayfasına yönlendirir.
        basketClick.click();

        //*******************************************************************************************

        // 1. Sepet sayfasında görmeyi beklediğimiz tam başlık metnini tanımlar.
        String expectedBasketText = "Sepetim (1 Ürün)";

        // 2. Sepet başlığını içeren alanı, class ismi içindeki "basket-header-title" parçasından yakalar.
        WebElement actualBasketText = driver.findElement(By.xpath("//div[contains(@class, 'basket-header-title')]"));

        // 3. Beklediğimiz "Sepetim (1 Ürün)" yazısı ile ekrandaki gerçek yazıyı kıyaslar; uyuşmazsa testi "FAILED" yapar.
        Assert.assertEquals(expectedBasketText, actualBasketText.getText());

        //*******************************************************************************************

        // 1. Sepet sayfasındaki o devasa turuncu "Sepeti Onayla" butonunu tam metniyle yakalar.
        WebElement basketCheck = driver.findElement(By.xpath("//p[text()='Sepeti Onayla']"));

        // 2. Butona tıklar ve kullanıcıyı adres seçimi/ödeme sayfasına yönlendirir.
        basketCheck.click();

        //*******************************************************************************************

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //*******************************************************************************************

        // 1. Sayfadaki "Giriş Yap / Üye Ol" yazılı bağlantıyı (link) tam metin eşleşmesiyle bulur.
        WebElement basketToLogin = driver.findElement(By.xpath("//a[text()='Giriş Yap / Üye Ol']"));

        // 2. Bağlantıya tıklar ve kullanıcıyı e-posta/şifre girilecek olan login sayfasına yönlendirir.
        basketToLogin.click();

        //*******************************************************************************************

        // 1. Trendyol giriş sayfasındaki e-posta giriş alanını benzersiz ID'si ile bulur.
        WebElement loginEmail = driver.findElement(By.id("login-email"));

        // 2. Kutuya e-posta adresini ("gunesenes.38@gmail.com") yazar.
        loginEmail.sendKeys("gunesenes.38@gmail.com");

        //*******************************************************************************************

        // 1. İçerisinde 'email-check-button' sınıfı geçen butonu XPath ile bulur.
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(@class, 'email-check-button')]"));

        // 2. Butona tıklar ve şifre girme ekranına veya bir sonraki onay adımına geçer.
        submitButton.click();

        //*******************************************************************************************

        // 1. Trendyol giriş sayfasındaki şifre kutusunu benzersiz ID'si ile bulur.
        WebElement loginPassword = driver.findElement(By.id("login-password"));

        // 2. Kutuya şifreyi ("cesibam38") yazar ve klavyeden "ENTER" tuşuna basarak girişi tamamlar.
        loginPassword.sendKeys("cesibam38" + Keys.ENTER);

        //*******************************************************************************************

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //*******************************************************************************************

        WebElement chekoutClick = driver.findElement(By.xpath("//p[text()='Sepeti Onayla']"));

        // 1. Önce butona doğru bir kaydır (Scroll)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", chekoutClick);

        // 2. Kısa bir nefes aldır (0.5 saniye)
        Thread.sleep(500);

        // 3. JavaScript ile "bam bam" tıkla (Önünde engel olsa bile tıklar)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chekoutClick);

        //*******************************************************************************************

        // 1. "Eklemeden Devam Et" yazılı butonu tam metin eşleşmesiyle (XPath) bulur.
        WebElement basketModalClose = driver.findElement(By.xpath("//button[text()='Eklemeden Devam Et']"));

        // 2. Butona tıklar ve ek teklifleri reddederek doğrudan ödeme/login adımına ilerler.
        basketModalClose.click();

        //*******************************************************************************************

        // 1. "Adres Ekle" butonunun dış sarmalayıcısını (wrapper) class ismiyle bulur.
        WebElement addAdress = driver.findElement(By.className("add-address-button-wrapper"));

        // 2. Butona tıklar ve yeni adres giriş formunun açılmasını sağlar.
        addAdress.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //*******************************************************************************************

        // 1. Sayfadaki "Yeni Adres Ekle" kutusunu (box) 'add-address-box' sınıfıyla bulur.
        WebElement addAdressButton = driver.findElement(By.className("add-address-box"));

        // 2. Kutuya tıklar ve adres bilgilerinin girileceği giriş formunu tetikler.
        addAdressButton.click();

        //*******************************************************************************************

        // 1. Adres formundaki "Ad" (name) giriş alanını XPath niteliğiyle bulur.
        WebElement adressName = driver.findElement(By.xpath("//input[@name='name']"));

        // 2. Kutuya "enes" ismini yazar.
        adressName.sendKeys("enes");

        //*******************************************************************************************

        // 1. Adres formundaki "Soyad" (surname) giriş alanını XPath niteliğiyle bulur.
        WebElement adressSurname = driver.findElement(By.xpath("//input[@name='surname']"));

        // 2. Kutuya "güneş" soyismini yazar.
        adressSurname.sendKeys("güneş");

        //*******************************************************************************************

        // 1. Adres formundaki "Telefon" (phone) giriş alanını XPath niteliğiyle bulur.
        WebElement adressPhone = driver.findElement(By.xpath("//input[@name='phone']"));

        // 2. Önce kutuya tıklar; bu, imlecin doğru yere odaklanmasını (focus) ve maskelemenin açılmasını sağlar.
        adressPhone.click();

        // 3. Kutuya "5458359698" telefon numarasını yazar.
        adressPhone.sendKeys("5458359698");

        //*******************************************************************************************

        // "sehirSec" metoduna "İstanbul" parametresini göndererek; dropdown'ı açar,
        // listeyi aşağı kaydırır (scroll) ve İstanbul'u JavaScript ile seçer.
        sehirSec("İstanbul");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //*******************************************************************************************

        // "ilceSec" metoduna "Esenler" parametresini göndererek; İstanbul'un altındaki
        // ilçe listesini açar, Esenler'i bulur ve JS ile garantili şekilde seçer.
        ilceSec("Esenler");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //*******************************************************************************************

        // "mahalleSec" metoduna "Turgut Reis Mah" parametresini göndererek; Esenler'e bağlı
        // mahalle listesini açar, Turgut Reis'i bulur ve JS ile üzerine tıklar.
        mahalleSec("Turgut Reis Mah");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //*******************************************************************************************

        // 1. Adres formundaki "Adres Detayı" (textarea) alanını 'name' niteliğiyle bulur.
        WebElement adressDetail = driver.findElement(By.xpath("//textarea[@name='addressLine']"));

        // 2. Önce kutuya tıklar (focus); bu, imlecin kutu içine girmesini sağlar.
        adressDetail.click();

        // 3. Kutuya "Test 12345 hahahahahh" metnini yazar (Sokak, Bina No, Kat vb. bilgiler buraya gelir).
        adressDetail.sendKeys("Test 12345 hahahahahh");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //*******************************************************************************************

        // 1. Rastgele bir sayı üretelim (0 ile 9999 arası)
        int rastgeleSayi = (int)(Math.random() * 10000);

        // 2. Başlığı kısa tutalım (Örn: "Adres7421" -> Toplam 9 karakter, sınırın çok altında)
        String dinamikBaslik = "Adres" + rastgeleSayi;

        // 3. Gerisi aynı:
        WebElement adressTitleBox = driver.findElement(By.xpath("//input[@name='addressName']"));
        adressTitleBox.click();
        adressTitleBox.sendKeys(dinamikBaslik);

        //*******************************************************************************************

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //*******************************************************************************************

        // Tarayıcının penceresini (window) mevcut konumundan 100 piksel aşağı kaydırır.
        // Bu sayede sayfanın altında kalan "Kaydet" butonu gibi elementler görüş alanına girer.
        js.executeScript("window.scrollBy(0,100)");

        //*******************************************************************************************

        // 1. Adres formunun altındaki, tipi 'submit' olan ve üzerinde "Kaydet" yazan butonu bulur.
        WebElement adressSubmit = driver.findElement(By.xpath("//button[@type='submit' and text()='Kaydet']"));

        // 2. Butona tıklar; bu işlem girdiğin tüm adres bilgilerini Trendyol veritabanına gönderir.
        js.executeScript("arguments[0].click();", adressSubmit);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //*******************************************************************************************

        //*** sözleşme

        // 1. Ödeme sayfasındaki "Ön Bilgilendirme Koşulları" yazan kalın (strong) metni bulur.
        WebElement checkBoxMetni = driver.findElement(By.xpath("//strong[text()='Ön Bilgilendirme Koşulları']"));
        // Metne JS ile tıklar; bu, yanındaki checkbox'ı tetikler ve genelde yasal metin penceresini açar.
        js.executeScript("arguments[0].click();", checkBoxMetni);

        try {
            // 2. Açılan sözleşme penceresinin (pop-up) animasyonla tam yüklenmesi için 2 saniye bekler.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3. Sözleşme penceresinin içindeki "Onaylıyorum" butonunu, boşlukları temizleyerek (normalize-space) bulur.
        WebElement agreementSubmit = driver.findElement(By.xpath("//button[normalize-space()='Onaylıyorum']"));
        // Butona JS ile tıklar; bu, yasal onayı verir ve pencereyi kapatır.
        js.executeScript("arguments[0].click();", agreementSubmit);

        try {
            // 4. Pencerenin kapanması ve ana sayfanın stabilize olması için 2 saniye daha mola verir.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //*******************************************************************************************

        // ... (Sözleşme onaylama kodların burada biter)

        // 4. Pencerenin kapanması için bekle
        Thread.sleep(3000);


        // 3. Assert (Doğrulama) kısmında da aynı değişkeni kullan:
        WebElement adressCheck = driver.findElement(By.xpath("//strong[.='" + dinamikBaslik + "']"));
        Assert.assertEquals(dinamikBaslik, adressCheck.getText());

        //yanlışlıkla ödeme yapmamak için ödeme butonuna tıklama pasif
     /*

        // 1. İçinde "Ödeme Yap" yazan bir span barındıran butonu (button) akıllı XPath ile bulur.
        // Bu locator, butonun içindeki metne odaklandığı için çok sağlamdır.
        WebElement payment = driver.findElement(By.xpath("//button[.//span[text()='Ödeme Yap']]"));

        // 2. Butona tıklar ve ödeme işlemini (sipariş oluşturma) başlatır.
        payment.click();

    */

    }

    public void sehirSec(String sehirAdi) throws InterruptedException {

        // 1. Şehir listesini açan ana kutucuğu (dropdown) 'name' niteliğiyle bulur ve tıklar.
        WebElement cityDropdown = driver.findElement(By.xpath("//div[@name='cityId']"));
        cityDropdown.click();

        // 2. Listenin yüklenmesi ve animasyonun tamamlanması için kısa bir bekleme atar.
        Thread.sleep(1500);

        // 3. Gönderdiğin 'sehirAdi' değişkenini kullanarak dinamik bir XPath oluşturur.
        // Örn: sehirAdi "Kayseri" ise "//div[@class='ty-select-option' and text()='Kayseri']" olur.
        String dinamikXpath = "//div[@class='ty-select-option' and text()='" + sehirAdi + "']";
        WebElement hedefSehir = driver.findElement(By.xpath(dinamikXpath));

        // 4. Selenium'un yetmediği yerlerde tarayıcının motoruna (JavaScript) bağlanır.
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 5. Hedef şehri listenin içinde bulup ekrana (görüş alanına) getirir.
        js.executeScript("arguments[0].scrollIntoView(true);", hedefSehir);
        Thread.sleep(500);

        // 6. Selenium .click() bazen "elementin üstünde başka bir şey var" diyebilir;
        // ama JS click doğrudan DOM üzerinden tıklar, asla hata vermez!
        js.executeScript("arguments[0].click();", hedefSehir);

        System.out.println("JavaScript ile seçilen şehir: " + sehirAdi);
    }

    public void ilceSec(String ilceAdi) throws InterruptedException {

        // 1. Şehir seçildikten sonra aktifleşen "İlçe" dropdown kutusunu bulur ve tıklar.
        WebElement districtDropdown = driver.findElement(By.xpath("//div[@name='districtId']"));
        districtDropdown.click();

        // 2. İlçelerin listelenmesi ve DOM'a yüklenmesi için 1.5 saniye bekler.
        Thread.sleep(1500);

        // 3. Gönderdiğin 'ilceAdi' bilgisini (örneğin "Melikgazi") XPath içine dinamik olarak yerleştirir.
        String dinamikXpath = "//div[@class='ty-select-option' and text()='" + ilceAdi + "']";
        WebElement hedefIlce = driver.findElement(By.xpath(dinamikXpath));

        // 4. JavaScript motorunu kullanarak elementi önce görüş alanına getirir, sonra tıklar.
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", hedefIlce);
        Thread.sleep(500); // Kaydırma işlemi sonrası elementin stabil hale gelmesini bekler.

        // 5. Standart Selenium click'i yerine JS click kullanarak engelleri (overlay vb.) aşar.
        js.executeScript("arguments[0].click();", hedefIlce);

        System.out.println("Seçilen ilçe: " + ilceAdi);
    }

    public void mahalleSec(String mahalleAdi) throws InterruptedException {

        // 1. İlçe seçildikten sonra aktif hale gelen "Mahalle" açılır menüsünü (dropdown) bulur ve tıklar.
        WebElement neighborhoodDropdown = driver.findElement(By.xpath("//div[@name='neighborhoodId']"));
        neighborhoodDropdown.click();

        // 2. Mahallelerin listelenmesi ve DOM'a tam yerleşmesi için 1.5 saniye mola verir.
        Thread.sleep(1500);

        // 3. Gönderdiğin 'mahalleAdi' (örn: "Yıldırım Beyazıt Mah.") için dinamik bir XPath üretir.
        String dinamikXpath = "//div[@class='ty-select-option' and text()='" + mahalleAdi + "']";
        WebElement hedefMahalle = driver.findElement(By.xpath(dinamikXpath));

        // 4. JavaScript Executor'ı devreye alarak sayfayı yormadan hedefe odaklanır.
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 5. Hedef mahalleyi liste içinde bulup görünür kılar (scroll) ve JS üzerinden tıklar.
        // Bu yöntem, Selenium'un standart click() metodunun "Element Not Interactable" hatası vermesini önler.
        js.executeScript("arguments[0].scrollIntoView(true);", hedefMahalle);
        Thread.sleep(500);
        js.executeScript("arguments[0].click();", hedefMahalle);

        System.out.println("Seçilen mahalle: " + mahalleAdi);
    }

    @AfterEach
    public void close() {
        if (driver != null) {
            // Test bittiğinde (Hatalı veya Başarılı) ekran görüntüsünü Allure raporuna gömer
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            io.qameta.allure.Allure.addAttachment("Test Sonu Ekran Görüntüsü", new java.io.ByteArrayInputStream(screenshot));
            driver.quit();
        }


    }
}
