/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timsoft.ehr.org.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olalekan
 */
@Service
public class AppService {

    @Autowired
    BankdepositRepo bankdepositRepo;
    @Autowired
    SupplierRepo supplierRepo;
    @Autowired
    ExcategoryRepo excategoryRepo;
    @Autowired
    StockRepo stockRepo;
    @Autowired
    ShiftingRepo shiftingRepo;
    @Autowired
    ReservoirlogRepo reservoirlogRepo;
    @Autowired
    ReservoirRepo reservoirRepo;
    @Autowired
    PumpRepo pumpRepo;
    @Autowired
    ExpenditureRepo expenditureRepo;
    @Autowired
    ApplogRepo applogRepo;
    @Autowired
    StaffRepo staffRepo;
    @Autowired
    TransactionRepo transactionRepo;
    @Autowired
    UserRepo UserRepo;

    public SupplierRepo getSupplierRepo() {
        return supplierRepo;
    }

    public ExcategoryRepo getExcategoryRepo() {
        return excategoryRepo;
    }

    public StockRepo getStockRepo() {
        return stockRepo;
    }

    public ShiftingRepo getShiftingRepo() {
        return shiftingRepo;
    }

    public ReservoirlogRepo getReservoirlogRepo() {
        return reservoirlogRepo;
    }

    public ReservoirRepo getReservoirRepo() {
        return reservoirRepo;
    }

    public PumpRepo getPumpRepo() {
        return pumpRepo;
    }

    public ExpenditureRepo getExpenditureRepo() {
        return expenditureRepo;
    }

    public ApplogRepo getApplogRepo() {
        return applogRepo;
    }

    public StaffRepo getStaffRepo() {
        return staffRepo;
    }

    public TransactionRepo getTransactionRepo() {
        return transactionRepo;
    }

    public UserRepo getUserRepo() {
        return UserRepo;
    }

    public BankdepositRepo getBankdepositRepo() {
        return bankdepositRepo;
    }

    public void setBankdepositRepo(BankdepositRepo bankdepositRepo) {
        this.bankdepositRepo = bankdepositRepo;
    }

}
