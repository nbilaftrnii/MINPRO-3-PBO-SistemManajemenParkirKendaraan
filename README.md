# 🚗 Sistem Manajemen Parkir Kendaraan 🚗

**Nama** : Nabila Fitriani 

**NIM**  : 2509116063  

**Kelas** : B  

---
## 📌 Deskripsi Singkat Program

Program ini merupakan aplikasi berbasis **Java** yang digunakan untuk mengelola sistem parkir kendaraan pada berbagai tempat yang menyediakan area parkir, seperti **coffee shop, mall, kampus, perkantoran, maupun tempat usaha lainnya.**

Program hanya dapat **diakses** oleh **petugas** yang melakukan **login** menggunakan akun yang telah terdaftar di dalam sistem. Dengan demikian, fitur-fitur pengelolaan parkir hanya dapat digunakan setelah petugas berhasil melakukan login.

Program membantu petugas dalam mengelola data kendaraan dan transaksi parkir. Sistem menyediakan fitur **CRUD (Create, Read, Update, Delete)** pada data parkir, mulai dari menambahkan, melihat, memperbarui, menghapus, hingga mencari data berdasarkan ID.

Program juga dapat menghitung tarif parkir berdasarkan **jenis kendaraan dan lama parkir**, **mengelola status slot**, serta **menampilkan receipt pembayaran** setelah transaksi selesai.

### 🔹Fitur Program

🗂️ **Pengelolaan Data**

- Mengelola data kendaraan, slot parkir, dan transaksi parkir.
- Melihat, mencari, memperbarui, dan menghapus data parkir, dengan konfirmasi penghapusan.
- Menyediakan dummy data awal dan generate ID otomatis.
  
🔐 **Login dan Validasi**

- Login petugas (nama + password, maksimal 3 kali percobaan).
- Menerapkan validasi input.
  
🅿️ **Operasional Parkir**

- Masuk Parkir > pendaftaran kendaraan dan check-in dalam satu langkah.
- Slot parkir otomatis dengan kapasitas (25 slot Motor, 10 slot Mobil). Jika penuh, kendaraan tidak dapat masuk.
- Waktu keluar otomatis dari waktu masuk ditambah lama parkir, dengan format waktu yang divalidasi.
- Menghitung tarif parkir berdasarkan jenis kendaraan dan lama parkir.

💳 **Pembayaran**

- Pembayaran Cash atau QRIS (QRIS otomatis sesuai tarif tanpa input jumlah bayar), kembalian dihitung otomatis.
  
🧩 **Konsep OOP**

- MVC, Access Modifier, Encapsulation, Inheritance, Polymorphism (Overriding dan Overloading), Abstraction, Interface, keyword final, dan setter bersyarat.

---
## 📂 Struktur Program

Program menggunakan struktur MVC (Model-View-Controller) dengan beberapa package yang memiliki fungsi berbeda.

<img width="367" height="416" alt="image" src="https://github.com/user-attachments/assets/81bf8bca-772e-490d-8b0e-b9d4386d4a08" />

**Penjelasan Package**

**1. model**

Berisi class yang merepresentasikan objek dalam sistem, yaitu Kendaraan, Motor, Mobil, Petugas, SlotParkir, dan Parkir.

**2. view**

Berisi ParkirView yang menangani tampilan menu, input user, validasi input, dan pesan yang ditampilkan kepada user.

**3. controller**

Berisi ParkirController yang mengatur alur program dan menghubungkan bagian View dengan Service.

**4. service**

Berisi ParkirService yang menangani pengelolaan data menggunakan ArrayList, seperti tambah, cari, update, hapus, dan generate ID.

**5. main**

Berisi Main sebagai titik awal program. Class ini membuat object Service, View, dan Controller, kemudian menjalankan program.

### Pengembangan struktur

Package model kini juga berisi **interface Bertarif**, dan Kendaraan dibuat sebagai **abstract** class. Package service juga menangani login petugas dan pemilihan slot otomatis.

```
src
├── model
│   ├── Bertarif.java        (interface)
│   ├── Kendaraan.java       (abstract class)
│   ├── Motor.java
│   ├── Mobil.java
│   ├── Petugas.java
│   ├── SlotParkir.java
│   └── Parkir.java
├── view
│   └── ParkirView.java
├── controller
│   └── ParkirController.java
├── service
│   └── ParkirService.java
└── main
    └── Main.java
```

---
## ⚙️ Penjelasan Alur Program

Program dimulai dari login petugas, lalu masuk ke menu utama.

### A. Login Petugas

<img width="510" height="337" alt="image" src="https://github.com/user-attachments/assets/57941040-89be-40b3-ba40-2d7f5cace49f" />

Saat program dijalankan, petugas memasukkan nama dan password. Hanya petugas terdaftar dengan password yang benar yang dapat masuk. Petugas diberi kesempatan 3 kali, setelah itu program otomatis ditutup. Petugas yang berhasil login otomatis tercatat pada setiap transaksi parkir, sehingga tidak perlu lagi memilih ID petugas.

**Akun petugas untuk login:**

- Jai 12345
- Ahmad 54321

<img width="382" height="120" alt="image" src="https://github.com/user-attachments/assets/02607073-6fe0-4adc-8608-0f370aeb8674" />

---
### B. Menu Utama

<img width="383" height="325" alt="image" src="https://github.com/user-attachments/assets/a6db1a9a-3f5b-444d-987f-4296918b8db2" />

Program menampilkan menu utama yang berisi pilihan untuk menambah, melihat, memperbarui, menghapus, dan mencari data parkir. User dapat memilih menu dengan memasukkan nomor sesuai fitur yang ingin digunakan.

Selain itu, menu juga menampilkan sisa slot yang tersedia.

C. Masuk Parkir
Menu ini menggabungkan Tambah Kendaraan dan Tambah Data Parkir menjadi satu langkah.

<img width="572" height="278" alt="image" src="https://github.com/user-attachments/assets/97cde08e-4103-45b9-9c31-cb15c9e632b5" />

- Petugas memilih jenis kendaraan, kemudian memasukkan nomor plat.
- Sistem mengecek slot kosong untuk jenis tersebut. Jika penuh, proses dihentikan sebelum petugas mengisi data lain.
- Selanjutnya, petugas mengisi merk, warna, serta atribut pembeda: kapasitas mesin (cc) untuk Motor atau jumlah pintu untuk Mobil.
- Petugas memasukkan waktu masuk dengan format DD-MM-YYYY HH:MM (contoh 24-09-2026 17:30).
- Data parkir tersimpan dengan status Masih Parkir, slot dipilih otomatis (Motor: A01-A25, Mobil: B01-B10) dan berubah menjadi Terisi.

---
### D. Lihat Data Parkir

<img width="296" height="480" alt="image" src="https://github.com/user-attachments/assets/26ce8ea1-482d-43b4-8b18-206240f196fa" />

Sistem menampilkan seluruh data parkir yang telah tersimpan. Informasi yang ditampilkan meliputi kendaraan, slot, petugas, waktu masuk, waktu keluar, status parkir, dan status pembayaran.

---
### E. Keluar Parkir

**1. Metode Pembayaran Cash**

<img width="296" height="480" alt="image" src="https://github.com/user-attachments/assets/c367d4d0-3069-402f-a3ce-fd9eef4a4f7c" />

<img width="291" height="380" alt="image" src="https://github.com/user-attachments/assets/55b18d1a-7fb0-45f4-bf82-ed496f05b4ad" />

**2. Metode Pembayaran Qris**

<img width="379" height="303" alt="image" src="https://github.com/user-attachments/assets/e3b2cf45-6768-46b5-880a-a313e7c514e8" />

<img width="296" height="378" alt="image" src="https://github.com/user-attachments/assets/bdc9552a-6a85-42f9-b5e1-ce0f3efa5a7f" />

1. Petugas memasukkan lama parkir (jam). Waktu keluar dihitung otomatis dari waktu masuk ditambah lama parkir.
2. Petugas memilih metode pembayaran:
   - Cash: petugas memasukkan jumlah bayar. Jika kurang dari tarif, transaksi ditolak dan data tidak berubah. Jika cukup, kembalian dihitung otomatis.
   - QRIS: jumlah bayar otomatis sama dengan tarif (tanpa input dan tanpa kembalian).
3. Setelah pembayaran berhasil > status parkir Selesai, pembayaran Lunas, slot kembali Kosong, dan receipt pembayaran dicetak.


**Jenis Kendaraan Tarif per Jam Tarif Maksimal**

- Motor > Min Rp2.000, Max Rp20.000
- Mobil > Min Rp5.000, Max Rp50.000
  
Sistem hanya menampilkan kendaraan yang masih parkir. Jika tidak ada, muncul pesan dan kembali ke menu.

<img width="374" height="135" alt="image" src="https://github.com/user-attachments/assets/871ab2d0-f34c-486d-ab3c-1abe45ba866d" />

---
### F. Hapus Data Parkir

<img width="291" height="512" alt="image" src="https://github.com/user-attachments/assets/c03ff979-f3c8-4420-b818-48e8ab93f569" />

**1. Batal Menghapus Data**

<img width="291" height="512" alt="image" src="https://github.com/user-attachments/assets/d9fe18b3-5315-4028-b8c3-389730ac6436" />

<img width="291" height="512" alt="image" src="https://github.com/user-attachments/assets/3662ea6f-7a3b-42c4-8241-eb5c74355c05" />

**2. Berhasil Menghapus Data**

<img width="296" height="318" alt="image" src="https://github.com/user-attachments/assets/c5e988c6-129d-4d29-ab1c-38a2a47cca2a" />

Petugas memasukkan ID parkir yang ingin dihapus. Jika data ditemukan, sistem menampilkan data parkir dan meminta konfirmasi penghapusan. 

Petugas dapat memilih Y untuk menghapus atau T untuk membatalkan. Jika penghapusan dikonfirmasi, data dihapus dan status slot berubah menjadi Kosong sehingga dapat digunakan kembali.

---
### F. Lihat Data Parkir

<img width="314" height="346" alt="image" src="https://github.com/user-attachments/assets/66f6ca05-1ac9-47e8-8659-135c9ea43805" />

Petugas memasukkan ID parkir yang ingin dicari. Program akan mencari data berdasarkan ID tersebut.

Jika data ditemukan, program menampilkan informasi data parkir seperti kendaraan, slot, petugas, waktu masuk, waktu keluar, status parkir, dan status pembayaran.

<img width="312" height="166" alt="image" src="https://github.com/user-attachments/assets/1b37f094-6a7e-4b64-b9e5-3b3bf913cf1a" />

Jika data tidak ditemukan, program menampilkan pesan bahwa data parkir dengan ID tersebut tidak ditemukan.

---
### G. Logout

<img width="516" height="96" alt="image" src="https://github.com/user-attachments/assets/bcf58d5a-0242-4f8c-9bce-a78eefab7764" />

Pengguna memilih menu Logout untuk keluar dari program. Setelah itu sistem menampilkan pesan "Sampai jumpa!" dan program selesai.

---
## 🔹 Access Modifier

Penerapan Access Modifier pada program ini terdapat pada atribut-atribut di dalam class model seperti Kendaraan, Petugas, SlotParkir, dan Parkir.

Atribut dibuat menggunakan access modifier private, sehingga tidak dapat diakses secara langsung dari luar class.

Contohnya pada class Parkir terdapat atribut:

<img width="305" height="200" alt="image" src="https://github.com/user-attachments/assets/0d21bc9c-a944-4a66-bcd8-5b8274c0b0b9" />

Dengan penggunaan private, akses terhadap atribut dapat dibatasi sehingga data di dalam object menjadi lebih terkontrol.

---
## 🔹 Encapsulation

Penerapan Encapsulation dilakukan dengan membungkus data atau atribut di dalam class dan menyediakan getter dan setter untuk mengakses atau mengubah data tersebut.

Pada class SlotParkir, getter digunakan untuk mengambil nilai atribut, sedangkan setter digunakan untuk mengubah nilai tertentu.

Contohnya:

<img width="382" height="375" alt="image" src="https://github.com/user-attachments/assets/839d7f2e-e0c9-4c20-8956-d310292c8c82" />

Dengan adanya encapsulation, atribut yang bersifat private tidak diakses secara langsung dari luar class, tetapi melalui method yang telah disediakan.

Selain itu, terdapat pula setter dengan kondisi. Setter pada data yang krusial diberi kondisi agar nilai yang salah ditolak, contohnya status slot hanya boleh Kosong atau Terisi.

<img width="690" height="108" alt="image" src="https://github.com/user-attachments/assets/7dd9699c-fc23-4f78-b7f6-fdefedf0340f" />

Adapun beberapa contoh penerapan encapsulation pada class lainnya,sebagai berikut.

<img width="641" height="111" alt="image" src="https://github.com/user-attachments/assets/a1824643-e6b8-467c-ad62-8c454a77f899" />

<img width="699" height="105" alt="image" src="https://github.com/user-attachments/assets/a623d546-c0fd-4e73-b860-7680abd3fc4d" />

<img width="621" height="118" alt="image" src="https://github.com/user-attachments/assets/d63c1d0f-2edc-45e4-a14c-0d0f57a179f6" />

<img width="640" height="219" alt="image" src="https://github.com/user-attachments/assets/a17ad171-c3da-4c3b-8164-2a47611fe8f5" />

<img width="705" height="384" alt="image" src="https://github.com/user-attachments/assets/2dbaec76-76f2-4317-9d87-23ab44eb5f4b" />

---
## 🔹 Keyword final

Keyword final digunakan agar nilai yang tidak boleh berubah terlindungi. Berikut salah satu contoh penerapannya dalam program.

<img width="454" height="76" alt="image" src="https://github.com/user-attachments/assets/787bde3f-d9c4-43e6-b68f-2d3d41b96e0b" />

---
## 🔹 Validasi Input

Penerapan Validasi Input terdapat pada class **ParkirView.java.**

Program memiliki beberapa method khusus untuk memastikan input yang diberikan user sesuai dengan ketentuan, berikut beberapa contohnya.

<img width="667" height="455" alt="image" src="https://github.com/user-attachments/assets/b886e6ff-94f4-4e3a-a8f5-8d82630737e2" />

<img width="764" height="416" alt="image" src="https://github.com/user-attachments/assets/e240951d-1b31-4735-b4a2-1845a53d9ab3" />

Validasi input angka juga menggunakan **try-catch**, sehingga program tidak langsung crash ketika user memasukkan input dengan tipe data yang salah.

---
## 🔹 Inheritance
Penerapan Inheritance terdapat pada class Motor dan Mobil yang mewarisi class Kendaraan sebagai superclass.

Struktur inheritance pada program:

```
Kendaraan
  /    \
Motor  Mobil
```

**</> Kode SubClass**

**1. Subclass Motor**

<img width="791" height="162" alt="image" src="https://github.com/user-attachments/assets/54c53326-c98a-4280-8a60-64b2d8314907" />

**2. Subclass Mobil**

<img width="789" height="167" alt="image" src="https://github.com/user-attachments/assets/abe5588b-e332-486b-9b10-57791323d2ee" />

Dengan **inheritance**, class **Motor** dan **Mobil** dapat menggunakan atribut dan method yang terdapat pada class Kendaraan tanpa harus membuatnya kembali.

---
## 🔹 Polymorphism

Penerapan Polymorphism pada program ini menggunakan method **overriding** dan **overloading**. Method **tampilkanInfo()** yang terdapat pada class Kendaraan **dioverride** pada class Motor dan Mobil.

**1. Pada class Motor:**

<img width="395" height="112" alt="image" src="https://github.com/user-attachments/assets/a587fe58-de0d-4204-92c9-9cfc39e3d487" />

**2. Pada class Mobil:**

<img width="397" height="113" alt="image" src="https://github.com/user-attachments/assets/3f8e55bf-b31d-4777-851a-cc25c461a946" />

Dengan overriding tersebut, method **tampilkanInfo()** dapat menghasilkan tampilan yang berbeda sesuai dengan object yang digunakan.

Selain tampilkanInfo(), method getJenisKendaraan(), getTarifPerJam(), dan getTarifMaksimal() juga di-override pada Motor dan Mobil, sehingga tarif berbeda sesuai jenis kendaraan (Motor Rp2.000/jam, Mobil Rp5.000/jam). Class Parkir cukup memanggil kendaraan.hitungTarif(lamaJam) dan kendaraan.tampilkanInfo() tanpa perlu tahu apakah objeknya Motor atau Mobil.

Sedangkan untuk **Overloading**, terdapat pada **Method cariKendaraan()** dalam ParkirService yang memiliki dua versi dengan parameter berbeda.

<img width="615" height="353" alt="image" src="https://github.com/user-attachments/assets/58094b41-3ab6-4c12-be4a-5c2c1a0cb238" />

---
## 🔹 Abstraction

Class **Kendaraan** dibuat sebagai **abstract** class sehingga tidak dapat dibuat objek langsung, hanya melalui Motor atau Mobil. 

Class ini memiliki abstract method yang wajib diisi oleh subclass.

**1. Abstract Class**

<img width="436" height="93" alt="image" src="https://github.com/user-attachments/assets/4b724508-de25-4ab9-ba9d-6bb66eea03bd" />

**2. abstract Method**

<img width="376" height="97" alt="image" src="https://github.com/user-attachments/assets/9fd29663-071e-49c3-846f-9884825f720c" />

---
## 🔹 Interface

Interface digunakan dalam program ini dengan nama **Bertarif** berada di package model (**Bertarif.java**) dan menjadi **kontrak** untuk objek yang memiliki tarif parkir. 

Interface ini diterapkan oleh Kendaraan. Rumus tarif ditulis satu kali di Kendaraan, sedangkan angka tarif diambil dari subclass masing-masing.

**1. Model Interface**

<img width="310" height="59" alt="image" src="https://github.com/user-attachments/assets/2c589467-8336-46b5-ac11-b760bf771427" />

**2. Penerapan**

<img width="556" height="78" alt="image" src="https://github.com/user-attachments/assets/08dcea18-d932-4cf4-a131-f0d2965a19e5" />

---
## 🔹 Struktur MVC

Penerapan struktur MVC pada program dilakukan dengan memisahkan class berdasarkan fungsinya ke dalam beberapa package.

<img width="350" height="344" alt="image" src="https://github.com/user-attachments/assets/836c9825-978b-4577-ba05-aac40981d872" />

Struktur package tersebut membuat setiap bagian program memiliki tugas yang berbeda sehingga kode lebih terorganisir dan mudah dikembangkan.

---
## 🔹 Dummy Data

Program menyediakan dummy data awal pada ParkirService, berupa data kendaraan, petugas, slot parkir, dan data parkir. Dummy data digunakan agar saat program dijalankan, data sudah tersedia dan dapat langsung ditampilkan.

**1. Slot Parkir**

<img width="434" height="61" alt="image" src="https://github.com/user-attachments/assets/c422932c-6d44-427c-bf36-a322433618ae" />

**2. Petugas**

<img width="451" height="81" alt="image" src="https://github.com/user-attachments/assets/e72de9ef-dcc7-4141-b47b-0e1725ca3aef" />

**3. Data Kendaraan dan Parkir**

<img width="661" height="128" alt="image" src="https://github.com/user-attachments/assets/9e94f087-4cd9-4a13-8e2d-468a51769b87" />
