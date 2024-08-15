package com.example.demo.service.implement;

import com.example.demo.config.exception.BusinessException;
import com.example.demo.entity.*;
import com.example.demo.enums.ErrorCode;
import com.example.demo.enums.OrderEnum;
import com.example.demo.model.info.PaginationInfo;
import com.example.demo.model.request.ApproveBillRequest;
import com.example.demo.model.request.BillsRequest;
import com.example.demo.model.request.GetBillRequest;
import com.example.demo.model.response.BillsResponse;
import com.example.demo.model.response.DetailOrderResponse;
import com.example.demo.model.result.BillResult;
import com.example.demo.model.result.OrderDetailResult;
import com.example.demo.model.result.OrderGeneralResult;
import com.example.demo.model.result.SubOrderResult;
import com.example.demo.model.utilities.CommonUtil;
import com.example.demo.model.utilities.Constant;
import com.example.demo.model.utilities.FakeData;
import com.example.demo.repository.*;
import com.example.demo.service.BillService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class BillServiceImplement implements BillService {
    @PersistenceContext
    private EntityManager entityManager;
    private final BillRepository billRepository;
    private final DetailBillRepository detailBillRepository;
    private final ProductRepository productRepository;
    private final DetailProductRepository detailProductRepository;
    private final ShippingHistoryRepository shippingHistoryRepository;

    @Override
    public BillsResponse getBills(GetBillRequest request, int page, int size, String sortField, String sortType) {
        log.info("getBills: {}", CommonUtil.beanToString(request));
        BillsResponse response = new BillsResponse();
        List<BillResult> results = new ArrayList<>();
//        String code = request.getCode();
//        String numberPhone = request.getNumberPhone();
//        String customerName = request.getCustomerName();
        String status = request.getStatus();
        String keyword = request.getKeyword();
//        if (CommonUtil.isNullOrEmpty(code)) {
//            code = "";
//        }
//        if (CommonUtil.isNullOrEmpty(numberPhone)) {
//            numberPhone = "";
//        }
//        if (CommonUtil.isNullOrEmpty(customerName)) {
//            customerName = "";
        //}
        if (CommonUtil.isNullOrEmpty(keyword)) {
            keyword = "";
        }
        Pageable pageable = null;
        if (CommonUtil.isNullOrEmpty(sortField)) {
            pageable = PageRequest.of(page, size);
        } else {
            Sort sort = Sort.by(Sort.Direction.fromString(sortType), sortField);
            pageable = PageRequest.of(page, size, sort);
        }
        // Page<Bill> bills = billRepository.getAll(code, customerName, numberPhone, status, pageable);
        Page<Bill> bills = billRepository.searchBills(keyword, status, pageable);
        for (Bill bill : bills) {
            BillResult result = new BillResult();
            result.setId(bill.getId());
            result.setCode(bill.getCode());
            result.setCustomerName(bill.getRecipientName());
            result.setNumberPhone(bill.getRecipientPhoneNumber());
            result.setTotal(bill.getTotalAmount());
            result.setStatus(CommonUtil.getStatusVn(bill.getStatus()));
            result.setCreatedBy(bill.getCreatedBy());
            result.setCreatedDate(CommonUtil.date2Str(bill.getCreatedDate()));
            result.setPrice(bill.getPrice());
            result.setNote(bill.getNote());
            result.setModifiedBy(bill.getModifiedBy());
            result.setModifiedDate(CommonUtil.date2Str(bill.getModifiedDate()));
            result.setReceiveDate(CommonUtil.date2Str(bill.getEstimatedDeliveryDate()));
            results.add(result);
        }
        PaginationInfo paginationInfo = CommonUtil.getPaginationInfo(bills.getNumber(), bills.getSize(), Math.toIntExact(bills.getTotalElements()));
        response.setPagination(paginationInfo);
        response.setBills(results);
        return response;
    }

    @Override
    public DetailOrderResponse detail(String code) throws BusinessException {
        log.info("detail: {}", code);
        DetailOrderResponse response = new DetailOrderResponse();
        Optional<Bill> optional = billRepository.findByCode(code);
        if (optional.isEmpty()) {
            throw new BusinessException(ErrorCode.BILL_NOT_FOUND);
        }
        Bill bill = optional.get();
        response.setId(bill.getId());
        response.setOrderCode(bill.getCode());
        response.setNumberPhone(bill.getRecipientPhoneNumber());
        response.setFullName(bill.getRecipientName());
        response.setUsername(bill.getCreatedBy());
        response.setFullAddress(bill.getReceiverAddress());
        response.setStatus(CommonUtil.getStatusVn(bill.getStatus()));
        response.setStatusCode(bill.getStatus());
        response.setPrice(bill.getPrice());
        response.setFee(bill.getShippingMoney());
        response.setSumPrice(bill.getPrice().add(bill.getShippingMoney()));
        response.setPaymentMethod(CommonUtil.getPaymentMethod(bill.getPaymentMethod().getNameMethod()));
        response.setReceiveDate(CommonUtil.date2Str(bill.getEstimatedDeliveryDate()));
        List<SubOrderResult> subOrderResults = new ArrayList<>();
        List<BillDetail> detailBills = detailBillRepository.findByBillId(bill.getId());
        log.info("Processing BillDetail for Bill ID: {}", bill.getId());
        System.out.println("---------------------Omygot-----------------------" + detailBills);
        for (BillDetail detailBill : detailBills) {
            SubOrderResult subOrderResult = new SubOrderResult();
            subOrderResult.setProductName(detailBill.getProductDetail().getProduct().getProductName());
            subOrderResult.setQuantity(detailBill.getQuantity());
            subOrderResult.setPrice(detailBill.getPrice());
            //subOrderResult.setProductImage(detailBill.getProductDetail().getProduct().);
            subOrderResult.setProductCode(detailBill.getProductDetail().getProduct().getCode());
            subOrderResult.setSize(detailBill.getProductDetail().getSize().getName());
            subOrderResults.add(subOrderResult);
        }
        response.setProducts(subOrderResults);
        response.setGenerals(getOrderGeneralResults(bill.getId()));
        response.setDetails(getOrderDetailResults(bill.getId()));
        return response;
    }

    @Transactional
    @Override
    public boolean init(ApproveBillRequest request, String username) throws BusinessException {
        System.out.println("init:---------------------{}" + CommonUtil.beanToString(request));
        Optional<Bill> optional = billRepository.findById(request.getId());
        if (optional.isEmpty()) {
            throw new BusinessException(ErrorCode.BILL_NOT_FOUND);
        }
        Bill bill = optional.get();
        ShippingHistory history = this.shippingHistoryRepository.findFirstByBillIdAndParentIdIsNullAndStatusOrderByModifiedDateAsc(bill.getId(), OrderEnum.PENDING.getValue());
        if (history == null) {
            throw new BusinessException(ErrorCode.SHIPPING_HISTORY_NOT_FOUND); // Thay đổi thành mã lỗi phù hợp
        }
        List<ShippingHistory> histories = new ArrayList<>();
        switch (request.getStatus()) {
            case Constant.STATUS_PAYMENT.WAITING:
                if (!bill.getStatus().equals(Constant.STATUS_PAYMENT.PENDING)) {
                    throw new BusinessException(ErrorCode.BILL_INVALID_STATUS);
                }
                bill.setStatus(Constant.STATUS_PAYMENT.WAITING);
                histories = FakeData.getChildPAYMENTTC(username, bill.getId(), history.getId());
                break;
            case Constant.STATUS_PAYMENT.TRANSPORTING:
                if (!bill.getStatus().equals(Constant.STATUS_PAYMENT.WAITING)) {
                    throw new BusinessException(ErrorCode.BILL_INVALID_STATUS);
                }
                histories = FakeData.getChildSHIPPING(username, bill.getId(), history.getId());
                bill.setStatus(Constant.STATUS_PAYMENT.TRANSPORTING);
                break;
            case Constant.STATUS_PAYMENT.FINISH:
                if (!bill.getStatus().equals(Constant.STATUS_PAYMENT.TRANSPORTING)) {
                    throw new BusinessException(ErrorCode.BILL_INVALID_STATUS);
                }
                histories = FakeData.getChildRECEIVED(username, bill.getId(), history.getId());
                bill.setStatus(Constant.STATUS_PAYMENT.FINISH);
                this.shippingHistoryRepository.updateStatusByBillId(bill.getId(), OrderEnum.DONE.getValue());
                break;
            case Constant.STATUS_PAYMENT.REJECT:
                if (!bill.getStatus().equals(Constant.STATUS_PAYMENT.WAITING)) {
                    throw new BusinessException(ErrorCode.BILL_INVALID_STATUS);
                }
            case Constant.STATUS_PAYMENT.ROLLBACK:
                if (bill.getStatus().equals(Constant.STATUS_PAYMENT.WAITING)) {
                    // Delete all shipping history records with LEVEL >= 2 (for example)
                    this.rollbackShippingHistory(bill.getId(), 2);
                    // this.shippingHistoryRepository.deleteByBillIdAndLevelGreaterThanEqual(bill.getId(), 2);
                    bill.setStatus(Constant.STATUS_PAYMENT.PENDING);
                }
                break;
            default:
                bill.setStatus(Constant.STATUS_PAYMENT.CANCEL);
        }
        bill.setModifiedBy(username);
        if (bill.equals(Constant.STATUS_PAYMENT.FINISH)) {
            bill.setPaymentStatus(Constant.PAYMENT_STATUS.PAID);
        }
        if (bill.equals(Constant.STATUS_PAYMENT.REJECT) || bill.equals(Constant.STATUS_PAYMENT.CANCEL)) {
            rollbackProduct(bill.getId());
        }
        history.setStatus(OrderEnum.DONE.getValue());
        history.setModifiedBy(username);
        history.setModifiedDate(LocalDateTime.now());
        this.shippingHistoryRepository.save(history);
        this.shippingHistoryRepository.saveAll(histories);
        this.billRepository.save(bill);
        return true;
    }


    public void rollbackShippingHistory(Integer billId, int level) {
        int deletedRows = shippingHistoryRepository.deleteByBillIdAndLevelGreaterThanEqual(billId, level);
        System.out.println("Number of deleted rows: " + deletedRows);
        //shippingHistoryRepository.deleteByBillIdAndLevelGreaterThanEqual(billId, level);
    }


    @Override
    public BillsResponse getBills(String status, String billCode, int page, int size, String sortField, String sortType, String username) {
        log.info("getBills: {}", CommonUtil.beanToString(status));
        BillsResponse response = new BillsResponse();
        List<BillResult> results = new ArrayList<>();
        if (CommonUtil.isNullOrEmpty(billCode)) {
            billCode = "";
        }
        Pageable pageable = null;
        if (CommonUtil.isNullOrEmpty(sortField)) {
            pageable = PageRequest.of(page, size);
        } else {
            Sort sort = Sort.by(Sort.Direction.fromString(sortType), sortField);
            pageable = PageRequest.of(page, size, sort);
        }
        Page<Bill> bills = billRepository.getAll(billCode, username, status, pageable);
        for (Bill bill : bills) {
            BillResult result = new BillResult();
            result.setId(bill.getId());
            result.setCode(bill.getCode());
            result.setCustomerName(bill.getRecipientName());
            result.setNumberPhone(bill.getRecipientPhoneNumber());
            result.setTotal(bill.getTotalAmount());
            result.setStatus(CommonUtil.getStatusVn(bill.getStatus()));
            result.setCreatedBy(bill.getCreatedBy());
            result.setCreatedDate(CommonUtil.date2Str(bill.getCreatedDate()));
            result.setPrice(bill.getPrice());
            result.setNote(bill.getNote());
            result.setModifiedBy(bill.getModifiedBy());
            result.setModifiedDate(CommonUtil.date2Str(bill.getModifiedDate()));
            result.setReceiveDate(CommonUtil.date2Str(bill.getEstimatedDeliveryDate()));
            results.add(result);
        }
        PaginationInfo paginationInfo = CommonUtil.getPaginationInfo(bills.getNumber(), bills.getSize(), Math.toIntExact(bills.getTotalElements()));
        response.setPagination(paginationInfo);
        response.setBills(results);
        return response;
    }

    private List<OrderGeneralResult> getOrderGeneralResults(Integer id) {
        List<ShippingHistory> histories = this.shippingHistoryRepository.findByBillIdAndParentIdIsNullOrderByModifiedDateAsc(id);
        List<OrderGeneralResult> orderGeneralResults = new ArrayList<>();
        int i = 0;
        for (ShippingHistory history : histories) {
            OrderGeneralResult orderGeneralResult = new OrderGeneralResult();
            orderGeneralResult.setStatus(history.getStatus().equals(OrderEnum.DONE.getValue()) ? Constant.STATUS_HISTORY.COMPLETED : Constant.STATUS_HISTORY.WAITING);
            switch (i) {
                case 0:
                    orderGeneralResult.setIcon(Constant.ICON.ORDER);
                    break;
                case 1:
                    orderGeneralResult.setIcon(Constant.ICON.PAYMENT);
                    break;
                case 2:
                    orderGeneralResult.setIcon(Constant.ICON.TRANSPORTING);
                    break;
                case 3:
                    orderGeneralResult.setIcon(Constant.ICON.SUCCESS);
                    break;
                case 4:
                    orderGeneralResult.setIcon(Constant.ICON.DONE);
                    break;
                default:
                    orderGeneralResult.setIcon(Constant.ICON.PENDING);
            }
            orderGeneralResult.setStepCode(history.getId());
            orderGeneralResult.setStepName(history.getTitle());
            orderGeneralResult.setTimeStep(CommonUtil.date2Str(history.getModifiedDate()));
            orderGeneralResults.add(orderGeneralResult);
            i++;
        }
        return orderGeneralResults;
    }

    private List<OrderDetailResult> getOrderDetailResults(Integer id) {
        List<ShippingHistory> histories = this.shippingHistoryRepository.findByBillIdAndStatusOrderByModifiedDateDesc(id, OrderEnum.DONE.getValue());
        List<OrderDetailResult> orderDetailResults = new ArrayList<>();
        int i = 0;
        for (ShippingHistory history : histories) {
            OrderDetailResult orderDetailResult = new OrderDetailResult();
            if (history.getParentId() == null) {
                switch (i) {
                    case 0:
                        orderDetailResult.setIcon(Constant.ICON.ORDER);
                        break;
                    case 1:
                        orderDetailResult.setIcon(Constant.ICON.PAYMENT);
                        break;
                    case 2:
                        orderDetailResult.setIcon(Constant.ICON.TRANSPORTING);
                        break;
                    case 3:
                        orderDetailResult.setIcon(Constant.ICON.SUCCESS);
                        break;
                    case 4:
                        orderDetailResult.setIcon(Constant.ICON.DONE);
                        break;
                    default:
                        orderDetailResult.setIcon(Constant.ICON.PENDING);
                }
                i++;
            } else {
                orderDetailResult.setIcon(Constant.ICON.TASK);
            }
            orderDetailResult.setStepCode(history.getId() + "");
            orderDetailResult.setStepName(history.getTitle());
            orderDetailResult.setStepDate(CommonUtil.date2Str(history.getModifiedDate()));
            orderDetailResult.setDescription(history.getContent());
            orderDetailResults.add(orderDetailResult);
        }
        return orderDetailResults;
    }

    private void rollbackProduct(int billId) {
        List<BillDetail> detailBills = detailBillRepository.findByBillId(billId);
        List<Product> products = new ArrayList<>();
        List<ProductDetail> detailProducts = new ArrayList<>();
        for (BillDetail detailBill : detailBills) {
//            Product product = productRepository.getById(detailBill.getProductDetail().getProduct().getId());
//            product.setTotal(product.getTotal() - detailBill.getQuantity());
//            products.add(product);
//            ProductDetail detailProduct = detailProductRepository.getById(detailBill.getDetailProductId());
//            detailProduct.setTotal(detailProduct.getTotal() - detailBill.getQuantity());
//            detailProducts.add(detailProduct);

            ProductDetail detailProduct = detailProductRepository.getById(detailBill.getProductDetail().getId());
            detailProduct.setQuantity(detailProduct.getQuantity() - detailBill.getQuantity());
            detailProducts.add(detailProduct);
        }
        this.detailProductRepository.saveAll(detailProducts);
        productRepository.saveAll(products);
    }
}
