/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.project.facade;

import cz.muni.fi.pa165.project.dto.DeliveryDTO;
import java.util.List;

/**
 *
 * @author Jakub Gavlas
 */
public interface  DeliveryFacade {    
    public void create(DeliveryDTO d);
    public void update(DeliveryDTO d);
    public void delete(DeliveryDTO d);
    public DeliveryDTO findById(Long id);
    public List<DeliveryDTO> findAll();
    
}
