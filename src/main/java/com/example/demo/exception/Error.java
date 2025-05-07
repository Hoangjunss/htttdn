package com.example.demo.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum Error {
    //Client Error
    NOT_FOUND(404, "Resource not found", HttpStatus.NOT_FOUND), //Resource not found
    BAD_REQUEST(400, "Bad request", HttpStatus.BAD_REQUEST), //Syntax error or malformed request
    UNAUTHORIZED(401, "Unauthorized", HttpStatus.UNAUTHORIZED), // unauthenticated user
    FORBIDDEN(403, "Forbidden", HttpStatus.FORBIDDEN), //The user does not have permission to access the resource
    CONFLICT(409, "Conflict", HttpStatus.CONFLICT), // Resource state conflicts. For example, it can happen when trying to create a duplicate record or update data that is being edited at the same time by someone else.
    INTERNAL_SERVER_ERROR(500, "Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR), //Internal Server Error
    //Database Error
    DATABASE_ACCESS_ERROR(9998, "Database access error", HttpStatus.INTERNAL_SERVER_ERROR),
    DUPLICATE_KEY(9996, "Duplicate key found", HttpStatus.CONFLICT),
    EMPTY_RESULT(9995, "No result found", HttpStatus.NOT_FOUND),
    NON_UNIQUE_RESULT(9994, "Non-unique result found", HttpStatus.CONFLICT),

    //TaiKhoan-related errors
    TAIKHOAN_NOT_FOUND(1001, "Tài khoản không tìm thấy", HttpStatus.NOT_FOUND),
    TAIKHOAN_ALREADY_EXISTS(1002, "Tài khoản đã tồn tại", HttpStatus.CONFLICT),
    TAIKHOAN_UNABLE_TO_SAVE(1003, "Không thể lưu tài khoản", HttpStatus.INTERNAL_SERVER_ERROR),
    TAIKHOAN_UNABLE_TO_UPDATE(1004, "Không thể cập nhật tài khoản", HttpStatus.INTERNAL_SERVER_ERROR),
    TAIKHOAN_UNABLE_TO_DELETE(1005, "Không thể xóa tài khoản", HttpStatus.INTERNAL_SERVER_ERROR),
    ACCOUNT_LOCKED(1006,"Tài khoản đã bị khóa",HttpStatus.INTERNAL_SERVER_ERROR),
    //Field TaiKhoan error
    TAIKHOAN_INVALID_USERNAME(1101, "Tên đăng nhập không hợp lệ", HttpStatus.BAD_REQUEST),
    TAIKHOAN_INVALID_PASSWORD(1102, "Mật khẩu không hợp lệ", HttpStatus.BAD_REQUEST),

    //NhanVien-related errors
    NHANVIEN_NOT_FOUND(2001, "Nhân viên không tìm thấy", HttpStatus.NOT_FOUND),
    NHANVIEN_ALREADY_EXISTS(2002, "Nhân viên đã tồn tại", HttpStatus.CONFLICT),
    NHANVIEN_UNABLE_TO_SAVE(2003, "Không thể lưu nhân viên", HttpStatus.INTERNAL_SERVER_ERROR),
    NHANVIEN_UNABLE_TO_UPDATE(2004, "Không thể cập nhật nhân viên", HttpStatus.INTERNAL_SERVER_ERROR),
    NHANVIEN_UNABLE_TO_DELETE(2005, "Không thể xóa nhân viên", HttpStatus.INTERNAL_SERVER_ERROR),
    //Field NhanVien error
    NHANVIEN_INVALID_NAME(2101, "Tên nhân viên không hợp lệ", HttpStatus.BAD_REQUEST),
    NHANVIEN_INVALID_EMAIL(2102, "Email không hợp lệ", HttpStatus.BAD_REQUEST),
    NHANVIEN_INVALID_PHONE(2103, "Số điện thoại không hợp lệ", HttpStatus.BAD_REQUEST),
    NHANVIEN_INVALID_ADDRESS(2104, "Địa chỉ không hợp lệ", HttpStatus.BAD_REQUEST),
    NHANVIEN_INVALID_BIRTH_DATE(2105, "Ngày sinh không hợp lệ định dạng YYYY-MM-DD", HttpStatus.BAD_REQUEST),
    NHANVIEN_INVALID_GENDER(2106, "Giới tính không hợp lệ", HttpStatus.BAD_REQUEST),
    NHANVIEN_INVALID_COMMISSION(2107, "Tỉ lệ hoa hồng không hợp lệ", HttpStatus.BAD_REQUEST),
    NHANVIEN_INVALID_HOURLY_WAGE(2108, "Lương theo giờ không hợp lệ", HttpStatus.BAD_REQUEST),
    NHANVIEN_INVALID_POSITION(2109, "Chức vụ không hợp lệ", HttpStatus.BAD_REQUEST),

    //Role-related errors
    ROLE_NOT_FOUND(3001, "Vai trò không tìm thấy", HttpStatus.NOT_FOUND),
    ROLE_ALREADY_EXISTS(3002, "Vai trò đã tồn tại", HttpStatus.CONFLICT),
    ROLE_UNABLE_TO_SAVE(3003, "Không thể lưu vai trò", HttpStatus.INTERNAL_SERVER_ERROR),
    ROLE_UNABLE_TO_UPDATE(3004, "Không thể cập nhật vai trò", HttpStatus.INTERNAL_SERVER_ERROR),
    ROLE_UNABLE_TO_DELETE(3005, "Không thể xóa vai trò", HttpStatus.INTERNAL_SERVER_ERROR),
    //Field Role error
    ROLE_INVALID_ROLE(3101, "Tên vai trò không hợp lệ", HttpStatus.BAD_REQUEST),

    //CuaHang-related errors
    CUAHANG_NOT_FOUND(4001, "Cửa hàng không tìm thấy", HttpStatus.NOT_FOUND),
    CUAHANG_ALREADY_EXISTS(4002, "Cửa hàng đã tồn tại", HttpStatus.CONFLICT),
    CUAHANG_UNABLE_TO_SAVE(4003, "Không thể lưu cửa hàng", HttpStatus.INTERNAL_SERVER_ERROR),
    CUAHANG_UNABLE_TO_UPDATE(4004, "Không thể cập nhật cửa hàng", HttpStatus.INTERNAL_SERVER_ERROR),
    CUAHANG_UNABLE_TO_DELETE(4005, "Không thể xóa cửa hàng", HttpStatus.INTERNAL_SERVER_ERROR),
    //Field CuaHang error
    CUAHANG_INVALID_NAME(4101, "Tên cửa hàng không hợp lệ", HttpStatus.BAD_REQUEST),
    CUAHANG_INVALID_ADDRESS(4102, "Địa chỉ cửa hàng không hợp lệ", HttpStatus.BAD_REQUEST),
    CUAHANG_INVALID_MANAGER(4103, "Mã quản lý không hợp lệ", HttpStatus.BAD_REQUEST),

    //Kho-related errors
    KHO_NOT_FOUND(5001, "Kho không tìm thấy", HttpStatus.NOT_FOUND),
    KHO_ALREADY_EXISTS(5002, "Kho đã tồn tại", HttpStatus.CONFLICT),
    KHO_UNABLE_TO_SAVE(5003, "Không thể lưu kho", HttpStatus.INTERNAL_SERVER_ERROR),
    KHO_UNABLE_TO_UPDATE(5004, "Không thể cập nhật kho", HttpStatus.INTERNAL_SERVER_ERROR),
    KHO_UNABLE_TO_DELETE(5005, "Không thể xóa kho", HttpStatus.INTERNAL_SERVER_ERROR),
    //Field Kho error
    KHO_INVALID_NAME(5101, "Tên kho không hợp lệ", HttpStatus.BAD_REQUEST),
    KHO_INVALID_ADDRESS(5102, "Địa chỉ kho không hợp lệ", HttpStatus.BAD_REQUEST),

    //TonKho-related errors
    TONKHO_NOT_FOUND(6001, "Tồn kho không tìm thấy", HttpStatus.NOT_FOUND),
    TONKHO_ALREADY_EXISTS(6002, "Tồn kho đã tồn tại", HttpStatus.CONFLICT),
    TONKHO_UNABLE_TO_SAVE(6003, "Không thể lưu tồn kho", HttpStatus.INTERNAL_SERVER_ERROR),
    TONKHO_UNABLE_TO_UPDATE(6004, "Không thể cập nhật tồn kho", HttpStatus.INTERNAL_SERVER_ERROR),
    TONKHO_UNABLE_TO_DELETE(6005, "Không thể xóa tồn kho", HttpStatus.INTERNAL_SERVER_ERROR),
    //Field TonKho error
    TONKHO_INVALID_QUANTITY(6101, "Số lượng tồn kho không hợp lệ", HttpStatus.BAD_REQUEST),

    //SanPham-related errors
    SANPHAM_NOT_FOUND(7001, "Sản phẩm không tìm thấy", HttpStatus.NOT_FOUND),
    SANPHAM_ALREADY_EXISTS(7002, "Sản phẩm đã tồn tại", HttpStatus.CONFLICT),
    SANPHAM_UNABLE_TO_SAVE(7003, "Không thể lưu sản phẩm", HttpStatus.INTERNAL_SERVER_ERROR),
    SANPHAM_UNABLE_TO_UPDATE(7004, "Không thể cập nhật sản phẩm", HttpStatus.INTERNAL_SERVER_ERROR),
    SANPHAM_UNABLE_TO_DELETE(7005, "Không thể xóa sản phẩm", HttpStatus.INTERNAL_SERVER_ERROR),
    //Field SanPham error
    SANPHAM_INVALID_NAME(7101, "Tên sản phẩm không hợp lệ", HttpStatus.BAD_REQUEST),
    SANPHAM_INVALID_DESCRIPTION(7102, "Mô tả sản phẩm không hợp lệ", HttpStatus.BAD_REQUEST),
    SANPHAM_INVALID_PRICE(7103, "Giá sản phẩm không hợp lệ", HttpStatus.BAD_REQUEST),
    SANPHAM_INVALID_STATUS(7104, "Trạng thái sản phẩm không hợp lệ", HttpStatus.BAD_REQUEST),
    SANPHAM_INVALID_GENDER(7105, "Giới tính của sản phẩm không hợp lệ", HttpStatus.BAD_REQUEST),
    SANPHAM_INVALID_IMAGE(7106, "Hình ảnh sản phẩm không hợp lệ", HttpStatus.BAD_REQUEST),

    //LoaiSanPham-related errors
    LOAISANPHAM_NOT_FOUND(8001, "Loại sản phẩm không tìm thấy", HttpStatus.NOT_FOUND),
    LOAISANPHAM_ALREADY_EXISTS(8002, "Loại sản phẩm đã tồn tại", HttpStatus.CONFLICT),
    LOAISANPHAM_UNABLE_TO_SAVE(8003, "Không thể lưu loại sản phẩm", HttpStatus.INTERNAL_SERVER_ERROR),
    LOAISANPHAM_UNABLE_TO_UPDATE(8004, "Không thể cập nhật loại sản phẩm", HttpStatus.INTERNAL_SERVER_ERROR),
    LOAISANPHAM_UNABLE_TO_DELETE(8005, "Không thể xóa loại sản phẩm", HttpStatus.INTERNAL_SERVER_ERROR),
    //Field LoaiSanPham error
    LOAISANPHAM_INVALID_NAME(8101, "Tên loại sản phẩm không hợp lệ", HttpStatus.BAD_REQUEST),

    //Size-related errors
    SIZE_NOT_FOUND(9001, "Size không tìm thấy", HttpStatus.NOT_FOUND),
    SIZE_ALREADY_EXISTS(9002, "Size đã tồn tại", HttpStatus.CONFLICT),
    SIZE_UNABLE_TO_SAVE(9003, "Không thể lưu size", HttpStatus.INTERNAL_SERVER_ERROR),
    SIZE_UNABLE_TO_UPDATE(9004, "Không thể cập nhật size", HttpStatus.INTERNAL_SERVER_ERROR),
    SIZE_UNABLE_TO_DELETE(9005, "Không thể xóa size", HttpStatus.INTERNAL_SERVER_ERROR),
    //Field Size error
    SIZE_INVALID_VALUE(9101, "Giá trị size không hợp lệ", HttpStatus.BAD_REQUEST),

    //Image-related errors
    IMAGE_NOT_FOUND(10001, "Hình ảnh không tìm thấy", HttpStatus.NOT_FOUND),
    IMAGE_ALREADY_EXISTS(10002, "Hình ảnh đã tồn tại", HttpStatus.CONFLICT),
    IMAGE_UNABLE_TO_SAVE(10003, "Không thể lưu hình ảnh", HttpStatus.INTERNAL_SERVER_ERROR),
    IMAGE_UNABLE_TO_UPDATE(10004, "Không thể cập nhật hình ảnh", HttpStatus.INTERNAL_SERVER_ERROR),
    IMAGE_UNABLE_TO_DELETE(10005, "Không thể xóa hình ảnh", HttpStatus.INTERNAL_SERVER_ERROR),
    //Field Image error
    IMAGE_INVALID_URL(10101, "URL hình ảnh không hợp lệ", HttpStatus.BAD_REQUEST),

    //NhaCungCap-related errors
    NHACUNGCAP_NOT_FOUND(11001, "Nhà cung cấp không tìm thấy", HttpStatus.NOT_FOUND),
    NHACUNGCAP_ALREADY_EXISTS(11002, "Nhà cung cấp đã tồn tại", HttpStatus.CONFLICT),
    NHACUNGCAP_UNABLE_TO_SAVE(11003, "Không thể lưu nhà cung cấp", HttpStatus.INTERNAL_SERVER_ERROR),
    NHACUNGCAP_UNABLE_TO_UPDATE(11004, "Không thể cập nhật nhà cung cấp", HttpStatus.INTERNAL_SERVER_ERROR),
    NHACUNGCAP_UNABLE_TO_DELETE(11005, "Không thể xóa nhà cung cấp", HttpStatus.INTERNAL_SERVER_ERROR),
    //Field NhaCungCap error
    NHACUNGCAP_INVALID_NAME(11101, "Tên nhà cung cấp không hợp lệ", HttpStatus.BAD_REQUEST),
    NHACUNGCAP_INVALID_PHONE(11102, "Số điện thoại không hợp lệ", HttpStatus.BAD_REQUEST),

    //PhieuNhapKho-related errors
    PHIEUNHAPKHO_NOT_FOUND(12001, "Phiếu nhập kho không tìm thấy", HttpStatus.NOT_FOUND),
    PHIEUNHAPKHO_ALREADY_EXISTS(12002, "Phiếu nhập kho đã tồn tại", HttpStatus.CONFLICT),
    PHIEUNHAPKHO_UNABLE_TO_SAVE(12003, "Không thể lưu phiếu nhập kho", HttpStatus.INTERNAL_SERVER_ERROR),
    PHIEUNHAPKHO_UNABLE_TO_UPDATE(12004, "Không thể cập nhật phiếu nhập kho", HttpStatus.INTERNAL_SERVER_ERROR),
    PHIEUNHAPKHO_UNABLE_TO_DELETE(12005, "Không thể xóa phiếu nhập kho", HttpStatus.INTERNAL_SERVER_ERROR),
    //Field PhieuNhapKho error
    PHIEUNHAPKHO_INVALID_TOTAL_PRICE(12101, "Tổng giá nhập không hợp lệ", HttpStatus.BAD_REQUEST),
    PHIEUNHAPKHO_INVALID_DATE(12102, "Thời gian nhập không hợp lệ", HttpStatus.BAD_REQUEST),

    //ChiTietPhieuNhap-related errors
    CHITIETPHIEUNHAP_NOT_FOUND(13001, "Chi tiết phiếu nhập không tìm thấy", HttpStatus.NOT_FOUND),
    CHITIETPHIEUNHAP_ALREADY_EXISTS(13002, "Chi tiết phiếu nhập đã tồn tại", HttpStatus.CONFLICT),
    CHITIETPHIEUNHAP_UNABLE_TO_SAVE(13003, "Không thể lưu chi tiết phiếu nhập", HttpStatus.INTERNAL_SERVER_ERROR),
    CHITIETPHIEUNHAP_UNABLE_TO_UPDATE(13004, "Không thể cập nhật chi tiết phiếu nhập", HttpStatus.INTERNAL_SERVER_ERROR),
    CHITIETPHIEUNHAP_UNABLE_TO_DELETE(13005, "Không thể xóa chi tiết phiếu nhập", HttpStatus.INTERNAL_SERVER_ERROR),
    //Field ChiTietPhieuNhap error
    CHITIETPHIEUNHAP_INVALID_QUANTITY(13101, "Số lượng không hợp lệ", HttpStatus.BAD_REQUEST),
    CHITIETPHIEUNHAP_INVALID_IMPORT_PRICE(13102, "Giá nhập không hợp lệ", HttpStatus.BAD_REQUEST),
    CHITIETPHIEUNHAP_INVALID_SELLING_PRICE(13103, "Giá bán không hợp lệ", HttpStatus.BAD_REQUEST),

    //DonHang-related errors
    DONHANG_NOT_FOUND(14001, "Đơn hàng không tìm thấy", HttpStatus.NOT_FOUND),
    DONHANG_ALREADY_EXISTS(14002, "Đơn hàng đã tồn tại", HttpStatus.CONFLICT),
    DONHANG_UNABLE_TO_SAVE(14003, "Không thể lưu đơn hàng", HttpStatus.INTERNAL_SERVER_ERROR),
    DONHANG_UNABLE_TO_UPDATE(14004, "Không thể cập nhật đơn hàng", HttpStatus.INTERNAL_SERVER_ERROR),
    DONHANG_UNABLE_TO_DELETE(14005, "Không thể xóa đơn hàng", HttpStatus.INTERNAL_SERVER_ERROR),
    //Field DonHang error
    DONHANG_INVALID_DATE(14101, "Thời gian bán không hợp lệ", HttpStatus.BAD_REQUEST),
    DONHANG_INVALID_TOTAL_VALUE(14102, "Tổng giá trị không hợp lệ", HttpStatus.BAD_REQUEST),
    DONHANG_INVALID_DISCOUNT(14103, "Giá giảm không hợp lệ", HttpStatus.BAD_REQUEST),
    DONHANG_INVALID_STATUS(14104, "Trạng thái không hợp lệ", HttpStatus.BAD_REQUEST),
    DONHANG_INVALID_PAYMENT_METHOD(14105, "Phương thức thanh toán không hợp lệ", HttpStatus.BAD_REQUEST),

    //ChiTietDonHang-related errors
    CHITIETDONHANG_NOT_FOUND(15001, "Chi tiết đơn hàng không tìm thấy", HttpStatus.NOT_FOUND),
    CHITIETDONHANG_ALREADY_EXISTS(15002, "Chi tiết đơn hàng đã tồn tại", HttpStatus.CONFLICT),
    CHITIETDONHANG_UNABLE_TO_SAVE(15003, "Không thể lưu chi tiết đơn hàng", HttpStatus.INTERNAL_SERVER_ERROR),
    CHITIETDONHANG_UNABLE_TO_UPDATE(15004, "Không thể cập nhật chi tiết đơn hàng", HttpStatus.INTERNAL_SERVER_ERROR),
    CHITIETDONHANG_UNABLE_TO_DELETE(15005, "Không thể xóa chi tiết đơn hàng", HttpStatus.INTERNAL_SERVER_ERROR),
    //Field ChiTietDonHang error
    CHITIETDONHANG_INVALID_QUANTITY(15101, "Số lượng không hợp lệ", HttpStatus.BAD_REQUEST),
    CHITIETDONHANG_INVALID_PRICE(15102, "Đơn giá không hợp lệ", HttpStatus.BAD_REQUEST),

    //GioHang-related errors
    GIOHANG_NOT_FOUND(16001, "Giỏ hàng không tìm thấy", HttpStatus.NOT_FOUND),
    GIOHANG_ALREADY_EXISTS(16002, "Giỏ hàng đã tồn tại", HttpStatus.CONFLICT),
    GIOHANG_UNABLE_TO_SAVE(16003, "Không thể lưu giỏ hàng", HttpStatus.INTERNAL_SERVER_ERROR),
    GIOHANG_UNABLE_TO_UPDATE(16004, "Không thể cập nhật giỏ hàng", HttpStatus.INTERNAL_SERVER_ERROR),
    GIOHANG_UNABLE_TO_DELETE(16005, "Không thể xóa giỏ hàng", HttpStatus.INTERNAL_SERVER_ERROR),
    //Field GioHang error
    GIOHANG_INVALID_QUANTITY(16101, "Số lượng không hợp lệ", HttpStatus.BAD_REQUEST),
    GIOHANG_INVALID_PRICE(16102, "Đơn giá không hợp lệ", HttpStatus.BAD_REQUEST),
    GIOHANG_INVALID_TOTAL(16103, "Tổng cộng không hợp lệ", HttpStatus.BAD_REQUEST),

    //ChamCong-related errors
    CHAMCONG_NOT_FOUND(17001, "Chấm công không tìm thấy", HttpStatus.NOT_FOUND),
    CHAMCONG_ALREADY_EXISTS(17002, "Chấm công đã tồn tại", HttpStatus.CONFLICT),
    CHAMCONG_UNABLE_TO_SAVE(17003, "Không thể lưu chấm công", HttpStatus.INTERNAL_SERVER_ERROR),
    CHAMCONG_UNABLE_TO_UPDATE(17004, "Không thể cập nhật chấm công", HttpStatus.INTERNAL_SERVER_ERROR),
    CHAMCONG_UNABLE_TO_DELETE(17005, "Không thể xóa chấm công", HttpStatus.INTERNAL_SERVER_ERROR),
    //Field ChamCong error
    CHAMCONG_INVALID_CHECK_IN_TIME(17101, "Thời gian vào không hợp lệ", HttpStatus.BAD_REQUEST),
    CHAMCONG_INVALID_CHECK_OUT_TIME(17102, "Thời gian ra không hợp lệ", HttpStatus.BAD_REQUEST),
    CHAMCONG_INVALID_WORKING_HOURS(17103, "Tổng giờ làm không hợp lệ", HttpStatus.BAD_REQUEST),
    CHAMCONG_INVALID_STATUS(17104, "Trạng thái không hợp lệ", HttpStatus.BAD_REQUEST),

    //BangLuong-related errors
    BANGLUONG_NOT_FOUND(18001, "Bảng lương không tìm thấy", HttpStatus.NOT_FOUND),
    BANGLUONG_ALREADY_EXISTS(18002, "Bảng lương đã tồn tại", HttpStatus.CONFLICT),
    BANGLUONG_UNABLE_TO_SAVE(18003, "Không thể lưu bảng lương", HttpStatus.INTERNAL_SERVER_ERROR),
    BANGLUONG_UNABLE_TO_UPDATE(18004, "Không thể cập nhật bảng lương", HttpStatus.INTERNAL_SERVER_ERROR),
    BANGLUONG_UNABLE_TO_DELETE(18005, "Không thể xóa bảng lương", HttpStatus.INTERNAL_SERVER_ERROR),
    //Field BangLuong error
    BANGLUONG_INVALID_MONTH(18101, "Tháng tính lương không hợp lệ", HttpStatus.BAD_REQUEST),
    BANGLUONG_INVALID_YEAR(18102, "Năm tính lương không hợp lệ", HttpStatus.BAD_REQUEST),
    BANGLUONG_INVALID_BASE_SALARY(18103, "Lương cơ bản không hợp lệ", HttpStatus.BAD_REQUEST),
    BANGLUONG_INVALID_NET_SALARY(18104, "Thực nhận không hợp lệ", HttpStatus.BAD_REQUEST),
    BANGLUONG_INVALID_COMMISSION(18105, "Tổng hoa hồng không hợp lệ", HttpStatus.BAD_REQUEST),
    BANGLUONG_INVALID_WORKING_HOURS(18106, "Tổng giờ làm không hợp lệ", HttpStatus.BAD_REQUEST),
    BANGLUONG_INVALID_SALARY_PERIOD(18107, "Quý tính lương không hợp lệ", HttpStatus.BAD_REQUEST),
    BANGLUONG_INVALID_DEDUCTION(18108, "Khấu trừ không hợp lệ", HttpStatus.BAD_REQUEST),

    //Jwt token-related error
    JWT_INVALID(19001, "JWT token không hợp lệ", HttpStatus.UNAUTHORIZED),
    JWT_EXPIRED(19002, "JWT token đã hết hạn", HttpStatus.UNAUTHORIZED),
    JWT_MALFORMED(19003, "JWT token bị sai định dạng", HttpStatus.UNAUTHORIZED),
    ;

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    /**
     * Constructor for ErrorCode.
     *
     * @param code       the error code
     * @param message    the error message
     * @param statusCode the corresponding HTTP status code
     */
    Error(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

}