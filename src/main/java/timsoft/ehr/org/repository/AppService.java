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
    AccountsActivitiesRepo accountsActivitiesRepo;

    @Autowired
    AccountsRepo accountsRepo;

    @Autowired
    BankdepositRepo bankdepositRepo;

    @Autowired
    CatlogRepo catlogRepo;

    @Autowired
    CustomersRepo customersRepo;

    @Autowired
    DeleteLogsRepo deleteLogsRepo;

    @Autowired
    ExpencesRepo expencesRepo;

    @Autowired
    LibraryRepo libraryRepo;

    @Autowired
    ProductSupplyRepo productSupplyRepo;

    @Autowired
    PurchaseDebtLogsRepo purchaseDebtLogsRepo;

    @Autowired
    PurchaseDebtRepo purchaseDebtRepo;

    @Autowired
    ReceiptLogsRepo receiptLogsRepo;

    @Autowired
    RolesRepo rolesRepo;

    @Autowired
    SupplierRepo supplierRepo;

    public AccountsActivitiesRepo getAccountsActivitiesRepo() {
        return accountsActivitiesRepo;
    }

    public void setAccountsActivitiesRepo(AccountsActivitiesRepo accountsActivitiesRepo) {
        this.accountsActivitiesRepo = accountsActivitiesRepo;
    }

    public AccountsRepo getAccountsRepo() {
        return accountsRepo;
    }

    public void setAccountsRepo(AccountsRepo accountsRepo) {
        this.accountsRepo = accountsRepo;
    }

    public BankdepositRepo getBankdepositRepo() {
        return bankdepositRepo;
    }

    public void setBankdepositRepo(BankdepositRepo bankdepositRepo) {
        this.bankdepositRepo = bankdepositRepo;
    }

    public CatlogRepo getCatlogRepo() {
        return catlogRepo;
    }

    public void setCatlogRepo(CatlogRepo catlogRepo) {
        this.catlogRepo = catlogRepo;
    }

    public CustomersRepo getCustomersRepo() {
        return customersRepo;
    }

    public void setCustomersRepo(CustomersRepo customersRepo) {
        this.customersRepo = customersRepo;
    }

    public DeleteLogsRepo getDeleteLogsRepo() {
        return deleteLogsRepo;
    }

    public void setDeleteLogsRepo(DeleteLogsRepo deleteLogsRepo) {
        this.deleteLogsRepo = deleteLogsRepo;
    }

    public ExpencesRepo getExpencesRepo() {
        return expencesRepo;
    }

    public void setExpencesRepo(ExpencesRepo expencesRepo) {
        this.expencesRepo = expencesRepo;
    }

    public LibraryRepo getLibraryRepo() {
        return libraryRepo;
    }

    public void setLibraryRepo(LibraryRepo libraryRepo) {
        this.libraryRepo = libraryRepo;
    }

    public ProductSupplyRepo getProductSupplyRepo() {
        return productSupplyRepo;
    }

    public void setProductSupplyRepo(ProductSupplyRepo productSupplyRepo) {
        this.productSupplyRepo = productSupplyRepo;
    }

    public PurchaseDebtLogsRepo getPurchaseDebtLogsRepo() {
        return purchaseDebtLogsRepo;
    }

    public void setPurchaseDebtLogsRepo(PurchaseDebtLogsRepo purchaseDebtLogsRepo) {
        this.purchaseDebtLogsRepo = purchaseDebtLogsRepo;
    }

    public PurchaseDebtRepo getPurchaseDebtRepo() {
        return purchaseDebtRepo;
    }

    public void setPurchaseDebtRepo(PurchaseDebtRepo purchaseDebtRepo) {
        this.purchaseDebtRepo = purchaseDebtRepo;
    }

    public ReceiptLogsRepo getReceiptLogsRepo() {
        return receiptLogsRepo;
    }

    public void setReceiptLogsRepo(ReceiptLogsRepo receiptLogsRepo) {
        this.receiptLogsRepo = receiptLogsRepo;
    }

    public RolesRepo getRolesRepo() {
        return rolesRepo;
    }

    public void setRolesRepo(RolesRepo rolesRepo) {
        this.rolesRepo = rolesRepo;
    }

    public SupplierRepo getSupplierRepo() {
        return supplierRepo;
    }

    public void setSupplierRepo(SupplierRepo supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    public Object getUserRepo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
