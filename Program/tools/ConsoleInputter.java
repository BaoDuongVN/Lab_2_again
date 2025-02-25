package Program.tools;

import java.util.Scanner;
import java.util.Date; //lớp mô tả cho ngày tháng
import java.text.DateFormat; //lớp mô tả dạng ngày tháng
import java.text.SimpleDateFormat; //định dạng ngày tháng đơn giản dd-MM-yyyy...
import java.text.ParseException; //lớp mô tả lỗi khi phân tích (parse) string sang số/Date...
// import java.util.Calendar; //Lớp trừu tượng mô tả chung cho lịch
import java.util.GregorianCalendar; //lớp cụ thể cho dương lịch

public class ConsoleInputter {
    public static Scanner sc = new Scanner(System.in);

    /*
     * Nhập data dạng boolean. Cách dùng:
     * boolean cont = getBoolean("Do you want to continue");
     */
    public static boolean getBoolean(String prompt) {
        // xuất lời nhắc nhở cùng với giải thích cách nhập
        System.out.println(prompt + " (Y/N, T/F, 1/0)?: ");
        String data = sc.nextLine().trim().toUpperCase(); // lấy vào 1 chuỗi
        char c = data.charAt(0); // lấy ký tự đầu tiên do user trả lời
        // trả trị true cho 3 trường hợp sau / ngược lại trả trị false
        return c == 'Y' || c == 'T' || c == '1';
    }

    /*
     * nhập số nguyên trong 1 khoàng [min, max]
     * Cách dùng: int age = getInt("Age", 18, 60);
     */
    public static int getInt(String prompt, int min, int max) {
        int result = 0;
        do {
            System.out.println(prompt + "[" + min + ", " + max + "]: ");
            result = Integer.parseInt(sc.nextLine().trim());
            if (result < min || result > max) {
                System.out.println("Value range: " + "[" + min + ", " + max + "]");
            }
        } while (result < min || result > max);
        return result;
    }

    /*
     * Nhập số thực trong 1 khoảng [min, max]
     * Cách dùng: double salary = getDouble("Salary", 1.0, 4000.0);
     */
    public static double getDouble(String prompt, double min, double max) {
        double result = 0;
        do {
            System.out.println(prompt + "[" + min + ", " + max + "]: ");
            result = Double.parseDouble(sc.nextLine().trim());
            if (result < min || result > max) {
                System.out.println("Value range: " + "[" + min + ", " + max + "]");
            }
        } while (result < min || result > max);
        return result;
    }

    /*
     * nhập 1 chuỗi bất kỳ
     */
    public static String getStr(String prompt) {
        System.out.println(prompt + ": "); // xuất lời nhắc nhở
        return sc.nextLine().trim();
    }

    /*
     * Nhập 1 chuỗi theo mẫu quy định (pattern). Vì pattern có nhiều dạng nên trong
     * prompt nên có lời giải thích về mẫu nhập. Nếu user nhập sai, một
     * thông báo errorMsg được xuất ra. Thí dụ một cách dùng:
     * String phone = getStr("Phone no.", "[\\d]{9}|[\\d]{11}",
     * "Phone: 9/11 digits!"; Tham khảo thêm về Regular Expression cho mô tả
     * pattern:
     * https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
     * https://www.w3schools.com/java/java regex.asp
     */

    public static String getStr(String prompt, String pattern, String errorMsg) {
        String data;
        boolean valid;
        do {
            System.out.println(prompt + ": ");
            data = sc.nextLine().trim();
            valid = data.matches(pattern); // kiểm tra data có trùng với mẫu không
            if (!valid)
                System.out.println(errorMsg); // Không trùng mẫu thì hiện thông báo lỗi ra cho người dùng
        } while (!valid); // lặp lại nếu data không trùng mẫu
        return data;
    }

    /*
     * Nhập Date theo mẫu dd-MM-yyyy hoặc MM-dd-yyyy, ...
     * Cách dùng: Date d = getDate("Date of birth: ", "dd-MM-yyyy");
     * ở đây hành vi parse(...) của lớp DateFormat được dùng. Hành vi này sẽ tự động
     * điều chỉnh ngày lên xuống để có ngày kết quả phù hợp.
     * Thí dụ 32-12-2024 sẽ chuyển thành 01/01/2025.
     * Nếu không muốn dùng cơ chế tự động này, bạn cần tự viết lấy cơ chế chuyển
     * đổi, Cách làm được đề nghị:
     * - Nhập chuỗi
     * - Cắt chuỗi thành 3 thành phần (date, month, year)
     * - Kiểm tra tính hợp lệ của 3 thành phần này (lưu ý về năm nhuận)
     * - Nếu dữ liệu nhập hợp lệ mới nhờ DataFormat chuyển String sang Date
     */
    public static Date getDate(String prompt, String dateFormat) {
        String dateStr;
        Date d;
        // Tạo DateFormat formatter với date format trong tham số
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        do {
            System.out.println(prompt + ": "); // xuất lời nhắc
            dateStr = sc.nextLine().trim(); // nhập data
            try { // phân tích String -> Date
                  // hành vi pase sẽ tự động điều chỉnh phù hợp
                  // ví dụ 32-12-2024 sẽ chuyển thành 01/01/2025

                d = formatter.parse(dateStr);
            } catch (ParseException e) {
                System.out.println("Date format should be " + dateFormat + ".");
                d = null;
            }
        } while (d == null);
        return d;
    }

    /*
     * Đổi Date sang chuỗi dd-MM-yyyy / MM-dd-yyyy,...
     * Cách dùng: System.out.println(dateStr(aDate, "dd-MM-yyyy"));
     */
    public static String dateStr(Date date, String dateFormat) {
        if (date == null)
            return null;
        // tạo DateFormat object làm việc với định dạng trong tham số thứ hai
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        // rồi trả kq sau khi đã chuyển dạng
        return formatter.format(date);
    }

    /*
     * lấy 1 thành phần trong Date. Lớp Calendar giúp truy xuất các thành phần
     * Cách dùng: int year = getPart(aDate, Calendar.YEAR);
     * Chú ý: thành phần tháng cho kết quả: tháng 1 --> 0
     */
    public static int getPart(Date d, int calendarPart) {
        GregorianCalendar cal = new GregorianCalendar(); // tạo calendar
        cal.setTime(d); // cho calendar mang ngày d
        return cal.get(calendarPart);
    }

    /*
     * Thay vì xây dựng lớp cho Menu, hàm menu được xây dựng. Cú pháp
     * DataType...(hàm có số đối sô thay đổi)
     * mô tả cho 1 nhóm với số phần tử tùy ý.
     * Trình biên dịch cư xử với tham số kiểu này giống nư mảng 1 chiều.
     */

    /*
     * Menu trả về số int mà người dùng chọn
     * Cách dùng: int choice = intMenu("Add", "Search", "Remove");
     */
    public static int intMenu(Object... options) {
        // int choice
        int n = options.length; // số mục trong menu
        for (int i = 0; i < n; i++) { // xuất các options
            System.out.println((i + 1) + "-" + options[i]);
        }
        return getInt("Choose ", 1, n); // User bị buộc nhập số phù hợp 1...n
    }

    /*
     * Menu trả về object mà người dùng chọn
     * Cách dùng: String objChoice = (String)objMenu("Add", "Search", "Remove");
     */
    public static Object objMenu(Object... options) {
        int choice = intMenu(options);
        return options[choice - 1];
    }
}
