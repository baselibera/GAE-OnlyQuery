/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jolab.report.bridge;

import org.jolab.report.Interrogazione;

/**
 *
 * @author jolab
 */
public interface WebFacade {
    public Interrogazione getGenericResults(String statement, int offset, int rowsPerPage);
}
