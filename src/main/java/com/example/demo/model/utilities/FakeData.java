package com.example.demo.model.utilities;

import com.example.demo.entity.ShippingHistory;
import com.example.demo.enums.OrderEnum;

import java.time.LocalDateTime;
import java.util.List;

public class FakeData {

    public static List<ShippingHistory> getParentShippingHistoryTC(String username, Integer billId) {
        LocalDateTime dateTime = LocalDateTime.now();
        return List.of(
                new ShippingHistory(1, billId, OrderEnum.DONE.getValue(), username, username, "Đặt hàng", "Đơn hàng đã được đặt và xác nhận", null, dateTime, dateTime),
                new ShippingHistory(2, billId, OrderEnum.PENDING.getValue(), username, username, "Thanh toán", "Khách hàng đã hoàn tất việc thanh toán cho đơn hàng", null, dateTime.plusDays(1).plusHours(5), dateTime.plusDays(1).plusHours(5)),
                new ShippingHistory(3, billId, OrderEnum.PENDING.getValue(), username, username, "Đang giao hàng", "Đơn hàng đã được chuyển đến đơn vị vận chuyển và đang trên đường tới khách hàng", null, dateTime.plusDays(2).plusHours(5), dateTime.plusDays(2).plusHours(5)),
                new ShippingHistory(4, billId, OrderEnum.PENDING.getValue(), username, username, "Đã nhận hàng", "Khách hàng đã nhận được hàng", null, dateTime.plusDays(3).plusHours(5), dateTime.plusDays(3).plusHours(5)),
                new ShippingHistory(5, billId, OrderEnum.PENDING.getValue(), username, username, "Hoàn thành", "Đơn hàng đã được thanh toán, giao hàng thành công và không có yêu cầu trả hàng hoặc hoàn tiền", null, dateTime.plusDays(4).plusHours(5), dateTime.plusDays(4).plusHours(5))
        );
    }

    public static List<ShippingHistory> getParentShippingHistory(String username, Integer billId) {
        LocalDateTime dateTime = LocalDateTime.now();
        return List.of(
                new ShippingHistory(null, billId, OrderEnum.DONE.getValue(), username, username, "Đặt hàng", "Đơn hàng đã được đặt thanh công", null, dateTime, dateTime),
                new ShippingHistory(null, billId, OrderEnum.PENDING.getValue(), username, username, "Đang xử lý", "Đơn hàng đang chờ xác nhận", null, dateTime.plusHours(2), dateTime.plusHours(2)),
                new ShippingHistory(null, billId, OrderEnum.PENDING.getValue(), username, username, "Đang giao hàng", "Đơn hàng đã được chuyển đến đơn vị vận chuyển và đang trên đường tới khách hàng", null, dateTime.plusHours(3), dateTime.plusHours(4)),
                new ShippingHistory(null, billId, OrderEnum.PENDING.getValue(), username, username, "Đã nhận hàng", "Khách hàng đã nhận được hàng", null, dateTime.plusHours(5), dateTime.plusHours(5)),
                new ShippingHistory(null, billId, OrderEnum.PENDING.getValue(), username, username, "Hoàn thành", "Đơn hàng đã được thanh toán, giao hàng thành công và không có yêu cầu trả hàng hoặc hoàn tiền", null, dateTime.plusHours(6), dateTime.plusHours(6))
        );
    }

    public static List<ShippingHistory> getChildPENDINGTC(String username, Integer billId, int parentId) {
        LocalDateTime dateTime = LocalDateTime.now();
        return List.of(
                new ShippingHistory(null, billId, OrderEnum.DONE.getValue(), username, username, "kiểm tra tồn kho", "Sản phẩm còn hàng trong kho", parentId, dateTime, dateTime),
                new ShippingHistory(null, billId, OrderEnum.DONE.getValue(), username, username, "Đang đóng gói", "Sản phẩm đang được đóng gói để chuẩn bị cho vận chuyển", parentId, dateTime.plusHours(1), dateTime.plusHours(1)),
                new ShippingHistory(null, billId, OrderEnum.DONE.getValue(), username, username, "Chờ xác nhận thanh toán", "Đơn hàng đang chờ xác nhận thanh toán từ cổng thanh toán hoặc ngân hàng", parentId, dateTime.plusHours(2), dateTime.plusHours(2)),
                new ShippingHistory(null, billId, OrderEnum.DONE.getValue(), username, username, "Đang xác nhận đơn hàng", "Đơn hàng đang được xác nhận bởi nhân viên", parentId, dateTime.plusHours(3), dateTime.plusHours(3))
        );
    }

    public static List<ShippingHistory> getChildPAYMENTTC(String username, Integer billId, int parentId) {
        LocalDateTime dateTime = LocalDateTime.now();
        return List.of(
                new ShippingHistory(null, billId, OrderEnum.DONE.getValue(), username, username, "Thanh toán thành công, chờ xác nhận", "Thanh toán đã thành công nhưng vẫn đang chờ xác nhận từ hệ thống", parentId, dateTime.plusDays(1).plusHours(1), dateTime.plusDays(1).plusHours(1)),
                new ShippingHistory(null, billId, OrderEnum.DONE.getValue(), username, username, "Đã nhận thanh toán qua chuyển khoản ngân hàng", " Thanh toán đã được thực hiện thành công qua chuyển khoản ngân hàng", parentId, dateTime.plusDays(1).plusHours(1), dateTime.plusDays(1).plusHours(1))
        );
    }

    public static List<ShippingHistory> getChildPENDING(String username, Integer billId, int parentId) {
        LocalDateTime dateTime = LocalDateTime.now();
        return List.of(
                new ShippingHistory(null, billId, OrderEnum.DONE.getValue(), username, username, "kiểm tra tồn kho", "Sản phẩm còn hàng trong kho", parentId, dateTime.plusDays(2), dateTime.plusDays(2)),
                new ShippingHistory(null, billId, OrderEnum.DONE.getValue(), username, username, "Đang đóng gói", "Sản phẩm đang được đóng gói để chuẩn bị cho vận chuyển", parentId, dateTime.plusDays(2).plusHours(1), dateTime.plusDays(2).plusHours(1)),
                new ShippingHistory(null, billId, OrderEnum.DONE.getValue(), username, username, "Chờ xác nhận thanh toán", "Đơn hàng đang chờ xác nhận thanh toán từ cổng thanh toán hoặc ngân hàng", parentId, dateTime.plusDays(2).plusHours(2), dateTime.plusDays(2).plusHours(2)),
                new ShippingHistory(null, billId, OrderEnum.DONE.getValue(), username, username, "Đang xác nhận đơn hàng", "Đơn hàng đang được xác nhận bởi nhân viên", parentId, dateTime.plusDays(2).plusHours(3), dateTime.plusDays(2).plusHours(3))
        );
    }

    public static List<ShippingHistory> getChildSHIPPING(String username, Integer billId, int parentId) {
        LocalDateTime dateTime = LocalDateTime.now();
        return List.of(
                new ShippingHistory(null, billId, OrderEnum.DONE.getValue(), username, username, "Đã chuyển cho bên vận chuyển", "Đơn hàng đã được chuyển cho đơn vị vận chuyển", parentId, dateTime.plusDays(3), dateTime.plusDays(3)),
                new ShippingHistory(null, billId, OrderEnum.DONE.getValue(), username, username, "Đang giao hàng", "Đơn hàng đã được chuyển đến đơn vị vận chuyển và đang trên đường tới khách hàng", parentId, dateTime.plusDays(3).plusHours(1), dateTime.plusDays(3).plusHours(1))
        );
    }

    public static List<ShippingHistory> getChildRECEIVED(String username, Integer billId, int parentId) {
        LocalDateTime dateTime = LocalDateTime.now();
        return List.of(
                new ShippingHistory(null, billId, OrderEnum.DONE.getValue(), username, username, "Đã nhận hàng", "Khách hàng đã nhận được hàng", parentId, dateTime.plusDays(4), dateTime.plusDays(4)),
                new ShippingHistory(null, billId, OrderEnum.DONE.getValue(), username, username, "Kiểm tra hàng hóa", "Khách hàng đang kiểm tra hàng hóa", parentId, dateTime.plusDays(4).plusHours(1), dateTime.plusDays(4).plusHours(1)),
                new ShippingHistory(null, billId, OrderEnum.DONE.getValue(), username, username, "Hoàn thành", "Đơn hàng đã được thanh toán, giao hàng thành công và không có yêu cầu trả hàng hoặc hoàn tiền", parentId, dateTime.plusDays(4).plusHours(2), dateTime.plusDays(4).plusHours(2))
        );
    }

}
