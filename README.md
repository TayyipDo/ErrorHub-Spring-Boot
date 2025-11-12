# Error Hub: Teknik Bilgi Bankası

Bu proje, Spring Boot, Spring Security ve Thymeleaf kullanılarak geliştirilmiş tam özellikli bir "Error Hub" (Teknik Bilgi Bankası) web uygulamasıdır. Kullanıcıların kayıt olup karşılaştıkları teknik hataları veya çözümleri kaydetmelerine, kategorize etmelerine ve daha sonra bu bilgilere hızlıca ulaşmalarına olanak tanır.

Proje, güvenli bir admin paneli aracılığıyla tam içerik yönetimi (CRUD) sağlar ve tüm verilerini bir MySQL veritabanında saklar.

## 🚀 Temel Özellikler

* **Kullanıcı Yönetimi:** Spring Security ile güvence altına alınmış kayıt olma ve giriş yapma sistemi.
* **Admin Paneli:**
    * **Kullanıcı Yönetimi:** Kullanıcıları listeleme, anlık filtreleme ve admin yetkisi verme.
    * **Kategori Yönetimi:** Yeni kategoriler ekleme ve mevcutları silme.
    * **Post Yönetimi:** Kategorilere bağlı yeni hata/çözüm postları ekleme ve silme.
    * **Dinamik Arayüz:** Admin panelindeki tüm silme işlemleri için JavaScript destekli, Tailwind CSS ile stillendirilmiş "Emin misiniz?" onay modalları.
* **Ana Sayfa ve İçerik:**
    * **Hızlı Arama:** Ana sayfada post başlıklarına göre arama (`findByTitleContainingIgnoreCase`).
    * **Kategori Bazlı Listeleme:** Kategorilere tıklandığında ilgili tüm postların listelenmesi (`findAllByCategoryId`).
    * **Gelişmiş Filtreleme:** Kategori sayfasında "başlık + içerik" bazlı çalışan anlık JavaScript filtresi (`filterErrors`).
* **Kullanıcıya Özel Alanlar:**
    * **Kaydedilenler:** Kullanıcıların ilgilerini çeken postları kendi profillerine kaydetmeleri için `/saved` uç noktası (`findByUser`).
* **Veritabanı ve Performans:**
    * **Sayfalama (Pagination):** Listeleme uç noktalarında `Pageable` desteği kullanılarak 15-20 kayıtlık parçalar halinde veri çekilir. Bu, MySQL üzerinde `LIMIT/OFFSET` kullanarak bellek tüketimini azaltır ve performansı artırır.
    * **Veritabanı:** Tüm veriler MySQL veritabanında saklanır.

## 🛠️ Kullanılan Teknolojiler

* **Backend:**
    * Spring Boot
    * Spring Security (Otantikasyon ve Otorizasyon)
    * Spring Data JPA (Veritabanı işlemleri)
    * Maven
* **Frontend:**
    * Thymeleaf (Server-side template engine)
    * Tailwind CSS (Modern UI tasarımı)
    * JavaScript (Dinamik filtreleme ve onay modalları)
* **Veritabanı:**
    * MySQL

## ⚙️ Kurulum

1.  **Depoyu Klonlayın:**
    ```bash
    git clone [https://github.com/](https://github.com/)[kullaniciadiniz]/[depoadiniz].git
    cd [depoadiniz]
    ```

2.  **Veritabanını Ayarlayın:**
    * **phpMyAdmin** (veya DBeaver, MySQL Workbench gibi) bir veritabanı yönetim aracı kullanarak `errorhub_db` (veya istediğiniz başka bir ad) adında yeni bir MySQL veritabanı oluşturun.
    * `src/main/resources/application.properties` dosyasını açın.
    * Aşağıdaki bilgileri kendi veritabanı ayarlarınızla güncelleyin:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/errorhub_db
    spring.datasource.username=root
    spring.datasource.password=sifreniz
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Projeyi Çalıştırın:**
    * Projeyi bir IDE (IntelliJ, Eclipse) üzerinden açın ve çalıştırın.
    * Veya terminal üzerinden Maven kullanarak çalıştırın:
    ```bash
    mvn spring-boot:run
    ```

4.  **Uygulamaya Erişin:**
    * Uygulama: `http://localhost:8080`
    * Admin Paneli: `http://localhost:8080/admin`

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

# Error Hub: Technical Knowledge Base

This project is a full-featured "Error Hub" (Technical Knowledge Base) web application developed using Spring Boot, Spring Security, and Thymeleaf. It allows users to register, save technical errors or solutions they encounter, categorize them, and quickly access this information later.

The project provides full content management (CRUD) through a secure admin panel and stores all its data in a MySQL database.

## 🚀 Key Features

* **User Management:** Secure registration and login system powered by Spring Security.
* **Admin Panel:**
    * **User Management:** List, live-filter, and grant admin privileges to users.
    * **Category Management:** Add new categories and delete existing ones.
    * **Post Management:** Add new error/solution posts linked to categories and delete them.
    * **Dynamic UI:** JavaScript-powered "Are you sure?" confirmation modals, styled with Tailwind CSS, for all delete operations in the admin panel.
* **Home Page and Content:**
    * **Quick Search:** Search by post titles on the main page (`findByTitleContainingIgnoreCase`).
    * **Category-Based Listing:** List all posts belonging to a specific category upon clicking (`findAllByCategoryId`).
    * **Advanced Filtering:** An instant JavaScript filter on the category page that searches by both "title + content" (`filterErrors`).
* **User-Specific Areas:**
    * **Saved Posts:** A dedicated `/saved` endpoint (`findByUser`) for users to save posts they find interesting to their own profile.
* **Database and Performance:**
    * **Pagination:** List endpoints use `Pageable` support to fetch data in chunks of 15-20 records. This reduces memory consumption and improves performance by using `LIMIT/OFFSET` on MySQL.
    * **Database:** All data is stored in a MySQL database.

## 🛠️ Technology Stack

* **Backend:**
    * Spring Boot
    * Spring Security (Authentication & Authorization)
    * Spring Data JPA (Database operations)
    * Maven
* **Frontend:**
    * Thymeleaf (Server-side template engine)
    * Tailwind CSS (Modern UI design)
    * JavaScript (Dynamic filtering and confirmation modals)
* **Database:**
    * MySQL

## ⚙️ Setup

1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/](https://github.com/)[yourusername]/[repositoryname].git
    cd [repositoryname]
    ```

2.  **Configure the Database:**
    * Create a new MySQL database named `errorhub_db` (or your preferred name) using a management tool like **phpMyAdmin** (or DBeaver, MySQL Workbench).
    * Open the `src/main/resources/application.properties` file.
    * Update the following properties with your own database configuration:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/errorhub_db
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Run the Project:**
    * Open and run the project from your favorite IDE (IntelliJ, Eclipse).
    * Or, run it using Maven from the terminal:
    ```bash
    mvn spring-boot:run
    ```

4.  **Access the Application:**
    * Application: `http://localhost:8080`
    * Admin Panel: `http://localhost:8080/admin`
