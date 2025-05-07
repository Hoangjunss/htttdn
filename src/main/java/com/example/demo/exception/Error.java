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
    //User-related errors
    USER_NOT_FOUND(1001, "User not found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS(1002, "User already exists", HttpStatus.CONFLICT),
    USER_UNABLE_TO_SAVE(1003, "Unable to save user", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_UNABLE_TO_UPDATE(1004, "Unable to update user", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_UNABLE_TO_DELETE(1005, "Unable to delete user", HttpStatus.INTERNAL_SERVER_ERROR),
    ACCOUNT_LOCKED(1006,"account locked",HttpStatus.INTERNAL_SERVER_ERROR),
        //Field User error
        USER_INVALID_EMAIL(1101, "Invalid email", HttpStatus.BAD_REQUEST),
        USER_INVALID_PASSWORD(1102, "Invalid password", HttpStatus.BAD_REQUEST),
        USER_INVALID_NAME(1103, "Invalid first name", HttpStatus.BAD_REQUEST),
    //Role error
    ROLE_NOT_FOUND(1201, "Role not found", HttpStatus.NOT_FOUND),
    ROLE_ALREADY_EXISTS(1202, "Role already exists", HttpStatus.CONFLICT),
    ROLE_UNABLE_TO_SAVE(1203, "Unable to save role", HttpStatus.INTERNAL_SERVER_ERROR),
    ROLE_UNABLE_TO_UPDATE(1204, "Unable to update role", HttpStatus.INTERNAL_SERVER_ERROR),
    ROLE_UNABLE_TO_DELETE(1205, "Unable to delete role", HttpStatus.INTERNAL_SERVER_ERROR),
        //Field Role error
        ROLE_INVALID_ROLE(1206, "Invalid name", HttpStatus.BAD_REQUEST),
    //Supply error codes
    SUPPLY_NOT_FOUND(1301, "Supply not found", HttpStatus.NOT_FOUND),
    SUPPLY_ALREADY_EXISTS(1302, "Supply already exists", HttpStatus.CONFLICT),
    SUPPLY_UNABLE_TO_SAVE(1303, "Unable to save supply", HttpStatus.INTERNAL_SERVER_ERROR),
    SUPPLY_UNABLE_TO_UPDATE(1304, "Unable to update supply", HttpStatus.INTERNAL_SERVER_ERROR),
    SUPPLY_UNABLE_TO_DELETE(1305, "Unable to delete supply", HttpStatus.INTERNAL_SERVER_ERROR),
        //Field Supply error
        SUPPLY_INVALID_SUPPLY_NAME(1306, "Invalid supply name", HttpStatus.BAD_REQUEST),
    // ProductSale-related errors
    PRODUCTSALE_NOT_FOUND(2001, "ProductSale not found", HttpStatus.NOT_FOUND),
    PRODUCTSALE_ALREADY_EXISTS(2002, "ProductSale already exists", HttpStatus.CONFLICT),
    PRODUCTSALE_UNABLE_TO_SAVE(2003, "Unable to save ProductSale", HttpStatus.INTERNAL_SERVER_ERROR),
    PRODUCTSALE_UNABLE_TO_UPDATE(2004, "Unable to update ProductSale", HttpStatus.INTERNAL_SERVER_ERROR),
    PRODUCTSALE_UNABLE_TO_DELETE(2005, "Unable to delete ProductSale", HttpStatus.INTERNAL_SERVER_ERROR),
        // Field ProductSale errors
        PRODUCTSALE_INVALID_QUANTITY(2101, "Invalid quantity", HttpStatus.BAD_REQUEST),
        PRODUCTSALE_INVALID_STATUS(2102, "Invalid status", HttpStatus.BAD_REQUEST),
    // Product-related errors
    PRODUCT_NOT_FOUND(3001, "Product not found", HttpStatus.NOT_FOUND),
    PRODUCT_ALREADY_EXISTS(3002, "Product already exists", HttpStatus.CONFLICT),
    PRODUCT_UNABLE_TO_SAVE(3003, "Unable to save product", HttpStatus.INTERNAL_SERVER_ERROR),
    PRODUCT_UNABLE_TO_UPDATE(3004, "Unable to update product", HttpStatus.INTERNAL_SERVER_ERROR),
    PRODUCT_UNABLE_TO_DELETE(3005, "Unable to delete product", HttpStatus.INTERNAL_SERVER_ERROR),
        // Field Product errors
        PRODUCT_INVALID_NAME(3101, "Invalid product name", HttpStatus.BAD_REQUEST),
        PRODUCT_INVALID_DESCRIPTION(3102, "Invalid product description", HttpStatus.BAD_REQUEST),
        PRODUCT_INVALID_AUTHOR(3103, "Invalid product author", HttpStatus.BAD_REQUEST),
        PRODUCT_INVALID_PAGE(3104, "Invalid number of pages", HttpStatus.BAD_REQUEST),
        PRODUCT_INVALID_DATE_PUBLIC(3105, "Invalid date", HttpStatus.BAD_REQUEST),
        PRODUCT_INVALID_STATUS(3106, "Invalid status", HttpStatus.BAD_REQUEST),
        PRODUCT_INVALID_SIZE(3107, "Invalid size", HttpStatus.BAD_REQUEST),
    // Image-related errors
    IMAGE_NOT_FOUND(4001, "Image not found", HttpStatus.NOT_FOUND),
    IMAGE_ALREADY_EXISTS(4002, "Image already exists", HttpStatus.CONFLICT),
    IMAGE_UNABLE_TO_SAVE(4003, "Unable to save image", HttpStatus.INTERNAL_SERVER_ERROR),
    IMAGE_UNABLE_TO_UPDATE(4004, "Unable to update image", HttpStatus.INTERNAL_SERVER_ERROR),
    IMAGE_UNABLE_TO_DELETE(4005, "Unable to delete image", HttpStatus.INTERNAL_SERVER_ERROR),
        // Field Image errors
        IMAGE_INVALID_URL(4101, "Invalid image URL", HttpStatus.BAD_REQUEST),
    // Category-related errors
    CATEGORY_NOT_FOUND(5001, "Category not found", HttpStatus.NOT_FOUND),
    CATEGORY_ALREADY_EXISTS(5002, "Category already exists", HttpStatus.CONFLICT),
    CATEGORY_UNABLE_TO_SAVE(5003, "Unable to save category", HttpStatus.INTERNAL_SERVER_ERROR),
    CATEGORY_UNABLE_TO_UPDATE(5004, "Unable to update category", HttpStatus.INTERNAL_SERVER_ERROR),
    CATEGORY_UNABLE_TO_DELETE(5005, "Unable to delete category", HttpStatus.INTERNAL_SERVER_ERROR),
        // Field Category errors
        CATEGORY_INVALID_NAME(5101, "Invalid category name", HttpStatus.BAD_REQUEST),
    // Warehouse-related errors
    WAREHOUSE_NOT_FOUND(6001, "Warehouse not found", HttpStatus.NOT_FOUND),
    WAREHOUSE_ALREADY_EXISTS(6002, "Warehouse already exists", HttpStatus.CONFLICT),
    WAREHOUSE_UNABLE_TO_SAVE(6003, "Unable to save warehouse", HttpStatus.INTERNAL_SERVER_ERROR),
    WAREHOUSE_UNABLE_TO_UPDATE(6004, "Unable to update warehouse", HttpStatus.INTERNAL_SERVER_ERROR),
    WAREHOUSE_UNABLE_TO_DELETE(6005, "Unable to delete warehouse", HttpStatus.INTERNAL_SERVER_ERROR),
        // Field Warehouse errors
        WAREHOUSE_INVALID_QUANTITY(6101, "Invalid quantity", HttpStatus.BAD_REQUEST),
        WAREHOUSE_INVALID_PRICE(6102, "Invalid price", HttpStatus.BAD_REQUEST),
        WAREHOUSE_INVALID_STATUS(6103, "Invalid status", HttpStatus.BAD_REQUEST),
        WAREHOUSE_INVALID_DATE(6104, "Invalid date", HttpStatus.BAD_REQUEST),
    // WarehouseReceipt-related errors
    WAREHOUSERECEIPT_NOT_FOUND(7001, "WarehouseReceipt not found", HttpStatus.NOT_FOUND),
    WAREHOUSERECEIPT_ALREADY_EXISTS(7002, "WarehouseReceipt already exists", HttpStatus.CONFLICT),
    WAREHOUSERECEIPT_UNABLE_TO_SAVE(7003, "Unable to save WarehouseReceipt", HttpStatus.INTERNAL_SERVER_ERROR),
    WAREHOUSERECEIPT_UNABLE_TO_UPDATE(7004, "Unable to update WarehouseReceipt", HttpStatus.INTERNAL_SERVER_ERROR),
    WAREHOUSERECEIPT_UNABLE_TO_DELETE(7005, "Unable to delete WarehouseReceipt", HttpStatus.INTERNAL_SERVER_ERROR),
        // Field WarehouseReceipt errors
        WAREHOUSERECEIPT_INVALID_QUANTITY(7101, "Invalid quantity", HttpStatus.BAD_REQUEST),
        WAREHOUSERECEIPT_INVALID_TOTALPRICE(7102, "Invalid total price", HttpStatus.BAD_REQUEST),
        WAREHOUSERECEIPT_INVALID_DATE(7103, "Invalid date", HttpStatus.BAD_REQUEST),
    // WarehouseReceiptDetail-related errors
    WAREHOUSERECEIPTDETAIL_NOT_FOUND(8001, "WarehouseReceiptDetail not found", HttpStatus.NOT_FOUND),
    WAREHOUSERECEIPTDETAIL_ALREADY_EXISTS(8002, "WarehouseReceiptDetail already exists", HttpStatus.CONFLICT),
    WAREHOUSERECEIPTDETAIL_UNABLE_TO_SAVE(8003, "Unable to save WarehouseReceiptDetail", HttpStatus.INTERNAL_SERVER_ERROR),
    WAREHOUSERECEIPTDETAIL_UNABLE_TO_UPDATE(8004, "Unable to update WarehouseReceiptDetail", HttpStatus.INTERNAL_SERVER_ERROR),
    WAREHOUSERECEIPTDETAIL_UNABLE_TO_DELETE(8005, "Unable to delete WarehouseReceiptDetail", HttpStatus.INTERNAL_SERVER_ERROR),
        // Field WarehouseReceiptDetail errors
        WAREHOUSERECEIPTDETAIL_INVALID_QUANTITY(8101, "Invalid quantity", HttpStatus.BAD_REQUEST),
        WAREHOUSERECEIPTDETAIL_INVALID_UNITPRICE(8102, "Invalid unit price", HttpStatus.BAD_REQUEST),
        WAREHOUSERECEIPTDETAIL_INVALID_TOTALPRICE(8103, "Invalid total price", HttpStatus.BAD_REQUEST),
    // Voucher-related errors
    VOUCHER_NOT_FOUND(9001, "Voucher not found", HttpStatus.NOT_FOUND),
    VOUCHER_ALREADY_EXISTS(9002, "Voucher already exists", HttpStatus.CONFLICT),
    VOUCHER_UNABLE_TO_SAVE(9003, "Unable to save voucher", HttpStatus.INTERNAL_SERVER_ERROR),
    VOUCHER_UNABLE_TO_UPDATE(9004, "Unable to update voucher", HttpStatus.INTERNAL_SERVER_ERROR),
    VOUCHER_UNABLE_TO_DELETE(9005, "Unable to delete voucher", HttpStatus.INTERNAL_SERVER_ERROR),
        // Field Voucher errors
        VOUCHER_INVALID_NAME(9101, "Invalid voucher name", HttpStatus.BAD_REQUEST),
        VOUCHER_INVALID_PERCENT(9102, "Invalid voucher percent", HttpStatus.BAD_REQUEST),
        VOUCHER_INVALID_START_DATE(9103, "Invalid voucher start date", HttpStatus.BAD_REQUEST),
        VOUCHER_INVALID_END_DATE(9104, "Invalid voucher end date", HttpStatus.BAD_REQUEST),
        VOUCHER_INVALID_PRODUCTS(9105, "Invalid products associated with voucher", HttpStatus.BAD_REQUEST),
        VOUCHER_INVALID_USERS(9106, "Invalid users associated with voucher", HttpStatus.BAD_REQUEST),
    // Address-related errors
    ADDRESS_NOT_FOUND(10001, "Address not found", HttpStatus.NOT_FOUND),
    ADDRESS_ALREADY_EXISTS(10002, "Address already exists", HttpStatus.CONFLICT),
    ADDRESS_UNABLE_TO_SAVE(10003, "Unable to save address", HttpStatus.INTERNAL_SERVER_ERROR),
    ADDRESS_UNABLE_TO_UPDATE(10004, "Unable to update address", HttpStatus.INTERNAL_SERVER_ERROR),
    ADDRESS_UNABLE_TO_DELETE(10005, "Unable to delete address", HttpStatus.INTERNAL_SERVER_ERROR),
        // Field Address errors
        ADDRESS_INVALID_ADDRESS(10101, "Invalid address", HttpStatus.BAD_REQUEST),
        ADDRESS_INVALID_PHONE(10102, "Invalid phone number", HttpStatus.BAD_REQUEST),
    // Orders-related errors
    ORDERS_NOT_FOUND(11001, "Order not found", HttpStatus.NOT_FOUND),
    ORDERS_ALREADY_EXISTS(11002, "Order already exists", HttpStatus.CONFLICT),
    ORDERS_UNABLE_TO_SAVE(11003, "Unable to save order", HttpStatus.INTERNAL_SERVER_ERROR),
    ORDERS_UNABLE_TO_UPDATE(11004, "Unable to update order", HttpStatus.INTERNAL_SERVER_ERROR),
    ORDERS_UNABLE_TO_DELETE(11005, "Unable to delete order", HttpStatus.INTERNAL_SERVER_ERROR),
        // Field Orders errors
        ORDERS_INVALID_USER(11101, "Invalid user associated with order", HttpStatus.BAD_REQUEST),
        ORDERS_INVALID_QUANTITY(11102, "Invalid quantity", HttpStatus.BAD_REQUEST),
        ORDERS_INVALID_TOTAL_PRICE(11103, "Invalid total price", HttpStatus.BAD_REQUEST),
        ORDERS_INVALID_ADDRESS(11104, "Invalid address associated with order", HttpStatus.BAD_REQUEST),
    // OrderDetail-related errors
    ORDERDETAIL_NOT_FOUND(12001, "OrderDetail not found", HttpStatus.NOT_FOUND),
    ORDERDETAIL_ALREADY_EXISTS(12002, "OrderDetail already exists", HttpStatus.CONFLICT),
    ORDERDETAIL_UNABLE_TO_SAVE(12003, "Unable to save OrderDetail", HttpStatus.INTERNAL_SERVER_ERROR),
    ORDERDETAIL_UNABLE_TO_UPDATE(12004, "Unable to update OrderDetail", HttpStatus.INTERNAL_SERVER_ERROR),
    ORDERDETAIL_UNABLE_TO_DELETE(12005, "Unable to delete OrderDetail", HttpStatus.INTERNAL_SERVER_ERROR),
        // Field OrderDetail errors
        ORDERDETAIL_INVALID_ORDER(12101, "Invalid order associated with OrderDetail", HttpStatus.BAD_REQUEST),
        ORDERDETAIL_INVALID_PRODUCT(12102, "Invalid product associated with OrderDetail", HttpStatus.BAD_REQUEST),
        ORDERDETAIL_INVALID_QUANTITY(12103, "Invalid quantity", HttpStatus.BAD_REQUEST),
        ORDERDETAIL_INVALID_UNIT_PRICE(12104, "Invalid unit price", HttpStatus.BAD_REQUEST),
        ORDERDETAIL_INVALID_TOTAL_PRICE(12105, "Invalid total price", HttpStatus.BAD_REQUEST),
        ORDERDETAIL_INVALID_PRODUCTSALE(12106, "Quantity Long to Quantity product sale ", HttpStatus.BAD_REQUEST),
    // Invoice-related errors
    INVOICE_NOT_FOUND(13001, "Invoice not found", HttpStatus.NOT_FOUND),
    INVOICE_ALREADY_EXISTS(13002, "Invoice already exists", HttpStatus.CONFLICT),
    INVOICE_UNABLE_TO_SAVE(13003, "Unable to save invoice", HttpStatus.INTERNAL_SERVER_ERROR),
    INVOICE_UNABLE_TO_UPDATE(13004, "Unable to update invoice", HttpStatus.INTERNAL_SERVER_ERROR),
    INVOICE_UNABLE_TO_DELETE(13005, "Unable to delete invoice", HttpStatus.INTERNAL_SERVER_ERROR),
        // Field Invoice errors
        INVOICE_INVALID_USER(13101, "Invalid user associated with invoice", HttpStatus.BAD_REQUEST),
        INVOICE_INVALID_QUANTITY(13102, "Invalid quantity", HttpStatus.BAD_REQUEST),
        INVOICE_INVALID_TOTAL_PRICE(13103, "Invalid total price", HttpStatus.BAD_REQUEST),
        INVOICE_INVALID_ADDRESS(13104, "Invalid address associated with invoice", HttpStatus.BAD_REQUEST),
    // InvoiceDetail-related errors
    INVOICEDETAIL_NOT_FOUND(14001, "InvoiceDetail not found", HttpStatus.NOT_FOUND),
    INVOICEDETAIL_ALREADY_EXISTS(14002, "InvoiceDetail already exists", HttpStatus.CONFLICT),
    INVOICEDETAIL_UNABLE_TO_SAVE(14003, "Unable to save InvoiceDetail", HttpStatus.INTERNAL_SERVER_ERROR),
    INVOICEDETAIL_UNABLE_TO_UPDATE(14004, "Unable to update InvoiceDetail", HttpStatus.INTERNAL_SERVER_ERROR),
    INVOICEDETAIL_UNABLE_TO_DELETE(14005, "Unable to delete InvoiceDetail", HttpStatus.INTERNAL_SERVER_ERROR),
        // Field InvoiceDetail errors
        INVOICEDETAIL_INVALID_INVOICE(14101, "Invalid invoice associated with InvoiceDetail", HttpStatus.BAD_REQUEST),
        INVOICEDETAIL_INVALID_PRODUCT(14102, "Invalid product associated with InvoiceDetail", HttpStatus.BAD_REQUEST),
        INVOICEDETAIL_INVALID_QUANTITY(14103, "Invalid quantity", HttpStatus.BAD_REQUEST),
        INVOICEDETAIL_INVALID_UNIT_PRICE(14104, "Invalid unit price", HttpStatus.BAD_REQUEST),
        INVOICEDETAIL_INVALID_TOTAL_PRICE(14105, "Invalid total price", HttpStatus.BAD_REQUEST),
    // Cart-related errors
    CART_NOT_FOUND(15001, "Cart not found", HttpStatus.NOT_FOUND),
    CART_ALREADY_EXISTS(15002, "Cart already exists", HttpStatus.CONFLICT),
    CART_UNABLE_TO_SAVE(15003, "Unable to save cart", HttpStatus.INTERNAL_SERVER_ERROR),
    CART_UNABLE_TO_UPDATE(15004, "Unable to update cart", HttpStatus.INTERNAL_SERVER_ERROR),
    CART_UNABLE_TO_DELETE(15005, "Unable to delete cart", HttpStatus.INTERNAL_SERVER_ERROR),
        // Field Cart errors
        CART_INVALID_USER(15101, "Invalid user associated with cart", HttpStatus.BAD_REQUEST),
        CART_INVALID_QUANTITY(15102, "Invalid quantity", HttpStatus.BAD_REQUEST),
    // CartDetail-related errors
    CARTDETAIL_NOT_FOUND(16001, "CartDetail not found", HttpStatus.NOT_FOUND),
    CARTDETAIL_ALREADY_EXISTS(16002, "CartDetail already exists", HttpStatus.CONFLICT),
    CARTDETAIL_UNABLE_TO_SAVE(16003, "Unable to save CartDetail", HttpStatus.INTERNAL_SERVER_ERROR),
    CARTDETAIL_UNABLE_TO_UPDATE(16004, "Unable to update CartDetail", HttpStatus.INTERNAL_SERVER_ERROR),
    CARTDETAIL_UNABLE_TO_DELETE(16005, "Unable to delete CartDetail", HttpStatus.INTERNAL_SERVER_ERROR),
        // Field CartDetail errors
        CARTDETAIL_INVALID_PRODUCTSALE(16101, "Invalid ProductSale associated with CartDetail", HttpStatus.BAD_REQUEST),
        CARTDETAIL_INVALID_CART(16102, "Invalid cart associated with CartDetail", HttpStatus.BAD_REQUEST),
        CARTDETAIL_INVALID_QUANTITY(16103, "Invalid quantity", HttpStatus.BAD_REQUEST),
    //Jwt token-related error
    JWT_INVALID(1101, "Invalid JWT token", HttpStatus.UNAUTHORIZED),
    JWT_EXPIRED(1102, "JWT token expired", HttpStatus.UNAUTHORIZED),
    JWT_MALFORMED(1103, "Malformed JWT token", HttpStatus.UNAUTHORIZED),
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
