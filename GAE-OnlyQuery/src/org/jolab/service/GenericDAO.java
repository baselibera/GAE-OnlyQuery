/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jolab.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author jolab
 */

public interface GenericDAO< T > {

    long countAll(Map params);

    T create(T t);

    void delete(Object id);

    T find(Object id);

    T update(T t);   

}
