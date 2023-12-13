/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.troisIl.evaluation.Exceptions;

/**
 *
 * @author emnat
 */
public class ExceptionNoConnectionDB extends RuntimeException{
    public ExceptionNoConnectionDB() {
        super();
    }

    public ExceptionNoConnectionDB(String message) {
        super(message);
    }
}
