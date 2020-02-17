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
SupplierRepo supplierRepo;
@Autowired
ExcategoryRepo excategoryRepo;
}
