# Modules For Java Swing

config connectionString để kết nối database trong file `DatabaseConnection` \
```java
private static final String URL = "jdbc:mysql://0.0.0.0:3000/student_sys";
private static final String USER = "root";
private static final String PASSWORD = "my-secret-pw";
```
Import database từ file student_sys.sql \
Để dùng 2 service này thì chỉ còn khởi tạo không tham số;  
```java
UserService userService = new UserService();
StudentService studentService = new StudentService();
```
## UserService
```Java
public User getUserById(int id)
public List<User> getAllUsers()
// Chỉ update được fullname, age, imageUrl, phonenumber , instance user phải có Id
public void updateUser(User user)
public void deleteUser(int id)
public User getUserByUsername(String username)
public boolean registerUser(String username, String password)
public boolean login(String username, String password)
```
## StudentService
```java
public Student getStudentById(Long id)
public List<Student> getAllStudents()
public void addStudent(Student student)
// Chỉ update được fullname, age, phonenumber , instance student phải có Id
public void updateStudent(Student student)
public void deleteStudent(Long id)
public void addCertificateToStudent(Certificate certificate, Long studentId)
public void addCertificatesToStudent(List<Certificate> certificates , Long studentId)
public List<Certificate> getCertificatesOfStudent(Long studentId)
public void updateCertificate(Certificate certificate)
public void deleteCertificate(Long id)
```
Ví Dụ:
```java
if (userService.login("trannhutanh@admin.com","trannhutanh")){
    System.out.println("Login Success");
}else {
    System.out.println("Login Failed");
}
```

